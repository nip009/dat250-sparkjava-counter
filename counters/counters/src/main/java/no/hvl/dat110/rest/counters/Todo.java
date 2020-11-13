package no.hvl.dat110.rest.counters;

import com.google.gson.Gson;

public class Todo {
	
	private int id;
	
	private String summary, description;

	public Todo (int id, String summary, String description) {
		this.id = id;
		this.summary = summary;
		this.description = description;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	String toJson () {
    	
    	Gson gson = new Gson();
    	    
    	String jsonInString = gson.toJson(this);
    	
    	return jsonInString;
    }
}