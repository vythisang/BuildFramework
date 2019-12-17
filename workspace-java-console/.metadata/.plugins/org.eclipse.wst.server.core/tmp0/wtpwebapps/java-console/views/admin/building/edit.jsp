<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>         
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>Chinh sua toa nha</title>

	</head>

	<body class="no-skin">

			<div class="navbar-container" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

			</div><!-- /.navbar-container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar                  responsive">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>


				


				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>

			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>
							<li class="active">Dashboard</li>
						</ul><!-- /.breadcrumb -->

					</div>

					<div class="page-content">						
						<div class="row">
							<div class="col-xs-12">
								<form class="form-horizontal" id="formEdit">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="name"> TÃªn tÃ²a nhÃ  </label>

										<div class="col-sm-9">
											<input type="text" id="name"  class="form-control" name="name" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="numberOfBasement"> Sá» táº§ng háº§m </label>

										<div class="col-sm-9">
											<input type="number" id="numberOfBasement"  class="form-control" name="numberOfBasement" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" > Loáº¡i tÃ²a nhÃ  </label>

										<div class="col-sm-9">
											<label class="checkbox-inline"><input type="checkbox" value="TANG_TRET" id="buildingTypes" name="buildingTypes">Táº§ng trá»t</label>
											<label class="checkbox-inline"><input type="checkbox" value="NGUYEN_CAN" id="buildingTypes" name="buildingTypes">NguyÃªn CÄn</label>
											<label class="checkbox-inline"><input type="checkbox" value="NOI_THAT" id="buildingTypes" name="buildingTypes">Ná»i Tháº¥t</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="areaRent"> Diá»n tÃ­ch thuÃª </label>
										<div class="col-sm-9">
											<input type="text" id="areaRent"  class="form-control" name="areaRent" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" ></label>
										<div class="col-sm-9">
											<button type="button" class="btn btn-primary" id="btnAddBuilding">ThÃªm tÃ²a nhÃ </button>
											<button type="button" class="btn btn-primary">Há»§y</button>
										</div>
									</div>
								</form>
								
							</div>
						</div><!-- /.page-header -->

						
					</div>
				</div><!-- /.main-content -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!-- Modal -->
		<div class="modal fade" id="assignmentBuildingModal" role="dialog">
			<div class="modal-dialog">
			
			  <!-- Modal content-->
			  <div class="modal-content">
				<div class="modal-header">
				  <button type="button" class="close" data-dismiss="modal">&times;</button>
				  <h4 class="modal-title">Danh sÃ¡ch nhÃ¢n viÃªn </h4>
				</div>
				<div class="modal-body">
				  <p>Some text in the modal.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Giao tÃ²a nhÃ </button>
				  	<button type="button" class="btn btn-default" data-dismiss="modal">ÄÃ³ng</button>
				</div>
			  </div>
			  
			</div>
		</div>
	


		
		
	</body>
</html>
