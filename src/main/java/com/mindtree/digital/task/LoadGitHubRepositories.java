package com.mindtree.digital.task;

import org.apache.log4j.Logger;

import com.atlassian.bamboo.ww2.actions.PlanActionSupport;
import com.atlassian.bamboo.ww2.aware.permissions.PlanEditSecurityAware;

public class LoadGitHubRepositories extends PlanActionSupport implements PlanEditSecurityAware{
	
	  private static final Logger log = Logger.getLogger(LoadGitHubRepositories.class);
	
	  public String doLoad() throws Exception
	    {
	        return SUCCESS;
	    }
}
