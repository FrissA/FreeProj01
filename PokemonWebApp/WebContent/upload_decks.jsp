<%@include file="include/top.jsp"%>
<title>Pokemon | Upload Decks</title>
</head>
<body>
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
					<div class="upload-decks">
						<form>
							<div class="form-group">
								<label>Decks</label>
								<textarea rows="10" cols="10" name=uploadDecks id="uploadDecks"
									class="form-control" placeholder="Enter a Valid Json.."></textarea>
							</div>
							<div class="submit-button">
								<input type="button" id="upload-decks-submit" value="Upload Decks"
									class="btn btn-primary" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@include file="include/footer.jsp"%>
</body>
</html>