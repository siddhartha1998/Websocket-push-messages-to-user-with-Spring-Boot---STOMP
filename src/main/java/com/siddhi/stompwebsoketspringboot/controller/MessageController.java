package com.siddhi.stompwebsoketspringboot.controller;

import com.siddhi.stompwebsoketspringboot.POJO.Greeting;
import com.siddhi.stompwebsoketspringboot.POJO.HelloMessage;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Greeting handleMessage(HelloMessage message){
        System.out.println("public message call: "+message.getName());
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/private-message")
    public void handleMultipleMessages(HelloMessage message, Principal principal) throws InterruptedException {
        // Send multiple responses back to the client
        System.out.println("private message call: "+message.getName());
       // for (int i = 1; i <= 5; i++) {
           Greeting greeting = new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
          //  messagingTemplate.convertAndSend("/topic/greetings", greeting);
           Thread.sleep(1000); // Simulate delay
           messagingTemplate.convertAndSendToUser(principal.getName(),"/topic/private-messages", greeting);

     //   }
    }
}
