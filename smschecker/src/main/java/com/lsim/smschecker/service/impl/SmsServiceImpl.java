package com.lsim.smschecker.service.impl;

import com.lsim.smschecker.model.Sms;
import com.lsim.smschecker.repository.SmsRepository;
import com.lsim.smschecker.service.inter.SmsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsServiceImpl implements SmsService {

    private final SmsRepository smsRepository;

    public SmsServiceImpl(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @Override
    public List<Sms> getAllSmsMessages() {
        return smsRepository.findAll();
    }

    @Override
    public List<Sms> getSpecificUnAnsweredMessages() {
        return smsRepository.specificUnAnsweredMessages(0);
    }

    @Override
    public void updateSpecificUnAnsweredMessage(String msisdn, String messageBody) {
        smsRepository.updateSpecificUnAnsweredMessage(msisdn, messageBody);
    }

    @Override
    public void insertUnAnsweredMessage(String msisdn, String senderName, String messageBody) {
        smsRepository.insertUnAnsweredMessage(msisdn, senderName, messageBody);
    }
}
