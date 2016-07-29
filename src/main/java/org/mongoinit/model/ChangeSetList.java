
package org.mongoinit.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ChangeSetList to contain all the change sets that should be
 * running.
 *
 * @author pkabiraj
 */
public class ChangeSetList {

	private List<ChangeSet> list = null;

	/**
	 * Gets the change set list.
	 * 
	 * @return the list
	 */
	public List<ChangeSet> getList() {
		return list;
	}

	/**
	 * Sets the change set list.
	 * 
	 * @param list the new list
	 */
	public void setList(List<ChangeSet> list) {
		this.list = list;
	}

	/**
	 * Adds the change set to the list.
	 * 
	 * @param changeSet the change set
	 */
	public void add(ChangeSet changeSet) {
		if (list == null) {
			list = new ArrayList<ChangeSet>();
		}
		this.list.add(changeSet);
	}
}