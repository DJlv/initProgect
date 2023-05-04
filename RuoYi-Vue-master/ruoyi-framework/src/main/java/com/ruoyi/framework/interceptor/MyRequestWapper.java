package com.ruoyi.framework.interceptor;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MyRequestWapper extends HttpServletRequestWrapper {


    //请求数据
    private String body;
    //请求二进制数据
    private byte[] bytes;


    public MyRequestWapper(HttpServletRequest request) {
        super(request);

        try (ServletInputStream inputStream = request.getInputStream()){
            bytes = new byte[request.getContentLength()];
            inputStream.read(bytes);

            body = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        //这一步最关键
        //这一步使得后续获取InputStream都是这个对象
        //而在此时我们也把我们自定义的数据塞进去了
        //也就是说后续处理中获取到的参数就是我们此时塞进去的数据
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ServletInputStream inputStream = new ServletInputStream() {
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

            //这里流的读取，就是从我们自定义流中读取数据
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return inputStream;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
