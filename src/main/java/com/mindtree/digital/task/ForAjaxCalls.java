package com.mindtree.digital.task;


import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.AbstractTaskConfigurator;
import com.atlassian.bamboo.task.TaskDefinition;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ForAjaxCalls  extends AbstractTaskConfigurator{
	
	
	@Override
	public Map<String, String> generateTaskConfigMap(ActionParametersMap params,
			TaskDefinition previousTaskDefinition) {
		
		
		final Map<String, String> config = super.generateTaskConfigMap(params, previousTaskDefinition);
		
		return super.generateTaskConfigMap(params, previousTaskDefinition);
	}
	
	@Override
	public void populateContextForEdit(Map<String, Object> context, TaskDefinition taskDefinition) {
		
		super.populateContextForEdit(context, taskDefinition);
		context.put("redial", "redialvalue");
	}
	
	
	public void parameterValues(ParameterEntity entity){
		
		
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

	public JSONObject getToreturn() {
		return toreturn;
	}

	public void setToreturn(JSONObject toreturn) {
		this.toreturn = toreturn;
	}



	String paramKeys[];
	String paramValues[];
	JSONObject toreturn;
	
	
	
	
	
	  
}
