package com.mindtree.digital.task;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.TaskConfigurator;
import com.atlassian.bamboo.task.TaskDefinition;

import scala.util.parsing.json.JSON;

public class PersistTemplateDataServlet extends HttpServlet {
	
	private Logger log = Logger.getLogger(PersistTemplateDataServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String paramKeys[] = req.getParameterValues("paramKeys");
		String paramValues[] = req.getParameterValues("paramValues");
		JSONObject toreturn=new JSONObject();
		for(int i=0;i<paramKeys.length;i++){
				try {
					toreturn.put(paramKeys[i], paramValues[i]);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		}
		PrintWriter out = resp.getWriter();
		out.print(toreturn);
		
		ParameterEntity entity=new ParameterEntity();
		entity.setParamKeys(paramKeys);
		entity.setParamValues(paramValues);
		entity.setToreturn(toreturn);
		
		ForAjaxCalls ajaxCalls=new ForAjaxCalls();
		ajaxCalls.parameterValues(entity);
		
	
	}
}
