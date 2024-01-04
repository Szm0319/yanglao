package com.example.yanglao.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WechatPayService {

    private final String appId = "YOUR_APP_ID";
    private final String mchId = "YOUR_MERCHANT_ID";
    private final String apiKey = "YOUR_API_KEY";
    private final String notifyUrl = "YOUR_NOTIFY_URL";

    private String generateSign(Map<String, String> data) throws NoSuchAlgorithmException {
        String stringA = data.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty() && !entry.getKey().equals("sign"))
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        String stringSignTemp = stringA + "&key=" + apiKey;
        MessageDigest crypt = MessageDigest.getInstance("MD5");
        crypt.reset();
        crypt.update(stringSignTemp.getBytes());
        return byteToHex(crypt.digest());
    }

    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result.toUpperCase();
    }

    private String callWechatPayApi(Map<String, String> data) throws Exception {
        // 将数据转换为XML格式
        String xmlData = "<xml>" + data.entrySet().stream()
                .map(entry -> "<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">")
                .collect(Collectors.joining()) + "</xml>";

        // 发送请求到微信支付API
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlData, String.class);

        // 创建一个用于解析XML的DocumentBuilderFactory实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 解析XML响应字符串，创建一个Document对象
        Document doc = builder.parse(new InputSource(new StringReader(response)));
        // 获取XML文档的根元素
        Element root = doc.getDocumentElement();
        // 从根元素中获取名为"return_code"的子元素的文本内容
        String returnCode = root.getElementsByTagName("return_code").item(0).getTextContent();
         // 检查返回码是否为"SUCCESS"
        if ("SUCCESS".equals(returnCode)) {
            // 如果返回码为"SUCCESS"，则继续获取名为"result_code"的子元素的文本内容
            String resultCode = root.getElementsByTagName("result_code").item(0).getTextContent();
            // 检查结果码是否也为"SUCCESS"
            if ("SUCCESS".equals(resultCode)) {
                // 如果结果码也为"SUCCESS"，则获取名为"prepay_id"的子元素的文本内容
                // 这个"prepay_id"是发起微信支付所需的关键信息
                return root.getElementsByTagName("prepay_id").item(0).getTextContent();
            }
        }
        return null;
    }

    public String createWechatPayOrder(String orderId, int totalFee, String openId) throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("appid", appId);
        data.put("mch_id", mchId);
        data.put("nonce_str", "RANDOM_STRING");
        data.put("body", "Product Description");
        data.put("out_trade_no", orderId);
        data.put("total_fee", String.valueOf(totalFee));
        data.put("spbill_create_ip", "CLIENT_IP");
        data.put("notify_url", notifyUrl);
        data.put("trade_type", "JSAPI");
        data.put("openid", openId);
        data.put("sign", generateSign(data));

        return callWechatPayApi(data);
    }
}

