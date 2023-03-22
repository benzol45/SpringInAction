package com.example.topic2.integration;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "integrationOverFileChanel")
public interface FileGateway {
    void writeToFile(@Header(FileHeaders.FILENAME) String fileName, MySimpleClass data);

}
