package hello.servlet.basic.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "responseHeaderServlet",urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line] 응답의 첫번째 라인
        response.setStatus(HttpServletResponse.SC_OK);
        
        //[response-headers] 이거는 밑 content메서드가 대체 가능
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        //캐시 완전 무효화
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        //과거 캐시까지 모두 무효화
        response.setHeader("Pragma","no-cache");
        //커스텀헤더
        response.setHeader("my-header","hello");

        //[Header의 편의 메서드]
//        content(response);
        // cookie(response);
        redirect(response);
        PrintWriter writer = response.getWriter();
        writer.print("ok");
    }

    private void content(HttpServletResponse response){
//        Content-Type: text/plain;charset=utf-8
//        Content-Length: 2
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//        response.setContentLength(2); //(생략 시 자동 생성)

    }
    
    private void cookie(HttpServletResponse response){
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie","myCookie=good;Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException{
        //Statis Code 302
        //Locatio: /basic/hello-form.html

//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html");
        //더 편하게 보내기
        response.sendRedirect("/basic/hello-form.html");
    }
}
