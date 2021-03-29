let index = {
	init: function() {
		$("#btn-save").on("click", () => { //function(){}, ()=>{} this를 바인딩 하기위해서 사용함.
			this.save();
		});
		$("#btn-delete").on("click", () => { //function(){}, ()=>{} this를 바인딩 하기위해서 사용함.
			this.delete();
		});
		$("#btn-update").on("click", () => { //function(){}, ()=>{} this를 바인딩 하기위해서 사용함.
			this.update();
		});
	},

	save: function() {
		//alert("user의 save함수 호출");
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글 등록이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	delete: function() {
		var id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/boardDelete/"+id,
			dataType: "json"
		}).done(function(resp) {
			alert("글 삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	update: function() {
		//alert("user의 save함수 호출");
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({
			type: "PUT",
			url: "/api/boardUpdate/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글 수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();