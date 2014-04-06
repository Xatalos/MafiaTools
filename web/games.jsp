<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
        <p><a href="Game"><button type="button" class="btn btn-xs btn-default">Create New Game</button></a></p>
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
                <div class="game">${games.getName}</div>
            </c:forEach>
            <tr>
                <td>Insane Mafia LXXVI</td>
                <td><a href="Game"><button type="button" class="btn btn-xs btn-default">View Game</button></a></td>
                <td><button type="button" class="btn btn-xs btn-default">Delete Game</button></td>
            </tr>
            <tr>
                <td>Insane Mafia LXXVII</td>
                <td><a href="Game"><button type="button" class="btn btn-xs btn-default">View Game</button></a></td>
                <td><button type="button" class="btn btn-xs btn-default">Delete Game</button></td>
            </tr>
            <tr>
                <td>Insane Mafia LXXVIII</td>
                <td><a href="Game"><button type="button" class="btn btn-xs btn-default">View Game</button></a></td>
                <td><button type="button" class="btn btn-xs btn-default">Delete Game</button></td>
            </tr>
            </tbody>
        </table>
    </div>
</t:base>