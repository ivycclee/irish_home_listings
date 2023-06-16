package com.controller;

import com.model.Agent;
import com.model.Property;
import com.model.PropertyDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ShowPropertyByAgent", value = "/ShowPropertyByAgent")
public class ShowPropertyByAgent extends HttpServlet {
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
            PropertyDB db = new PropertyDB();

            List<String> ber = Arrays.asList("", "A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3", "D1", "D2", "E1", "E2", "F", "G");
            req.getSession().setAttribute("ber", ber);

            //find agent object stored in session
            Agent a = (Agent) req.getSession().getAttribute("agent");

            List<Property> list = db.getPropertyByAgentID(a.getAgentId());

            if (!list.isEmpty())
            {
                req.getSession().setAttribute("propertiesByAgentID", list);
                next = "/ManageMyProperties.jsp";
            }
            else { next = "/error.jsp"; }

        }catch(Exception e) {
            System.out.println(e);
            next = "/error.jsp";
        }
        req.getRequestDispatcher(next).forward(req, resp);
    }
}
