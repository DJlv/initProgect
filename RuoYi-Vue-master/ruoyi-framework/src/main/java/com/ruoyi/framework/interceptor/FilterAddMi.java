package com.ruoyi.framework.interceptor;


import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.filter.RepeatedlyRequestWrapper;
import com.ruoyi.common.utils.http.HttpHelper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.JsonAggregateFunction;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceArray;

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
        String str = "";
        String nowParams = "";

        System.out.println("过滤器 解密开始.....");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();

        List<String> strings = Arrays.asList("/system/user/profile/avatar","/dev-api/captchaImage");
        // 过滤提交图片url
        if(strings.contains(httpServletRequest.getRequestURI()) || "image/png".equals(httpServletRequest.getContentType())){
            filterChain.doFilter(servletRequest, servletResponse);
        }

        if("GET".equals(httpServletRequest.getMethod())){
            BodyRequestWrapperPloader bodyRequestWrapperPloader = new BodyRequestWrapperPloader(httpServletRequest);
            Map<String, String[]> parameterMap1 = bodyRequestWrapperPloader.getParameterMap();
            log.info("get---{}",parameterMap1);

            parameterMap1.forEach((key,value)-> {
                HashMap<String, String> objectObjectHashMap = new HashMap<>();
                for (String valuevo : value) {
                    byte[] decode = Base64.getDecoder().decode(valuevo);
                    String  strvo = new String(decode);
                    log.info("解密----{}",strvo);
                    objectObjectHashMap.put("str",strvo);
                }
                bodyRequestWrapperPloader.nullstr(key);
                bodyRequestWrapperPloader.addParameter(key,objectObjectHashMap.get("str"));
            });
            filterChain.doFilter(bodyRequestWrapperPloader, servletResponse);
        } else if("POST".equals(httpServletRequest.getMethod())) {             // Content-Type'] = 'application/json;charset=utf-8'
            BodyRequestWrapperJson parameterRequestWrapperJson = new BodyRequestWrapperJson(httpServletRequest);
            if (servletRequest instanceof RepeatedlyRequestWrapper) {
                RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper) servletRequest;
                nowParams = HttpHelper.getBodyString(repeatedlyRequest);
                JSONObject parse = JSONObject.parse(nowParams);
                str = parse.getString("str");
            }
            if (!ObjectUtils.isEmpty(str)) {
                byte[] decode = Base64.getDecoder().decode(str);
                String termq = new String(decode);
//            Map maps = (Map) JSON.parse(termq);
                parameterRequestWrapperJson.setBody(termq.getBytes());
            }
            filterChain.doFilter(parameterRequestWrapperJson, servletResponse);
        }
        System.out.println("过滤器 解密完成.....");
    }
}


class BodyRequestWrapperJson extends HttpServletRequestWrapper {


    private byte[] requestBody = null;

    public BodyRequestWrapperJson(HttpServletRequest request) throws IOException {
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

class BodyRequestWrapperPloader extends HttpServletRequestWrapper {
    private final Map<String, String[]> modifiedParameters;

    public BodyRequestWrapperPloader(HttpServletRequest request) {
        super(request);
        modifiedParameters = new HashMap<>(request.getParameterMap());
    }

    @Override
    public String getParameter(String name) {
        String[] values = modifiedParameters.get(name);
        if (values != null && values.length > 0) {
            return values[0];
        } else {
            return null;
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Collections.unmodifiableMap(modifiedParameters);
    }
    public void nullstr(String str) {
        String[] strings = null;
        modifiedParameters.put(str,strings) ;
    }
    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(modifiedParameters.keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        return modifiedParameters.get(name);
    }

    public void addParameter(String name, String value) {
        String[] values = modifiedParameters.get(name);
        if (values == null) {
            values = new String[0];
        }
        String[] newValues = Arrays.copyOf(values, values.length + 1);
        newValues[values.length] = value;
        modifiedParameters.put(name, newValues);
    }
}

