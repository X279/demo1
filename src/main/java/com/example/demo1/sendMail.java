package com.example.demo1;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.regex.Pattern;

public class sendMail {
    private IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou","LTAI4FnFAWu8z3VGdGT8AvTn","VzH6nHOM1wr3jW9fOj5D19hj3Mjioo");
    private IAcsClient client = new DefaultAcsClient(profile);
    private final String address;
    private final String subject;
    private final String information;


    sendMail(String address,String subject,String information){

        this.address = address;
        this.subject = subject;
        this.information = information;
    }

    public String varify(){
        String pattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        boolean isMatch = Pattern.matches(pattern, address);
        if(isMatch){
            return "Y";
        }
        else
            return "N";
    }

    private String send(String add){
        SingleSendMailRequest request = new SingleSendMailRequest();
        try{
            request.setAccountName("zhouning@cugyn.xyz");
            request.setReplyToAddress(true);
            request.setFromAlias("lkx");
            request.setAddressType(1);
            request.setToAddress(add);
            request.setSubject(this.subject);
            request.setHtmlBody(this.information);
            request.setClickTrace("0");
            System.out.println("发送...");
            SingleSendMailResponse response = client.getAcsResponse(request);
            return "Y";
        }catch (ServerException e1){
            System.out.println("Error1:" + e1.getErrCode());
            e1.printStackTrace();
            return "N";
        }catch (ClientException e2){
            System.out.println("Error2:" + e2.getErrCode());
            e2.printStackTrace();
            return "N";
        }
    }

    public String sendMailBatch(){
        String[] urlList = address.split(";");
        System.out.println(urlList.length);
        String res = "";
        for(int i=0;i<urlList.length;i++){
            System.out.println(urlList[i]);
             res += send(urlList[i]);
        }
        if(res.contains("N"))
            return "N";
        else
            return "Y";
    }
}
