<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base pageTitle="MafiaTools Games">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Games</a></li>
        <li><a href="Players">Players</a></li>
        <li><a href="index.jsp">Log Out</a></li>
    </ul>
    <div class="container">
        <p> </p>
        <p>Hello Username!</p>
        <p>You have created 3 games and 3 players so far.</p>
        <p> </p>
        <h1>Games</h1>
        <p><a href="CreateGame"><button type="button" class="btn btn-xs btn-default">Create New Game</button></a></p>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Game Name</th>
                    <th>View Game</th>
                    <th>Delete Game</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="game" items="${games}">
                    <tr>
                        <td>${game.name}</td>
                        <td><a href="Game?id=${game.id}"><button type="button" class="btn btn-xs btn-default">View Game</button></a></td>
                        <td><a href="DeleteGame?id=${game.id}"><button type="button" class="btn btn-xs btn-default">Delete Game</button></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</t:base>