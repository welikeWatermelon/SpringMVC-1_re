package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        //[response-line]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        System.out.println("기타등등");
    }
}
