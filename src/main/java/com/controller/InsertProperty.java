package com.controller;

import com.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "InsertProperty", value = "/InsertProperty")

public class InsertProperty extends HttpServlet {
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
            List<Style> styles = new ArrayList<>();
            List<PropertyType> pTypes = new ArrayList<>();
            List<String> ber = Arrays.asList("", "A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3", "D1", "D2", "E1", "E2", "F", "G");
            List<GarageType> gTypes = new ArrayList<>();
            List<Vendor> vendors = new ArrayList<>();

            //get list of style IDs for dropdown
            StyleDB sdb = new StyleDB();
            styles = sdb.getAll();

            //get list of propertytype IDs for dropdown
            PropertyTypeDB pdb = new PropertyTypeDB();
            pTypes = pdb.getAll();

            //get list of garage type IDs for dropdown
            GarageTypeDB gdb = new GarageTypeDB();
            gTypes = gdb.getAll();

            //get list of vendor IDs for dropdown
            VendorDB vdb = new VendorDB();
            vendors = vdb.getAll();

            req.getSession().setAttribute("styles", styles);
            req.getSession().setAttribute("propertyTypes", pTypes);
            req.getSession().setAttribute("ber", ber);
            req.getSession().setAttribute("garageTypes", gTypes);
            req.getSession().setAttribute("vendors", vendors);

            next = "/InsertProperty.jsp";

        }catch (Exception e) {
            System.out.println(e);
            next = "/error.jsp";
        }

        req.getRequestDispatcher(next).forward(req, resp);
    }
}
