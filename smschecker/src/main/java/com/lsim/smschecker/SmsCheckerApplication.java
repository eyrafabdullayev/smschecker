package com.lsim.smschecker;

import com.lsim.smschecker.model.Sms;
import com.lsim.smschecker.utilities.SmsChecker;
import com.lsim.smschecker.utilities.SmsRepo;
import com.lsim.smschecker.utilities.SmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class SmsCheckerApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SmsRepo smsRepo;

    @Bean
    public SmsChecker checker() {
        return new SmsChecker(smsRepo);
    }

    @Bean
    SmsSender sender() {
        return new SmsSender();
    }

    @Bean
    public CommandLineRunner runner() {
        CommandLineRunner clr = args -> {
            checker().start();

            // because i wish my checker get messages from repo after there are some messages
            Thread.sleep(1000);

            ExecutorService executorService = Executors.newFixedThreadPool(3);
            while (true) {
                if (SmsRepo.unAnsweredMessages.size() > 0) {
                    List<Sms> unAnsweredMessages = new ArrayList<>(smsRepo.get());
                    unAnsweredMessages.stream()
                            .forEach(sms -> {
                                executorService.submit(new SmsSender(sms));
                                // if you wish do not show again with sms messages which Msisdn is not corrent!
                                SmsRepo.unAnsweredMessages.remove(sms);
                            });
                } else
                    logger.info("EMPTY_RESULT: There are no any unanswered sms messages!");

                Thread.sleep(((int) Math.random() + 1) * 10000);
            }
        };
        return clr;
    }

    public static void main(String[] args) {
        SpringApplication.run(SmsCheckerApplication.class, args);
    }
}
