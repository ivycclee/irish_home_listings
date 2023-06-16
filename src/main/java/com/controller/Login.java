package com.controller;

import com.model.Agent;
import com.model.AgentDB;
import com.model.User;
import com.model.UserDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String next;

        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            //check user table first
            UserDB db = new UserDB();
            User user = db.login(email, password);

            if (user == null)
            {
                //no user found, now check agent table in case it's an agent logging in
                AgentDB adb = new AgentDB();
                Agent agent = adb.login(email, password);

                if (agent == null)
                {
                    //no agent (and no user) with those credentials
                    next = "/Login.jsp";
                }
                else
                {
                    //agent found
                    req.getSession().setAttribute("logged_in", "agent");
                    req.getSession().setAttribute("agent", agent);
                    next = "/index.jsp";
                }

            }
            else {
                //user found
                next = "/index.jsp";
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("logged_in", "user");

//                System.out.println(req.getSession().getAttribute("logged_in"));
            }

        } catch (Exception e) {
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
