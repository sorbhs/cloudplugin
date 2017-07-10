package com.mindtree.digital.task;

import org.codehaus.jettison.json.JSONArray;
import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.AbstractTaskConfigurator;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.google.gson.JsonArray;
import com.opensymphony.xwork.TextProvider;
//import com.sun.jna.platform.win32.OaIdl.INVOKEKIND;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

//import freemarker.template.Configuration;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.runtime.directive.Foreach;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
//import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.ui.Model;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExampleTaskConfigurator extends AbstractTaskConfigurator {

	private TextProvider textProvider;

	public Map<String, String> generateTaskConfigMap(@NotNull final ActionParametersMap params,
			@Nullable final TaskDefinition previousTaskDefinition) {
		final Map<String, String> config = super.generateTaskConfigMap(params, previousTaskDefinition);

		config.put("myoperation", params.getString("myoperation"));
		config.put("cloudregion", params.getString("cloudregion"));
		config.put("awstemplate", params.getString("awstemplate"));
		config.put("azuretemplate", params.getString("azuretemplate"));
		config.put("cloudprovider", params.getString("cloudprovider"));

		config.put("clientid", params.getString("clientid"));
		config.put("clientsecret", params.getString("clientsecret"));
		config.put("tenantid", params.getString("tenantid"));
		config.put("subscriptionid", params.getString("subscriptionid"));
		config.put("resourcegroupname", params.getString("resourcegroupname"));
		config.put("deploymentname", params.getString("deploymentname"));
		config.put("persistphonename", params.getString("persistphonename"));
		config.put("ssphoneid1", params.getString("ssphoneid1"));
		config.put("ssphoneid2", params.getString("ssphoneid2"));
		config.put("jsonpersistname", params.getString("jsonpersistname"));
		config.put("savekeysid", params.getString("savekeysid"));
		config.put("saveparamvaluesid", params.getString("saveparamvaluesid"));
		config.put("saveparamkeysid", params.getString("saveparamkeysid"));
		

		config.put("cloudbagurl",params.getString("cloudbagurl"));
		config.put("cloudbagusername", params.getString("cloudbagusername"));
		config.put("cloudbagPassword", params.getString("cloudbagPassword"));
		System.out.println("cloudbagurl"+params.getString("cloudbagurl"));
		System.out.println("cloudbagusername"+ params.getString("cloudbagusername"));
		System.out.println("cloudbagPassword"+params.getString("cloudbagPassword"));
		
		/*String paramValuesString = params.getString("saveparamvaluesid");
		String paramvalues[];
		List<String> list = new ArrayList<String>(Arrays.asList(paramValuesString.split(",")));
		String aa="helo";
		Map<Integer, String> testparamvalues = new HashMap<Integer, String>();
		int kk = 1;
		for (String ss : list) {
			config.put(ss, params.getString(aa+kk));
			testparamvalues.put(kk, params.getString(aa+kk));
			kk++;
		}
		config.put("testparamvalues", "d");*/
		
		
		System.out.println("hel0ooooooooooooooooooooooooooooooo*************++++++++++++++++++++++");
		Map<Integer, String> testparamvalues = new HashMap<Integer, String>();
		String paramKeysString = params.getString("saveparamkeysid");
		System.out.println("this is paramkeysstring"+paramKeysString);
		String paramkeys[];
		List<String> keyslist = new ArrayList<String>(Arrays.asList(paramKeysString.split(",")));
		System.out.println("this is key list:"+keyslist);
		Map<Integer, String> testparamkeys = new HashMap<Integer, String>();
		int kk=1;
		for (String sskey : keyslist) {
			config.put(sskey, params.getString(sskey));
			//testparamvalues.put(kk, params.getString(sskey));
		//	kk++;
		}
		
	/*	String paramValuesString = params.getString("saveparamvaluesid");
		List<String> valueslist = new ArrayList<String>(Arrays.asList(paramValuesString.split(",")));
		for (String ssvalue : valueslist) {
			config.put(ssvalue, params.getString(ssvalue));
		//	kk++;
		}*/
		
		return config;
	}

	@SuppressWarnings("deprecation")
	public void validate(@NotNull final ActionParametersMap params, @NotNull final ErrorCollection errorCollection) {
		super.validate(params, errorCollection);
		final String clientidvalue = params.getString("clientid");
		final String clientsecretvalue = params.getString("clientsecret");
		final String tenantidvalue = params.getString("tenantid");
		final String subscriptionidvalue = params.getString("subscriptionid");
		final String resourcegroupnamevalue = params.getString("resourcegroupname");
		final String deploymentnamevalue = params.getString("deploymentname");

		if (StringUtils.isEmpty(clientidvalue)) {
			// errorCollection.addError("say",
			// textProvider.getText("helloworld.say.error"));
			errorCollection.addErrorMessage("Client ID cannot be empty.");
		}
		if (StringUtils.isEmpty(clientsecretvalue)) {
			errorCollection.addErrorMessage("Client Secret cannot be empty.");
		}
		if (StringUtils.isEmpty(tenantidvalue)) {
			errorCollection.addErrorMessage("Tenant ID cannot be empty.");
		}
		if (StringUtils.isEmpty(subscriptionidvalue)) {
			errorCollection.addErrorMessage("Subscription ID cannot be empty.");
		}
		if (StringUtils.isEmpty(resourcegroupnamevalue)) {
			errorCollection.addErrorMessage("Resource Group Name cannot be empty.");
		}
		if (StringUtils.isEmpty(deploymentnamevalue)) {
			errorCollection.addErrorMessage("Deployment Name cannot be empty.");
		}
	}

	@Override
	public void populateContextForCreate(@NotNull final Map<String, Object> context) {
		super.populateContextForCreate(context);

		Map<Integer, String> operation = new HashMap<Integer, String>();
		operation.put(0, "CREATE");
		operation.put(1, "DELETE");
		operation.put(2, "STOP");
		operation.put(3, "START");
		context.put("operation", operation);

		Map<Integer, String> region = new HashMap<Integer, String>();
		region.put(0, "us-gov-west-1");
		region.put(1, "us-east-1");
		region.put(2, "us-west-1");
		region.put(3, "us-west-2");
		region.put(4, "eu-west-1");
		region.put(5, "eu-central-1");
		region.put(6, "ap-southeast-1");
		region.put(7, "ap-southeast-2");
		region.put(8, "ap-northeast-1");
		region.put(9, "sa-east-1");
		region.put(10, "cn-north-1");
		context.put("region", region);

		Map<Integer, String> cloudProvider = new HashMap<Integer, String>();
		cloudProvider.put(0, "Azure");
		cloudProvider.put(1, "AWS");
		context.put("cloudProvider", cloudProvider);

		GetToken getToken = new GetToken();
		List<List<String>> listOfTemplates = null;
		try {
			listOfTemplates = getToken.getTemplatesList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<Integer, String> myawslist = new HashMap<Integer, String>();
		int i = 0;
		for (String ss : listOfTemplates.get(0)) {

			myawslist.put(i, ss);
			i++;
		}

		context.put("listOfAWSTemplates", myawslist);
		i = 0;
		Map<Integer, String> myazurelist = new HashMap<Integer, String>();
		for (String ss : listOfTemplates.get(1)) {

			myazurelist.put(i, ss);
			i++;
		}

		context.put("listOfAZURETemplates", myazurelist);
		// *********************************************
		Map<String, String> singletemplatedata = new HashMap<String, String>();
		/*
		 * try { singletemplatedata = getToken.getSingleTemplateData(); } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		context.put("singletemplatedata", singletemplatedata);
	}

	@Override
	public void populateContextForEdit(@NotNull final Map<String, Object> context,
			@NotNull final TaskDefinition taskDefinition) {
		super.populateContextForEdit(context, taskDefinition);

		context.put("awstemplate", taskDefinition.getConfiguration().get("awstemplate"));
		context.put("azuretemplate", taskDefinition.getConfiguration().get("azuretemplate"));
		context.put("cloudprovider", taskDefinition.getConfiguration().get("cloudprovider"));
		context.put("myoperation", taskDefinition.getConfiguration().get("myoperation"));
		context.put("cloudregion", taskDefinition.getConfiguration().get("cloudregion"));
		context.put("clientid", taskDefinition.getConfiguration().get("clientid"));
		context.put("clientsecret", taskDefinition.getConfiguration().get("clientsecret"));
		context.put("tenantid", taskDefinition.getConfiguration().get("tenantid"));
		context.put("subscriptionid", taskDefinition.getConfiguration().get("subscriptionid"));
		context.put("resourcegroupname", taskDefinition.getConfiguration().get("resourcegroupname"));
		context.put("deploymentname", taskDefinition.getConfiguration().get("deploymentname"));
		context.put("persistphonename", taskDefinition.getConfiguration().get("persistphonename"));
		context.put("ssphoneid1", taskDefinition.getConfiguration().get("ssphoneid1"));
		context.put("ssphoneid2", taskDefinition.getConfiguration().get("ssphoneid2"));
		context.put("jsonpersistname", taskDefinition.getConfiguration().get("jsonpersistname"));
		context.put("savekeysid", taskDefinition.getConfiguration().get("savekeysid"));
		context.put("saveparamvaluesid", taskDefinition.getConfiguration().get("saveparamvaluesid"));
		context.put("saveparamkeysid", taskDefinition.getConfiguration().get("saveparamkeysid"));
		
		context.put("cloudbagurl", taskDefinition.getConfiguration().get("cloudbagurl"));
		context.put("cloudbagusername", taskDefinition.getConfiguration().get("cloudbagusername"));
		context.put("cloudbagPassword", taskDefinition.getConfiguration().get("cloudbagPassword"));
		
		String paramValuesString = taskDefinition.getConfiguration().get("saveparamvaluesid");
		//String paramvalues[];
		List<String> valueslist = new ArrayList<String>(Arrays.asList(paramValuesString.split(",")));
		/*String ss3="helo";
		Map<Integer, String> testparamvalues = new HashMap<Integer, String>();
		int kk = 1;
		for (String ss : list) {
			context.put(ss, taskDefinition.getConfiguration().get(ss));
			testparamvalues.put(kk, ss);
			kk++;
		}*/
		//context.put("testparamvalues", testparamvalues);
		
		Map<String, String> keyvaluemap=new HashMap<String, String>();
		List<String> paramvalues=new ArrayList<String>();
		Map<Integer, String> testparamvalues = new HashMap<Integer, String>();
		String paramKeysString = taskDefinition.getConfiguration().get("saveparamkeysid");
		List<String> paramkeys = new ArrayList<String>();
		List<String> keyslist = new ArrayList<String>(Arrays.asList(paramKeysString.split(",")));
		Map<Integer, String> testparamkeys = new HashMap<Integer, String>();
		int kk=1;
		ParameterPOJO parameterPOJO=new ParameterPOJO();
		for (String sskey : keyslist) {
			context.put(sskey, taskDefinition.getConfiguration().get(sskey));
			paramvalues.add(taskDefinition.getConfiguration().get(sskey));
			testparamvalues.put(kk, taskDefinition.getConfiguration().get(sskey));
			paramkeys.add(sskey);
			//keyvaluemap.put(valueslist.get(kk-1),taskDefinition.getConfiguration().get(sskey));
			kk++;
		}
		
		/*for (String string : valueslist) {
			paramkeys.add(string);			
		}*/
		parameterPOJO.setParamValues(paramvalues);
		parameterPOJO.setParamKeys(paramkeys);
		context.put("parameterPOJO", parameterPOJO);
		
		
		context.put("testparamvalues", testparamvalues);
		context.put("paramvalues", paramvalues);
		//context.put("keyvaluemap", keyvaluemap);
		
		Map<Integer, String> operation = new HashMap<Integer, String>();
		operation.put(0, "CREATE");
		operation.put(1, "DELETE");
		operation.put(2, "STOP");
		operation.put(3, "START");
		context.put("operation", operation);

		Map<Integer, String> region = new HashMap<Integer, String>();
		region.put(0, "us-gov-west-1");
		region.put(1, "us-east-1");
		region.put(2, "us-west-1");
		region.put(3, "us-west-2");
		region.put(4, "eu-west-1");
		region.put(5, "eu-central-1");
		region.put(6, "ap-southeast-1");
		region.put(7, "ap-southeast-2");
		region.put(8, "ap-northeast-1");
		region.put(9, "sa-east-1");
		region.put(10, "cn-north-1");
		context.put("region", region);

		Map<Integer, String> cloudProvider = new HashMap<Integer, String>();
		cloudProvider.put(0, "Azure");
		cloudProvider.put(1, "AWS");
		context.put("cloudProvider", cloudProvider);

		GetToken getToken = new GetToken();
		List<String> dummyawstemplates = new ArrayList<String>();
		List<String> dummyazuretemplates = new ArrayList<String>();
		List<List<String>> listOfTemplates = new ArrayList<List<String>>();
		try {
			/*
			 * dummyawstemplates.add("aws template 1");
			 * dummyawstemplates.add("aws template 2");
			 * dummyawstemplates.add("aws template 3");
			 * dummyawstemplates.add("aws template 4");
			 * dummyawstemplates.add("aws template 5");
			 * dummyazuretemplates.add("azure template 1");
			 * dummyazuretemplates.add("azure template 2");
			 * dummyazuretemplates.add("azure template 3");
			 * dummyazuretemplates.add("azure template 4");
			 * dummyazuretemplates.add("azure template 5");
			 * listOfTemplates.add(dummyawstemplates);
			 * listOfTemplates.add(dummyazuretemplates); listOfTemplates.add(0,
			 * dummyazuretemplates); listOfTemplates.add(1, dummyawstemplates);
			 */
			listOfTemplates = getToken.getTemplatesList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<Integer, String> myawslist = new HashMap<Integer, String>();
		int i = 0;
		for (String ss : listOfTemplates.get(0)) {
			myawslist.put(i, ss);
			i++;
		}

		context.put("listOfAWSTemplates", myawslist);

		Map<Integer, String> myazurelist = new HashMap<Integer, String>();
		for (String ss : listOfTemplates.get(1)) {
			myazurelist.put(i, ss);
			i++;
		}

		context.put("listOfAZURETemplates", myazurelist);
		// *******************************************************

		Map<String, String> singletemplatedata = new HashMap<String, String>();

		/*
		 * try { singletemplatedata = getToken.getSingleTemplateData(); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */

		singletemplatedata.put("name", "sourabh");
		singletemplatedata.put("place", "udaipur");
		singletemplatedata.put("age", "25");
		singletemplatedata.put("visits", "");
		singletemplatedata.put("gem", "abc");
		context.put("singletemplatedata", singletemplatedata);

		singletemplatedata = new HashMap<String, String>();
		singletemplatedata.put("sita", "gita");
		singletemplatedata.put("ram", "shyam");
		singletemplatedata.put("mohan", "sohan");
		singletemplatedata.put("jira", "");
		singletemplatedata.put("kbc", "abc");

		List<TaskValues> tasklist = new ArrayList<TaskValues>();
		TaskValues mytaskValues;
		for (Map.Entry<String, String> entry : singletemplatedata.entrySet()) {
			mytaskValues = new TaskValues();
			mytaskValues.setParameterKey(entry.getKey());
			mytaskValues.setParameterValue(entry.getValue());
			tasklist.add(mytaskValues);
		}
		context.put("tasklist", tasklist);
		// ******************************************************
		List<String> artifactIdsList = null;
		Map<String, String> artifactIdAndName = null;
		List<Map<String, String>> allTemplateDatas = new ArrayList<Map<String, String>>();
		Map<String, Map<String, String>> complexmap = new HashMap<String, Map<String, String>>();
		Map<String, String> oneTemplateData = null;

		try {
			artifactIdsList = getToken.getArtifactIds();
			artifactIdAndName = getToken.getArtifactIdandNames();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// context.put("artifactIdsList", artifactIdsList);

		/*
		 * for (String id : artifactIdsList) { try { oneTemplateData =
		 * getToken.getSingleTemplateData(id); } catch (Exception e) {
		 * e.printStackTrace(); } allTemplateDatas.add(oneTemplateData); }
		 */

		/*
		 * for (Map.Entry<String, String> entry : artifactIdAndName.entrySet())
		 * { try { oneTemplateData =
		 * getToken.getSingleTemplateData(entry.getKey());
		 * complexmap.put(entry.getValue(), oneTemplateData); } catch (Exception
		 * e) { e.printStackTrace(); } }
		 */

		oneTemplateData = new HashMap<String, String>();
		oneTemplateData.put("hello1", "hello1 ki value");
		oneTemplateData.put("hello2", "hello2 ki value");
		oneTemplateData.put("hello3", "hello3 ki value");
		oneTemplateData.put("hello4", "hello4 ki value");
		allTemplateDatas.add(oneTemplateData);
		complexmap.put("Basic Devops_without asg", oneTemplateData);

		oneTemplateData = new HashMap<String, String>();
		oneTemplateData.put("bolo1", "bolo1 ki value");
		oneTemplateData.put("bolo2", "bolo2 ki value");
		oneTemplateData.put("bolo3", "bolo3 ki value");
		oneTemplateData.put("bolo4", "bolo4 ki value");
		allTemplateDatas.add(oneTemplateData);
		complexmap.put("Nexus_without_asg", oneTemplateData);

		oneTemplateData = new HashMap<String, String>();
		oneTemplateData.put("chalo1", "chalo1 ki value");
		oneTemplateData.put("chalo2", "chalo2 ki value");
		oneTemplateData.put("chalo3", "chalo3 ki value");
		oneTemplateData.put("chalo4", "chalo4 ki value");
		allTemplateDatas.add(oneTemplateData);
		complexmap.put("singleVMCreation", oneTemplateData);

		context.put("allTemplateDatas", allTemplateDatas);
		context.put("complexmap", complexmap);

		// **********************************************************
		List<AllTemplates> allTemplateslist = new ArrayList<AllTemplates>();
		AllTemplates allTemplates;
		allTemplates = new AllTemplates();

		oneTemplateData = new HashMap<String, String>();
		oneTemplateData.put("hello1", "hello1 ki value");
		oneTemplateData.put("hello2", "hello2 ki value");
		oneTemplateData.put("hello3", "hello3 ki value");
		oneTemplateData.put("hello4", "hello4 ki value");
		allTemplates.setTemplateid("1");
		allTemplates.setTemplateName("Basic Devops_without asg");
		allTemplates.setTaskvaluemap(oneTemplateData);
		allTemplateslist.add(allTemplates);

		allTemplates = new AllTemplates();
		oneTemplateData = new HashMap<String, String>();
		allTemplates.setTemplateid("2");
		allTemplates.setTemplateName("Nexus_without_asg");
		oneTemplateData.put("bolo1", "bolo1 ki value");
		oneTemplateData.put("bolo2", "bolo2 ki value");
		oneTemplateData.put("bolo3", "bolo3 ki value");
		oneTemplateData.put("bolo4", "bolo4 ki value");
		allTemplates.setTaskvaluemap(oneTemplateData);
		allTemplateslist.add(allTemplates);

		allTemplates = new AllTemplates();
		allTemplates.setTemplateid("3");
		allTemplates.setTemplateName("singleVMCreation");
		oneTemplateData = new HashMap<String, String>();
		oneTemplateData.put("chalo1", "chalo1 ki value");
		oneTemplateData.put("chalo2", "chalo2 ki value");
		oneTemplateData.put("chalo3", "chalo3 ki value");
		oneTemplateData.put("chalo4", "chalo4 ki value");
		allTemplates.setTaskvaluemap(oneTemplateData);
		allTemplateslist.add(allTemplates);
		context.put("allTemplateslist", allTemplateslist);

		JSONObject jsonObjs = new JSONObject();

		JSONArray templatesarray = new JSONArray();

		JSONObject oneparameterelement = new JSONObject();
		JSONObject twoparameterelement = new JSONObject();

		JSONObject arryelementone = new JSONObject();
		JSONObject arryelementtwo = new JSONObject();

		try {

			oneparameterelement.put("hello1", "hello1 ki value");
			oneparameterelement.put("hello2", "hello2 ki value");
			oneparameterelement.put("hello3", "hello3 ki value");
			oneparameterelement.put("hello4", "hello4 ki value");
			arryelementone.put("name", "Basic Devops_without asg");
			arryelementone.put("parameters", oneparameterelement);

			twoparameterelement.put("bolo1", "bolo1 ki value");
			twoparameterelement.put("bolo2", "bolo2 ki value");
			twoparameterelement.put("bolo3", "bolo3 ki value");
			twoparameterelement.put("bolo4", "bolo4 ki value");
			arryelementtwo.put("name", "Nexus_without_asg");
			arryelementtwo.put("parameters", twoparameterelement);

			templatesarray.put(arryelementone);
			templatesarray.put(arryelementtwo);
			jsonObjs.put("size", 2);
			jsonObjs.put("templates", templatesarray);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// **********************************************************
		/*
		 * JSONObject parameter=null; JSONObject templateNameandParameters=null;
		 * JSONArray alltemplatearray=new JSONArray(); int x=0; for
		 * (Map.Entry<String, String> entry : artifactIdAndName.entrySet()) {
		 * try { oneTemplateData =
		 * getToken.getSingleTemplateData(entry.getKey());
		 * //complexmap.put(entry.getValue(), oneTemplateData); parameter=new
		 * JSONObject();
		 * 
		 * for(Map.Entry<String, String> ee:oneTemplateData.entrySet()){
		 * parameter.put(ee.getKey(), ee.getValue()); }
		 * templateNameandParameters=new JSONObject();
		 * templateNameandParameters.put("name", entry.getValue());
		 * templateNameandParameters.put("parameters", parameter);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } x++;
		 * alltemplatearray.put(templateNameandParameters); } try {
		 * jsonObjs.put("size", x); jsonObjs.put("templates", alltemplatearray);
		 * } catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		context.put("jsonObjs", jsonObjs);

		// ***********************************************************
		List<AllTemplates> dummyAllTemplates = new ArrayList<AllTemplates>();
		AllTemplates onetemplete = null;
		Map<String, String> parameters = null;

		onetemplete = new AllTemplates();
		parameters = new HashMap<String, String>();
		parameters.put("hello1", "hello1 kivalue");
		parameters.put("hello2", "hello2 kivalue");
		parameters.put("hello3", "hello3 kivalue");
		parameters.put("hello4", "hello4 kivalue");
		onetemplete.setTemplateid("1");
		onetemplete.setTemplateName("aws template 1");
		onetemplete.setTaskvaluemap(parameters);
		dummyAllTemplates.add(onetemplete);

		onetemplete = new AllTemplates();
		parameters = new HashMap<String, String>();
		parameters.put("bolo1", "bolo1 ki value");
		parameters.put("bolo2", "bolo2 ki value");
		parameters.put("bolo3", "bolo3 ki value");
		parameters.put("bolo4", "bolo4 ki value");
		onetemplete.setTemplateid("2");
		onetemplete.setTemplateName("azure template 1");
		onetemplete.setTaskvaluemap(parameters);
		dummyAllTemplates.add(onetemplete);

		onetemplete = new AllTemplates();
		parameters = new HashMap<String, String>();
		parameters.put("chalo1", "chalo1 ki value");
		parameters.put("chalo2", "chalo2 ki value");
		parameters.put("chalo3", "chalo3 ki value");
		parameters.put("chalo4", "chalo4 ki value");
		onetemplete.setTemplateid("3");
		onetemplete.setTemplateName("aws template 2");
		onetemplete.setTaskvaluemap(parameters);
		dummyAllTemplates.add(onetemplete);

		context.put("dummyAllTemplates", dummyAllTemplates);

		List<String> phonelistd = new ArrayList<String>();
		phonelistd.add("micromax");
		
		context.put("phonelistd", phonelistd);

		Map<Integer, String> phonelist = new HashMap<Integer, String>();
		int k = 1;
		for (String ss : phonelistd) {
			phonelist.put(k, ss);
			k++;
		}
		context.put("phonelist", phonelist);

	}

	@Override
	public void populateContextForView(@NotNull final Map<String, Object> context,
			@NotNull final TaskDefinition taskDefinition) {
		super.populateContextForView(context, taskDefinition);
		context.put("myoperation", taskDefinition.getConfiguration().get("myoperation"));
		context.put("cloudregion", taskDefinition.getConfiguration().get("cloudregion"));
		context.put("awstemplate", taskDefinition.getConfiguration().get("awstemplate"));
		context.put("azuretemplate", taskDefinition.getConfiguration().get("azuretemplate"));
		context.put("cloudprovider", taskDefinition.getConfiguration().get("cloudprovider"));

		context.put("clientid", taskDefinition.getConfiguration().get("clientid"));
		context.put("clientsecret", taskDefinition.getConfiguration().get("clientsecret"));
		context.put("tenantid", taskDefinition.getConfiguration().get("tenantid"));
		context.put("subscriptionid", taskDefinition.getConfiguration().get("subscriptionid"));
		context.put("resourcegroupname", taskDefinition.getConfiguration().get("resourcegroupname"));
		context.put("deploymentname", taskDefinition.getConfiguration().get("deploymentname"));
		context.put("persistphonename", taskDefinition.getConfiguration().get("persistphonename"));
		context.put("ssphoneid1", taskDefinition.getConfiguration().get("ssphoneid1"));
		context.put("ssphoneid2", taskDefinition.getConfiguration().get("ssphoneid2"));
		context.put("jsonpersistname", taskDefinition.getConfiguration().get("jsonpersistname"));
		context.put("savekeysid", taskDefinition.getConfiguration().get("savekeysid"));
		context.put("saveparamvaluesid", taskDefinition.getConfiguration().get("saveparamvaluesid"));
		context.put("saveparamkeysid", taskDefinition.getConfiguration().get("saveparamkeysid"));

		/*String paramValuesString = taskDefinition.getConfiguration().get("saveparamvaluesid");
		String paramvalues[];
		List<String> list = new ArrayList<String>(Arrays.asList(paramValuesString.split(",")));

		Map<Integer, String> testparamvalues = new HashMap<Integer, String>();
		int kk = 1;
		for (String ss : list) {
			context.put(ss, taskDefinition.getConfiguration().get(ss));
			testparamvalues.put(kk, taskDefinition.getConfiguration().get(ss));
			testparamvalues.put(kk, ss);
			kk++;
		}
		context.put("testparamvalues", paramValuesString);*/

	}
}