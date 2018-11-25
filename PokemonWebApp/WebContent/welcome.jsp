<%@include file="include/top.jsp"%>
<title>Pokemon | Welcome</title>
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
							<li class="active"><span>Welcome</span></li>
						</ol>
					</div>

				</div>
			</div>
		</div>
	</div>


	<%@include file="include/footer.jsp"%>
</body>
</html>