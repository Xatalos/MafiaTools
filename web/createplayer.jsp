<%-- 
    Document   : createplayer
    Created on : 10.4.2014, 0:16:31
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Sample Player">
    <div class="container">
        <h1>Sample Player</h1>
        <form class="form-horizontal" action="Player" method="POST">
            <div class="form-group">
                <label for="inputEmail1" class="col-md-2 control-label">Meta Information</label>
                <div class="col-md-10">
                    <textarea cols="70" rows="6" name="myname" wrap="hard">???</textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <a href="players.jsp"><button type="button" class="btn btn-xs btn-default">Go Back</button></a>
                    <a href="players.jsp"><button type="submit" class="btn btn-xs btn-default">Save Changes</button></a>
                </div>
            </div>
        </form>
    </div>
</t:base>