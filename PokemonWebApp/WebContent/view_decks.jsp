
<%@include file="include/top.jsp"%>
<title>Pokemon | View Decks</title>
</head>
<body onload="getDecks('');">
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
							<li class="active"><span>Upload Decks</span></li>
						</ol>
					</div>
					<div class="view-decks">
						<table
							class="table table-resonsive table-bordered table-striped table-hover"
							id="decks-table">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- BEGIN # MODAL Request Response -->
	<div class="modal fade" id="view-cards-modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<h4>View Cards</h4>
				</div>
				<div class="modal-body"
					style="max-height: 400px; overflow-y: scroll;">
					<table
						class="table table-resonsive table-bordered table-striped table-hover"
						id="view-cards-modal-table"></table>
				</div>
			</div>
		</div>
	</div>
	<!-- END # MODAL Request Response -->

	<%@include file="include/footer.jsp"%>
</body>
</html>