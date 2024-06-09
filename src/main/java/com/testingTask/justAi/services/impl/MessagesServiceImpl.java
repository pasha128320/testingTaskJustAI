package com.testingTask.justAi.services.impl;

import com.testingTask.justAi.objects.IncomingMessage;
import com.testingTask.justAi.services.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MessagesServiceImpl implements MessagesService {

    Environment environment; // enviroment used to get variables from application.properties
    RestTemplate restTemplate;

    @Autowired
    public MessagesServiceImpl(Environment environment, RestTemplate restTemplate) {
        this.environment = environment;
        this.restTemplate = restTemplate;
    }

    @Override
    public void messagesSend(IncomingMessage incomingMessage) { // Realize connect with messages.send method from VK API
        Integer user_id = incomingMessage.getObject().getMessage().getFrom_id(); // get user_id from incoming message
        String incomingTextMessage = incomingMessage.getObject().getMessage().getText();

        String answerText = "Вы написали: " + incomingTextMessage;

        if(incomingTextMessage.isEmpty()) {
            answerText = "Вы ничего не написали :(";
        }
        // Check if user sended attachments
        if(!incomingMessage.getObject().getMessage().getAttachments().isEmpty()){
            answerText += "\n (К сожалению, меня не научили цитировать вложения, однако в будущих версиях я смогу это делать)";
        }

        // create URI with query params to send query
        URI uri = UriComponentsBuilder.fromUriString(environment.getProperty("VK_API_METHOD_URL")+"messages.send")
                .queryParam("access_token", environment.getProperty("ACCESS_TOKEN"))
                .queryParam("v",incomingMessage.getV())
                .queryParam("user_id", user_id)
                .queryParam("message", answerText)
                .queryParam("random_id", 0)
                .build().toUri();

        // send query by URL above
        restTemplate.postForObject(uri, null, Object.class);

    }
}
