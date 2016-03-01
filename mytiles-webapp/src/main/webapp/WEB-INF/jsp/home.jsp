<!DOCTYPE html>
<html lang="en">
	<head>
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	  <title>MyTiles</title>
	
	  <!-- Bootstrap -->
	  <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	  
	  <link rel="stylesheet" type="text/css" href="/css/jquery-ui.min.css">
	  <link rel="stylesheet" type="text/css" href="/css/jquery-ui.structure.min.css">
	
	  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	  <!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	  <![endif]-->
	</head>
	
	<body role="document" onload="disconnect()">
		<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
		    Javascript and reload this page!</h2></noscript>
		    
        <nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">MyTiles</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="/home">Home</a></li>
						<li><a href="/about">About</a></li>
						<li><a href="/contact">Contact</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Settings <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li role="separator" class="divider"></li>
								<li class="dropdown-header">Nav header</li>
								<li><a href="#">Separated link</a></li>
								<li><a href="#">One more separated link</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</nav>
        
        <div class="container theme-showcase" role="main">
			<div class="page-header"></div>
			<div id="home2" class="page-header">
                <h1>Login</h1>			
	            <span id="nameDiv">
	                <label>username :</label>&nbsp;<input type="text" id="name" />
	            </span>
			    <span>
			        <button class="btn btn-xs btn-success" id="connect">Connect</button>
			        <button class="btn btn-xs btn-danger" id="disconnect" disabled="disabled">Disconnect</button>
			    </span>
            </div>
            <div class="page-header">    
			    <div id="tileBoardDiv" style="width:100%; height:800px;">
				    <span id="draggable359ac820-029f-11e5-b939-0800200c9a66" class="label label-primary ui-widget-content" style="font-size:20px;">Funny Word</span>
				    <span id="draggable468dc560-029f-11e5-b939-0800200c9a66" class="label label-primary ui-widget-content" style="font-size:20px;">Halarious Word</span>
			    </div>
			    
			    <div id="conversationDiv">
			        <p id="response"></p>
			    </div>          
			</div>
			
			<div id="about" class="page-header">
                <h1>About info here</h1>
			</div>
	
	        <div id="contact" class="page-header">
                <h1>Contact info here</h1>
	        </div>
        </div>
        		
        <script src="/js/sockjs-0.3.4.js"></script>
	    <script src="/js/stomp.js"></script>
	    <script src="/js/jquery-2.1.4.min.js"></script>
	    <script src="/js/jquery-ui.min.js"></script>
	    <script src="/bootstrap/js/bootstrap.min.js"></script>
	    <script type="text/javascript">
	        var stompClient = null;
	        
	        $(function() {
	            $('#conversationDiv').hide();
	            $( "#draggable359ac820-029f-11e5-b939-0800200c9a66" ).hide();
	            $( "#draggable468dc560-029f-11e5-b939-0800200c9a66" ).hide();
	            
	            $("#draggable359ac820-029f-11e5-b939-0800200c9a66").draggable({
	            	containment: "parent",
	            	opacity: 0.35,
	            	revert: function (droppable) {
	            	    return droppable && $("#draggable359ac820-029f-11e5-b939-0800200c9a66").addClass('ui-draggable-reverted');
	            	},
	                stop: function (event,ui) {
	                	if ($("#draggable359ac820-029f-11e5-b939-0800200c9a66").hasClass('ui-draggable-reverted')) {
	                		$("#draggable359ac820-029f-11e5-b939-0800200c9a66").removeClass('ui-draggable-reverted');
	                	} else {
	                	   sendTileMovement("359ac820-029f-11e5-b939-0800200c9a66",ui);
	                	}
	                }   
	            });
				$("#draggable359ac820-029f-11e5-b939-0800200c9a66").droppable({
				    greedy: true,
				    tolerance: 'touch',
				 });
	            
	            $("#draggable468dc560-029f-11e5-b939-0800200c9a66").draggable({
	            	containment: "parent",
	            	opacity: 0.35,
                    revert: function (droppable) {
                        return droppable && $("#draggable468dc560-029f-11e5-b939-0800200c9a66").addClass('ui-draggable-reverted');
                    },
	                stop: function (event,ui) {
	                    if ($("#draggable468dc560-029f-11e5-b939-0800200c9a66").hasClass('ui-draggable-reverted')) {
	                        $("#draggable468dc560-029f-11e5-b939-0800200c9a66").removeClass('ui-draggable-reverted');
	                    } else {
	                    	sendTileMovement("468dc560-029f-11e5-b939-0800200c9a66",ui);
	                    }
	                         
	                }
	            });
                $("#draggable468dc560-029f-11e5-b939-0800200c9a66").droppable({
                    greedy: true,
                    tolerance: 'touch',
                 });	            
	            
	            $("#connect").click(function() {
	                connect();
	            })
	            
	            $("#disconnect").click(function() {
	                disconnect();
	            })          
	        });
	
	        function connect() {
	            var socket = new SockJS('http://localhost:9090/mytiles/');
	            stompClient = Stomp.over(socket);
	            stompClient.connect({}, function(frame) {
	                setConnected(true);
	                console.log('Connected: ' + frame);
	                stompClient.subscribe('/topic/tileUpdate', function(movement){
	                    updateTileMovement(JSON.parse(movement.body));
	                });
	            });
	        }
	
	        function disconnect() {
	            if (stompClient != null) {
	                stompClient.disconnect();
	            }
	            setConnected(false);
	            console.log("Disconnected");
	        }
	
	        function setConnected(connected) {
	            $('#connect').prop('disabled',connected);
	            $('#disconnect').prop('disabled',!connected);
	            $('#name').prop('disabled',connected);
	            if (connected) {
	                $('#conversationDiv').show();
	                $( "#draggable359ac820-029f-11e5-b939-0800200c9a66" ).show();
	                $( "#draggable468dc560-029f-11e5-b939-0800200c9a66" ).show();
	                
	                $.get("http://localhost:9080/tileplacements/tileboard/07270496-d622-4351-a4df-d4f28b31fe5c/tilebag/7194d3ea-b2be-4e1f-b606-8d5867bb5a34", function(placements) {
	                    console.log('Getting current tile placements');
	                    $.each(placements, function(index, placement) {
	                        console.log('Tileplacement: ' + placement);
	                        updateTilePlacement(placement);
	                    });
	                    console.log('DONE: Getting current tile placements');
	                });
	            } else {
	                $('#conversationDiv').hide();
	                $( "#draggable359ac820-029f-11e5-b939-0800200c9a66" ).hide();
	                $( "#draggable468dc560-029f-11e5-b939-0800200c9a66" ).hide();
	            }
	            $('#response').html('');            
	        }
	
	        function sendTileMovement(id,ui) {
	            var name = $('#name').val();
	            var now = new Date();
	            stompClient.send("/app/tile/move", {}, JSON.stringify({ 
	            	'tileBoardId': '07270496-d622-4351-a4df-d4f28b31fe5c',
	            	'tileBagId': '7194d3ea-b2be-4e1f-b606-8d5867bb5a34',
	            	'tileId': id,
	                'tileUserId': name,
	                'tileTimestamp': now.getTime(),
	                'tileX': ui.position.left,
	                'tileY': ui.position.top,
	                'tileZ': 0
	            }));
	        }
	
	        function updateTileMovement(message) {
	            var draggable = $("#draggable" + message.tileId);
	            draggable.animate({
	                top: message.tileY,
	                left: message.tileX
	            }, 200);
	            
	            var response = $('#response');
	            var p = document.createElement('p');
	            p.style.wordWrap = 'break-word';
	            p.appendChild(document.createTextNode(message.tileId 
	                    + ", " + message.tileUserId 
	                    + ", " + message.tileTimestamp
	                    + ", " + message.tileX
	                    + ", " + message.tileY
	                    + ", " +   message.tileZ));
	            response.append(p);
	        }
	        
	        function updateTilePlacement(placement) {
	            var draggable = $("#draggable" + placement.tileId);
	            draggable.animate({
	                top: placement.y,
	                left: placement.x
	            }, 200);
	        }        
	    </script>
	</body>
</html>