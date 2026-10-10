package com.contactmanager.servlet;

import com.contactmanager.model.Contact;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/contacts/delete")
public class DeleteContactServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(ContactServlet.CSRF_KEY) == null
                || !session.getAttribute(ContactServlet.CSRF_KEY)
                    .equals(request.getParameter("csrfToken"))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid or expired form token. Please reload the page.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException exception) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid contact ID.");
            return;
        }

        @SuppressWarnings("unchecked")
        List<Contact> contacts = (List<Contact>) session.getAttribute(ContactServlet.CONTACTS_KEY);
        if (contacts != null) {
            Iterator<Contact> iterator = contacts.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getId() == id) {
                    iterator.remove();
                    session.setAttribute("flashMessage", "Contact deleted.");
                    break;
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/contacts");
    }
}
