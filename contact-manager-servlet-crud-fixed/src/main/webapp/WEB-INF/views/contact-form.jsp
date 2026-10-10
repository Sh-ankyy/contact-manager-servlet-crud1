<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Contact | Contact Manager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/contacts">Contact Manager</a>
    <nav><a href="${pageContext.request.contextPath}/contacts">Contact list</a></nav>
</header>
<main class="container narrow">
    <section class="panel">
        <p class="eyebrow">PERSONAL PORTAL</p>
        <h1>Add a contact</h1>
        <p class="muted">Enter the contact details below. Fields marked * are required.</p>

        <c:if test="${not empty errors}">
            <div class="alert alert-error" role="alert">Please correct the highlighted fields.</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/contacts/add" novalidate>
            <input type="hidden" name="csrfToken" value="<c:out value='${sessionScope.csrfToken}'/>">

            <div class="field">
                <label for="name">Name <span>*</span></label>
                <input id="name" name="name" type="text" minlength="2" maxlength="50"
                       value="<c:out value='${requestScope.name}'/>"
                       class="${not empty errors.name ? 'invalid' : ''}" required>
                <c:if test="${not empty errors.name}">
                    <small class="field-error"><c:out value="${errors.name}"/></small>
                </c:if>
            </div>

            <div class="field">
                <label for="email">Email <span>*</span></label>
                <input id="email" name="email" type="email"
                       value="<c:out value='${requestScope.email}'/>"
                       class="${not empty errors.email ? 'invalid' : ''}" required>
                <c:if test="${not empty errors.email}">
                    <small class="field-error"><c:out value="${errors.email}"/></small>
                </c:if>
            </div>

            <div class="field">
                <label for="phone">Phone <span class="optional">(optional)</span></label>
                <input id="phone" name="phone" type="tel" inputmode="numeric" maxlength="10"
                       pattern="[0-9]{10}" placeholder="10-digit phone number"
                       value="<c:out value='${requestScope.phone}'/>"
                       class="${not empty errors.phone ? 'invalid' : ''}">
                <c:if test="${not empty errors.phone}">
                    <small class="field-error"><c:out value="${errors.phone}"/></small>
                </c:if>
            </div>

            <div class="actions">
                <button class="button" type="submit">Save contact</button>
                <a class="button button-secondary" href="${pageContext.request.contextPath}/contacts">Cancel</a>
            </div>
        </form>
    </section>
</main>
<footer>Contact Manager · Servlet &amp; JSP learning project</footer>
</body>
</html>
