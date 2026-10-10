<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Contacts | Contact Manager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/contacts">Contact Manager</a>
    <nav><a href="${pageContext.request.contextPath}/contacts">Contacts</a></nav>
</header>
<main class="container">
    <section class="page-heading">
        <div>
            <p class="eyebrow">PERSONAL PORTAL</p>
            <h1>Your contacts</h1>
            <p class="muted">View and manage contacts stored in your current session.</p>
        </div>
        <a class="button" href="${pageContext.request.contextPath}/contacts/add">+ Add contact</a>
    </section>

    <c:if test="${not empty sessionScope.flashMessage}">
        <div class="alert alert-success" role="status">
            <c:out value="${sessionScope.flashMessage}"/>
        </div>
        <c:remove var="flashMessage" scope="session"/>
    </c:if>

    <section class="panel">
        <c:choose>
            <c:when test="${empty contacts}">
                <div class="empty-state">
                    <h2>No contacts yet</h2>
                    <p class="muted">Add your first contact to see it listed here.</p>
                    <a class="button" href="${pageContext.request.contextPath}/contacts/add">Create first contact</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-wrap">
                    <table>
                        <thead>
                        <tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Action</th></tr>
                        </thead>
                        <tbody>
                        <c:forEach var="contact" items="${contacts}">
                            <tr>
                                <td><c:out value="${contact.id}"/></td>
                                <td><c:out value="${contact.name}"/></td>
                                <td><c:out value="${contact.email}"/></td>
                                <td><c:out value="${empty contact.phone ? '—' : contact.phone}"/></td>
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/contacts/delete"
                                          onsubmit="return confirm('Delete this contact?');">
                                        <input type="hidden" name="csrfToken"
                                               value="<c:out value='${sessionScope.csrfToken}'/>">
                                        <input type="hidden" name="id" value="<c:out value='${contact.id}'/>">
                                        <button class="button button-danger button-small" type="submit">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
    <p class="note">Demo storage: contacts are kept in the HTTP session, not a database. They are not permanent.</p>
</main>
<footer>Contact Manager · Servlet &amp; JSP learning project</footer>
</body>
</html>
