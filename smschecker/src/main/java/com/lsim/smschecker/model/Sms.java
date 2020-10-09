package com.lsim.smschecker.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private int id;

    @JoinColumn(name = "Msisdn")
    @Basic(optional = false)
    private String msisdn;

    @JoinColumn(name = "Sender_name")
    @Basic(optional = false)
    private String senderName;

    @JoinColumn(name = "Message_body")
    @Basic(optional = false)
    private String messageBody;

    @JoinColumn(name = "Insert_date", columnDefinition = "default current_timestamp")
    private Timestamp insertDate;

    @JoinColumn(name = "Done_date", columnDefinition = "default on update current_timestamp")
    private Timestamp doneDate;

    @JoinColumn(name = "Is_sent", columnDefinition = "default 0")
    private int isSent;

    @JoinColumn(name = "Status", columnDefinition = "default 1")
    private int status;

    public Sms() {

    }

    public Sms(String msisdn, String senderName, String messageBody) {
        this.msisdn = msisdn;
        this.senderName = senderName;
        this.messageBody = messageBody;
    }

    public int getId() {
        return this.id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public Timestamp getInsertDate() {
        return insertDate;
    }

    public Timestamp getDoneDate() {
        return doneDate;
    }

    public int getIsSent() {
        return isSent;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public void setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate;
    }

    public void setDoneDate(Timestamp doneDate) {
        this.doneDate = doneDate;
    }

    public void setIsSent(int isSent) {
        this.isSent = isSent;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sms sms = (Sms) o;
        return id == sms.id &&
                isSent == sms.isSent &&
                status == sms.status &&
                Objects.equals(msisdn, sms.msisdn) &&
                Objects.equals(senderName, sms.senderName) &&
                Objects.equals(messageBody, sms.messageBody) &&
                Objects.equals(insertDate, sms.insertDate) &&
                Objects.equals(doneDate, sms.doneDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, msisdn, senderName, messageBody, insertDate, doneDate, isSent, status);
    }
}
