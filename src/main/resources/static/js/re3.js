$(function(){
	console.log("replace history");

	var pathname = window.location.pathname;
	if (pathname.indexOf("regist") != -1) {
		console.log("regist");
		var state = {a:1, b:2};
		window.history.replaceState(state, "result", "/redirect3/result/123");
		//window.history.pushState(state, "step3", pathname);
	}

	//戻る・進むボタンがクリックされたとき
	$(window).on("popstate", function(event) {
		console.log("*** popstate ***", event);
		console.log(event.originalEvent.state);
		alert("location: " + document.location + ", state: " + JSON.stringify(event.state));
	//	e.preventDefault();
	});

});

//window.onpopstate = function(event) {
//	alert("location: " + document.location + ", state: " + JSON.stringify(event.state));
//};