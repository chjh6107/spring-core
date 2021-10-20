package hello.servlet.basic.request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.StreamUtils;

import hello.servlet.basic.HelloData;

@WebServlet(name = "requestBodyJsonServlet",urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet{

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();//바디데이터 파싱하여 스트링으로 바꿔주는 과정
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = "+messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); //messageBody에 들어온 json데이터를 parsing하여 HelloData클래스로 바로 변환해줌

        System.out.println("helloData.username = "+helloData.getUsername());
        System.out.println("helloData.getAge = "+helloData.getAge());

        response.getWriter().write("ok");
    }
}
