<%-- 
    Document   : addplayer
    Created on : 30.3.2014, 19:28:14
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base pageTitle="MafiaTools Add Participant">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Games</a></li>
        <li><a href="Players">Players</a></li>
        <li><a href="Logout">Log Out</a></li>
    </ul>
    <div class="container">
        <h1>Players</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Player Name</th>
                    <th>Add Player</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="player" items="${players}">
                    <tr>
                        <td><c:out value="${player.name}"/></td>
                        <td><a href="AddParticipant2?gameid=${gameid}&playerid=${player.id}"><button type="button" class="btn btn-xs btn-default">Add Player</button></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <a href="Game?id=${gameid}"><button type="button" class="btn btn-xs btn-default">Go Back</button></a>
            </div>
        </div>
    </div>
</t:base>
