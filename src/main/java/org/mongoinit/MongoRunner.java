package org.mongoinit;

import java.util.List;

import org.mongoinit.model.ChangeSet;
import org.mongoinit.model.Script;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * <p>
 * The Class MongoRunner is the base class which takes or running the mongo
 * scripts after the data base is created only first time.
 * </p>
 * <p>
 * This class would be helpful in scenarios where some scripts needs to be run
 * only once in the mongo db like creating index.
 * </p>
 *
 * @author pkabiraj
 */
public class MongoRunner implements InitializingBean {

	private MongoTemplate mongoTemplate;
	private Resource file;

	/**
	 * Sets the mongo template.
	 * 
	 * @param mongoTemplate the mongoTemplate to set
	 */
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * Sets the file path which contains the change sets and the scripts to run.
	 * 
	 * @param file the file to set
	 */
	public void setFile(Resource file) {
		this.file = file;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() {
		// Get change sets
		List<ChangeSet> changeSets = new ChangeSetReader().getChangeSets(file);
		// Run scripts if the first time
		try {
			MongoDao mongoDao = new MongoDao(mongoTemplate);
			for (ChangeSet changeSet : changeSets) {
				// Check if change set should run always or not and the scripts
				// have
				// already been executed or not
				if (changeSet.isRunAlways() || !mongoDao.wasExecuted(changeSet)) {
					// Run scripts
					for (Script command : changeSet.getCommands()) {
						command.run(mongoDao);
					}
					// Save pre-processing data to check later if scripts have
					// already run
					// or not
					mongoDao.storeInfo(changeSet);
				}
			}
		} catch (Exception e) {
			// If mongo is not running or there are some configuration issue,
			// Ignore the processing step and do nothing.
		}
	}
}