package com.ruoyi.framework.interceptor;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.filter.RepeatedlyRequestWrapper;
import com.ruoyi.common.utils.http.HttpHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Component
public class FilterAddMi implements Filter {

    public FilterConfig config;

    private static Field requestField;

    private static Field parametersParsedField;

    private static Field coyoteRequestField;

    private static Field parametersField;

    private static Field hashTabArrField;

    private static String REQUEST_METOOD = "POST";

    private Field[] declaredFields = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器 解密开始.....");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        log.info("-------{}", parameterMap);
        String str = "";
        String nowParams = "";
        if (servletRequest instanceof RepeatedlyRequestWrapper) {
            RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper) servletRequest;
            nowParams = HttpHelper.getBodyString(repeatedlyRequest);
            JSONObject parse = JSONObject.parse(nowParams);
            str = parse.getString("str");
        }
        BodyRequestWrapper parameterRequestWrapper = new BodyRequestWrapper(httpServletRequest);
        if (!ObjectUtils.isEmpty(str)) {
            byte[] decode = Base64.getDecoder().decode(str);
            String termq = new String(decode);
//            Map maps = (Map) JSON.parse(termq);
            parameterRequestWrapper.setBody(termq.getBytes());
        }
        filterChain.doFilter(parameterRequestWrapper, servletResponse);

        System.out.println("过滤器 解密完成.....");
    }
}


class BodyRequestWrapper extends HttpServletRequestWrapper {


    private byte[] requestBody = null;

    public BodyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream in = request.getInputStream();
        in.available();
        requestBody = StreamUtils.copyToByteArray(in);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public void setBody(byte[] requestBody) {
        this.requestBody = requestBody;
    }
}

