<%-- 
    Document   : game
    Created on : 30.3.2014, 19:28:23
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base pageTitle="MafiaTools Game">
    <ul class="nav nav-tabs">
        <li><a href="Games">Games</a></li>
        <li><a href="Logout">Log Out</a></li>
    </ul>
    <div class="container">
        <form class="form-horizontal" action="EditGame" method="POST">
            <h1><c:out value="${game.name}"/></h1>
            <input type="text" class="form-control" id="inputEmail1" name="gamename" placeholder="Rename Game">
            <input type="hidden" name="id" value="${game.id}">
            <a href="Games"><button type="button" class="btn btn-xs btn-default">Go Back</button></a>
            <button type="submit" class="btn btn-xs btn-default">Rename Game</button>
            <a href="CreatePlayer?id=${game.id}"><button type="button" class="btn btn-xs btn-default">Add Player to the Game</button></a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Player Name</th>
                        <th>Points</th>
                        <th>Play Notes</th>
                        <th>Role Notes</th>
                        <th>Edit Points/Notes</th>
                        <th>Remove From Game</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="player" items="${players}">
                        <tr>
                            <td><c:out value="${player.name}"/></td>
                            <td><c:out value="${player.points}"/></td>
                            <td>${player.notes}</td>
                            <td>${player.rolenotes}</td>
                            <td><a href="Player?id=${player.id}&gameid=${game.id}"><button type="button" class="btn btn-xs btn-default">Edit Points/Notes</button></a></td>
                            <td><a href="DeletePlayer?id=${player.id}&gameid=${game.id}"><button type="button" class="btn btn-xs btn-default">Remove From Game</button></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</t:base>