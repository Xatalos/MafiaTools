<%-- 
    Document   : player
    Created on : 30.3.2014, 19:28:37
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base pageTitle="MafiaTools Player">
    <ul class="nav nav-tabs">
        <li><a href="Games">Games</a></li>
        <li><a href="Logout">Log Out</a></li>
    </ul>
    <div class="container">
        <h1>${player.name}</h1>
        <form name="myform" class="form-horizontal" action="EditPlayer" method="POST">
            <input type="hidden" name="gameid" value="${gameid}">
            <input type="hidden" name="playerid" value="${player.id}">
            <input type="hidden" name="playername" value="${player.name}">
            <div class="form-group">
                <label for="inputEmail1" class="col-md-1 control-label"></label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="inputEmail1" name="newname" placeholder="Rename Player">
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail1" class="col-md-1 control-label">Points</label>
                <div class="col-md-10">
                    <input name="points" value="${player.points}" type="number"></input>
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail1" class="col-md-1 control-label">Play Notes</label>
                <div class="col-md-10">
                    <input type="button" value="Add Link" onClick="addtext();">
                </div>
                <div class="col-md-10">
                    <textarea cols="70" rows="6" name="notes" placeholder="Play Notes" wrap="hard">${player.notes}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail1" class="col-md-1 control-label">Role Notes</label>
                <div class="col-md-10">
                    <input type="button" value="Add Link" onClick="addtext2();">
                </div>
                <div class="col-md-10">
                    <textarea cols="70" rows="6" name="rolenotes" placeholder="Role Notes" wrap="hard">${player.rolenotes}</textarea>
                </div>
            </div>
            <div class="col-md-offset-2 col-md-10">
                <a href="Game?id=${gameid}"><button type="button" class="btn btn-xs btn-default">Go Back</button></a>
                <button type="submit" class="btn btn-xs btn-default">Save Changes</button>
            </div>
    </div>
</form>
</div>

<script language="javascript" type="text/javascript">
                        function addtext() {
                            var newtext = '<a href="PUT_URL_HERE" target="_blank">PUT_TITLE_HERE</a>';
                            document.myform.notes.value += newtext;
                        }
                        function addtext2() {
                            var newtext = '<a href="PUT_URL_HERE" target="_blank">PUT_TITLE_HERE</a>';
                            document.myform.rolenotes.value += newtext;
                        }
</script>
</t:base>