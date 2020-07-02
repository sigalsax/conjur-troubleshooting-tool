package com.troubleshooting.controller;

import com.troubleshooting.troubleshootingtool.Input;
/**
 * Interface for Controllers
 */
interface Troubleshoot {

    boolean validateInput(Input input);

    String getTroubleshootingData(String containerId, String query);
}
