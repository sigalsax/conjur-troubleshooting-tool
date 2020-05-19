package com.troubleshooting.troubleshootingtool;

import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.core.command.LogContainerResultCallback;

import com.troubleshooting.controller.AuthnTroubleshootController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class TroubleshootingToolApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TroubleshootingToolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO add Model and View to Controller Constructor
		AuthnTroubleshootController start = new AuthnTroubleshootController("0921773ddba3");
		start.getTroubleshootingData();
	}

}


