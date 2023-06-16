package com.controller;

import com.model.Agent;
import com.model.ArchiveDB;
import com.model.Archive;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowArchivedByAgent", value = "/ShowArchivedByAgent")
public class ShowArchivedByAgent extends HttpServlet {
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
            //fetch agent id from session obj
            Agent a = (Agent) req.getSession().getAttribute("agent");

            List<Archive> list = new ArchiveDB().getArchivedByAgentID(a.getAgentId());

            if(!list.isEmpty())
            {
                req.setAttribute("archivedByAgentID", list);
                next = "/ShowArchived.jsp";
            }
            else { next = "/error.jsp"; }

        } catch (Exception e) {
            System.out.println(e);
            next = "/error.jsp";
        }
        req.getRequestDispatcher(next).forward(req, resp);
    }
}
