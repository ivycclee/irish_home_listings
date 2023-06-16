package com.controller;

import com.model.Property;
import com.model.PropertyDB;
import com.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ViewFavourites", value = "/ViewFavourites")
public class ViewFavourites extends HttpServlet {
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
        List<Property> favourites = new ArrayList<>();

        try {
            //populate user object in order to get userId
            User u = (User) req.getSession().getAttribute("user");

            //get all cookies
            Cookie[] cookies = req.getCookies();
//            Arrays.stream(cookies).forEach(cookie -> System.out.println(cookie.getName() + " " + cookie.getValue()));

            String cookieValue = "";

            //check if any cookies are the user's cookies
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals(String.valueOf(u.getUserId()))) {
                        cookieValue = c.getValue();
                    }
                }
            }

            //separate cookievalue string into array of property ids
            if (cookieValue != "") {
                int[] propertyIds = Arrays.stream(cookieValue.split("-")).mapToInt(Integer::parseInt).toArray();

                for (int i : propertyIds) {
                    new PropertyDB().getByID(i).ifPresent(favourites::add);
                }
            }

//            System.out.println(favourites.toString());

            if (!favourites.isEmpty()) {
                req.getSession().setAttribute("favourites", favourites);
            }

            next = "/ViewFavourites.jsp";

        }catch (Exception e) {
            next = "/error.jsp";
        }

        req.getRequestDispatcher(next).forward(req, resp);
    }
}
