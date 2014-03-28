<%@page import="com.moviedb.entity.MovieInfo"%>
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
    MovieInfo movieInfo = (MovieInfo) request.getAttribute("movieinfo");
%>

<title><%=movieInfo.getMovieTitle()%></title>


<link rel="stylesheet" type="text/css" href="styles_showmovie.css">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap-theme.css">

<link rel="stylesheet" type="text/css"
	href="star-rating/jquery.rating.css">

<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="WEB-INF/jumbotron.css">
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

	<!-- ---------------------------------------------------------------------------- -->

	<div class="jumbotron">
		<div class="container" align="center" id="header-wrap">

			<h2 class="blog-post-title">
				<strong><%=movieInfo.getMovieTitle()%></strong>
			</h2>

			<h4>
				A production of <a href="<%=movieInfo.getStudioLink()%>"><%=movieInfo.getStudioName()%></a>
			</h4>

			<p class="blog-post-meta">
				<a href="<%=movieInfo.getAwardsLink()%>">See Awards</a> | <a
					href="<%=movieInfo.getUserReviewsLink()%>">See User Review</a> | <a
					href="<%=movieInfo.getCriticReviewsLink()%>">See Critic Review</a>
			</p>

			<p><%=movieInfo.getLength()%>
				min ---
				<%=movieInfo.getGenreList()%>
				---
				<%=movieInfo.getReleaseYear()%>
				--- Rating:<%=movieInfo.getRating()%>
			</p>

		</div>
	</div>

	<!-- **************************************************************************************** -->

	<div class="container" align="center" id="header-wrap"
		style="color: rgb(48, 53, 54); font-size: medium;">

		<div class="row">
			<div class="col-md-4">
				<dl>
					<dt>Actors</dt>
					<%
					    for (int i = 0; i < movieInfo.getActorsName().size(); i++)
					        out.println("<dd>" + "<a href=\""
					                + movieInfo.getActorsLink().get(i) + "\">"
					                + movieInfo.getActorsName().get(i) + "</a>" + "</dd>");
					%>
				</dl>
			</div>
			<div class="col-md-4">
				<dl>
					<dt>Directors</dt>
					<%
					    for (int i = 0; i < movieInfo.getDirectorsName().size(); i++)
					        out.println("<dd>" + "<a href=\""
					                + movieInfo.getDirectorsLink().get(i) + "\">"
					                + movieInfo.getDirectorsName().get(i) + "</a>"
					                + "</dd>");
					%>
				</dl>
			</div>
			<div class="col-md-4">
				<dl>
					<dt>Producers</dt>
					<%
					    for (int i = 0; i < movieInfo.getProducersName().size(); i++)
					        out.println("<dd>" + "<a href=\""
					                + movieInfo.getProducersLink().get(i) + "\">"
					                + movieInfo.getProducersName().get(i) + "</a>"
					                + "</dd>");
					%>
				</dl>
			</div>
		</div>

		<p><%=movieInfo.getPlot()%></p>

		<div class="container">
			<fieldset class="rating" style="color: rgb(48, 53, 54);">
				<br /> <br />
				<h4 align="left">Rate this Movie</h4>

				<input name="star2" type="radio" value="1" class="star" /> <input
					name="star2" type="radio" value="2" class="star" /> <input
					name="star2" type="radio" value="3" class="star" checked="checked" />
				<input name="star2" type="radio" value="4" class="star" /> <input
					name="star2" type="radio" value="5" class="star" /> <input
					name="star2" type="radio" value="6" class="star" /> <input
					name="star2" type="radio" value="7" class="star" /> <input
					name="star2" type="radio" value="8" class="star" checked="checked" />
				<input name="star2" type="radio" value="9" class="star" /> <input
					name="star2" type="radio" value="10" class="star" />
			</fieldset>


			<div class=btn-group-horizontal>
				<%
				    boolean isWatchListed = ((Boolean) request.getAttribute("loggedin"))
				            && ((Boolean) request.getAttribute("iswatchlisted"));

				    String toggleWatchListMessage = isWatchListed ? "Remove from watchlist"
				            : "Add to watchlist";

				    boolean isAddedToFavs = ((Boolean) request.getAttribute("loggedin"))
				            && ((Boolean) request.getAttribute("isfavoritelisted"));

				    String toggleFavoriteListMessage = isAddedToFavs ? "Remove from Favorites"
				            : "Add to Favorites";
				%>


				<a class="btn btn-primary" role="button"
					href="/Movie_Database/togglewatchliststat<%="?movieid=" + movieInfo.getMovieID()%>">
					<%=toggleWatchListMessage%>
				</a> <a class="btn btn-primary" role="button"
					href="/Movie_Database/togglefavoritemoviesstat<%="?movieid=" + movieInfo.getMovieID()%>">
					<%=toggleFavoriteListMessage%>
				</a>


				<button type="submit" class="btn btn-primary">Download</button>
			</div>
		</div>


	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="star-rating/jquery.form.js"></script>
	<script type="text/javascript" src="star-rating/jquery.js"></script>
	<script type="text/javascript" src="star-rating/jquery.MetaData.js"></script>
	<script type="text/javascript" src="star-rating/jquery.rating.js"></script>
	<script type="text/javascript" src="star-rating/jquery.rating.pack.js"></script>


</body>
</html>