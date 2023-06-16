package com.controller;

import com.model.Vendor;
import com.model.VendorDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditVendor", value = "/EditVendor")
public class EditVendor extends HttpServlet {
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

        //get vendor id from query param
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Vendor v = new VendorDB().getById(id);

            if (v != null) {
                req.getSession().setAttribute("vendorToEdit", v);
                next = "/EditVendor.jsp";
            }
            else {
                next = "/error.jsp";
            }
        }catch (Exception e) {
            System.out.println(e);
            next = "/error.jsp";
        }
        req.getRequestDispatcher(next).forward(req, resp);
    }
}
