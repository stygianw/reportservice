
	var interval;
	var serverLink;
	
	var circle = new ProgressBar.Circle('#example-percent-container', {
		color : '#FCB03C',
		strokeWidth : 3,
		trailWidth : 1,
		duration : 1000,
		text : {
			value : '0'
		},
		step : function(state, bar) {
			bar.setText((bar.value() * 100).toFixed(0));
		}
	});
	
	function animate(val) {
		circle.animate(val / 100);
	}
	
	
	
	function getData() {
			var interval = setInterval(function(){
			$.ajax({
				url : serverLink + "ajax/progress",
				method : "GET",

			}).done(function(response) {
				animate(response);
				if (parseInt(response) >= 100) {
					
					clearInterval(interval);
					
					setTimeout(function(){
						$("#success").css("display", "block");
							
					}, 2500);
					setTimeout(function(){
						window.location = serverLink + "report/show";	
					}, 3500);
					
				}
			});	
			
		}, 1000);	
		
	}
	
	function beginQuery(link) {
		serverLink = link;
		getData();
	}