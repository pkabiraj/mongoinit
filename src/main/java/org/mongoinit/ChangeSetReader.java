package org.mongoinit;

import java.io.IOException;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.mongoinit.model.ChangeSet;
import org.mongoinit.model.ChangeSetList;
import org.mongoinit.model.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

/**
 * The Class ChangeSetReader to read change sets from the file mentioned in
 * order to get the scripts to run.
 *
 * @author pkabiraj
 */
public class ChangeSetReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChangeSetReader.class);
	private static final String LOG = "mongoChangeLog";
	private static final String LOG_SET = LOG + "/changeSet";
	private static final String LOG_SET_SCRIPT = LOG_SET + "/script";
	private static final String BODY = "body";
	private static final String ADD = "add";

	/**
	 * Gets the change sets.
	 * 
	 * @param file the file
	 * @return the change sets
	 */
	public List<ChangeSet> getChangeSets(Resource file) {
		try {
			// Create digester which will do the parsing
			Digester digester = new Digester();

			// Set validation to false. Not required.
			digester.setValidating(false);

			// Set model to appropriate elements and attributes
			digester.addObjectCreate(LOG, ChangeSetList.class);
			digester.addObjectCreate(LOG_SET, ChangeSet.class);
			digester.addSetProperties(LOG_SET);
			digester.addSetNext(LOG_SET, ADD);

			digester.addObjectCreate(LOG_SET_SCRIPT, Script.class);
			digester.addBeanPropertySetter(LOG_SET_SCRIPT, BODY);
			digester.addSetNext(LOG_SET_SCRIPT, ADD);

			// Parse the change set file
			ChangeSetList changeFileSet = (ChangeSetList) digester.parse(file.getInputStream());

			// Set file name to the change set
			List<ChangeSet> changeSetList = changeFileSet.getList();
			for (ChangeSet changeSet : changeSetList) {
				changeSet.setFileName(file.getFilename());
			}
			// Return the list of change sets
			return changeSetList;

		} catch (IOException e) {
			LOGGER.error("mongoinit.parse.error", e);
		} catch (SAXException e) {
			LOGGER.error("mongoinit.parse.error", e);
		}

		return null;
	}
}