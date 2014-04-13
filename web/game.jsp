<%-- 
    Document   : game
    Created on : 30.3.2014, 19:28:23
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
                        <td>${participant.points}</td>
                        <td>${participant.notes}</td>
                        <td>${participant.meta}</td>
                        <td><a href="RemoveParticipant?playerid=${participant.playerid}"><button type="button" class="btn btn-xs btn-default">Remove From Game</button></a></td>
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