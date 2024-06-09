package com.testingTask.justAi.services.impl;

import com.testingTask.justAi.objects.IncomingMessage;
import com.testingTask.justAi.objects.OutgoingMessage;
import com.testingTask.justAi.services.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MessagesServiceImpl implements MessagesService {

    Environment environment; // enviroment used to get properties from application.properties
    RestTemplate restTemplate;

    @Autowired
    public MessagesServiceImpl(Environment environment, RestTemplate restTemplate) {
        this.environment = environment;
        this.restTemplate = restTemplate;
    }

    @Override
    public void messagesSend(IncomingMessage incomingMessage) { // Realize connect with messages.send method from VK API
        String answerText = "Вы написали: " + incomingMessage.getObject().getMessage().getText();
        Integer user_id = incomingMessage.getObject().getMessage().getFrom_id(); // get user_id from incoming message

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
