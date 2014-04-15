<%-- 
    Document   : createplayer
    Created on : 10.4.2014, 0:16:31
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Create Player">
    <div class="container">
        <h1>Create Player</h1>
        <form class="form-horizontal" action="CreatePlayer2" method="POST">
            <div class="form-group">
                <label for="inputEmail1" class="col-md-2 control-label">Player Name</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="inputEmail1" name="playername" placeholder="Player Name">
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail1" class="col-md-2 control-label">Meta Information</label>
                <div class="col-md-10">
                    <textarea cols="70" rows="6" name="meta" wrap="hard"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <a href="Players"><button type="button" class="btn btn-xs btn-default">Go Back</button></a>
                    <button type="submit" class="btn btn-xs btn-default">Save Changes</button>
                </div>
            </div>
        </form>
    </div>
</t:base>