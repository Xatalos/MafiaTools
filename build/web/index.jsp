<%-- 
    Document   : index
    Created on : 13.3.2014, 23:03:22
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Login">
    <div class="container">
        Käyttäjänimi: <input type="text" name="username" value="${kayttaja}" />
        <h1>Log In</h1>
        <form class="form-horizontal" action="http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/html-demo/games.html" method="POST">
            <div class="form-group">
                <label for="inputEmail1" class="col-md-2 control-label">Username</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="inputEmail1" name="username" placeholder="Username">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword1" class="col-md-2 control-label">Password</label>
                <div class="col-md-10">
                    <input type="password" class="form-control" id="inputPassword1" name="password" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Remember Me
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <button type="submit" class="btn btn-default">Log In</button>
                    <a href="http://t-teesalmi.users.cs.helsinki.fi/MafiaTools/html-demo/register.html"><button type="button" class="btn btn-default">Register</button></a>
                </div>
            </div>
        </form>
    </div>
</t:base>