package com.controller;

import com.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "RemoveFromFavourites", value = "/RemoveFromFavourites")
public class RemoveFromFavourites extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String next;

        try {
            //get user id
            User u = (User) req.getSession().getAttribute("user");

            //get prop id from req
            String propId = req.getParameter("id").concat("-");

            //get user cookie
            Cookie[] cookies = req.getCookies();
            String value = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(String.valueOf(u.getUserId()))).findFirst().get().getValue();


            //remove substring
            value = value.replace(propId, "");

            //set new cookie value
            resp.addCookie(new Cookie(String.valueOf(u.getUserId()), value));


            next = "ViewFavourites";

            resp.sendRedirect(next);

        }catch (Exception e){
            System.out.println(e);
            next = "/error.jsp";
            req.getRequestDispatcher(next).forward(req, resp);
        }

    }
}
