package org.mongoinit.model;

import org.mongoinit.MongoDao;

/**
 * The Class Script to contain data about the scripts that needs to be executed.
 * 
 * @author pkabiraj
 */
public class Script {
    
    private String body;
    
    
    /**
     * Gets the body of the script to be executed.
     * 
     * @return the body
     */
    public String getBody() {
        return body;
    }
    
    /**
     * Sets the body of the script.
     * 
     * @param body the new body
     */
    public void setBody(String body) {
        this.body = body;
    }
    
    /**
     * Run the script.
     * 
     * @param dao the dao
     */
    public void run(MongoDao dao) {
        dao.runScript(body);
    }
}