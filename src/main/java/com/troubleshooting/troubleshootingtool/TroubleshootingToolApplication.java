package com.troubleshooting.troubleshootingtool;

import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.core.command.LogContainerResultCallback;

import com.troubleshooting.model.EnvModel;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@SpringBootApplication
public class TroubleshootingToolApplication implements CommandLineRunner {

	@Autowired
	ProgramProperties prop;

	public static void main(String[] args) {
		SpringApplication.run(TroubleshootingToolApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

        // TODO add below to Data Access Layer
		DockerClient dockerClient;
		dockerClient = DockerClientBuilder.getInstance().build();

		ContainerSession sess = new ContainerSession();

		// Holds string of Env
        EnvModel env = new EnvModel();

//		Extract Logs
		List<String> loggingFrames = sess.getLogs(dockerClient, prop.containerid);

        for(int i = 0; i < loggingFrames.size(); i++) {
            System.out.println(loggingFrames.get(i));
        }

        // Extract Env
        Map<String, String> envMap= new HashMap(sess.getEnv(dockerClient, prop.containerid));
        envMap.forEach((key, value) -> System.out.println(key + ":" + value));
	}

}


