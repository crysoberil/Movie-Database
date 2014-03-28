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

<%
    ArrayList<String> celebNames = (ArrayList<String>) request
            .getAttribute("celebnames");
    ArrayList<String> celebLinks = (ArrayList<String>) request
            .getAttribute("celeblinks");
    ArrayList<String> movieNames = (ArrayList<String>) request
            .getAttribute("movietitles");
    ArrayList<String> movieLinks = (ArrayList<String>) request
            .getAttribute("movielinks");
%>


<title>Search Results</title>


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


			<h2>
				<strong>Welcome to JMDB!</strong>
			</h2>

			<div class="row">
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<div class="input-group">
						<form name="input" action="/Movie_Database/search.do" method="get">
							<input type="text" placeholder="Search for a Movie / Celebrity"
								name="searchword" action="/Movie_Databse/search.do" method="get"
								class="form-control"> <span class="input-group-btn">
								<input type="button" id="searchbtn" value="Search"
								class="btn btn-default">
							</span>
						</form>
					</div>
					<!-- /input-group -->
				</div>
				<div class="col-lg-4"></div>
			</div>



			<h2 class='blog-post-title'>
				<strong>Search Results for Movies/Celebrities</strong>
			</h2>
			<%
			    if (movieNames.size() != 0 || celebNames.size()!=0) 
			    {
			%>

			<h2><%=movieNames.size()+celebNames.size() %>
				result(s) found
			</h2>

			<%
			    }
			
			    else
			    {		
			%>

			<h2>No results found!</h2>

			<%
			    }
			%>

		</div>
	</div>
	<!-- **************************************************************************** -->

	<div class="container">
		<div class="container" style="color: rgb(48, 53, 54);">
			<!-- List of search results -->

			<% 
			if(movieNames.size()!=0)
			{
		%>

			<div class="row">
				<dl>
					<dt>
						<h3>Found Movies</h3>
					</dt>
					<%		for (int i = 0; i < movieNames.size(); i++) 
						    {
						            out.println("<dd>" + "<a href=\"" + movieLinks.get(i)
						                    + "\">" + movieNames.get(i) + "</a>" + "</dd>");
						    }
						}
					%>
				</dl>
			</div>


			<%
			if (celebNames.size() != 0) 
			{
		%>

			<div class="row" style="">
				<dl>
					<dt>
						<h3 style="color: black;">Found Celebrities</h3>
					</dt>
					<%
					    for (int i = 0; i < celebNames.size(); i++) 
					    {
					            out.println("<dd>" + "<a href=\"" + celebLinks.get(i)
					                    + "\">" + celebNames.get(i) + "</a>" + "</dd>");
					    }
					  }
					%>
				</dl>
			</div>

		</div>
	</div>



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

</body>
</html>
