<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../include/header.jsp"%>

<!-- handlebars -->
<script src="/resources/handlebars/handlebars-v4.1.2.js"></script>

<script src="/resources/js/upload.js" type="text/javascript"></script>

<style>
.fileDrop {
  width: 80%;
  height: 100px;
  border: 1px dotted gray;
  background-color: lightslategrey;
  margin: auto;
  
}
</style>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">BOARD</h3>
				</div>
				<!-- /.box-header -->

<form role="form" method="post" action="modifyPage">
	<input type="hidden" name="page" value="${cri.page}">
	<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
	<input type="hidden" name="searchType" value="${cri.searchType}">
	<input type="hidden" name="keyword" value="${cri.keyword}">

	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">Bno</label> 
			<input type="text" name="bno" class="form-control" value="${boardVO.bno}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label> 
			<input type="text" name="title" class="form-control" value="${boardVO.title}">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> 
			<input type="text" name="writer" class="form-control" readonly="readonly" value="${boardVO.writer}">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">File DROP Here</label>
			<div class="fileDrop"></div>
		</div>
	</div>
	<!-- /.box-body -->
</form>


	<div class="box-footer">
		<div>
			<hr>
		</div>
		<ul class="mailbox-attachments clearfix uploadedList"></ul>
		<button type="button" class="btn btn-primary">Save</button>
		<button type="button" class="btn btn-warning">Cancel</button>
	</div>

			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script>
	$(document).ready(function() {
		var formObj = $("form[role='form']");
		var bno = ${boardVO.bno};
		
		$.getJSON("/sboard/getAttach/" + bno, function(list) {
			$(list).each(function() {
				var fileInfo = getFileInfo(this);
				var html = template(fileInfo);
				$(".uploadedList").append(html);
			});
		});
		
		$(".btn-primary").on("click", function() {
			formObj.submit();
		});
		
		$(".btn-warning").on("click", function() {
			self.location = "/sboard/list?page=${cri.page}&perPageNum=${cri.perPageNum}"
						  + "&searchType=${cri.searchType}&keyword=${cri.keyword}";
		});
	});
</script>

<%@include file="../include/footer.jsp"%>

<script id="template" type="text/x-handlebars-template">
<li>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">
		{{fileName}}
    </a>
	<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn">
		<i class="fa fa-fw fa-remove"></i>
	</a>
  </div>
</li>                
</script> 

<script>
	var template = Handlebars.compile($("#template").html());
	
	$(".fileDrop").on("dragenter dragover", function(event) {
		event.preventDefault();
	});
	
	$(".fileDrop").on("drop", function(event) {
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		
		var file = files[0];
		
		var formData = new FormData();
		formData.append("file", file);
		
		$.ajax({
			type: "post",
			url: "/uploadAjax",
			dataType: "text",
			data: formData,
			processData: false,
			contentType: false,
			success: function(data) {
				var fileInfo = getFileInfo(data);
				var html = template(fileInfo);
				$(".uploadedList").append(html);
 			}
		});
	});
	
	$("form[role='form']").submit(function(event) {
		event.preventDefault();
		
		var that = $(this);
		
		var str = "";
		
		$(".uploadedList .delbtn").each(function(index) {
			str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "'>";
		});
		
		that.append(str);
		
		that.get(0).submit();
	});
	
	$(".uploadedList").on("click", ".delbtn", function(event) {
		
		event.preventDefault();
		
		var that = $(this);
		
		$.ajax({
			type: "post",
			url: "/deleteFile",
			dataType: "text",
			data: {fileName:$(this).attr("href")},
			success: function(result) {
				if (result == 'deleted') {
					that.closest("li").remove();
				}
			}
		});
	})
</script>
