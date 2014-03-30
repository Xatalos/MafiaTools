<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Games">
    <ul class="nav nav-tabs">
            <li class="active"><a href="#">Games</a></li>
            <li><a href="http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/html-demo/players.html">Players</a></li>
            <li><a href="http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/html-demo/index.html">Log Out</a></li>
        </ul>
        <div class="container">
            <p> </p>
            <p>Hello Username!</p>
            <p>You have created 3 games and 3 players so far.</p>
            <p> </p>
            <h1>Games</h1>
            <p><a href="http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/html-demo/game.html"><button type="button" class="btn btn-xs btn-default">Create New Game</button></a></p>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Game Name</th>
                        <th>View Game</th>
                        <th>Delete Game</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Insane Mafia LXXVI</td>
                        <td><a href="http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/html-demo/game.html"><button type="button" class="btn btn-xs btn-default">View Game</button></a></td>
                        <td><button type="button" class="btn btn-xs btn-default">Delete Game</button></td>
                    </tr>
                    <tr>
                        <td>Insane Mafia LXXVII</td>
                        <td><a href="http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/html-demo/game.html"><button type="button" class="btn btn-xs btn-default">View Game</button></a></td>
                        <td><button type="button" class="btn btn-xs btn-default">Delete Game</button></td>
                    </tr>
                    <tr>
                        <td>Insane Mafia LXXVIII</td>
                        <td><a href="http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/html-demo/game.html"><button type="button" class="btn btn-xs btn-default">View Game</button></a></td>
                        <td><button type="button" class="btn btn-xs btn-default">Delete Game</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
</t:base>