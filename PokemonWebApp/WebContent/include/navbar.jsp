<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.pokemon.database.*"%>

<div class="row">
	<div class="col-sm-10">
		<!-- Navbar Start-->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="welcome.jsp">Home</a></li>
						<li><a href="view_decks.jsp">View Decks</a></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Challenges <span
								class="challenge-count" id="challenge-count">  </span> <span class="caret"></span></a>
							<ul class="dropdown-menu" id="list-challenges">
							</ul></li>
						<li><a href="upload_decks.jsp">Upload Decks</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><i class="fa fa-user"></i> <%
 	out.println(session.getAttribute("username"));
 %> <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="profile.jsp"><i class="fa fa-user"></i>
										View Profile</a></li>
								<li><a href="#"><i class="fa fa-power-off"></i> Manage
										Account</a></li>
							</ul></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<!-- Navbar End-->
	</div>
	<div class="col-sm-2">
		<div class="play-now-image">
			<a href="play_game.jsp" title="Play Now"><img
				src="images/play_now.gif" alt=""></a>
		</div>
	</div>
</div>