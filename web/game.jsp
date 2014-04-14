<%-- 
    Document   : game
    Created on : 30.3.2014, 19:28:23
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base pageTitle="MafiaTools Game">
    <div class="container">
        <form class="form-horizontal" action="EditGame" method="POST">
            <h1>${game.name}</h1>
            <input type="text" class="form-control" id="inputEmail1" name="gamename" placeholder="Rename Game">
            <input type="hidden" name="id" value="${game.id}">
            <a href="AddParticipant?id=${game.id}"><button type="button" class="btn btn-xs btn-default">Add Player to the Game</button></a>
            <a href="CreatePlayer"><button type="button" class="btn btn-xs btn-default">Create New Player</button></a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Player Name</th>
                        <th>Points</th>
                        <th>Notes</th>
                        <th>Meta Information</th>
                        <th>Remove From Game</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="participant" items="${participants}">
                    <tr>
                        <td>${participant.name}</td>
                        <td><textarea cols="5" rows="3" name="points" placeholder="Points" wrap="hard">${participant.points}</textarea></td>
                        <td><textarea cols="60" rows="4" name="notes" placeholder="Notes" wrap="hard">${participant.notes}</textarea></td>
                        <td>${participant.meta}</td>
                        <td><a href="RemoveParticipant?gameid=${game.id}&playerid=${participant.playerid}"><button type="button" class="btn btn-xs btn-default">Remove From Game</button></a></td>
                        <input type="hidden" name="participants" value="${participants}">
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <a href="Games"><button type="button" class="btn btn-xs btn-default">Go Back</button></a>
                    <button type="submit" class="btn btn-xs btn-default">Save Changes</button>
                </div>
            </div>
        </form>
    </div>
</t:base>