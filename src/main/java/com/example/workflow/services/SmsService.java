package com.example.workflow.services;


import com.example.workflow.entities.SmsPojo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
@Component
public class SmsService {


    private final String ACCOUNT_SID ="AC8f4e382c9a94ca4badc3cb8ec45bbc86";

    private final String AUTH_TOKEN = "f7605aa481645fc72506ec2af21d43a2";

    private final String FROM_NUMBER = "+12544061214";

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

}
