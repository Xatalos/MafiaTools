<%-- 
    Document   : game
    Created on : 30.3.2014, 19:28:23
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Sample Game">
    <div class="container">
        <h1>Sample Game</h1>
        <a href="AddPlayer"><button type="button" class="btn btn-xs btn-default">Add Player to the Game</button></a>
        <a href="Player"><button type="button" class="btn btn-xs btn-default">Create New Player</button></a>
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
                <tr>
                    <td>Xatalos</td>
                    <td>+10</td>
                    <td>Confirmed town</td>
                    <td>???</td>
                    <td><button type="button" class="btn btn-xs btn-default">Remove From Game</button></td>
                </tr>
                <tr>
                    <td>gonzaw</td>
                    <td>-10</td>
                    <td>Confirmed Mafia</td>
                    <td>???</td>
                    <td><button type="button" class="btn btn-xs btn-default">Remove From Game</button></td>
                </tr>
                <tr>
                    <td>marvellosity</td>
                    <td>+7</td>
                    <td>Probably town, very active and involved</td>
                    <td>More active and involved as town, less hesitant as Mafia</td>
                    <td><button type="button" class="btn btn-xs btn-default">Remove From Game</button></td>
                </tr>
            </tbody>
        </table>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <a href="Games"><button type="button" class="btn btn-xs btn-default">Go Back</button></a>
                <a href="Game"><button type="button" class="btn btn-xs btn-default">Save Changes</button></a>
            </div>
        </div>
    </div>
</t:base>