package com.controller;

import com.model.Agent;
import com.model.AgentDB;
import com.model.Property;
import com.model.PropertyDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "PropertyDrilldown", value = "/PropertyDrilldown")
public class PropertyDrilldown extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String next;
        int propertyID;

        /*
        "PropertyDrilldown?id=${p.id}" -> get id from this
        */
        try {
            propertyID = Integer.parseInt(req.getParameter("id"));


            PropertyDB db = new PropertyDB();
            Optional<Property> opt = db.getByID(propertyID);
            Property p = null;

            if (opt.isPresent()) { p = opt.get(); }

            if (p == null) {
                next = "/error.jsp";
            } else {
                next = "/ViewProperty.jsp";
                req.setAttribute("property", p);

                //getting correct path for images folder
                //System.out.println(req.getServletContext().getRealPath("/assets/images/properties/large/".concat(p.getListingNum().toString())));
                String path = req.getServletContext().getRealPath("/assets/images/properties/large/".concat(String.valueOf(p.getListingNum())));

                //populates array of files from the image dir
                File[] files = new File(path).listFiles();
                //System.out.println("Total num of files: " + files.length);

                List<String> photos = new ArrayList<String>();

                if (files.length > 0)
                {
                    for (File f : files)
                    {
                        photos.add(f.getName());
                        //System.out.println(f.getName());
                    }

                    req.setAttribute("photos", photos);
                }

                //need to populate agent object to view agent details
                Agent a = new AgentDB().getById(p.getAgentId());

                if (a != null) {
                    req.getSession().setAttribute("agentForThisProperty", a);
                }

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
