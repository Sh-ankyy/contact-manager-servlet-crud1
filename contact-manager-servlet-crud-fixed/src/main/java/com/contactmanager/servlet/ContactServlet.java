package com.contactmanager.servlet;

import com.contactmanager.model.Contact;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {
    public static final String CONTACTS_KEY = "contacts";
    public static final String CSRF_KEY = "csrfToken";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        if (session.getAttribute(CONTACTS_KEY) == null) {
            session.setAttribute(CONTACTS_KEY, new ArrayList<Contact>());
        }
        if (session.getAttribute(CSRF_KEY) == null) {
            session.setAttribute(CSRF_KEY, UUID.randomUUID().toString());
        }

        @SuppressWarnings("unchecked")
        List<Contact> contacts = (List<Contact>) session.getAttribute(CONTACTS_KEY);
        request.setAttribute("contacts", contacts);
        request.getRequestDispatcher("/WEB-INF/views/contact-list.jsp").forward(request, response);
    }
}
