/**
 * Direct interface with Docker Client using the [Docker Java API Client](https://github.com/docker-java/docker-java)
 * and extracts environment variables and logs from the defined container
 */
package com.troubleshooting.troubleshootingtool;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.core.command.LogContainerResultCallback;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContainerDataAccess implements IDataAccess{

    DockerClient dockerClient;

    public ContainerDataAccess() {
        dockerClient = DockerClientBuilder.getInstance().build();
    }

    public String getEnv(String containerID) {
        OutputStream outputStream = new ByteArrayOutputStream();

        ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerID)
                .withTty(true).withAttachStdin(true).withAttachStdout(true).withAttachStderr(false).withCmd("env").exec();

        try {
            dockerClient.execStartCmd(execCreateCmdResponse.getId()).exec(new ExecStartResultCallback(outputStream, null)).awaitCompletion(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }

    public List<String> getLogs(String containerID, String query) {
        List entry = new ArrayList();

        // TODO Add query to property configuration
        LogContainerCmd logContainer = dockerClient.logContainerCmd(containerID).withStdOut(true).withStdErr(true);
        try {
            logContainer.exec(new LogContainerResultCallback() {
                @Override
                public void onNext(Frame item) {
                    if (item.toString().contains(query)) {
                        entry.add(item.toString());
                    }
                }
            }).awaitCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return entry;
    }
}
