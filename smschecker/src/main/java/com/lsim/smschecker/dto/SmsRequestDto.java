package com.lsim.smschecker.dto;

import com.sun.istack.NotNull;

public class SmsRequestDto {
    @NotNull
    private String msisdn;
    @NotNull
    private String senderName;
    @NotNull
    private String messageBody;

    public SmsRequestDto() {
    }

    public SmsRequestDto(String msisdn, String senderName, String messageBody) {
        this.msisdn = msisdn;
        this.senderName = senderName;
        this.messageBody = messageBody;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
