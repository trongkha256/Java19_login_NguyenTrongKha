package com.login.controller;

import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginPage",urlPatterns = "/login")
public class LoginPage extends HttpServlet {
    private LoginService loginService=new LoginService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        session.setAttribute("email",email);
        session.setAttribute("password",password);
        session.setMaxInactiveInterval(5*60*60*60);
        boolean isLogin=loginService.checkLogin(email,password);
        if (isLogin){
            resp.sendRedirect(req.getContextPath()+"/welcome?email="+email);
        }
        else{
            req.getRequestDispatcher("webapp/login.html").forward(req,resp);
        }
        req.getRequestDispatcher("/webapp/login.html").forward(req,resp);
    }
}
