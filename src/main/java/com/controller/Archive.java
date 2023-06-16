package com.controller;

import com.model.ArchiveDB;
import com.model.NoteDB;
import com.model.Property;
import com.model.PropertyDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Archive", value = "/Archive")
public class Archive extends HttpServlet {
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
        Boolean archiveSuccess, noteDeleteSuccess, deleteSuccess;
//
        try {
            //grab property id from req
            int propertyId = Integer.parseInt(req.getParameter("id"));
//            System.out.println(propertyId);

            archiveSuccess = new ArchiveDB().archiveProperty(propertyId);
            System.out.println("Archive query: " + archiveSuccess);

            if (archiveSuccess) {
                //need to delete note in order to delete from property table
                NoteDB ndb = new NoteDB();
                Property p = new PropertyDB().getByID(propertyId).get();
                int pListingNum = p.getListingNum();

                noteDeleteSuccess = ndb.deleteNoteByListingNum(pListingNum);

                if (noteDeleteSuccess) {
                    deleteSuccess = new PropertyDB().deleteProperty(propertyId);
                    System.out.println("Note delete query: " + deleteSuccess);


                    PropertyDB pdb = new PropertyDB();
                    deleteSuccess = pdb.deleteProperty(p);

                    if (deleteSuccess) {
                        System.out.println("Delete query: " + deleteSuccess);
                        next = "/ShowPropertyByAgent";
                    } else {
                        next = "/error.jsp";
                    }
                }
                else {
                    next = "/error.jsp";
                }
            }
            else { next = "/error.jsp"; }



        }catch(Exception e) {
            System.out.println(e);
            next = "/error.jsp";
        }

        req.getRequestDispatcher(next).forward(req, resp);
    }
}
