package com.mindtree.digital.task;

import com.microsoft.aad.adal4j.AuthenticationCallback;
import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jna.platform.win32.OaIdl.SYSKIND;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetToken {

	private static String authority = "https://login.windows.net/common";

	public static String getAccessToken(String username, String password, String clientId) throws Exception {
		AuthenticationContext context;
		AuthenticationCallback callback = null;
		AuthenticationResult result = null;
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(1);
			context = new AuthenticationContext(authority, false, service);

			Future<AuthenticationResult> future = context.acquireToken("https://graph.windows.net", clientId, username,
					password, callback);

			result = future.get();
		} catch (Exception ex) {
			throw new Exception("Not able to get Access Token", ex);
		} finally {
			if (null != service) {
				service.shutdown();
			}
		}

		if (result == null) {
			throw new Exception("Not able to get Access Token");
		}
		return result.getAccessToken();
	}

	public List<List<String>> getTemplatesList() throws Exception {

		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		Client client = Client.create();
		WebResource webResource = client.resource("https://cloudbag.mindtree.com/api/Artifacts?userId=m1035913");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		// queryParams.add("json", js); //set parameters for request

		String appKey = "Bearer " + tokenString; // appKey is unique number

		// Get response from RESTful Server get(ClientResponse.class);
		ClientResponse response = null;
		response = webResource.queryParams(queryParams).header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", appKey).get(ClientResponse.class);

		String jsonStr = response.getEntity(String.class);

		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray jsonArray = jsonObj.getJSONArray("filterResponse");
		int size = jsonArray.length();
		List<List<String>> ss = new ArrayList<List<String>>();
		List<String> aws = new ArrayList<String>();
		List<String> azure = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			String provider = jsonArray.getJSONObject(i).getString("cloudProvider");
			if (provider.equals("aws"))
				aws.add(jsonArray.getJSONObject(i).getString("title"));

			else if (provider.equals("azure"))
				azure.add(jsonArray.getJSONObject(i).getString("title"));
		}
		ss.add(aws);
		ss.add(azure);
		return ss;
	}

	public List<String> getArtifactIds() throws Exception {
		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		Client client = Client.create();
		WebResource webResource = client.resource("https://cloudbag.mindtree.com/api/Artifacts?userId=m1035913");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		// queryParams.add("json", js); //set parameters for request

		String appKey = "Bearer " + tokenString; // appKey is unique number

		// Get response from RESTful Server get(ClientResponse.class);
		ClientResponse response = null;
		response = webResource.queryParams(queryParams).header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", appKey).get(ClientResponse.class);

		String jsonStr = response.getEntity(String.class);

		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray jsonArray = jsonObj.getJSONArray("filterResponse");
		int size = jsonArray.length();
		List<String> artifactIdsList = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			String artifactid = jsonArray.getJSONObject(i).getString("artifactId");
			artifactIdsList.add(artifactid);
		}
		return artifactIdsList;
	}

	public Map<String, String> getArtifactIdandNames() throws Exception {
		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		Client client = Client.create();
		WebResource webResource = client.resource("https://cloudbag.mindtree.com/api/Artifacts?userId=m1035913");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		// queryParams.add("json", js); //set parameters for request

		String appKey = "Bearer " + tokenString; // appKey is unique number

		// Get response from RESTful Server get(ClientResponse.class);
		ClientResponse response = null;
		response = webResource.queryParams(queryParams).header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", appKey).get(ClientResponse.class);

		String jsonStr = response.getEntity(String.class);

		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray jsonArray = jsonObj.getJSONArray("filterResponse");
		int size = jsonArray.length();
		Map<String, String> artifactIdAndName = new HashMap<String, String>();
		for (int i = 0; i < size; i++) {
			String artifactid = jsonArray.getJSONObject(i).getString("artifactId");
			String artifactname = jsonArray.getJSONObject(i).getString("title");
			artifactIdAndName.put(artifactid, artifactname);

		}
		return artifactIdAndName;
	}

	public String getArtifactIDFromName(String artifactName) throws Exception {
		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		Client client = Client.create();
		WebResource webResource = client.resource("https://cloudbag.mindtree.com/api/Artifacts?userId=m1035913");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		// queryParams.add("json", js); //set parameters for request

		String appKey = "Bearer " + tokenString; // appKey is unique number

		// Get response from RESTful Server get(ClientResponse.class);
		ClientResponse response = null;
		response = webResource.queryParams(queryParams).header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", appKey).get(ClientResponse.class);

		String jsonStr = response.getEntity(String.class);
		String artifactidtoreturn = null;
		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray jsonArray = jsonObj.getJSONArray("filterResponse");
		int size = jsonArray.length();
		for (int i = 0; i < size; i++) {
			String artifactid = jsonArray.getJSONObject(i).getString("artifactId");
			String artifactname = jsonArray.getJSONObject(i).getString("title");
			if (artifactname.equals(artifactName)) {
				artifactidtoreturn = artifactid;
				break;
			}
		}
		return artifactidtoreturn;
	}

	public JSONObject getSingleTemplateJSONData(String id) throws Exception {
		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		System.out.println(tokenString);
		Client client = Client.create();
		WebResource webResource = client
				.resource("https://cloudbag.mindtree.com/api/ScriptParameter/getParameters/" + id + "/m1035913");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		// queryParams.add("json", js); //set parameters for request

		String appKey = "Bearer " + tokenString; // appKey is unique number

		// Get response from RESTful Server get(ClientResponse.class);
		ClientResponse response = null;
		response = webResource.queryParams(queryParams).header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", appKey).get(ClientResponse.class);

		Boolean flag = false;
		String value = null;
		String jsonStr = response.getEntity(String.class);
		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONObject templateobj = new JSONObject();
		JSONObject parameters = jsonObj.getJSONObject("parameters");
		Iterator keysToCopyIterator = parameters.keys();
		while (keysToCopyIterator.hasNext()) {
			String key = (String) keysToCopyIterator.next();
			JSONObject singleKey = parameters.getJSONObject(key);
			Iterator keysinsinglekey = singleKey.keys();
			flag = false;
			value = null;
			while (keysinsinglekey.hasNext()) {
				String defaul = (String) keysinsinglekey.next();
				if (defaul.equals("defaultValue"))
					flag = true;
			}
			if (flag)
				value = singleKey.getString("defaultValue");
			else
				value = "";
			templateobj.putOpt(key, value);
		}
		return templateobj;

	}

	public Map<String, String> getSingleTemplateData(String id) throws Exception {

		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		System.out.println(tokenString);
		Client client = Client.create();
		WebResource webResource = client
				.resource("https://cloudbag.mindtree.com/api/ScriptParameter/getParameters/" + id + "/m1035913");

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		// queryParams.add("json", js); //set parameters for request

		String appKey = "Bearer " + tokenString; // appKey is unique number

		// Get response from RESTful Server get(ClientResponse.class);
		ClientResponse response = null;
		response = webResource.queryParams(queryParams).header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", appKey).get(ClientResponse.class);

		String jsonStr = response.getEntity(String.class);

		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONObject parameters = jsonObj.getJSONObject("parameters");
		Iterator keysToCopyIterator = parameters.keys();
		List<String> keysList = new ArrayList<String>();
		Map<String, String> allkeyvalues = new HashMap<String, String>();
		Boolean flag = false;
		String value = null;
		while (keysToCopyIterator.hasNext()) {
			String key = (String) keysToCopyIterator.next();
			JSONObject singleKey = parameters.getJSONObject(key);
			Iterator keysinsinglekey = singleKey.keys();
			flag = false;
			value = null;
			while (keysinsinglekey.hasNext()) {
				String defaul = (String) keysinsinglekey.next();
				if (defaul.equals("defaultValue"))
					flag = true;
			}
			if (flag)
				value = singleKey.getString("defaultValue");
			else
				value = "";
			allkeyvalues.put(key, value);
			keysList.add(key);
		}

		System.out.println(keysList);
		System.out.println(allkeyvalues);
		return allkeyvalues;
	}
	
	public String deleteVM(JSONObject deleteObject) throws Exception {

		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		try {
			Client client1 = Client.create();
			WebResource webResource1 = client1.resource("https://cloudbag.mindtree.com/api/VM/delete");
			com.sun.jersey.api.client.WebResource.Builder requestBuilder1 = webResource1
					.type(MediaType.APPLICATION_JSON);
			requestBuilder1.header("Authorization", tokenString);
			ClientResponse response1 = requestBuilder1.post(ClientResponse.class, deleteObject.toString());
			if (response1.getStatus() == 200) {
				return "VM started successfully";
			} else {
				return response1.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Nothing Happeneddddddd";
	}

	public String stopTemplate(JSONObject stopObject) throws Exception {

		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		try {
			Client client1 = Client.create();
			WebResource webResource1 = client1.resource("https://cloudbag.mindtree.com/api/VM/stop");
			com.sun.jersey.api.client.WebResource.Builder requestBuilder1 = webResource1
					.type(MediaType.APPLICATION_JSON);
			requestBuilder1.header("Authorization", tokenString);
			ClientResponse response1 = requestBuilder1.post(ClientResponse.class, stopObject.toString());
			if (response1.getStatus() == 200) {
				return "VM started successfully";
			} else {
				return response1.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Nothing Happeneddddddd";
	}

	public String startTemplate(JSONObject startObject) throws Exception {

		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		try {
			Client client1 = Client.create();
			WebResource webResource1 = client1.resource("https://cloudbag.mindtree.com/api/VM/start");
			com.sun.jersey.api.client.WebResource.Builder requestBuilder1 = webResource1
					.type(MediaType.APPLICATION_JSON);
			requestBuilder1.header("Authorization", tokenString);
			ClientResponse response1 = requestBuilder1.post(ClientResponse.class, startObject.toString());
			if (response1.getStatus() == 200) {
				return "VM started successfully";
			} else {
				return response1.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Nothing Happeneddddddd";
	}

	public String executeTemplate(JSONObject executeObject) throws Exception {

		System.out.println(executeObject);
		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		try {
			Client client1 = Client.create();
			WebResource webResource1 = client1.resource("https://cloudbag.mindtree.com/api/Execute/OnAzure");
			com.sun.jersey.api.client.WebResource.Builder requestBuilder1 = webResource1
					.type(MediaType.APPLICATION_JSON);
			requestBuilder1.header("Authorization", tokenString);
			ClientResponse response1 = requestBuilder1.post(ClientResponse.class, executeObject.toString());
			System.out.println("tttttthissssssssis tosting:"+executeObject.toString());
			if (response1.getStatus() == 200) {
				return "Deployment is Successful";
			} else {
				return response1.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Nothing Happeneddddddd";
	}

	public static void main(String[] args) throws Exception {
		String tokenString = getAccessToken("M1035913@mindtree.com", "Collonel*2",
				"ecc97145-098a-42a1-9a5b-71069e620f0d");
		System.out.println(tokenString);
		Client client = Client.create();
		WebResource webResource = client.resource("https://cloudbag.mindtree.com/api/Artifacts?userId=m1035913");
		
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		// queryParams.add("json", js); //set parameters for request

		String appKey = "Bearer " + tokenString; // appKey is unique number
		// Get response from RESTful Server get(ClientResponse.class);
		ClientResponse response = null;
		response = webResource.queryParams(queryParams).header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", appKey).get(ClientResponse.class);
		String jsonStr = response.getEntity(String.class);

		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray jsonArray = jsonObj.getJSONArray("filterResponse");
		int size = jsonArray.length();

		/*
		 * List<String> artifacts = new ArrayList<String>(); for (int i = 0; i <
		 * size; i++) {
		 * artifacts.add(jsonArray.getJSONObject(i).getString("title")); }
		 * System.out.println(artifacts); System.out.println(artifacts.size());
		 */

		List<List<String>> ss = new ArrayList<List<String>>();
		List<String> aws = new ArrayList<String>();
		List<String> azure = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			String provider = jsonArray.getJSONObject(i).getString("cloudProvider");
			if (provider.equals("aws"))
				aws.add(jsonArray.getJSONObject(i).getString("title"));

			else if (provider.equals("azure"))
				azure.add(jsonArray.getJSONObject(i).getString("title"));
		}
		ss.add(aws);
		ss.add(azure);

		webResource = client.resource("https://cloudbag.mindtree.com/api/ScriptParameter/getParameters/90001/m1035913");

		queryParams = new MultivaluedMapImpl();
		// queryParams.add("json", js); //set parameters for request

		appKey = "Bearer " + tokenString; // appKey is unique number

		// Get response from RESTful Server get(ClientResponse.class);
		response = null;
		response = webResource.queryParams(queryParams).header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", appKey).get(ClientResponse.class);

		jsonStr = response.getEntity(String.class);

		jsonObj = new JSONObject(jsonStr);
		JSONObject parameters = jsonObj.getJSONObject("parameters");
		Iterator keysToCopyIterator = parameters.keys();
		List<String> keysList = new ArrayList<String>();
		Map<String, String> allkeyvalues = new HashMap<String, String>();
		Boolean flag = false;
		while (keysToCopyIterator.hasNext()) {
			String key = (String) keysToCopyIterator.next();
			JSONObject singleKey = parameters.getJSONObject(key);
			Iterator keysinsinglekey = singleKey.keys();
			flag = false;
			String value = null;
			while (keysinsinglekey.hasNext()) {
				String defaul = (String) keysinsinglekey.next();
				if (defaul.equals("defaultValue"))
					flag = true;
			}
			if (flag)
				value = singleKey.getString("defaultValue");
			allkeyvalues.put(key, value);
			keysList.add(key);

		}
		System.out.println(keysList);
		System.out.println(allkeyvalues);
	}
}