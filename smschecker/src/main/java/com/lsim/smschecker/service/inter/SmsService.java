package com.lsim.smschecker.service.inter;

import com.lsim.smschecker.model.Sms;

import java.util.List;

public interface SmsService {

    List<Sms> getUnAnsweredMessages();

    List<Sms> getSpecificUnAnsweredMessages();

    void updateSpecificUnAnsweredMessage(String msisdn, String messageBody);

    void insertUnAnsweredMessage(String msisdn, String senderName, String messageBody);
}
