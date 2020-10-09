package com.lsim.smschecker.utilities;

import com.lsim.smschecker.model.Sms;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class SmsRepo {

    public static HashSet<Sms> unAnsweredMessages = new HashSet<>();
    private boolean available = false;

    public synchronized HashSet<Sms> get() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        available = false;
        notifyAll();
        return unAnsweredMessages;
    }

    public synchronized void put(Sms sms) {
        available = true;
        unAnsweredMessages.add(sms);
    }
}
