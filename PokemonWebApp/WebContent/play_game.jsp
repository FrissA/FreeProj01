<%-- 
<%
	if (Integer.parseInt(request.getParameter("gameId").toString()) != 0) {
		response.sendRedirect("welcome.jsp");
	}
%> --%>
<%@include file="include/top.jsp"%>
<title>Pokemon | Game Start</title>
</head>
<body onload="getDecks('start-game');">
	<div class="body">
		<%@include file="include/header.jsp"%>
		<div class="inner-body">
			<div class="row" style="width: 99%;">
				<%@include file="include/sidebar.jsp"%>
				<div class="col-sm-9">
					<%@include file="include/navbar.jsp"%>
					<div class="breadcrumb-body wrapper">
						<ol class="breadcrumb breadcrumb-arrow">
							<li><a href="welcome.jsp">Home</a></li>
							<li><a href="play_game.jsp">Game</a></li>
							<li class="active"><span>Start Game</span></li>
						</ol>
					</div>
					<div class="game wrapper">
						<div class="game-body">
							<div class="hand-board" id="player2"></div>
							<div class="retire-button">
								<a href="javascript:retireGame();">Retire Game</a>
							</div>
							<div class="power player2-power">
								<div class="progress">
									<div class="progress-bar" role="progressbar" aria-valuenow="70"
										aria-valuemin="0" aria-valuemax="100" style="width: 70%">70%

									</div>
								</div>
							</div>
							<div class="disk-image"
								style="background-image: url(images/disk.png);">
								<div class="player2 turn" style="visibility: hidden;">
									<img src="images/bottom-arrow.png" alt="" />
								</div>
								<div class="player1 turn">
									<img src="images/top-arrow.png" alt="" id="draw-card-player2" />
								</div>
							</div>
							<div class="power player1-power">
								<div class="progress">
									<div class="progress-bar" role="progressbar" aria-valuenow="70"
										aria-valuemin="0" aria-valuemax="100" style="width: 100%">100%

									</div>
								</div>
							</div>
							<div class="hand-board" id="player1"></div>
						</div>
						<!-- Slider Start -->
						<div id="carousel-example-generic" class="carousel slide"
							data-ride="carousel">
							<!-- Indicators -->
							<ol class="carousel-indicators">

							</ol>

							<!-- Wrapper for slides -->
							<div class="carousel-inner" id="decks-card-slider" role="listbox">

							</div>

							<!-- Controls -->
							<a class="left carousel-control" href="#carousel-example-generic"
								role="button" data-slide="prev"> <span
								class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
								<span class="sr-only">Previous</span>
							</a> <a class="right carousel-control"
								href="#carousel-example-generic" role="button" data-slide="next">
								<span class="glyphicon glyphicon-chevron-right"
								aria-hidden="true"></span> <span class="sr-only">Next</span>
							</a>
						</div>
						<!-- Slider End -->
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@include file="include/footer.jsp"%>
	<script>
		$(document).ready(function() {
			setInterval(function() {
				getBoard();
			}, 10000);
		});
	</script>
</body>
</html>