/** Sign In Validation */
$('#login-button').click(function() {
	document.getElementById('login-error').innerHTML = "";
	document.getElementById('register-error').innerHTML = "";
	var username = $('#login-username').val();
	var password = $('#login-password').val();
	if (username == "") {
		$('#login-error').append("Username Must Not Be Blank!");
	} else if (password == "") {
		$('#login-error').append("Password Must Not Be Blank!");
	} else {
		var jsonData = {
			"username" : username,
			"password" : password
		};
		$.ajax({
			type : "POST",
			url : "Login",
			data : jsonData,
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {
				if (data['msg'] != null || data['msg'] != undefined) {
					alert(data['msg']);
					window.open("welcome.jsp", "_self");
				} else if (data['error'] != null || dat['error'] != undefined) {
					$('#login-error').append(data['error']);
					$('#login-modal').modal("show");
				}
			},
			error : function(e) {
				alert("Wrong Username And Password! Please Try Again");
			}
		});
	}
});

/** Sign Up Validation */
$("#register-button")
		.click(
				function() {
					document.getElementById('login-error').innerHTML = "";
					document.getElementById('register-error').innerHTML = "";
					var username = $('#register-username').val();
					var password = $('#register-password').val();
					if (username == "") {
						$('#register-error').append(
								"Username Must Not Be Blank!");
					} else if (password == "") {
						$('#register-error').append(
								"Password Must Not Be Blank!");
					} else {
						var jsonData = {
							"username" : username,
							"password" : password
						};
						$
								.ajax({
									type : "POST",
									url : "Register",
									data : jsonData,
									dataType : 'json',
									cache : false,
									timeout : 600000,
									success : function(data) {
										if (data['msg'] != null
												|| data['msg'] != undefined) {
											alert(data['msg']);
											document
													.getElementById('register-username').value = "";
											document
													.getElementById('register-password').value = "";
										} else if (data['error'] != null
												|| data['error'] != undefined) {
											$('#register-error').append(
													data['error']);
											$('#login-modal').modal("show");
										}
									},
									error : function(e) {
										alert("Your Are Not Registered, Please Try Again!");
									}
								});
					}

				});

function getListPlayers() {
	var sessionUserId = $('#sessionUserId').val();
	document.getElementById('list-players').innerHTML = "";
	$
			.ajax({
				type : "GET",
				url : "ListPlayers",
				data : null,
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					var players = data['players'];
					for ( var i in players) {
						if (sessionUserId != players[i].playerId) {
							$('#list-players')
									.append(
											'<i class="fa fa-circle"></i>&nbsp;&nbsp;<span title="'
													+ players[i].username
													+ '">'
													+ players[i].username
													+ '</span><button id="user-'
													+ players[i].playerId
													+ '" class="btn btn-primary" title="Challenge to '
													+ players[i].username
													+ '" onclick="sendRequest('
													+ players[i].playerId
													+ ');">'
													+ players[i].button
													+ '</button>');
						}

					}
				},
				error : function(e) {

				}
			});
}

function getListChallenges() {
	document.getElementById('list-challenges').innerHTML = "";
	document.getElementById('challenge-count').innerHTML = "";
	$.ajax({
		type : "GET",
		url : "ListChallenges",
		data : null,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			var count = 0;
			var challenges = data['challenges'];
			for ( var i in challenges) {
				$('#list-challenges').append(
						'<li><a href="javascript:getRequestResponseModal('
								+ challenges[i].challengerId
								+ ');"><i class="fa fa-user"></i> '
								+ challenges[i].username + '</a></li>');
				count++;
			}

			document.getElementById('challenge-count').innerHTML = count;
		},
		error : function(e) {

		}
	});
}

