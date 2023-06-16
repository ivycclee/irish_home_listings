package com.controller;

import com.model.Note;
import com.model.NoteDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddNote", value = "/AddNote")
public class AddNote extends HttpServlet {
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
            Note newNote = new Note((Integer) req.getSession().getAttribute("newNoteUserID"), (Integer) req.getSession().getAttribute("newNoteListNum"), req.getParameter("add"));
            new NoteDB().addNote(newNote);

            next = "PropertyDrilldown?id=".concat(req.getSession().getAttribute("propId").toString());
            resp.sendRedirect(next);

        }catch (Exception e) {
            System.out.println(e);
            next = "/error.jsp";
        }

//        req.getRequestDispatcher(next).forward(req, resp);
    }
}
