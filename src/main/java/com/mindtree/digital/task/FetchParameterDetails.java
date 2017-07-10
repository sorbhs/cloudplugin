package com.mindtree.digital.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class FetchParameterDetails extends HttpServlet {
	private Logger log = Logger.getLogger(PersistTemplateDataServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		List<String> paramObjectKeys = new ArrayList<String>(
				Arrays.asList(req.getParameter("paramObjectKeys").split(",")));
		System.out.println("this is paramObjectKeys string:"+paramObjectKeys);
		List<String> paramObjectValues = new ArrayList<String>(
				Arrays.asList(req.getParameter("paramObjectValues").split(",")));
		System.out.println("this is paramObjectValues:"+paramObjectValues);

		JSONObject jsonObject = new JSONObject();
		int i = 0;
		int j = 0;

		/*
		 * for (String string : paramKeys){ paramKeysarray[i]=string; i++; j++;
		 * } i=0; for (String string : paramvalues){ paramvaluesarray[i]=string;
		 * i++; } for(int k=0;k<j;k++){ try { jsonObject.put(paramKeysarray[k],
		 * paramvaluesarray[k]); } catch (JSONException e) {
		 * e.printStackTrace(); } }
		 */

		/*
		 * for (String string : paramKeys) { try { jsonObject.put(string,
		 * string); } catch (JSONException e) { e.printStackTrace(); }
		 * 
		 * }
		 */
		try {
			for (i = 0; i < paramObjectKeys.size(); i++) {
				if (i == 0) {
					jsonObject.put(paramObjectKeys.get(i).substring(1), paramObjectValues.get(i).substring(1));
				} else if (i == paramObjectKeys.size() - 1) {
					jsonObject.put(paramObjectKeys.get(i).substring(1, paramObjectKeys.get(i).length() - 1),
							paramObjectValues.get(i).substring(1, paramObjectValues.get(i).length() - 1));
				} else {
					System.out.println("param keys HAHA:" + paramObjectKeys.get(i));
					System.out.println("param values HAHA:" + paramObjectValues.get(i));
					jsonObject.put(paramObjectKeys.get(i).substring(1), paramObjectValues.get(i).substring(1));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();
		out.print(jsonObject);
	}

}
