package com.lsim.smschecker.utilities;

import com.lsim.smschecker.model.Sms;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SmsSender extends Thread {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private Sms value;

    public SmsSender() {

    }

    public SmsSender(Sms value) {
        this.value = value;
    }

    public void run() {
        try {
            sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendGet() throws Exception {

        HttpGet request = new HttpGet("http://localhost:8080/sms/send?msisdn="+value.getMsisdn()+"&sender="+value.getSenderName()+"&text="+value.getMessageBody());

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }

    }
}
