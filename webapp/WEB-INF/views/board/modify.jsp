<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

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

<form role="form" method="post" action="/board/modify">
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
	</div>
	<!-- /.box-body -->
</form>


	<div class="box-footer">
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
		
		$(".btn-primary").on("click", function() {
			formObj.submit();
		});
		
		$(".btn-warning").on("click", function() {
			self.location = "/board/listAll";
		});
	});
</script>

<%@include file="../include/footer.jsp"%>
