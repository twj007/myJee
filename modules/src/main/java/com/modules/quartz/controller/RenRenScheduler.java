//package com.modules.quartz.controller;
//
//import com.common.model.vo.RenRenVo;
//import com.common.utils.ResultBody;
//import com.common.utils.Results;
//import org.apache.http.Header;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.net.URISyntaxException;
//
///***
// **@project: myJee
// **@description:
// **@Author: twj
// **@Date: 2019/12/24
// **/
//@RestController
//public class RenRenScheduler {
//
//    private static final Logger logger = LoggerFactory.getLogger(RenRenScheduler.class);
//
//    @Value("${zimuzu.token_url}")
//    private String tokenUrl;
//    @Value("${zimuzu.username}")
//    private String username;
//    @Value("${zimuzu.password}")
//    private String password;
//    @Value("${zimuzu.activity_url}")
//    private String activityUrl;
//    @Value("${zimuzu.login_url}")
//    private String loginUrl;
//    @Value("${zimuzu.info_url}")
//    private String infoUrl;
//
//    private static final String SET_COOKIE = "Set-Cookie";
//    private static final String PHPSESSID = "PHPSESSID";
//
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//
//    @Autowired
//    private HttpClient httpClient;
//
//    @Value("${zimuzu.python_position}")
//    private String python_position;
//
//
//    @RequestMapping("/sign/py")
//    public ResultBody signPy(){
//        String[] arguments = new String[] {"python", python_position};
//        try {
//            Process process = Runtime.getRuntime().exec(arguments);
//            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
//            StringBuffer sb = new StringBuffer();
//            String line;
//            while ((line = in.readLine()) != null) {
//                sb.append(line);
//                logger.info("[python] 脚本执行输出: {}", line);
//
//            }
//            in.close();
//            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
//            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
//            int re = process.waitFor();
//            if(re == 1){
//                return Results.BAD__REQUEST.result("fail", "脚本执行失败");
//            }else{
//                return Results.SUCCESS.result("success", sb.toString());
//            }
//
//        } catch(Exception e) {
//            e.printStackTrace();
//            return Results.BAD__REQUEST.result("fail", null);
//        }
//
//    }
//
//
//    @RequestMapping("/sign")
//    public ResultBody sign() throws URISyntaxException, IOException {
//        RenRenVo token = restTemplate.getForObject(tokenUrl + "&account="+username+"&password="+password, RenRenVo.class);
//        logger.info("token 为: {}", token);
//
//
////        String json = restTemplate.getForObject(activityUrl + "&uid=" + token.getData().getUid() + "&token=" + token.getData().getToken(), String.class);
////        logger.info("info: {}", json);
//        HttpGet request = new HttpGet();
//        request.setURI(new URI(activityUrl + "&uid=" + token.getData().getUid() + "&token=" + token.getData().getToken()));
//        request.setHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 9; zh-cn; MI 6 Build/9.0) AppleWebKit/533.1 (KHTML, like Gecko) Version/5.0 Mobile Safari/533.1");
//        HttpResponse response = httpClient.execute(request);
//        Header[] headers = response.getHeaders("Set-Cookie");
//        Header phpSession = null;
//        for(int i = 0; i < headers.length; i ++){
//            if(SET_COOKIE.equals(headers[i].getName())){
//                phpSession = headers[i];
//                break;
//            }
//        }
//        if (phpSession == null) {
//            return Results.BAD__REQUEST.result("获取php cookie失败", null);
//        }
//        logger.info("response: {}", response.getEntity());
//        logger.info("header: {}", phpSession.getValue());
//        HttpGet loginRequest = new HttpGet();
//        loginRequest.setHeader("Cookie", phpSession.getValue());
//        loginRequest.setHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 9; zh-cn; MI 6 Build/9.0) AppleWebKit/533.1 (KHTML, like Gecko) Version/5.0 Mobile Safari/533.1");
//        loginRequest.setURI(new URI(loginUrl));
//        HttpResponse response1 = httpClient.execute(loginRequest);
//        if(response1.getStatusLine().getStatusCode() != 200){
//            return Results.BAD__REQUEST.result("登陆失败", null);
//        }
//        HttpGet infoRequest = new HttpGet();
//        infoRequest.setHeader("Cookie", phpSession.getValue().split(";")[0]);
//        infoRequest.setHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 9; zh-cn; MI 6 Build/9.0) AppleWebKit/533.1 (KHTML, like Gecko) Version/5.0 Mobile Safari/533.1");
//        infoRequest.setURI(new URI(infoUrl));
//        HttpResponse response2 = httpClient.execute(infoRequest);
//        logger.info("info: {}", response2.getEntity().getContentLength());
//        return Results.SUCCESS.result("success", null);
//    }
//}
