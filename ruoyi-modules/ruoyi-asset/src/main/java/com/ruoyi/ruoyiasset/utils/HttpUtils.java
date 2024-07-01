package com.ruoyi.ruoyiasset.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.ruoyiasset.config.ConstantConfig;
import com.ruoyi.ruoyiasset.domain.Asset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;

@Component
public class HttpUtils {
    @Autowired
    ConstantConfig constantConfig;
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);


    /**
     * 处理json对象
     *
     * @param jsonString json字符串
     * @return asset
     */
    public Asset handleJson(String jsonString, String symbol) {
        Asset asset = new Asset();
        try {
            JSONObject jsonObject = JSON.parseObject(jsonString);
            JSONArray resultArray = jsonObject.getJSONObject("chart").getJSONArray("result");
            JSONObject priceObject = resultArray.getJSONObject(0).getJSONObject("meta");

            // Extract the values
            String currency = priceObject.getString("currency");
            BigDecimal regularMarketPrice = priceObject.getBigDecimal("regularMarketPrice");
            asset.setLastPrice(regularMarketPrice);
            asset.setCurrency(currency);
            asset.setSymbol(symbol);
        } catch (Exception e) {
            log.error("handleJson 失败", e);
            return null;
        }
        return asset;
    }

    /**
     * 发送get请求
     *
     * @param symbol 代号
     * @return asset
     */
    public Asset sendGetMsg(String symbol) {
        // 创建 RestTemplate 对象
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory(constantConfig.getProxyHost(), constantConfig.getProxyPort()));
        // 发送 GET 请求，并获取响应
        try {
            String jsonString = restTemplate.getForEntity(constantConfig.getSymbolUrl() + symbol, String.class).getBody();
            return handleJson(jsonString, symbol);
        } catch (Exception e) {
            log.error("sendGetMsg 发送失败", e);
            return null;
        }
    }

    /**
     * 得到httpclient工厂
     *
     * @param proxyHost 代理地址
     * @param proxyPort 代理端口
     * @return http代理对象
     */
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory(String proxyHost, int proxyPort) {
        // 创建代理对象
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        // 创建 SimpleClientHttpRequestFactory 对象
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        // 设置代理
        clientHttpRequestFactory.setProxy(proxy);
        return clientHttpRequestFactory;
    }
}
