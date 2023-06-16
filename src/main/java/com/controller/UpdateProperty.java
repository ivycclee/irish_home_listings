package com.controller;

import com.model.Property;
import com.model.PropertyDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "UpdateProperty", value = "/UpdateProperty")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB

public class UpdateProperty extends HttpServlet {
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

            Property p = (Property) req.getSession().getAttribute("propertyToEdit");

            System.out.println("Property before change: " + p);

            if(req.getParameter("street") != null) { p.setStreet(req.getParameter("street")); }
            if(req.getParameter("city") != null) { p.setCity(req.getParameter("city")); };
            if(req.getParameter("style") != null) { p.setStyleId(Integer.parseInt(req.getParameter("style"))); }
            if(req.getParameter("type") != null) { p.setTypeId(Integer.parseInt(req.getParameter("type"))); }
            if(req.getParameter("bedrooms") != null) { p.setBedrooms(Integer.parseInt(req.getParameter("bedrooms"))); }
            if(req.getParameter("bathrooms") != null) { p.setBathrooms(Integer.parseInt(req.getParameter("bathrooms"))); }
            if(req.getParameter("squarefeet") != null) { p.setSquarefeet(Integer.parseInt(req.getParameter("squarefeet"))); }
            if(req.getParameter("ber") != null) { p.setBerRating(req.getParameter("berRating")); }
            if(req.getParameter("description") != null) { p.setDescription(req.getParameter("description")); }
            if(req.getParameter("lotsize") != null) { p.setLotsize(req.getParameter("lotsize")); }
            if(req.getParameter("garagesize") != null) { p.setGaragesize(Integer.parseInt(req.getParameter("garagesize"))); }
            if(req.getParameter("garagetype") != null) { p.setGarageId(Integer.parseInt(req.getParameter("garagetype"))); }
            if(req.getParameter("price") != null) { p.setPrice(Double.parseDouble(req.getParameter("price"))); }
            if(req.getParameter("vendor") != null) { req.getParameter("vendor"); }

            System.out.println("Property after change: " + p);

            db.updateProperty(p);

            //add images if some are uploaded;

            int imageCount = 0;
            String savePath = getServletContext().getRealPath("assets/images/properties/large/"+p.getListingNum());
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
                System.out.println("Directory created for listing num " + p.getListingNum());
            }

            for(Part part : req.getParts()){

                if(part.getContentType() != null) {
                    String filename = imageCount == 0 ? Integer.toString(p.getListingNum()) + ".JPG" : Integer.toString(p.getListingNum()) + "_" + imageCount + ".JPG";
                    part.write(savePath + File.separator + filename);
                    imageCount++;
                }
            }

            next = "/ShowPropertyByAgent";

        }catch (Exception e) {
            System.out.println(e);
            next = "/error.jsp";
        }

        req.getRequestDispatcher(next).forward(req, resp);
    }

}
