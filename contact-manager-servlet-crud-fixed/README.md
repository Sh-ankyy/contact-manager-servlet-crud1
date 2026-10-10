# Contact Manager — Servlet-Based CRUD Application

A Java web application for a Personal Portal contact module, built with Jakarta Servlets, JSP, JSTL, JavaBeans, and HTTP sessions.

## Features

- Display a contact list.
- Add contacts using a JSP form.
- Validate name, email, and optional phone number on the server.
- Show field-level validation errors and preserve entered values.
- Delete contacts.
- Display success messages after actions.
- Use Post/Redirect/Get after a successful submission.
- Include session-bound CSRF tokens on add/delete forms.
- Escape displayed user content with JSTL `<c:out>`.

## Technology stack

- Java 17
- Maven
- Jakarta Servlet 6.0
- JSP and Jakarta Standard Tag Library (JSTL) 3
- Apache Tomcat 10.1+

## Important storage note

This assignment version stores contacts in the HTTP session, not in a database. The contacts are temporary and can disappear when the session expires or the server restarts. Database persistence can be added in a later stage.

## Run locally

### Prerequisites

Install:
1. JDK 17 or newer
2. Maven
3. Apache Tomcat 10.1 or newer

Check installations:

```bash
java -version
mvn -version
```

### Build

From the folder containing `pom.xml`, run:

```bash
mvn clean package
```

The deployable WAR will be created at:

```text
target/contact-manager.war
```

Copy the WAR into Tomcat's `webapps` folder, start Tomcat, then open:

```text
http://localhost:8080/contact-manager/
```

You can also import the folder as a Maven project in IntelliJ IDEA or Eclipse.

## Project structure

```text
contact-manager/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/contactmanager/
    │   ├── model/Contact.java
    │   ├── util/ContactValidator.java
    │   └── servlet/
    │       ├── ContactServlet.java
    │       ├── AddContactServlet.java
    │       └── DeleteContactServlet.java
    └── webapp/
        ├── index.jsp
        ├── assets/style.css
        └── WEB-INF/views/
            ├── contact-form.jsp
            └── contact-list.jsp
```

## Validation rules

- **Name:** required, 2–50 characters.
- **Email:** required, basic email-format validation.
- **Phone:** optional; if supplied, must contain exactly 10 digits.

## Routes

| Method | Route | Purpose |
|---|---|---|
| GET | `/contacts` | Show contact list |
| GET | `/contacts/add` | Show add-contact form |
| POST | `/contacts/add` | Validate and add a contact |
| POST | `/contacts/delete` | Delete a contact |

## GitHub upload

1. Create a repository named `contact-manager-servlet-crud`.
2. Upload the contents of this folder (not the ZIP file alone).
3. Commit the files.
4. Follow the Run locally steps to test it with Tomcat.

## Limitations and next steps

- No database persistence yet.
- No authentication/authorization layer; this is a learning/demo application, not ready for public production deployment.
- Add edit/update functionality if required by your assignment.
- Add automated tests and stronger email validation in a future iteration.
