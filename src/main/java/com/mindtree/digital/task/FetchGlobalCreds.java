package com.mindtree.digital.task;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.mindtree.digital.task.ConfigResource.Config;

@ExportAsService
@Named
public class FetchGlobalCreds {

	private final PluginSettingsFactory pluginSettingsFactory;

	  @Autowired
	public FetchGlobalCreds(@ComponentImport final  PluginSettingsFactory pluginSettingsFactory) {
		this.pluginSettingsFactory = pluginSettingsFactory;
	}

	public String fetchCreds() {
		PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
		String name = (String) settings.get(Config.class.getName() + ".name");
		String time = (String) settings.get(Config.class.getName() + ".time");
		System.out.println("dffffffffffffffffffffffffffffffff: " + name);
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh: " + time);
		return name + time;

	}

}
