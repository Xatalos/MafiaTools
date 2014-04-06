<%-- 
    Document   : creategame
    Created on : 6.4.2014, 16:45:26
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Create Game">
    <div class="container">
        <h1>Create Game</h1>
        <form class="form-horizontal" action="CreateGame2" method="POST">
            <div class="form-group">
                <label for="inputEmail1" class="col-md-2 control-label">Game Name</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="inputEmail1" name="gamename" placeholder="Game Name">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <a href="Games"><button type="button" class="btn btn-default">Go Back</button></a>
                    <button type="submit" class="btn btn-default">Create Game</button>
                </div>
            </div>
        </form>
    </div>
</t:base>
