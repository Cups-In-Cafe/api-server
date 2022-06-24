package com.highgroup.highgroup.common.utils;

import org.json.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.*;

/**
 * 네이버 클라우드 플랫폼 제공 Sens SMS API -> SMS 보내기
 * @author
 * @since 2020.12.09
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2021.01.20  LCY            최초 생성
 *
 *      </pre>
 */
@Component
public class SmsMessage{
    @Value("${sms.projectId}")
    private String projectId;
    @Value("${sms.accessKey}")
    private String accessKey;
    @Value("${sms.secretKey}")
    private String secretKey;
    @Value("${sms.fromNum}")
    private String fromNum;

    private String url =null;
    private String requestUrl = null;
    private String timestamp = null;
    private String method = "POST";	// method
    
    
    public String sendMessage(String toNum , String content) throws Exception {

        this.url = "/sms/v2/services/"+projectId+"/messages"; // Url
        this.requestUrl = "https://sens.apigw.ntruss.com"+url;//Full Url
        this.timestamp = Long.toString(System.currentTimeMillis());  //Timestamp

        JSONObject bodytJson = new JSONObject();
        JSONObject toJson = new JSONObject();
        JSONArray toArr = new JSONArray();
        toJson.put("subject" , "Hi SMS" );
        toJson.put("content" , content );
        toJson.put("to" , toNum );
        toArr.put(toJson);

        bodytJson.put("type" , "SMS");
        bodytJson.put("contentType" , "COMM");
        bodytJson.put("countryCode" , "82");
        bodytJson.put("from" , fromNum );
        bodytJson.put("subject" , "HI 인증번호");
        bodytJson.put("content" , "인증번호 확인 서비스");
        bodytJson.put("messages" , toArr );
        String body = bodytJson.toString();
        String result = doPost(requestUrl , body);
        return result;
    }
    // OkHttp 통신
    public String doPost(String requestURL , String jsonMessage) throws Exception {
        String message = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("x-ncp-apigw-timestamp", timestamp )
                .addHeader("x-ncp-iam-access-key", accessKey )
                .addHeader("x-ncp-apigw-signature-v2", makeSignature() )
                .url(requestURL)
                .post(RequestBody.create(MediaType.parse("application/json"), jsonMessage)) //POST로 전달할 내용 설정 
                .build();

        Response response = client.newCall(request).execute();  

        //출력
        message = response.body().string();
		
        return message;
    };
    
    // Signature생성
    public String makeSignature() throws Exception {

        String space = " ";		// one space
        String newLine = "\n";	// new line
        
        String message = new StringBuilder()
            .append(method)
            .append(space)
            .append(url)
            .append(newLine)
            .append(timestamp)
            .append(newLine)
            .append(accessKey)
            .toString();

        String encodeBase64String = null;
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
    
        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
      return encodeBase64String;
    };
    
}
