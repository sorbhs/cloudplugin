package com.mindtree.digital.task;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

public class ParameterEntity {
	
	String paramKeys[];
	String paramValues[];
	JSONObject toreturn;
	public JSONObject getToreturn() {
		return toreturn;
	}
	public void setToreturn(JSONObject toreturn) {
		this.toreturn = toreturn;
	}
	public String[] getParamKeys() {
		return paramKeys;
	}
	public void setParamKeys(String[] paramKeys) {
		this.paramKeys = paramKeys;
	}
	public String[] getParamValues() {
		return paramValues;
	}
	public void setParamValues(String[] paramValues) {
		this.paramValues = paramValues;
	}
	
	

}
