package com.controller;

import com.model.Note;
import com.model.NoteDB;
import com.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindNote", value = "/FindNote")
public class FindNote extends HttpServlet {
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

        // this needs to be cleared in case it was populated earlier
        req.getSession().removeAttribute("noteToEdit");
        req.getSession().removeAttribute("newNoteListNum");


        try {
            //check if note(s) exist for current property
            //1. get propertyID from req
            int listingNo = Integer.parseInt(req.getParameter("listingNum"));
            int propertyId = Integer.parseInt(req.getParameter("id"));
            System.out.println("listNo: " + listingNo);

            //2. get userID from session obj
            User u = (User) req.getSession().getAttribute("user");
            int userID = u.getUserId();
            System.out.println("userID: " + userID);

            //3. check db
            NoteDB db = new NoteDB();
            Note n = db.findNote(userID, listingNo);

            if (n != null) {
                System.out.printf(n.getNote());
                req.getSession().setAttribute("noteToEdit", n);
                req.getSession().setAttribute("propId", propertyId);
            }
            else {
                req.getSession().setAttribute("newNoteListNum", listingNo);
                req.getSession().setAttribute("newNoteUserID", userID);
                req.getSession().setAttribute("propId", propertyId);
            }

            next = "/EditNote.jsp";

        } catch (Exception e) {
            System.out.println(e);
            next = "/error.jsp";
        }

        req.getRequestDispatcher(next).forward(req, resp);
    }
}
