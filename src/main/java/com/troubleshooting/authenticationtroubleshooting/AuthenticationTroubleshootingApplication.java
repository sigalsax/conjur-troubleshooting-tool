package com.troubleshooting.authenticationtroubleshooting;

import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.core.command.LogContainerResultCallback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.api.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


@SpringBootApplication
public class AuthenticationTroubleshootingApplication {

	public static void main(String[] args) {
		String containerID = "8e53411614f2";

		DockerClient dockerClient;
		dockerClient = DockerClientBuilder.getInstance().build();

		// Extract Env
		OutputStream outputStream = new ByteArrayOutputStream();

		ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerID)
				.withAttachStdout(true).withAttachStderr(true).withCmd("env").exec();

		System.out.println(dockerClient.execStartCmd(execCreateCmdResponse.getId()).exec(new ExecStartResultCallback(outputStream, null)));

		// Extract Logs
		final List<String> loggingFrames = getDockerLogs(dockerClient, containerID);
		for(int i = 0; i < loggingFrames.size(); i++) {
			System.out.println(loggingFrames.get(i));
		}

		SpringApplication.run(AuthenticationTroubleshootingApplication.class, args);
	}

	public static List<String> getDockerLogs(DockerClient dockerClient, String containerID) {
		Pattern p = Pattern.compile("database");

		final List<String> logs = new ArrayList<>();
		LogContainerCmd logContainer = dockerClient.logContainerCmd(containerID).withStdOut(true).withStdErr(true);
		try {
			logContainer.exec(new LogContainerResultCallback() {
				@Override
				public void onNext(Frame item) {
					if (p.matcher(item.toString()).find()) {
						logs.add(item.toString());
					}
				}
			}).awaitCompletion();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return logs;
	}
}


