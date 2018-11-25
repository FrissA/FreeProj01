<%@include file="include/top.jsp"%>
<title>Pokemon | Profile</title>
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
							<li><a href="profile.jsp">Profile Of <%
								out.println(session.getAttribute("username"));
							%></a></li>
							<li class="active"><span>Summary</span></li>
						</ol>
					</div>
					<div class="profile">
						<div class="profile-details">
							<div class="row">
								<div class="col-sm-3">
									<div class="profile-left">
										<h4 class="user">
											<%
												out.println(session.getAttribute("username"));
											%>
										</h4>
										<p>
											<i class="fa fa-circle"></i> Online
										</p>
									</div>
									<div class="edit-link">
										<a href="">Edit Profile</a>
									</div>
								</div>
								<div class="col-sm-9">
									<div class="profile-right">
										<div class="row">
											<div class="col-xs-6">
												<p class="title">Username:</p>
												<p class="title">Email:</p>
												<p class="title">Age:</p>
											</div>
											<div class="col-xs-6">
												<p>
													<%
														out.println(session.getAttribute("username"));
													%>
												</p>
												<p>N/A</p>
												<p>N/A</p>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-xs-6">
												<p class="title">Date Registered:</p>
												<p class="title">Last Active:</p>
												<p class="title">Age:</p>
											</div>
											<div class="col-xs-6">
												<p>Today</p>
												<p>Today</p>
												<p>N/A</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@include file="include/footer.jsp"%>
</body>
</html>