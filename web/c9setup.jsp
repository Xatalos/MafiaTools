<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base pageTitle="MafiaTools C9++ Setup Generator">
    <div class="container">
        <h1>C9++ Setup Generator</h1>
        <p> <a href="index.jsp"><button type="button" class="btn btn-xs btn-default">Go Back</button></a>
            <a href="C9Setup"><button type="button" class="btn btn-xs btn-default">Generate New C9++ Setup</button></a>
        </p>
        <ul>
            <c:forEach var="role" items="${roles}">
                <li>${role}</li>
                </c:forEach>
        </ul>
    </div>
</t:base>