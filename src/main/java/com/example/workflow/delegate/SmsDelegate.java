package com.example.workflow.delegate;

import com.example.workflow.entities.SmsPojo;
import com.example.workflow.services.SmsService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class SmsDelegate  implements JavaDelegate {


    private final String  TOPIC_DESTINATION = "/lesson/sms";
    @Autowired
    SmsService service;

    @Autowired
    private SimpMessagingTemplate webSocket;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        SmsPojo sms = new SmsPojo();

        try{
            service.send(sms);
        }
        catch(Exception e){

            webSocket.convertAndSend(TOPIC_DESTINATION,  " Error sending the SMS: "+e.getMessage());
            throw e;
        }
        webSocket.convertAndSend(TOPIC_DESTINATION, " SMS has been sent!: "+sms.getTo());

    }
}
