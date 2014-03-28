<%@page import="com.moviedb.entity.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- Codes by HTML.am -->
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Home Page">
<meta name="author" content="">

<%
    ArrayList<UserReview> userReviews = (ArrayList<UserReview>) request
            .getAttribute("userreviews");

    long _movieID = (Long) request.getAttribute("movieid");
    String _movieName = (String) request.getAttribute("moviename");
    String _movieLink = userReviews.isEmpty() ? MovieInfo
            .getMovieLink(_movieID) : userReviews.get(0)
            .getMovieLink();
%>

<title>User reviews : <%= _movieName %></title>

<link rel="stylesheet" type="text/css" href="show_reviewstyles.css">
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
						        String userName = (String) request.getAttribute("username");
						%>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <%=userName%><b class="caret"></b></a>
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
						<%
						    }
						%>
					</ul>
				</div>
			</div>
			<%
			    if (f == Boolean.FALSE) {
			%>
			<div class="navbar-collapse collapse">
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

				<a class="nav navbar-nav navbar-right"
					href="/Movie_Database/signup.form">New? Sign Up</a>
				<%
				    } else {
				%>
				<a class="nav navbar-nav navbar-right"
					href="/Movie_Database/logout.do">Log Out</a>
				<%
				    }
				%>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</div>
	<!-- ------------------------------------------------------------------------------------------------------------------- -->

	<div class="jumbotron">
		<%
		    if (f == Boolean.TRUE) {
		%>
		<form action="/Movie_Database/movie/submitmoviereview.do"
			method="post">
			<!-- userreview   controller-->
			<textarea name="reviewstring" rows="5" cols="100"
				placeholder="Enter your review here..."></textarea>
			<br> <input type="submit" name="submitbutton" value="Submit">
			<input type="hidden" name="movieid"
				value="<%=(Long) request.getAttribute("movieid")%>">
		</form>
		<%
		    }
		%>
		<div class="container">
			<div class="row">
				<div class="panel panel-default widget">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-comment"></span>
						<%
						    if (userReviews.size() == 0) {
						%>
						<h3 class="panel-title">No Reviews Found</h3>
						<%
						    } else {
						%>
						<h3 class="panel-title">
							<a href="<%= _movieLink%>"><%=_movieName%></a>
						</h3>

						<h3 class="panel-title">User Reviews</h3>
						<span class="label label-info"><%=userReviews.size()%></span>
					</div>
					<div class="panel-body">
						<ul class="list-group">
							<%
							    for (UserReview s : userReviews) {
							%>
							<li class="list-group-item">
								<div class="row">
									<div class="col-xs-2 col-md-1">
										<img src="http://placehold.it/80"
											class="img-circle img-responsive" alt="" />
									</div>
									<div class="col-xs-10 col-md-11">
										<div>
											<a href=" <%=s.getMovieLink()%>"><%=s.getMovieTitle()%></a>
											<div class="mic-info">
												By:
												<%=s.getReviewerName()%>
											</div>
										</div>
										<div class="comment-text">
											<%=s.getReview()%>
										</div>
									</div>
								</div>
							</li>
							<%
							    }
							    }
							%>
						</ul>
					</div>
					<!-- panel body -->
				</div>
				<!-- panel widget-->
			</div>
			<!-- row -->
		</div>
		<!-- container -->

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
</body>
</html>