function sendRequest(id) {
	var jsonData = {
		"challengee" : id
	};

	$.ajax({
		type : "POST",
		url : "AddChallenge",
		data : jsonData,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			if (data['msg'] != null || data['msg'] != undefined) {
				alert(data['msg']);
				$('#' + challengee).html('Challenge Requested');
			} else if (data['error'] != null || data['error'] != undefined) {
				alert(data['error']);
			}
		},
		error : function(e) {
			alert("Your Are Not Registered, Please Try Again!");
		}
	});
}

function getRequestResponseModal(id) {
	$("#accept").attr("href", "javascript:acceptChallenge(" + id + ");");
	$("#refuse").attr("href", "javascript:refuseChallenge(" + id + ");");
	$('#request-response-modal').modal('show');
}

function acceptChallenge(challengerId) {
	document.getElementById('challenger-name').innerHTML = "";
	var jsonData = {
		"challengerId" : challengerId
	};
	$.ajax({
		type : "POST",
		url : "AcceptChallenge",
		data : jsonData,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			if (data['msg'] != null || data['msg'] != undefined) {
				$('#request-response-modal').modal('hide');
				$('#challenger-name').append(data['challengerName']);
				$('.game-start-button').append(
						'<a href="javascript:addBoard(' + data['challengerId']
								+ ');">Start</a>');
				$('#accept-request-modal').modal('show');
			} else if (data['error'] != null || data['error'] != undefined) {
				alert(data['error']);
			}
		},
		error : function(e) {
			alert("Server Error, Please Try Again!");
		}
	});
}

function refuseChallenge(challengerId) {
	document.getElementById('challenger-name').innerHTML = "";
	var jsonData = {
		"challengerId" : challengerId
	};
	$.ajax({
		type : "POST",
		url : "RefuseChallenge",
		data : jsonData,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			if (data['msg'] != null || data['msg'] != undefined) {
				alert(data['msg']);
				window.location.reload();
			} else if (data['error'] != null || data['error'] != undefined) {
				alert(data['error']);
			}
		},
		error : function(e) {
			alert("Server Error, Please Try Again!");
		}
	});
}

function getDecks(action) {
	$.ajax({
		type : "POST",
		url : "GetDeck",
		data : null,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			if (action == 'start-game') {
				appendDecksToBench(data);
			} else {
				appendDecksToViewDecks(data);
			}

		},
		error : function(e) {
			alert("Your Are Not Registered, Please Try Again!");
		}
	});
}

