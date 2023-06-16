package com.controller;

import com.model.Agent;
import com.model.Vendor;
import com.model.VendorDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateVendor", value = "/UpdateVendor")
public class UpdateVendor extends HttpServlet {
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
            //get name input
            String name = req.getParameter("name");
            //get phone input
            String phone = req.getParameter("phone");

            //get vendor obj
            Vendor v = (Vendor) req.getSession().getAttribute("vendorToEdit");

            v.setName(name);
            v.setPhone(phone);

            //update vendor in db
            new VendorDB().updateVendor(v);

            Agent a = (Agent) req.getSession().getAttribute("agent");

            next = "/ViewVendors?id=" + a.getAgentId();

        }catch (Exception e) {
            System.out.println(e);
            next = "/error.jsp";
        }

        req.getRequestDispatcher(next).forward(req, resp);
    }
}