package com.example.topic2.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;
import java.io.IOException;

@Configuration
public class FromFileConveyor {
    @Bean
    @InboundChannelAdapter(value = "fileChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new File("./FileForTransfer/"));
        return sourceReader;
    }

    @Bean
    public MessageChannel fileChannel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "fileChannel", outputChannel = "objectChanel")
    public GenericTransformer<File, MySimpleClass> getObject() {
        return payload -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                MySimpleClass mySimpleClass = objectMapper.readValue(payload, MySimpleClass.class);
                return mySimpleClass;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    @Bean
    public MessageChannel objectChanel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "objectChanel", outputChannel = "processedObjectChanel")
    public GenericTransformer<MySimpleClass, MySimpleClass> processObject() {
        return object -> {
            object.setMessage(object.getMessage().replaceFirst("START_MESSAGE_", "").replaceFirst("_END_MESSAGE", ""));
            return object;
        };
    }

    @Bean
    public MessageChannel processedObjectChanel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "processedObjectChanel")
    public GenericHandler<MySimpleClass> myHandlerToConsole() {
        return (mySimpleClass, headers) -> {
            System.out.println(mySimpleClass);
            return null;
        };
    }
}
