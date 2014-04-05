<%-- 
    Document   : players
    Created on : 30.3.2014, 19:28:45
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Players">
    <ul class="nav nav-tabs">
        <li><a href="Games">Games</a></li>
        <li class="active"><a href="#">Players</a></li>
        <li><a href="index.jsp">Log Out</a></li>
    </ul>
    <div class="container">
        <p> </p>
        <p>Hello Username!</p>
        <p>You have created 3 games and 3 players so far.</p>
        <p> </p>
        <h1>Players</h1>
        <p><a href="Player"><button type="button" class="btn btn-xs btn-default">Create New Player</button></a></p>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Player Name</th>
                    <th>View Player</th>
                    <th>Delete Player</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Xatalos</td>
                    <td><a href="Player"><button type="button" class="btn btn-xs btn-default">View Player</button></a></td>
                    <td><button type="button" class="btn btn-xs btn-default">Delete Player</button></td>
                </tr>
                <tr>
                    <td>gonzaw</td>
                    <td><a href="Player"><button type="button" class="btn btn-xs btn-default">View Player</button></a></td>
                    <td><button type="button" class="btn btn-xs btn-default">Delete Player</button></td>
                </tr>
                <tr>
                    <td>marvellosity</td>
                    <td><a href="Player"><button type="button" class="btn btn-xs btn-default">View Player</button></a></td>
                    <td><button type="button" class="btn btn-xs btn-default">Delete Player</button></td>
                </tr>
            </tbody>
        </table>
    </div>
</t:base>