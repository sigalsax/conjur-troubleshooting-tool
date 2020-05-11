package com.troubleshooting.troubleshootingtool;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.troubleshooting.model.ConjurLogModel;
import com.troubleshooting.model.EnvModel;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// Direct interface with Docker Java SDK
public class ContainerSession implements ISession{


    public Map getEnv(DockerClient dockerClient, String containerID) {
        EnvModel env = new EnvModel();

        // Extract Env
        OutputStream outputStream = new ByteArrayOutputStream();

		ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerID)
				.withTty(true).withAttachStdin(true).withAttachStdout(true).withAttachStderr(true).withCmd("env").exec();

        try {
            dockerClient.execStartCmd(execCreateCmdResponse.getId()).exec(new ExecStartResultCallback(outputStream, null)).awaitCompletion(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        env.addEnv(outputStream.toString());

        return env.getEnvMap();
    }

    public List<String> getLogs(DockerClient dockerClient, String containerID) {
        // TODO Add query to property configuration

        ConjurLogModel conjurLog = new ConjurLogModel();
        LogContainerCmd logContainer = dockerClient.logContainerCmd(containerID).withStdOut(true).withStdErr(true);
        try {
            logContainer.exec(new LogContainerResultCallback() {
                @Override
                public void onNext(Frame item) {
                    conjurLog.addLog(item.toString());
                }
            }).awaitCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return conjurLog.getLogMap();
    }
}
