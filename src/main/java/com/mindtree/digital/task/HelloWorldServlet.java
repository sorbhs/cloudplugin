package com.mindtree.digital.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloWorldServlet extends HttpServlet {
	private Logger log = Logger.getLogger(ForAjaxCalls.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> resolvingRepoList = new ArrayList<String>();
		resp.setContentType("application/json");
		String templateName = req.getParameter("templateName");
		String templateId = null;
		GetToken getToken = new GetToken();
		Map<String, String> getArtifactIdandNames = null;
		try {
			getArtifactIdandNames = getToken.getArtifactIdandNames();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Map.Entry<String, String> entry : getArtifactIdandNames.entrySet()) {
			if (entry.getValue().equals(templateName)) {
				templateId = entry.getKey();
				break;
			}
		}
	
		PrintWriter out = resp.getWriter();
		try {
			out.print(getToken.getSingleTemplateJSONData(templateId));
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * JSONObject jsonObjs = new JSONObject();
		 * 
		 * JSONArray templatesarray = new JSONArray();
		 * 
		 * JSONObject oneparameterelement = new JSONObject(); JSONObject
		 * twoparameterelement = new JSONObject();
		 * 
		 * JSONObject arryelementone = new JSONObject(); JSONObject
		 * arryelementtwo = new JSONObject();
		 * 
		 * try {
		 * 
		 * oneparameterelement.put("hello1", "hello1 ki value");
		 * oneparameterelement.put("hello2", "hello2 ki value");
		 * oneparameterelement.put("hello3", "hello3 ki value");
		 * oneparameterelement.put("hello4", "hello4 ki value");
		 * arryelementone.put("name", "Basic Devops_without asg");
		 * arryelementone.put("parameters", oneparameterelement);
		 * 
		 * twoparameterelement.put("bolo1", "bolo1 ki value");
		 * twoparameterelement.put("bolo2", "bolo2 ki value");
		 * twoparameterelement.put("bolo3", "bolo3 ki value");
		 * twoparameterelement.put("bolo4", "bolo4 ki value");
		 * arryelementtwo.put("name", "Nexus_without_asg");
		 * arryelementtwo.put("parameters", twoparameterelement);
		 * 
		 * templatesarray.put(arryelementone);
		 * templatesarray.put(arryelementtwo); jsonObjs.put("size", 2);
		 * jsonObjs.put("templates", templatesarray);
		 * 
		 * PrintWriter out = resp.getWriter(); out.print(jsonObjs); } catch
		 * (JSONException e) { e.printStackTrace(); }
		 */

	}

	private void returnJsonObject(HttpServletResponse resp, Object toReturn) throws IOException {
		JsonFactory jsonFactory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper();
		jsonFactory.setCodec(mapper);
		PrintWriter writer = null;
		try {
			writer = resp.getWriter();
			JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(writer);
			jsonGenerator.writeObject(toReturn);
			writer.flush();
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

}
