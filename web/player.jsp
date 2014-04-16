<%-- 
    Document   : player
    Created on : 30.3.2014, 19:28:37
    Author     : Teemu
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base pageTitle="MafiaTools Player">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Games</a></li>
        <li><a href="Players">Players</a></li>
        <li><a href="Logout">Log Out</a></li>
    </ul>
    <div class="container">
        <h1><c:out value="${player.name}"/></h1>
        <form class="form-horizontal" action="EditPlayer" method="POST">
            <input type="hidden" name="id" value="${player.id}">
            <input type="text" class="form-control" id="inputEmail1" name="playername" placeholder="Rename Player">
            <div class="form-group">
                <div class="col-md-10">
                    <textarea cols="70" rows="6" name="meta" placeholder="Meta Information" wrap="hard">${player.meta}</textarea>
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