package com.controller;

import com.model.Agent;
import com.model.Vendor;
import com.model.VendorDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewVendors", value = "/ViewVendors")
public class ViewVendors extends HttpServlet {
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
            //get vendors associated with agent that is logged in
            Agent a = (Agent) req.getSession().getAttribute("agent");

            List<Vendor> vendors = new VendorDB().getByAgentId(a.getAgentId());
//            System.out.println(vendors);

            req.getSession().setAttribute("associatedVendors", vendors);

            next = "/ViewVendors.jsp";

        }catch (Exception e) {
            System.out.println(e);
            next = "/error.jsp";
        }

        req.getRequestDispatcher(next).forward(req, resp);
    }
}
