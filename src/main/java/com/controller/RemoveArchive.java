package com.controller;

import com.model.Archive;
import com.model.ArchiveDB;
import com.model.Property;
import com.model.PropertyDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveArchive", value = "/RemoveArchive")
public class RemoveArchive extends HttpServlet {
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
        Property p;
        Boolean insertPropertySuccess, removeFromArchiveSuccess;

        try {
            int listingNum = Integer.parseInt(req.getParameter("listingNum"));

            //check if property exists in archive table using listingNum
            Archive a = new ArchiveDB().findByListingNum(listingNum);

            //if a exists, convert to property to insert into property table
            if (a != null) {
                p = new Property(a.getStreet(), a.getCity(), a.getListingNum(), a.getStyleId(), a.getTypeId(), a.getBedrooms(), a.getBathrooms(), a.getSquarefeet(), a.getBerRating(), a.getDescription(), a.getStreet(), a.getGaragesize(), a.getGarageId(), a.getAgentId(), a.getPrice(), a.getDateAdded(), a.getVendorId());

                insertPropertySuccess = new PropertyDB().insertProperty(p);

                //if successfully inserted back into property table, remove from archive table
                if (insertPropertySuccess) {
                    System.out.println("Insert property query: " + insertPropertySuccess);
                    removeFromArchiveSuccess = new ArchiveDB().removeFromArchive(a.getId());

                    if (removeFromArchiveSuccess) {
                        System.out.println("Remove from archive query: " + removeFromArchiveSuccess);
                        next = "/ShowPropertyByAgent";
                    } else {
                        next = "/error.jsp";
                    }
                } else {
                    next = "/error.jsp";
                }
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