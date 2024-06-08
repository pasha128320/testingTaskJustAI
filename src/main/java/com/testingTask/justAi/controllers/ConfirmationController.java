package com.testingTask.justAi.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConfirmationController {

    private static final String CONFIRMATION_KEY = "d3ac5d62";
    private static final String ACCESS_TOKEN = "vk1.a.PdGUkepPdmCsyoSfMxrW2695C9qIls3HMbl9k5CxlK3JP121JRYZLSlZcDS2AyiVhTIs7u6Go5xtr9BUbgiJlGRBs0EhhrkhpeRI20YYmwVGGytS4dMP_Ckwdd1vIMKY6dRr1W0yKrRnlRLizvJpatTtoFyk3jdq8vJ7toG9TG7gOUl5CYyIcyyDEtyVXEeUXHPmWe_4uxvrFvI_bcXQmg";
    private static final String ACCESS_FINAL_TOKEN = "e257ee99e257ee99e257ee9955e14f7d33ee257e257ee998433d29709f0b3362aaa266c";
    private static final String ACCESS_SECURITY_KEY = "BkCKS2MEFmLEeW8yCWMq";
    private static final Integer groupId = 226173682;

    @PostMapping
    @ResponseBody
    public String confirmServerAvailibility(@RequestBody String response){

        System.out.println(response);
        return CONFIRMATION_KEY;
    }


    @GetMapping("/messages")
    @ResponseBody
    public void getChat(){
        RestTemplate restTemplate = new RestTemplate();

        System.out.println(restTemplate.getForEntity("https://api.vk.com/method/messages.send?chat_id=1&v=5.199&access_token=" + ACCESS_FINAL_TOKEN,String.class));
    }
}
