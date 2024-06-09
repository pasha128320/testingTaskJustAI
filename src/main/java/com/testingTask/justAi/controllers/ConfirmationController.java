package com.testingTask.justAi.controllers;


import com.google.gson.Gson;
import com.testingTask.justAi.objects.IncomingMessage;
import com.testingTask.justAi.services.MessagesService;
import com.testingTask.justAi.services.impl.MessagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConfirmationController {

    private RestTemplate restTemplate;
    private Environment environment;
    private MessagesServiceImpl messagesService;

    @Autowired
    public ConfirmationController(RestTemplate restTemplate, Environment environment, MessagesServiceImpl messagesService) {
        this.restTemplate = restTemplate;
        this.environment = environment;
        this.messagesService = messagesService;
    }

    @PostMapping
    @ResponseBody
    public String getEventQuery(@RequestBody String response) { // get event notification from CallBack API
        // If VK require confirm server address
        if(response.contains("\"type\":\"confirmation\"")){
            return environment.getProperty("CONFIRMATION_KEY");
        }

        Gson gson = new Gson(); // initializing JSON parser
        IncomingMessage incomingMessage = gson.fromJson(response, IncomingMessage.class); // get EventAnswer object from JSON string

        // If notification has new message from user for answer
        if(incomingMessage.getType().equals("message_new")) {
            messagesService.messagesSend(incomingMessage);
        }

        return "ok"; // API requires return message "ok" to stop sending the last notification
    }


    public ConfirmationController() {
    }

}