function appendDecksToViewDecks(data) {
	$('#decks-table')
			.children()
			.remove()
			.end()
			.append(
					'<thead><tr><th>S.No.</th><th>Deck Id</th><th>View Cards</th></tr></thead>');
	var count = 1;
	var cards = data['deck'];
	for ( var i in cards) {
		$('#decks-table')
				.append(
						'<tbody><tr><td>'
								+ count
								+ '</td><td>'
								+ cards[i].id
								+ '</td><td><a href="javascript:getCardDetails('
								+ cards[i].id
								+ ');" title="View Cards"><i class="fa fa-eye"></i></a><input type="hidden" id="cards-'
								+ cards[i].id + '" value="'
								+ cards[i].cards.replace(/"/g, "'")
								+ '"/></td></tr>');
		count++;
	}

}

function appendDecksToBench(data) {
	var htmlString = '';
	var indicatorString = '';
	var cards = data['deck'];

	for ( var i in cards) {
		var cardJson = JSON.parse(cards[i].cards);
		for ( var card in cardJson) {
			if (card % 10 == 0) {
				indicatorString += '<li data-target="#carousel-example-generic" data-slide-to="'
						+ card + '"';
			}
			if (card == 0) {
				indicatorString += 'class="active"';
			}
			if (card % 10 == 0) {
				indicatorString += '></li>';
			}
			if (card % 10 == 0) {
				htmlString += '<div class="item';
			}
			if (card == 0) {
				htmlString += ' active';
			}
			if (card % 10 == 0) {
				htmlString += '">';
			}
			htmlString += '<a href="javascript:addCardToHand(' + card
					+ ');"><img src="images/';
			if (cardJson[card]['t'] == 'e') {
				htmlString += 'energy.jpg';
			} else if (cardJson[card]['t'] == 'p') {
				htmlString += 'fire.jpg';
			} else if (cardJson[card]['t'] == 't') {
				htmlString += 'trainer.jpg';
			}
			htmlString += '" alt=""/></a>';
			if ((parseInt(card) + 1) % 10 == 0) {
				htmlString += '</div>';
			}
		}
	}

	$('.carousel-indicators').append(indicatorString);
	$('#decks-card-slider').append(htmlString);
}
$('#upload-decks-submit').click(
		function() {
			var uploadDeck = $('#uploadDecks').val();
			if (uploadDeck == "") {
				alert("Upload Json Must not be blank!");
			} else {
				var jsonData = {
					"deck" : uploadDeck
				};
				$.ajax({
					type : "POST",
					url : "UploadDeck",
					data : jsonData,
					dataType : 'json',
					cache : false,
					timeout : 600000,
					success : function(data) {
						if (data['msg'] != null || data['msg'] != undefined) {
							alert(data['msg']);
							window.location.reload();
						} else if (data['error'] != null
								|| data['error'] != undefined) {
							alert(data['error']);
						}
					},
					error : function(e) {
						alert("Server Error, Please Try Again!");
					}
				});
			}

		});

function getCardDetails(id) {
	var cards = $('#cards-' + id).val().replace(/'/g, '"');
	var cardsJson = JSON.parse(cards);
	$('#view-cards-modal-table').children().remove().end().append(
			'<thead><tr><th>Task</th><th>Name</th></thead>');
	for ( var item in cardsJson) {
		var taskName = '';
		if (cardsJson[item]['t'] == 'e') {
			taskName = 'Energy';
		} else if (cardsJson[item]['t'] == 'p') {
			taskName = 'Pokemon';
		} else if (cardsJson[item]['t'] == 't') {
			taskName = 'Trainer';
		}
		$('#view-cards-modal-table').append(
				'<tbody><tr><td>' + taskName + '</td><td>'
						+ cardsJson[item]['n'] + '</td></tr></tbody>');
	}
	$('#view-cards-modal').modal('show');
}

function addCardToHand(cardPosition) {
	$.ajax({
		type : "POST",
		url : "AddCardToHand",
		data : {
			"cardPosition" : cardPosition
		},
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			if (data['error'] != null || data['error'] != undefined) {
				alert(data['error']);
			}

			window.location.reload();
		},
		error : function(e) {
			alert("Server Error, Please Try Again!");
		}
	});
}

function getHands(bench, playerId, action) {
	document.getElementById('player1').innerHTML = "";
	document.getElementById('player2').innerHTML = "";
	$('#player').html("");
	$
			.ajax({
				type : "POST",
				url : "GetHand",
				data : {
					"bench" : bench.toString(),
					"playerId" : playerId
				},
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					var htmlString = '';

					var hands = JSON.parse(data['bench']);
					for ( var i in hands) {

						htmlString += '<div style="background-image:url(images/';
						if (hands[i]['t'] == 'e') {
							htmlString += 'energy.jpg';
						} else if (hands[i]['t'] == 'p') {
							htmlString += 'fire.jpg';
						} else if (hands[i]['t'] == 't') {
							htmlString += 'trainer.jpg';
						}
						htmlString += ');"><p><a href="javascript:UpdateDeckAndHand('
								+ i
								+ ');"><i class="fa fa-close"></i></a></p><a href="javascript:drawCard('
								+ i + ');" class="draw-button">Draw</a></div>';
					}

					/*
					 * $(data) .each( function(index, value) { for ( var item in
					 * value) { var hands = JSON.parse(value[item]); for ( var i
					 * in hands) {
					 * 
					 * htmlString += '<div
					 * style="background-image:url(images/'; if (hands[i]['t'] ==
					 * 'e') { htmlString += 'energy.jpg'; } else if
					 * (hands[i]['t'] == 'p') { htmlString += 'fire.jpg'; } else
					 * if (hands[i]['t'] == 't') { htmlString += 'trainer.jpg'; }
					 * htmlString += ');"><p><a
					 * href="javascript:UpdateDeckAndHand(' + i + ');"><i
					 * class="fa fa-close"></i></a></p><a
					 * href="javascript:drawCard(' + i + ');"
					 * class="draw-button">Draw</a></div>'; } } });
					 */
					if (data['player2'] == "player2") {
						$('#player2').append(htmlString);
					} else {
						$('#player1').append(htmlString);
					}
					if (action == "no-player") {
						$('#player2')
								.append(
										"<h1 style='color:red;'>Waiting For Player</h1>");
					}
					/* $('#player1').append(htmlString); */
				},
				error : function(e) {
					alert("Your Are Not Registered, Please Try Again!");
				}
			});
}

