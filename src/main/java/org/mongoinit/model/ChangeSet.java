
package org.mongoinit.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ChangeSet contains data about the change set.
 *
 * @author pkabiraj
 */
public class ChangeSet {

	private String changeId;
	private String fileName;
	private boolean runAlways;

	private final List<Script> commands = new ArrayList<Script>();

	/**
	 * Gets the change id.
	 * 
	 * @return the change id
	 */
	public String getChangeId() {
		return changeId;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Sets the change id.
	 * 
	 * @param changeId the new change id
	 */
	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}

	/**
	 * Checks if is run always is set or not.
	 * 
	 * @return true, if is run always
	 */
	public boolean isRunAlways() {
		return runAlways;
	}

	/**
	 * Sets the run always.
	 * 
	 * @param runAlways the new run always
	 */
	public void setRunAlways(boolean runAlways) {
		this.runAlways = runAlways;
	}

	/**
	 * Adds the command to the commands list.
	 * 
	 * @param command the command
	 */
	public void add(Script command) {
		commands.add(command);
	}

	/**
	 * Gets the commands.
	 * 
	 * @return the commands
	 */
	public List<Script> getCommands() {
		return commands;
	}
}