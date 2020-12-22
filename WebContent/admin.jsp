<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<html>
<head>
   <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1"> 
  <title>Application Monitoring Tool</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="icon" href="img/loader.gif">
  <link rel="stylesheet" href="/AppMonitor/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="/AppMonitor/bower_components/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="/AppMonitor/bower_components/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="/AppMonitor/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <link rel="stylesheet" href="/AppMonitor/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/AppMonitor/dist/css/skins/skin-blue.min.css">

 <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> 
</head>

<body class="hold-transition skin-blue layout-top-nav ">
<div class="wrapper">

  <header class="main-header">

      <%@ include file = "header.jsp" %>    
    
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  

  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
       <h1>
        User Administration        
       </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="${pageContext.request.contextPath}/admin">Administration</a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

     	<div class="row">
        <div class="col-xs-12">
          <div class="box">
         	
            <div class="box-header">
              <div class="col-6"> <h3 class="box-title">User's List</h3> </div>
              <div class="col-6"> <a href ="#addRoleModal" class="btn btn-info" data-toggle="modal"> <i class="material-icons"></i><span> Add User </span> </a>
              </div> <span class="alert alert-info" > ${message}</span>               		
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="userrole" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <!-- <th> <span class="custom-checkbox"> <input type="checkbox" id="selectAll"></span></th> -->
                  <th>Net Id</th>
                  <th>Name</th>                   
                  <th>Email</th>
                  <th>Role</th>
                  <th>Tower </th> 
                  <th></th>                  
                </tr>
                </thead>
                <tbody>
                 <c:forEach items="${userList}" var="userList">
                <tr>
                  <td><c:out value="${userList.netId}" /></td>
                  <td><c:out value="${userList.userName}" /></td>   
                  <td><c:out value="${userList.emailId}" /></td> 
                  <td><c:out value="${entityMap[userList.userRole]}" /></td> 
                  <td><c:out value="${entityMap[userList.userTower]}" /></td>
                  <td>
                 	<button type="button" class="btn btn-info editbtn" data-toggle="modal" data-target="#editRoleModal" data-netid="${userList.netId}" data-role="${userList.userRole}">Edit</button>
                  </td>               
                </tr>
                </c:forEach>
                </tbody>
               
              </table>
            </div>
            <!-- /.box-body -->
          </div>
      </div>
      </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->


	


  <!-- Main Footer -->
  <footer class="main-footer">
   <%@ include file = "footer.jsp" %>    
  </footer>

 
  <div class="control-sidebar-bg"></div>
</div>


