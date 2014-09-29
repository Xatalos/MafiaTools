<%-- 
    Document   : register
    Created on : 30.3.2014, 19:28:52
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Register">
    <div class="container">
        <h1>Register</h1>
        <form class="form-horizontal" action="Register" method="POST">
            <div class="form-group">
                <label for="inputEmail1" class="col-md-2 control-label">Username</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="inputEmail1" name="username" size="30" placeholder="Username">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword1" class="col-md-2 control-label">Password</label>
                <div class="col-md-10">
                    <input type="password" class="form-control" id="inputPassword1" name="password" size="30" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword2" class="col-md-2 control-label">Repeat password</label>
                <div class="col-md-10">
                    <input type="password" class="form-control" id="inputPassword1" name="password2" size="30" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <a href="index.jsp"><button type="button" class="btn btn-default">Go Back</button></a>
                    <button type="submit" class="btn btn-default">Register</button>
                </div>
            </div>
        </form>
    </div>
</t:base>
