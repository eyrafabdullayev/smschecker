package com.lsim.smschecker.controller;

import com.lsim.smschecker.dto.SmsRequestDto;
import com.lsim.smschecker.service.inter.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SmsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SmsService service;

    public SmsController(SmsService smsService) {
        service = smsService;
    }

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<Map<String, String>> serverSideSendSms(@Valid @RequestBody SmsRequestDto smsRequestDto, BindingResult
            bindingResult) {
        Map<String, String> response = new HashMap<String, String>();

        try {

            if (bindingResult.hasErrors())
                throw new IllegalArgumentException();

            service.insertUnAnsweredMessage(smsRequestDto.getMsisdn(), smsRequestDto.getSenderName(),
                    smsRequestDto.getMessageBody());

            response.put("success", "201");

            logger.info("SMS_ADDED_TO_DATABASE", "SMS added to the db successfully!");
        } catch (IllegalArgumentException e) {
            response.put("fail", "500");
            logger.warn("ILLEGAL_ARGUMENT_EXCEPTION", "Illegal Argument(s)");
        } catch (Exception e) {
            response.put("fail", "500");
            logger.error("SMS_COULD_NOT_ADD_TO_DATABASE", "Internal Server Error");
        } finally {
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/sms/send")
    @ResponseBody
    public ResponseEntity<Map<String, String>> clientSideSendSms(@RequestParam("msisdn") String msisdn,
                                                                 @RequestParam("sender") String senderName,
                                                                 @RequestParam("text") String messageBody) {

        Map<String, String> response = new HashMap<String, String>();

        try {
            boolean match = msisdn.matches(new MyRegex().pattern);

            if (!match)
               throw new IllegalStateException();

            service.updateSpecificUnAnsweredMessage(msisdn, messageBody);

            response.put("code", "0");
            response.put("message", "OK");

            logger.info("SMS_SENT", "SMS sent to the client successfully!");
        } catch (IllegalStateException e) {
            response.put("code", "1");
            response.put("message", "FAIL");  
            logger.info("SMS_IS_NOT_ANSWERED", "SMS couldn't send to the client!");
        } catch (Exception e) {
            response.put("code", "1");
            response.put("message", "FAIL");
            logger.error("SMS_IS_NOT_ANSWERED", "Internal Server Error");
        } finally {
            return ResponseEntity.ok(response);
        }
    }

    class MyRegex {
        public String pattern = "^994(10|50|51|55|60|70|77|99)\\d{7}$";
    }
}
