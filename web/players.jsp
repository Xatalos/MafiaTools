<%-- 
    Document   : players
    Created on : 30.3.2014, 19:28:45
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base pageTitle="MafiaTools Players">
    <ul class="nav nav-tabs">
        <li><a href="Games">Games</a></li>
        <li class="active"><a href="#">Players</a></li>
        <li><a href="Logout">Log Out</a></li>
    </ul>
    <div class="container">
        <p> </p>
        <p>Hello <c:out value="${user.name}"/>!</p>
        <h1>Players</h1>
        <p><a href="CreatePlayer"><button type="button" class="btn btn-xs btn-default">Create New Player</button></a></p>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Player Name</th>
                    <th>View Player</th>
                    <th>Delete Player</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="player" items="${players}">
                    <tr>
                        <td><c:out value="${player.name}"/></td>
                        <td><a href="Player?id=${player.id}"><button type="button" class="btn btn-xs btn-default">View Player</button></a></td>
                        <td><a href="DeletePlayer?id=${player.id}"><button type="button" class="btn btn-xs btn-default">Delete Player</button></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</t:base>