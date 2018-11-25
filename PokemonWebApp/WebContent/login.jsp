<html>
<head>
<title>Pokemon Planet | Login</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/font-awesome.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="body">
		<div class="login-button">
			<a href="#" role="button" data-toggle="modal"
				data-target="#login-modal">Login Now</a>
		</div>
	</div>



	<!-- BEGIN # MODAL LOGIN -->
	<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<img id="img_logo" src="images/logo.png">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-6" style="border-right: 1px solid black;">
							<div class="login-form">
								<h3>Login</h3>
								<p id="login-error"></p>
								<form>
									<div class="form-group">
										<label for="">Username</label> <input type="text"
											name="login-username" id="login-username"
											placeholder="Enter Your Username.." class="form-control">
									</div>
									<div class="form-group">
										<label for="">Password</label> <input type="password"
											name="login-password" id="login-password"
											placeholder="Enter Your Username.." class="form-control">
									</div>
									<div class="forgot-password">
										<a href="#">Forgot Password</a>
									</div>
									<div class="submit-button">
										<input type="button" id="login-button" class="btn btn-primary"
											value="Login">
									</div>
								</form>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="login-form">
								<h3>Sign Up</h3>
								<p id="register-error"></p>
								<form>
									<div class="form-group">
										<label for="">Username</label> <input type="text"
											name="register-username" id="register-username"
											placeholder="Enter Your Username.." class="form-control">
									</div>
									<div class="form-group">
										<label for="">Password</label> <input type="password"
											name="register-password" id="register-password"
											placeholder="Enter Your Username.." class="form-control">
									</div>
									<div class="submit-button">
										<input type="button" id="register-button"
											class="btn btn-primary" value="Register">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END # MODAL LOGIN -->
	<footer>
		<p>
			&copy; Copyright 2018 | Pokemon All Rights are Reserved | Designed By
			<span><a href="https://p4logics.com/" title="p4logics"
				target="_blank">p4logics</a></span>
		</p>
	</footer>
	<script src="bootstrap/js/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/custom.js"></script>
</body>
</html>