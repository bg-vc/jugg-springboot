package com.jugg.vince.springboot.common.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

import com.jugg.vince.springboot.common.core.ServiceException;
import com.jugg.vince.springboot.common.enums.ResultCode;
import com.jugg.vince.springboot.common.model.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
/**
 * Author:   Vince
 * Date:     2018/7/25 上午12:22
 * Description:
 */
@Slf4j
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter{

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,//保留空的字段
                SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
                SerializerFeature.WriteNullNumberAsZero);//Number null -> 0
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result<String> result = new Result<String>();
                if (e instanceof ServiceException) {
                    log.info("ServiceException:{}", e.getMessage());
                    result.setResult(ResultCode.FAIL).setMessage(e.getMessage());
                } else if (e instanceof NoHandlerFoundException) {
                    log.info("NoHandlerFoundException:{}", e.getMessage());
                    result.setResult(ResultCode.NOT_FOUND).setMessage("interface [" + request.getRequestURI() + "] not exist");
                } else if (e instanceof ServletException) {
                    log.info("ServletException:{}", e.getMessage());
                    result.setResult(ResultCode.FAIL).setMessage(e.getMessage());
                } else {
                    log.info("Exception:{}", e.getMessage());
                    result.setResult(ResultCode.INTERNAL_SERVER_ERROR).setMessage("interface [" + request.getRequestURI() + "] internal error");
                }
                responseResult(response, result);
                return new ModelAndView();
            }
        });
    }

    public static void responseResult(HttpServletResponse response, Result<String> result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        PrintWriter out = null;
        try {
            response.reset();
            out = response.getWriter();
            out.print(JSON.toJSONString(result));
            out.flush();
        } catch (IOException e) {
            log.error("responseResult error:{}", e.getMessage());
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }

}

