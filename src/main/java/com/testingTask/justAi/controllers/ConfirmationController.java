package com.testingTask.justAi.controllers;


import com.google.gson.Gson;
import com.testingTask.justAi.objects.EventNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Controller
public class ConfirmationController {

    private static final String CONFIRMATION_KEY = "d3ac5d62";
    private static final String ACCESS_TOKEN = "vk1.a.PdGUkepPdmCsyoSfMxrW2695C9qIls3HMbl9k5CxlK3JP121JRYZLSlZcDS2AyiVhTIs7u6Go5xtr9BUbgiJlGRBs0EhhrkhpeRI20YYmwVGGytS4dMP_Ckwdd1vIMKY6dRr1W0yKrRnlRLizvJpatTtoFyk3jdq8vJ7toG9TG7gOUl5CYyIcyyDEtyVXEeUXHPmWe_4uxvrFvI_bcXQmg";
    private static final String ACCESS_FINAL_TOKEN = "e257ee99e257ee99e257ee9955e14f7d33ee257e257ee998433d29709f0b3362aaa266c";
    private static final String ACCESS_SECURITY_KEY = "BkCKS2MEFmLEeW8yCWMq";
    private static final Integer GROUP_ID = 226173682;
    private static final String VK_API_METHOD_URI = "https://api.vk.com/method/";
    private static final String VK_API_URI = "https://api.vk.com";


    RestTemplate restTemplate = new RestTemplate();
    private EventNotification answer;


    @PostMapping
    @ResponseBody
    public String getEventQuery(@RequestBody String response) { // get event notification from CallBack API

        Gson gson = new Gson();

        answer = gson.fromJson(response, EventNotification.class); // get EventAnswer object from JSON string

        String apiMethod = "messages.send";


        // get
        if(answer.getType().equals("message_new")) {
            String answerToUser = "Вы написали: "+ answer.getObject().getMessage().getText();
            restTemplate.postForObject(VK_API_METHOD_URI + "messages.send?access_token=" + ACCESS_TOKEN + "&user_id=161876243&random_id=0&message="+ answerToUser +"&v=5.199", null, Object.class);
        }
        return "ok"; // API requires return message "ok" to stop sending the last notification
    }


//    @GetMapping("/messages")
//    @ResponseBody
//    public ResponseEntity<String> getChat(){
////        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type","application/json");
//        Gson gson = new Gson();
//
//        return new ResponseEntity<String>(gson.toJson(answer),headers, HttpStatus.OK) ;
////        System.out.println(restTemplate.getForEntity("https://api.vk.com/method/messages.send?chat_id=1&v=5.199&access_token=" + ACCESS_TOKEN,String.class));
//    }

    public ConfirmationController(RestTemplate restTemplate, EventNotification answer) {
        this.restTemplate = restTemplate;
        this.answer = answer;
    }

    public ConfirmationController() {
    }
}
