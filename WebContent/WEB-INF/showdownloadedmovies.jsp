<%@page import="com.moviedb.entity.CelebrityInfo"%>
<%@page import="com.moviedb.entity.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Home Page">
<meta name="author" content="">

<title>Downloaded Movies</title>
<!--<c:if test="${empty username_error}">Empty</c:if>  -->


<link rel="stylesheet" type="text/css" href="styles.css">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap-theme.css">

<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="jumbotron.css">
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

						<div class="navbar-collapse collapse"
							style="position: fixed; right: 250px;">
							<li class="dropdown"><a class="navbar-brand dropdown-toggle"
								href="#" data-toggle="dropdown"><%=(String) request.getAttribute("username")%><b
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
								</ul></li>
						</div>
						<%
						    }
						%>
					</ul>
				</div>
			</div>

			<div class="navbar-collapse collapse" style="float: right">

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
				<a class="navbar-brand" style="float: right"
					href="/Movie_Database/logout.do">Log Out</a>
				<%
				    }
				%>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</div>

	<!-- ------------------------------------------------------------------------------------------------- -->
	<div class="jumbotron">
		<div class="container" align="center">
			<% String userName = (String) request.getAttribute("username"); %>
			<h2 class='blog-post-title'>
				<%=userName %>
			</h2>
			<h2>Downloaded Movies</h2>
		</div>
	</div>

	<!-- **************************************************** -->

	<div class="container">
		<div class="container"
			style="color: rgb(48, 53, 54); font-size: large;">

			<br /> <br />

			<%ArrayList<AddedMovieListElement> showdownloadedMoviesList=(ArrayList<AddedMovieListElement>) 
		       request.getAttribute("downloadedmovieslist"); 
		    
		    if(!showdownloadedMoviesList.isEmpty())
		    {
	%>
			<div class="container">
				<div class="row">
					<div class="col-md-5">
						<dl>
							<dt>Movies</dt>
							<%
						  for(AddedMovieListElement s : showdownloadedMoviesList) //use for-each loop
						  {
							  out.println("<dd>" + "<a href=\""
						                + s.getMovieLink()+ "\">"
						                + s.getMovieName() + "</a>" + "</dd>");
						  }
						 %>
						</dl>
					</div>
					<div class="col-md-3">
						<dl>
							<dt>Download date</dt>
							<%
						    for(AddedMovieListElement s : showdownloadedMoviesList) //use for-each loop
							  {
								  out.println("<dd>"+ s.getAddedDate()+ "</dd>");
							  }
						%>
						</dl>
					</div>
				</div>
			</div>
			<%
		  	} 
		    else out.println("<br/><h4><em>Boo! You have no downloaded movies yet!</em></h4>");
		    	
		    %>


		</div>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

</body>
</html>
