package com.mindtree.digital.task;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.AbstractTaskConfigurator;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.utils.error.ErrorCollection;

public class TestExampleTaskConfigurator extends AbstractTaskConfigurator{
	
	@Override
	public Map<String, String> generateTaskConfigMap(@Nullable final ActionParametersMap params,
			@Nullable TaskDefinition previousTaskDefinition) {
		final Map<String, String> config = super.generateTaskConfigMap(params, previousTaskDefinition);
		config.put("tenantid", params.getString("tenantid"));
		return config;
	}
	
	@Override
	public void populateContextForEdit(Map<String, Object> context, TaskDefinition taskDefinition) {
		super.populateContextForEdit(context, taskDefinition);
		context.put("tenantid",taskDefinition.getConfiguration().get("tenantid"));
		
		Map<Integer, String> myazurelist = new HashMap<Integer, String>();
		myazurelist.put(1, "pen");
		myazurelist.put(2, "pencil");
		myazurelist.put(3, "bottle");
		myazurelist.put(4, "mouse");
		context.put("listOfAZURETemplates", myazurelist);
		
		
		
	}
	
	
	@Override
	public void populateContextForCreate(Map<String, Object> context) {
		super.populateContextForCreate(context);
	}

	
	@Override
	public void populateContextForView(Map<String, Object> context, TaskDefinition taskDefinition) {
		super.populateContextForView(context, taskDefinition);
	}
	
}
