package com.controller;

import com.model.User;
import com.model.UserDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String next;

        try {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");

            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");

            //make sure passwords match
            if (password.equals(confirmPassword)) {
                try {
                    UserDB check = new UserDB();

                    //check if email exists already in user db
                    Boolean userExists = check.findByEmail(email);

                    if (userExists)
                    {
                        System.out.println("User exists");
                        next = "/Login.jsp";
                    }
                    else {
                        UserDB db = new UserDB();
                        Boolean success = db.addUser(new User(firstName, lastName, email, password));

                        if (success) {
                            next = "/success.jsp";
                        } else {
                            next = "/error.jsp";
                        }
                    }

                } catch (Exception e) {
                    next = "/error.jsp";
                }
            } else {
                next = "/error.jsp";
            }

        }catch (Exception e) {
            next = "/error.jsp";
        }

        req.getRequestDispatcher(next).forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
