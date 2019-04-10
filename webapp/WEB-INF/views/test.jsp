<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ajax Test Page</title>
		
	<style>
		#modDiv {
			width: 300px;
			height: 100px;
			background-color: gray;
			position: absolute;
			top: 50%;
			left: 50%;
			margin-top: -50px;
			margin-left: -150px;
			padding: 10px;
			z-index: 1000;
		}
	</style>
	
	<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	
	<script type="text/javascript">
		var bno = 1;

		function getAllList() {
			$.getJSON("/replies/all/" + bno, function(data) {
				console.log(data.length);
				
				var str = "";
				$(data).each(function() {
					str += "<li data-rno='" + this.rno + "' class='replyLi'>"
						+  this.rno + ":" + this.replytext
						+  "<button>MOD</button></li>";
				});
				
				$("#replies").html(str);
			});
		}
		
		$(document).ready(function() {
			$("#replyAddBtn").on("click", function() {
				var replyer = $("#newReplyWriter").val();
				var replytext = $("#newReplyText").val();
				
 				$.ajax({
					type: 'post',
					url: "/replies",
					headers : {
						"Content-Type": "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType: 'text',
					data: JSON.stringify({
						bno: bno,
						replyer: replyer,
						replytext: replytext
					}),
					success: function(result) {
						if (result == 'SUCCESS') {
							alert('등록되었습니다.');
							getAllList();
						} else {
							alert(result);
						}
					}
				});
			});
			
			$("#replies").on("click", ".replyLi button", function() {
				var reply = $(this).parent();
				var rno = reply.attr("data-rno");
				var replytext = reply.text();
				
				$(".modal-title").html(rno);
				$("#replytext").val(replytext);
				$("#modDiv").show("slow");
			});
			
			$("#replyDelBtn").on("click", function() {
				var rno = $(".modal-title").text();
				var replytext = $("#replytext").val();
				
				$.ajax({
					type: 'delete',
					url: "/replies/" + rno,
					headers : {
						"Content-Type": "application/json",
						"X-HTTP-Method-Override" : "DELETE"
					},
					success: function(result) {
						if (result == 'SUCCESS') {
							alert('삭제되었습니다.');
							$("#modDiv").hide("slow");
							
							getAllList();
						} else {
							alert(result);
						}
					}
				});
			});
			
			$("#replyModBtn").on("click", function() {
				var rno = $(".modal-title").text();
				var replytext = $("#replytext").val();
				
				$.ajax({
					type: 'put',
					url: "/replies/" + rno,
					headers : {
						"Content-Type": "application/json",
						"X-HTTP-Method-Override" : "PUT"
					},
					dataType: 'text',
					data: JSON.stringify({
						replytext: replytext
					}),
					success: function(result) {
						if (result == 'SUCCESS') {
							alert('수정되었습니다.');
							$("#modDiv").hide("slow");
							
							getAllList();
						} else {
							alert(result);
						}
					}
				});
			});
			
			
			
			getAllList();
		});
	</script>
</head>

<body>
	<h2>Ajax Test Page</h2>
	
	<div>
		<div>
			REPLYER <input type='text' name='replyer' id='newReplyWriter'>
		</div>
		<div>
			REPLY TEXT <input type='text' name='replytext' id='newReplyText'>
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	<ul id="replies">
		
	</ul>


	<div id='modDiv' style="display: none;">
		<div class='modal-title'></div>
		<div>
			<input type='text' id='replytext'>
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">DELETE</button>
			<button type="button" id='closeBtn'>Close</button>
		</div>
	</div>
</body>
</html>