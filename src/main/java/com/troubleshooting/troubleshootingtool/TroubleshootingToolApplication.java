package com.troubleshooting.troubleshootingtool;

import com.troubleshooting.model.AuthnUserDataModel;
import com.troubleshooting.view.AuthnTroubleshootView;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.troubleshooting.controller.AuthnTroubleshootController;

@SpringBootApplication
public class TroubleshootingToolApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TroubleshootingToolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AuthnTroubleshootController start = new AuthnTroubleshootController(new AuthnUserDataModel(), new AuthnTroubleshootView());
	}

}


