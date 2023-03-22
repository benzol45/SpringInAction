package com.example.topic2.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.util.function.Function;

@Configuration
public class ToFileConveyor {

    @Bean
    public MessageChannel integrationOverFileChanel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    @Transformer(inputChannel = "integrationOverFileChanel", outputChannel = "writeToFileChanel")
    public GenericTransformer<MySimpleClass, String> transformAddPrefixSuffix() {
        return source -> {
            source.setMessage("START_MESSAGE_" + source.getMessage() + "_END_MESSAGE");
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(source);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return "";
        };
    }

    @Bean
    public MessageChannel writeToFileChanel() {
        return new PublishSubscribeChannel ();
    }

    @Bean
    @ServiceActivator(inputChannel = "writeToFileChanel")
    public FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File("./FileForTransfer/"));
        fileWritingMessageHandler.setExpectReply(false);
        fileWritingMessageHandler.setFileExistsMode(FileExistsMode.APPEND);
        fileWritingMessageHandler.setAppendNewLine(true);

        return fileWritingMessageHandler;
    }
}
