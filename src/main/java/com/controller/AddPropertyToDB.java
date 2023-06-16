package com.controller;

import com.model.Agent;
import com.model.Property;
import com.model.PropertyDB;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet(name = "AddPropertyToDB", value = "/AddPropertyToDB")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB

public class AddPropertyToDB extends HttpServlet {
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

            //need agentID for constructor
            Agent a = (Agent) req.getSession().getAttribute("agent");
            int agentId = a.getAgentId();

            //need today's date for constructor
            Date date = new Date();

            Property p = new Property(
                    req.getParameter("street"),
                    req.getParameter("city"),
                    Integer.valueOf(req.getParameter("listingNum")),
                    Integer.valueOf(req.getParameter("style")),
                    Integer.valueOf(req.getParameter("type")),
                    !req.getParameter("bedrooms").isEmpty() ? Integer.valueOf(req.getParameter("bedrooms")) : 0,
                    !req.getParameter("bathrooms").isEmpty() ? Integer.valueOf(req.getParameter("bathrooms")) : 0,
                    !req.getParameter("squarefeet").isEmpty() ? Integer.valueOf(req.getParameter("squarefeet")) : 0,
                    req.getParameter("berRating"),
                    req.getParameter("description"),
                    !req.getParameter("lotsize").isEmpty() ? req.getParameter("lotsize") : "",
                    !req.getParameter("garagesize").isEmpty() ? Integer.valueOf(req.getParameter("garagesize")) : 0,
                    Integer.valueOf(req.getParameter("garagetype")),
                    agentId,
                    Double.parseDouble(req.getParameter("price")),
                    date,
                    Integer.valueOf(req.getParameter("vendor"))
            );
            System.out.println(p);

            Boolean success = db.insertProperty(p);

            //if successfully added to db, add images & create thumbnails
            if (success)
            {
                String listingNum = req.getParameter("listingNum");

                int imageCount = 0;
                String savePath = getServletContext().getRealPath("assets/images/properties/large/"+listingNum);
                File fileSaveDir = new File(savePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                    System.out.println("Directory created for listing num " + listingNum);
                }

                for (Part part : req.getParts()) {
                    if (part.getContentType() != null) {
                        String filename = imageCount == 0 ? listingNum + ".JPG" : listingNum + "_" + imageCount + ".JPG";
                        part.write(savePath + File.separator + filename);
                        if(imageCount==0)
                        {
                            BufferedImage bufferedImage = ImageIO.read(new File(getServletContext().getRealPath("assets/images/properties/large/"+ listingNum + "/" + listingNum + ".JPG")));
                            BufferedImage bufferedImageResult = new BufferedImage(
                                    75,
                                    50,
                                    bufferedImage.getType()
                            );
                            Graphics2D graphics2D = bufferedImageResult.createGraphics();
                            graphics2D.drawImage(bufferedImage, 0, 0, 75, 50, null);
                            graphics2D.dispose();
                            ImageIO.write(bufferedImageResult, "jpg", new File(getServletContext().getRealPath("assets/images/properties/thumbs/"+ listingNum + ".jpg")));
                        }
                        imageCount++;
                    }
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
