package com.lsim.smschecker.utilities;

import com.lsim.smschecker.model.Sms;
import com.lsim.smschecker.service.inter.SmsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SmsChecker extends Thread {

    private final SmsRepo smsRepo;

    public SmsChecker(SmsRepo smsRepo) {
        this.smsRepo = smsRepo;
    }

    @Autowired
    private SmsService smsService;

    public void run() {
        List<Sms> unAnsweredMessagesFromDb;
        while (true) {
            try {
                unAnsweredMessagesFromDb = smsService.getSpecificUnAnsweredMessages();
                if (unAnsweredMessagesFromDb != null && unAnsweredMessagesFromDb.size() > 0) {
                    unAnsweredMessagesFromDb.stream()
                            .forEach(sms -> smsRepo.put(sms));
                }

                Thread.sleep(((int) Math.random() + 1) * 5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
