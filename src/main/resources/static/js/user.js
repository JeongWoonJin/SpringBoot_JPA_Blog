let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{ //function(){}, ()=>{} this를 바인딩 하기위해서 사용함.
				this.save();
			});
			$("#btn-login").on("click", ()=>{ //function(){}, ()=>{} this를 바인딩 하기위해서 사용함.
				this.login();
			});
		},
		
		save: function(){
			//alert("user의 save함수 호출");
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
			};
			//console.log(data);
			
			//ajax호출시 default가 비동기 호출.
			//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
			$.ajax({
				type: "POST",
				url: "/blog/api/user",
				data: JSON.stringify(data), //http body 데이터
				contentType: "application/json; charset=utf-8", //body데이터가 어떤타입인지(MIME)
				dataType: "json"//응답받을 데이터
			}).done(function(resp){
				alert("회원가입이 완료되었습니다.");
				//console.log(resp);
				location.href="/blog";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		},
		
		login: function(){
			//alert("user의 save함수 호출");
			let data = {
				username: $("#username").val(),
				password: $("#password").val()
			};
			
			$.ajax({
				type: "POST",
				url: "/blog/api/user/login",
				data: JSON.stringify(data), //http body 데이터
				contentType: "application/json; charset=utf-8", //body데이터가 어떤타입인지(MIME)
				dataType: "json"//응답받을 데이터
			}).done(function(resp){
				alert("로그인이 완료되었습니다.");
				//console.log(resp);
				location.href="/blog";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		}
}

index.init();