$(function() {
	console.log("main js");
	console.log("length:", window.history.length);
	console.log("state:", window.history.state);
	console.log("location:", window.location);

	if (window.history && window.history.pushState) {
		console.log("history OK");
	} else {
		console.log("history NG");
	}

	$(".label-primary").on("click", function(){
		console.log("*** click ***");
	});

	$(".back").on("click", function(){
		console.log("*** back ***");
		history.back();
	});

	$(".forward").on("click", function(){
		console.log("*** forward ***");
		history.forward();
	});

	var ele = document.getElementById('foo');

	ele.addEventListener('touchstart',
		function(event) {
			event.preventDefault();
			alert('foo touchstart');
		},
		{capture: true, passive: true}
	);

	sub2();

});
