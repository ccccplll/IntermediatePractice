package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

@Slf4j
public class Chat {
    private final String ACCESS_TOKEN_URI = "https://aip.baidubce.com/oauth/2.0/token";

    private String apiKey = "yourApikey";
    private String secretKey = "yourSecetkey";
    private String accessToken;

    private OkHttpClient client = new OkHttpClient();

    public boolean getAccessToken(){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        //创建一个请求
        Request request = new Request.Builder()
                .url(ACCESS_TOKEN_URI+"?client_id=" + apiKey + "&client_secret=" + secretKey + "&grant_type=client_credentials")
                .method("POST",body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            //使用浏览器对象发起请求
            Response response = client.newCall(request).execute();
            //只能执行一次response.body().string()。下次再执行会抛出流关闭异常，因此需要一个对象存储返回结果
            String responseMessage = response.body().string();
            log.debug("获取accessToken成功");
            JSONObject jsonObject = JSON.parseObject(responseMessage);
            accessToken = (String) jsonObject.get("access_token");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 调用Prompt接口
     * @param param
     */
//    public void getPrompt(int id,String param){
//        Request request = new Request.Builder()
////        https://aip.baidubce.com/rest/2.0/wenxinworkshop/api/v1/template/info?access_token=xxx&id=7964&name=value
//                //https://qianfan.baidubce.com/wenxinworkshop/prompt/template/info
//                .url(CHAT_URI + "?access_token="+accessToken+"&id="+id+"&name="+param)
//                .addHeader("Content-Type", "application/json")
//                .method("GET",null)
//                .build();
//        try {
//            Response responseMessage = client.newCall(request).execute();
//            JSONObject jsonObject = JSON.parseObject(responseMessage.body().string());
//            log.debug(jsonObject.toString());
//            Object result = jsonObject.get("result");
//            log.debug("{}",result.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}