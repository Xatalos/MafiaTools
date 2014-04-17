<%-- 
    Document   : participant
    Created on : 14.4.2014, 23:42:59
    Author     : Teemu
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Participant">
    <ul class="nav nav-tabs">
        <li><a href="Games">Games</a></li>
        <li><a href="Players">Players</a></li>
        <li><a href="Logout">Log Out</a></li>
    </ul>
    <div class="container">
        <h1>${participant.name}</h1>
        <form class="form-horizontal" action="EditParticipant2" method="POST">
            <input type="hidden" name="gameid" value="${gameid}">
            <input type="hidden" name="playerid" value="${participant.playerid}">
            <div class="form-group">
                <label for="inputEmail1" class="col-md-1 control-label">Points</label>
                <div class="col-md-10">
                    <textarea cols="5" rows="3" name="points" placeholder="Points" wrap="hard">${participant.points}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail1" class="col-md-1 control-label">Notes</label>
                <div class="col-md-10">
                    <textarea cols="70" rows="6" name="notes" placeholder="Notes" wrap="hard">${participant.notes}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail1" class="col-md-1 control-label">Meta Information</label>
                <div class="col-md-10">
                    <textarea cols="70" rows="6" name="meta" placeholder="Meta Information" wrap="hard">${participant.meta}</textarea>
                </div>
            </div>
            <div class="col-md-offset-2 col-md-10">
                <a href="Game?id=${gameid}"><button type="button" class="btn btn-xs btn-default">Go Back</button></a>
                <button type="submit" class="btn btn-xs btn-default">Save Changes</button>
            </div>
    </div>
</form>
</div>
</t:base>