<div id ="addRoleModal" class="modal modal-info fade">
		<div class="modal-dialog">
			<div class="modal-content">
		   		<form name="adminform" method="post" action="${pageContext.request.contextPath}/adduser" id="adminform" >
					<div class="modal-header">
						<h4 class="modal-title"> Add User Role</h4>
					    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>						   		
		   			<div class="modal-body">
		   				<div class="box-body">
						
							<div class="form-group">
							  <input type= "hidden" name="page" id="page" value="">			                 
			                  <label  class="col-sm-4 control-label">User's Net Id</label>
			                  <div class="col-sm-8">
			                    <input type="text" id="netId" name="netId"  class="form-control" required="required">
			                  </div>
			                </div>
							
							<div class="form-group">                 	
			                  <label class="col-sm-4 control-label">Select Tower</label>			
			                  <div class="col-sm-8">
			                   <select id="towerid" name= "towerid" class="form-control" >	
			                    <c:forEach items="${entityList}" var="entity">	
			                    	<c:if test="${entity.entityValue == 1}">	                    
			                    		<option value=" <c:out value="${entity.entityId}" />">  <c:out value="${entity.entityName}" /> </option>
			                    	</c:if>	
			                     </c:forEach>                      
			                  </select>
			                  </div>			                 
			                </div>
							
							<div class="form-group">
							     <label class="col-sm-4 control-label">User Role </label>
							    <div class="col-sm-8">
							    <c:forEach items="${entityList}" var="entity">	
			                    	<c:if test="${entity.entityValue == 3}">	  
										<input type="radio" name="role" id="role" value="${entity.entityId}" required="required"> <c:out value="${entity.entityName}" /> 
									</c:if>	
			                     </c:forEach> 
								</div>												
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input  type="submit" class="btn btn-default" value="Save">
					</div>
					
		   		</form>
		    </div>		
		</div>
	</div>
	
	
	<div id ="editRoleModal" class="modal modal-info fade">
		<div class="modal-dialog">
			<div class="modal-content">
		   		<form name="adminform" method="post" action="${pageContext.request.contextPath}/edituser" id="adminform" >
					<div class="modal-header">
						<h4 class="modal-title"> Edit User's Role</h4>
					    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>						   		
		   			<div class="modal-body">
		   				<div class="box-body">
											
							<div class="form-group">			                 
			                  <label  class="col-sm-4 control-label">User's Net Id</label>
			                  <div class="col-sm-8">
			                    <input type="text" id="netId" name="netId"  class="form-control" readonly="readonly">
			                  </div>
			                </div>
							
							<div class="form-group">                 	
			                  <label class="col-sm-4 control-label">Select Tower</label>			
			                  <div class="col-sm-8">
			                   <select id="towerid" name= "towerid" class="form-control" >			                                   
			                    <c:forEach items="${entityList}" var="entity">	
			                    	<c:if test="${entity.entityValue == 1}">	                    
			                    		<option value=" <c:out value="${entity.entityId}" />">  <c:out value="${entity.entityName}" /> </option>
			                    	</c:if>	
			                     </c:forEach>                      
			                  </select>
			                  </div>			                 
			                </div>
							
							<div class="form-group">
							     <label class="col-sm-4 control-label">User Role </label>
							    <div class="col-sm-8">
							    <c:forEach items="${entityList}" var="entity">	
			                    	<c:if test="${entity.entityValue == 3}">	  
										<input type="radio" name="role" id="role" value="${entity.entityId}"> <c:out value="${entity.entityName}" /> 
									</c:if>	
			                     </c:forEach> 
								</div>												
							</div>
							
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input  type="submit" class="btn btn-default" value="Save">
					</div>
					
		   		</form>
		    </div>		
		</div>
	</div>
<script src="/AppMonitor/bower_components/jquery/dist/jquery.min.js"></script>
<script src="/AppMonitor/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/AppMonitor/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/AppMonitor/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="/AppMonitor/dist/js/adminlte.min.js"></script>

<script>
  $(function () {
    $('#userrole').DataTable({
      'paging'      : true,
      'lengthChange': true,
      'searching'   : true,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : true
    });
    
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click( function(){
    	if(this.checked){
    		checkbox.each(function(){ this.checked = true;});
    	}else{
    		checkbox.each(function(){ this.checked = false;});
    	}
    });
    
    
    checkbox.click(function(){
    	if(!this.checked){ $("#selectAll").prop("checked",false);}	
    });   
    
    $('#addRole').click( function(){
    	$netId = $("#netId").value();
    	$role = $("#role").value();
    	$('#addRoleModal').modal('hide');
    	
    });
    
   
    $('#editRoleModal').on('show.bs.modal', function(e) {    	
    	$("#editRoleModal .modal-body #netId").val(e.relatedTarget.dataset.netid);
    	
    	if(e.relatedTarget.dataset.role=='ADMIN'){
    		$("#editRoleModal .modal-body #admin").prop('checked', true);
    		//$("#editRoleModal .modal-body #role").val(e.relatedTarget.dataset.role);
    	}else{
    		$("#editRoleModal .modal-body #user").prop('checked', true);
    		//$("#editRoleModal .modal-body #role").val(e.relatedTarget.dataset.role);
    	}
    	
    });
    
  
    
  });
 
</script>

</body>
</html>