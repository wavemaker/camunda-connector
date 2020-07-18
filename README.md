## Connector Introduction

Connector is a Java based backend extension for WaveMaker applications. Connectors are built as Java modules & exposes java based SDK to interact with the connector implementation.
Each connector is built for a specific purpose and can be integrated with one of the external services. Connectors are imported & used in the WaveMaker application. Each connector runs on its own container thereby providing the ability to have it’s own version of the third party dependencies.

## Features of Connectors

1. Connector is a java based extension which can be integrated with external services and reused in many Wavemaker applications.
1. Each connector can work as an SDK for an external system.
1. Connectors can be imported once in a WaveMaker application and used many times in the applications by creating multiple instances.
1. Connectors are executed in its own container in the WaveMaker application, as a result there are no dependency version conflict issues between connectors.

## About Camunda Connector

### Camunda Connector Introduction
Camunda BPM is a lightweight, open source platform for Business Process Management.Camunda is an open source Java based BPM platform used primarily to automate Business Process Model and Notation (BPMN) 2.0 processes. It is built around the process engine
component.

This connector will provide an easy api to intract with Camunda business process.

### Prerequisite

1. Camunda business processes, which is already designed and deployed in camunda server.
1. Camunda connection details such as server IP and port.
1. Java 1.8 or above
1. Maven 3.1.0 or above
1. Any java editor such as Eclipse, Intelij..etc
1. Internet connection


### Build
You can build this connector using following command
```
mvn clean install
```

### Deploy
You can import connector dist/camunda.zip artifact in WaveMaker studio application using file upload option.
On after deploying camunda-Connector in the WaveMaker studio application, make sure to update connector properties are updated in the profile properties.Such as camunda server Ip and Port no.

### Use Camunda Connector in WaveMaker

```

@Autowired
CamundaConnector camundaConnector;

Map<String, Variable> variables = new HashMap<>();
variables.put("cityname", new Variable("chennai", "String"));

ProcessRequest processRequest = new ProcessRequest();
processRequest.setProcessName("someprocess");
processRequest.setVariables(variables);
processRequest.setWithVariablesInReturn(true);
ProcessResponse response = camundaConnector.executeShortLivedProcess(processRequest);

```









