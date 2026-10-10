package com.contactmanager.servlet;

import com.contactmanager.model.Contact;
import com.contactmanager.util.ContactValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/contacts/add")
public class AddContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/contact-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(ContactServlet.CSRF_KEY) == null
                || !session.getAttribute(ContactServlet.CSRF_KEY)
                    .equals(request.getParameter("csrfToken"))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid or expired form token. Please reload the form.");
            return;
        }

        String name = clean(request.getParameter("name"));
        String email = clean(request.getParameter("email"));
        String phone = clean(request.getParameter("phone"));

        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);

        Map<String, String> errors = ContactValidator.validate(name, email, phone);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/views/contact-form.jsp").forward(request, response);
            return;
        }

        @SuppressWarnings("unchecked")
        List<Contact> contacts = (List<Contact>) session.getAttribute(ContactServlet.CONTACTS_KEY);
        if (contacts == null) {
            contacts = new ArrayList<>();
            session.setAttribute(ContactServlet.CONTACTS_KEY, contacts);
        }

        int nextId = contacts.stream().mapToInt(Contact::getId).max().orElse(0) + 1;
        contacts.add(new Contact(nextId, name, email, phone == null ? "" : phone));

        // Post/Redirect/Get avoids accidental resubmission on refresh.
        session.setAttribute("flashMessage", "Contact added successfully.");
        response.sendRedirect(request.getContextPath() + "/contacts");
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
