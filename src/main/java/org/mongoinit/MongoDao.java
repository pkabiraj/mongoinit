package org.mongoinit;

import org.mongoinit.model.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;

/**
 * The Class MongoDao to do all the database operations on mongo db.
 *
 * @author pkabiraj
 */
public class MongoDao {

	private static final String COLLECTION = "mongoinit";
	private static final String CHANGE_ID = "changeId";
	private static final String FILE = "file";

	private final MongoTemplate mongoTemplate;

	/**
	 * Instantiates a new mongo dao.
	 * 
	 * @param mongoTemplate the mongo template
	 */
	public MongoDao(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;

		BasicDBObject keys = new BasicDBObject();
		keys.append(CHANGE_ID, 1);
		keys.append(FILE, 1);
		this.mongoTemplate.getCollection(COLLECTION).ensureIndex(keys);
	}

	/**
	 * Run script.
	 * 
	 * @param code the code
	 */
	public void runScript(String code) {
		mongoTemplate.executeCommand(BasicDBObjectBuilder.start().add("$eval", code).get());
	}

	/**
	 * Checks whether the script was already executed or not.
	 * 
	 * @param changeSet the change set
	 * @return true, if successful
	 */
	public boolean wasExecuted(ChangeSet changeSet) {
		Criteria critera = Criteria.where(CHANGE_ID).is(changeSet.getChangeId()).and(FILE)
				.is(changeSet.getFileName());
		Query query = Query.query(critera);
		return mongoTemplate.count(query, COLLECTION) > 0;
	}

	/**
	 * Store pre-processing data to check if scripts have already run or not.
	 * 
	 * @param changeSet the change set
	 */
	public void storeInfo(ChangeSet changeSet) {
		BasicDBObject object = new BasicDBObject();
		object.append(FILE, changeSet.getFileName());
		object.append(CHANGE_ID, changeSet.getChangeId());

		mongoTemplate.insert(object, COLLECTION);
	}
}