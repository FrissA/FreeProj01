<!-- BEGIN # MODAL Request Response -->
<div class="modal fade" id="request-response-modal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" align="center">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
				<h4>Would you like accept to challenge...</h4>
			</div>
			<div class="modal-body">
				<img src="images/pic1.jpg" alt="" width="100%" />
				<div class="row">
					<div class="col-sm-6">
						<div class="request-response">
							<a href="" class="accept" id="accept">Accept</a>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="request-response">
							<a href="" class="refuse" id="refuse">Refuse</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END # MODAL Request Response -->


<!-- BEGIN # MODAL Request Response -->
<div class="modal fade" id="accept-request-modal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" align="center">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-sm-5">
						<div class="user1">
							<img src="images/user1.jpg" />
							<p id="">
								<%
									out.print(session.getAttribute("username"));
								%>
							</p>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="vs">V/S</div>
					</div>
					<div class="col-sm-5">
						<div class="user2">
							<img src="images/user2.jpg" />
							<p id="challenger-name"></p>
						</div>
					</div>
				</div>
				<hr>
				<div class="game-start-button"></div>
			</div>
		</div>
	</div>
</div>
<!-- END # MODAL Request Response -->

<footer>
	<p>
		&copy; Copyright 2018 | Pokemon 
	</p>
</footer>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/custom.js"></script>

<script>
	$(document).ready(function() {
		setInterval(function() {
			getListPlayers();
			getListChallenges();
			getGameId();
		}, 10000);
	});
	/* getGameId(); */
</script>