function UpdateDeckAndHand(cardPosition) {
	$.ajax({
		type : "POST",
		url : "UpdateDeckAndHand",
		data : {
			"cardPosition" : cardPosition
		},
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			if (data['error'] != null || data['error'] != undefined) {
				alert(data['error']);
			}
		}
	});
}

function drawCard(cardPosition) {
	$.ajax({
		type : "POST",
		url : "DrawCardFromHand",
		data : {
			"cardPosition" : cardPosition
		},
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			$(data).each(function(index, value) {
				for ( var item in value) {
					var jsonCard = JSON.parse(value[item]);
					var imageUrl = '';
					if (jsonCard['t'] == 'e') {
						imageUrl = 'energy.jpg';
					} else if (jsonCard['t'] == 'p') {
						imageUrl = 'fire.jpg';
					} else if (jsonCard['t'] == 't') {
						imageUrl = 'trainer.jpg';
					}
				}

				$('#draw-card-player2').attr("src", 'images/' + imageUrl);
				$('.player2').removeClass("turn");
				$('.player1').css("visibility", "visible");

			});
		}
	});
}

function addBoard(player2) {
	$.ajax({
		type : "POST",
		url : "AddBoard",
		data : {
			"player2" : player2
		},
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			if (data['error'] != null || data['error'] != undefined) {
				alert(data['error']);
			}
			window.open("play_game.jsp", "_self");

		}
	});
}

function getBoard() {
	$.ajax({
		type : "POST",
		url : "GetBoard",
		data : null,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			var players = data['board']['players'];
			var decks = data['board']['decks'];
			var play = data['board']['play'];
			var player1 = players[0];
			var player2 = players[1];
			var statusPlayer1 = play[player1]['status'];
			var statusPlayer2 = play[player2];
			var action = "";
			if (statusPlayer2 == undefined) {
				action = "no-player"
			}
			var benchForplayer1 = play[player1]['bench'];
			getHands(benchForplayer1, player1, action);

			if (statusPlayer2 != undefined) {
				var benchForplayer2 = play[player2]['bench'];
				getHands(benchForplayer2, player2, action);
			}
			if (data['error'] != null || data['error'] != undefined) {
				alert(data['error']);
			}

		}
	});
}

function getGameId() {
	$.ajax({
		type : "POST",
		url : "GetGameId",
		data : null,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			if (data['msg'] != null || data['msg'] != undefined) {
				$('#challenger-name').append(data['player1Username']);
				$('.game-start-button').append(
						'<a href="javascript:addBoard(' + data['player1']
								+ ');">Start</a>');
				$('#accept-request-modal').modal('show');
			}
		}
	});
}

function retireGame() {
	$.ajax({
		type : "POST",
		url : "RetireGame",
		data : null,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			if (data['msg'] != null || data['msg'] != undefined) {
				document.getElementById('player1').innerHtml = "";
				$('#player1').append("Retire From Game");
			}
		}
	});
}