package com.troubleshooting.troubleshootingtool;

import com.troubleshooting.controller.AuthenticationTroubleshootController;
import com.troubleshooting.model.AuthenticationUserDataModel;
import com.troubleshooting.view.AuthenticationTroubleshootView;

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
		AuthenticationTroubleshootController start = new AuthenticationTroubleshootController(new AuthenticationUserDataModel(), new AuthenticationTroubleshootView());
	}

}


