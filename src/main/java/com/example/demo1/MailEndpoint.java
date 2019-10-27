package com.example.demo1;

import com.example.mail.MailRequest;
import com.example.mail.MailResponse;
import com.example.mail.VarifyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class MailEndpoint {

    private static final String NAMESPACE_URI = "http://www.howtodoinjava.com/xml/school";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "MailRequest")
    @ResponsePayload
    public MailResponse getMailInfo(@RequestPayload MailRequest request) {
        MailResponse response = new MailResponse();
        sendMail s = new sendMail(request.getAddress(),request.getSubject(),request.getInformation());
        String res = s.sendMailBatch();
        response.setResponse(res);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "VarifyRequest")
    @ResponsePayload
    public MailResponse varifyEmail(@RequestPayload VarifyRequest request) {
        MailResponse response = new MailResponse();
        sendMail s = new sendMail(request.getAddress(), null, null);
        String res = s.varify();
        response.setResponse(res);
        return response;
    }
}
