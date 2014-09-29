<%-- 
    Document   : index
    Created on : 13.3.2014, 23:03:22
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base pageTitle="MafiaTools Login">
    <div class="container">
        <h1>Log In</h1>
        <form class="form-horizontal" action="Login" method="POST">
            <div class="form-group">
                <label for="inputEmail1" class="col-md-2 control-label">Username</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="inputEmail1" name="username" value="${username}" placeholder="Username">
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
                    <button type="submit" class="btn btn-default">Log In</button>
                    <a href="register.jsp"><button type="button" class="btn btn-default">Register</button></a>
                    <a href="c9setup.html"><button type="button" class="btn btn-default">C9++ Setup Generator</button></a>
                </div>
            </div>
        </form>
    </div>
</t:base>