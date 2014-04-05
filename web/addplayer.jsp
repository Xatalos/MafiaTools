<%-- 
    Document   : addplayer
    Created on : 30.3.2014, 19:28:14
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Add Player">
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
                <tr>
                    <td>Xatalos</td>
                    <td><a href="Game"><button type="button" class="btn btn-xs btn-default">Add Player</button></a></td>
                </tr>
                <tr>
                    <td>gonzaw</td>
                    <td><a href="Game"><button type="button" class="btn btn-xs btn-default">Add Player</button></a></td>
                </tr>
                <tr>
                    <td>marvellosity</td>
                    <td><a href="Game"><button type="button" class="btn btn-xs btn-default">Add Player</button></a></td>
                </tr>
            </tbody>
        </table>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <a href="Game"><button type="button" class="btn btn-xs btn-default">Go Back</button></a>
            </div>
        </div>
    </div>
</t:base>
