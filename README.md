# Conjur Troubleshooting Tool

## Proposal

Troubleshooting can be a time-consuming undertaking as a developer works with different development and deployment environments, platforms, and tooling that finding the source of the problem is rarely ever clear. To tackle this ongoing issue, we need an effective tool (with a great UI) that will aid in the troubleshooting process.

## Goal

Provide value to the team while also expanding knowledge and experience on a variety of topics REST/curl, Models, Design Patterns, Angular.

## Ideas:

- A tool that fetches specific logs according to specific errors/keywords from Conjur in a containerized environment and grabs the ENV variables from policy and container ENV. Ex: user input: "authentication" will return all log entries with "Authentication".
    
    - For DAP, checks API Endpoints are functioning correctly by making queries to health, nginx services, etc
    
    - For DAP, fetch Master/Follower logs and configurations
 
- Sales force tool that extracts all useful information from cases necessary for engineers to troubleshoot the case

- Page with mappings of repository names to their pull commands. Ex: `conjur` → `docker pull registry2.itci.conjur.net/conjur` (for both private and public registries)

- Slackbot where if you use a certain #tag (#documentation), will create salesforce cases for documentation  

## MVP

For the first iteration of this tool, I have decided to pursue the first idea and create a tool that will do the following:

- Connect with Conjur / DAP containers and extract ENV variables

    - *NOTE:* By default, the tool also supports DAP b/c from the functionality currently offered, the user can input the container name/id and receive the necessary logs and ENV variables which is environment agnostic
    
    - *NOTE:* For OSS, we are limited to setting the log levels as container ENV variables before OSS spin up

- Parse and extract Conjur / DAP logs based on the type user requests

- Return a result page with the ENV variables and all associated log entries listed

## Phase 0

- [x] Reach out to developers, PO/Ms, and SEs to understand the pain points and use this feedback to develop tooling that will feature in the project

- [x] Define an MVP that will provide immediate value and a running backlog

- [x] Create the design and map out call chains and possible user interactions

## Design

Class diagram:
![Class Diagram](./classDiagram.png)

Sequence diagram:
![System Sequence Diagram](./troubleshootSystemSequenceDiagram.png)

Activity diagram:

![Activity Diagram](./activityDiagram.png)



UI Mocks can be found here: https://www.figma.com/file/QcAxsC58Lh969NLunI8sTT/Authn2Mock?node-id=0%3A1

### Open questions:

- I provided the Use Case Diagram above but do not feel that those (`Get Container Data` and `Extract Logs/Env`) are use cases as these items depend on Troubleshooting to operate correctly. Are there more use cases besides troubleshooting? 

- In the Troubleshooting class should I use a factory method that chooses between 2 different classes (DAP/Conjur) that implements the same interface (ConjurEnv)?

- What language to write this project in? 
    
    - Angular/Typescript, Node Docker API: https://www.npmjs.com/package/node-docker-api