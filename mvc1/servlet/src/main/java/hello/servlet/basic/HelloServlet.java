package hello.servlet.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//name:서블릿 명, urlPatterns:URL매핑
//name과 urlPatterns의 이름은 겹치면 안 된다
@WebServlet(name="helloServlet",urlPatterns = "/hello")
public class HelloServlet extends HttpServlet{ //서블릿을 이용하기 위해서는 HttpServlet을 상속받아야 함
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println(request);
        System.out.println(response);

        //쿼리파라미터 중 username을 읽어옴
        String username = request.getParameter("username");
        System.out.println(username);

        //응답메시지 던지기
        response.setContentType("text/plain"); //http헤더정보,단순문자를 보냄
        response.setCharacterEncoding("utf-8"); //http헤더정보,문자 인코딩 정보를 날림
        response.getWriter().write("hello "+username); //http 바디내용
    }
}
