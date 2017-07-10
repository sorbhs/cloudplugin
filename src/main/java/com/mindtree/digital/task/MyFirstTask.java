package com.mindtree.digital.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.atlassian.bamboo.build.logger.BuildLogger;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskResultBuilder;
import com.atlassian.bamboo.task.TaskType;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

public class MyFirstTask implements TaskType {
	public TaskResult execute(final TaskContext taskContext) throws TaskException {
		final BuildLogger buildLogger = taskContext.getBuildLogger();

		GetToken getToken = new GetToken();
		Map<String, String> config = taskContext.getConfigurationMap();
		String oauthToken;
		String action = config.get("myoperation");
		
		FetchGlobalCreds creds;
		buildLogger.addBuildLogEntry("ddddddddddddd haha:"+creds.fetchCreds());
	/*	JSONObject keyObject = new JSONObject();
		try {
			keyObject.put("keyName", "sorbhskey");
			keyObject.put("clientId", config.get("clientid"));
			keyObject.put("clientSecret", config.get("clientsecret"));
			keyObject.put("tenantId", config.get("tenantid"));
			keyObject.put("subscriptionId", config.get("subscriptionid"));
			keyObject.put("isDefault", true);
			keyObject.put("userId", "M1035913");
		} catch (JSONException e) {
			e.printStackTrace();
		}*/

		JSONObject params = new JSONObject();
		String paramKeysString = config.get("saveparamkeysid");
		List<String> keyslist = new ArrayList<String>(Arrays.asList(paramKeysString.split(",")));
		for (String string : keyslist) {
			buildLogger.addBuildLogEntry(config.get(string));
			try {
				params.put(string, config.get(string));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
/*
		try {
			params.put("adminUsername", "azureuser");
			params.put("newStorageAccountName", "sorbhsdemovmstorage2");
			params.put("dnsNameForPublicIP", "dnsdemo123vm");
			params.put("ubuntuOSVersion", "14.04.2-LTS");
			params.put("location", "Southeast Asia");
			params.put("adminPassword", "Welcome@123");

		} catch (JSONException e) {
			e.printStackTrace();
		}*/

		buildLogger.addBuildLogEntry("Resource gruop Name is  :" + config.get("resourcegroupname"));
		buildLogger.addBuildLogEntry("Region Name is  :" + config.get("cloudregion"));
		buildLogger.addBuildLogEntry("Template/Artifact name is  :" + config.get("azuretemplate"));
		buildLogger.addBuildLogEntry("Deployment Name is  :" + config.get("deploymentname"));
		buildLogger.addBuildLogEntry("-----------------------------------------");
		buildLogger.addBuildLogEntry("Fetching artifact ID ...");

		JSONObject executeObject = new JSONObject();
		try {
			String artifactid = getToken.getArtifactIDFromName(config.get("azuretemplate"));
			executeObject.put("artifactId", artifactid);
			buildLogger.addBuildLogEntry("Artifact ID is  :" + artifactid);
			executeObject.put("updatedTemplateParams", params);
			executeObject.put("resourceName", config.get("resourcegroupname"));
			executeObject.put("deploymentName", config.get("deploymentname"));
			executeObject.put("userId", "M1035913");
			executeObject.put("keyName", "sorbhskey");
			executeObject.put("region", config.get("cloudregion"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	/*	if (action.equals("CREATE"))
			try {
				buildLogger.addBuildLogEntry("Executing template...");
				buildLogger.addBuildLogEntry("in myfirst executeobject to stering:"+executeObject.toString());
				buildLogger.addBuildLogEntry(getToken.executeTemplate(executeObject));
			} catch (Exception e) {
				e.printStackTrace();
			}

		JSONObject startObject = new JSONObject();
		try {
			startObject.put("vmName", "MyUbuntuVM1");
			startObject.put("userId", "M1035913");
			startObject.put("cloud", "azure");
			startObject.put("resourceGroup", config.get("resourcegroupname"));
			startObject.put("keyName", "sorbhskey");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (action.equals("START"))
			try {
				buildLogger.addBuildLogEntry("Starting vm...");
				buildLogger.addBuildLogEntry(getToken.startTemplate(startObject));
			} catch (Exception e) {
				e.printStackTrace();
			}

		if (action.equals("STOP"))
			try {
				buildLogger.addBuildLogEntry("Stopping vm...");
				buildLogger.addBuildLogEntry(getToken.stopTemplate(startObject));
			} catch (Exception e) {
				e.printStackTrace();
			}

		if (action.equals("DELETE"))
			try {
				buildLogger.addBuildLogEntry("Deleting vm...");
				buildLogger.addBuildLogEntry(getToken.deleteVM(startObject));
			} catch (Exception e) {
				e.printStackTrace();
			}*/

		return TaskResultBuilder.create(taskContext).success().build();
	}
}