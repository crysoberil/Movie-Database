<%@page import="com.sun.org.apache.xpath.internal.operations.Bool"%>
<%@page import="com.moviedb.entity.CelebrityInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Home Page">
<meta name="author" content="">

<%
    CelebrityInfo celebInfo = (CelebrityInfo) request
            .getAttribute("celebinfo");
%>

<title><%=celebInfo.getName()%></title>


<link rel="stylesheet" type="text/css" href="styles_showmovie.css">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap-theme.css">

<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="jumbotron.css">
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="navbar-brand"><a href="/Movie_Database/">Home</a></li>
						<li class="navbar-brand"><a href="/Movie_Database/movies">Movies</a></li>
						<li class="navbar-brand"><a
							href="/Movie_Database/celebrities">Celebrities</a></li>
						<%
						    boolean f = (Boolean) request.getAttribute("loggedin");
						    if (f == Boolean.TRUE) {
						%>
						
						<div class="navbar-collapse collapse" style="position: fixed; right:250px;">
							<li class="dropdown"><a  class="navbar-brand dropdown-toggle" href="#"
								data-toggle="dropdown"><%=(String) request.getAttribute("username")%><b
									class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="/Movie_Database/showdownloadedmovies.do">Downloaded
											Movies</a></li>
									<li><a href="/Movie_Database/showfavoritemovies.do">Favorite
											Movies</a></li>
									<li><a href="/Movie_Database/showfavoritecelebs.do">Favorite
											Celebrities</a></li>
									<li><a href="/Movie_Database/checkwatchlist.do">WatchList</a></li>
									<li><a href="#">Settings</a></li>
								</ul>
							</li>
						</div>
						<%
						    }
						%>
					</ul>
				</div>
			</div>
			
			<div class="navbar-collapse collapse" style="float:right">
			
				<%
				    boolean g = (Boolean) request.getAttribute("loggedin");
				    if (g == Boolean.FALSE) {
				%>
				<form class="navbar-form navbar-left" role="form" method="post"
					action="/Movie_Database/login.do">
					<div class="form-group">
						<input type="text" name="username" placeholder="Username"
							class="form-control" autocomplete="off">
					</div>
					<div class="form-group">
						<input type="password" name="password" placeholder="Password"
							class="form-control" autocomplete="off">
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
				</form>
				
			
				<a class="navbar-brand" style="float: right"
					href="/Movie_Database/signup.form">New? Sign Up</a>
				<%
				    } else {
				%>
				<a class="navbar-brand" style="float: right" href="/Movie_Database/logout.do">Log Out</a>
				<%
				    }
				%>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</div>
	<!-- ------------------------------------------------------------------------------------------------------------------- -->
	<div class="container" align="center" id="header-wrap" style="color:black;">

		<h2 class="blog-post-title"><%=celebInfo.getName()%></h2>
		<p class="blog-post-meta">
			<a href="<%=celebInfo.getCelebrityAwardLink()%>">See Awards</a>
		</p>
		<p class="blog-post-meta">
			Sex :
			<%=celebInfo.getCelebSex()%>
			<br> Born on
			<%=celebInfo.getDateOfBirth()%>
			<br> From
			<%=celebInfo.getCoutry()%>
		<p>
		<br/>
		<br/>
		
			<h4>Short Bio</h4>
			<%=celebInfo.getShortBio()%>
			
			<br/><br/> <br/>
			
		<div class=btn-group-vertical style="float:center;">
			<a type="button" class="btn btn-primary"
				href="/Movie_Database/togglefavoritecelebsstat?celebid=<%=(Long) celebInfo.getCelebID()%>">
				<%=request.getAttribute("isfavoritelisted") != null
                    && (Boolean) request.getAttribute("isfavoritelisted") ? "Remove from favorites"
                    : "Add to favorites"%></a>
		</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
</body>
</html>