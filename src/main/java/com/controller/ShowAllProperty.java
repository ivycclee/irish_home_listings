package com.controller;

import com.model.Note;
import com.model.NoteDB;
import com.model.Property;
import com.model.PropertyDB;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowAllProperty", value = "/ShowAllProperty")
public class ShowAllProperty extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String next;

        try {
            PropertyDB db = new PropertyDB();
            List<Property> list = db.getAll();

            List<Note> allnotes = new NoteDB().getAll();

            if (!allnotes.isEmpty()) {
                req.getSession().setAttribute("allnotes", allnotes);
            }

            if (list == null || list.isEmpty()) {
                next = "/error.jsp";
            } else {
                req.setAttribute("allProperties", list);
                next = "/ShowAllListings.jsp";
            }
        }catch(Exception e) {
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
