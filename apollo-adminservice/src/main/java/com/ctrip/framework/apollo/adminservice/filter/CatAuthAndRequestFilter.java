package com.ctrip.framework.apollo.adminservice.filter;

import com.alidaodao.app.Cat;
import com.alidaodao.app.servlet.CatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Jack
 * @since 2021-02-23
 */
@Configuration
public class CatAuthAndRequestFilter {

    /**
     * 每个项目的domain都是不同的
     * 所以不要从Apollo中读取
     * 这里使用的Apollo的app.id
     */
    @Value("${cat.app.id}")
    private String domain;

    /**
     * CAT服务端的端口，从Apollo中读取
     */
    @Value("${cat.server.port}")
    private int port;

    /**
     * CAT服务端的HTTP端口，从Apollo中读取
     */
    @Value("${cat.server.http.port}")
    private int httpPort;

    /**
     * CAT服务端的IP列表，多个以逗号分隔，从Apollo中读取
     */
    @Value("${cat.server.servers}")
    private String servers;
    private static final Logger log = LoggerFactory.getLogger(CatAuthAndRequestFilter.class);
    /**
     * 初始化CAT客户端
     */
    @PostConstruct
    public void initCat() {
        try {
            Cat.initializeByDomain(domain, port, httpPort, servers.split(","));
            System.out.println("start cat server");
        } catch (Exception e) {
            log.error("Initialization of CAT client failed", e);
        }
    }


    @Bean
    public FilterRegistrationBean<CatFilter> catFilter() {
        FilterRegistrationBean<CatFilter> registration = new FilterRegistrationBean<>();
        CatFilter filter = new CatFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("cat-filter");
        registration.setOrder(1);
        return registration;
    }
}
