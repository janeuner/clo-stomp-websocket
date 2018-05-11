var stompClient = null;
var psid = null;

var clientJoin = null;
var characterSelect = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#nameInput").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#msgInput").prop("disabled", !connected);
//    if (connected) {
//        $("#conversation").show();
//    }
//    else {
//        $("#conversation").hide();
//    }
    
    if (!connected) {
        $("#messages").html("");
    	clientJoin = null;
    	characterSelect = null;
    }
}

function receive(serverMessage) {
	var msg = JSON.parse(serverMessage.body);
	if ((typeof msg == "object") && (typeof msg.psid == "string"))
		psid = msg.psid;
	
	if (typeof msg.type == "string") {
		
		if (msg.type == "gameSetup")
			handleGameSetup(msg);
		
		else if (msg.type == "chatHistory") 
			handleChatHistory(msg);
		
		else if (msg.type == "error") 
			handleError(msg);
	}
}

function connect() {
    var socket = new SockJS('/clo');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //console.log('Connected: ' + frame);
        $("#connect").prop("disabled", true);
        $("#nameInput").prop("disabled", true);
        $("#disconnect").prop("disabled", true);
        
        stompClient.subscribe('/user/queue/server', receive);
        
        sendClientJoin($("#nameInput").val());
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    //console.log("Disconnected");
}

/////////////// START MESSAGE HANDLERS ///////////////
/////////////// START MESSAGE HANDLERS ///////////////
/////////////// START MESSAGE HANDLERS ///////////////

function handleGameSetup(msg) {
    setConnected(true);
    //TODO: Update connected player list
}

function handleChatHistory(msg) {
	showChatHistory(msg);	
}

function handleError(msg) {

	var errItem = $("#errorPanelItemTemplate").clone();
	errItem[0].id = "";
	if (typeof msg.errorMessage == "string")
		errItem[0].children[1].innerText = msg.errorMessage;
	else
		errItem[0].children[1].innerText = "Unknown error";
	$("#errorPanel").append(errItem);
	errItem.show();
	
	if (typeof msg.errorType == "string") {
		
		if (msg.errorType == "clientJoin") {
			disconnect();
		}
	}
}

//////////////// END MESSAGE HANDLERS ////////////////
//////////////// END MESSAGE HANDLERS ////////////////
//////////////// END MESSAGE HANDLERS ////////////////

function makeMessage(type) {
	var message = {};
	message.type = type;
	if (typeof psid == "string")
		message.psid = psid;
	message.mid = uuidv4();
	return message;
}

function sendClientJoin(playerName, sessionPassword) {
	var message = makeMessage("clientJoin");
	if (typeof sessionPassword == "string")
		message.sessionPassword = sessionPassword;
	message.playerName = playerName
	stompClient.send("/client/message", {}, JSON.stringify(message));
}

function sendChat() {
	var message = makeMessage("chat");
	message.msg = $("#msgInput").val();
    stompClient.send("/client/message", {}, JSON.stringify(message));
    $("#msgInput").val("");
    $("#msgInput").focus();
}

//function showChat(message) {
//    $("#messages").append("<tr><td>" + message + "</td></tr>");
//}

function showChatHistory(ch) {
	var messages = $("#messages"); 
	if ((ch != null) && (typeof ch.msgList == "object")) {
	    messages.html("");
	    for (var i = 0; i < ch.msgList.length; i++) {
	    	var from = ch.msgList[i].playerName;
	    	var msg = ch.msgList[i].msg;
	    	if ((typeof from == "string") && (typeof msg == "string"))
	    		messages.append("<tr><td>[" + from + "] "+ msg + "</td></tr>");
	    }
	    if (messages[0].childElementCount > 0)
	    	messages[0].lastChild.scrollIntoView();
	}
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendChat(); });
});
