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
  <link rel="stylesheet" href="/AppMonitor/bower_components/bootstrap-daterangepicker/daterangepicker.css">
   <link rel="stylesheet" href="/AppMonitor/plugins/timepicker/bootstrap-timepicker.min.css">
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
        Reports        
       </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="${pageContext.request.contextPath}/report">Reports</a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
    
    
    
    <div class="row">
		   <div class="col-md-2"></div>
    
    	<div class="col-md-8">
          <!-- Horizontal Form -->
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Availability Report</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
           
            <form class="form-horizontal" name="reportform" method="post" action="${pageContext.request.contextPath}/reportdata" id="reportform" >
              <div class="box-body">             
                
                
                <div class="col-xs-9">
	            	<div class="form-group">
	                  	<div class="col-xs-4">
	                  	<label>Sub-Tower</label>
	                  	 </div>
	                  	 <div class="col-xs-8">
		                  <select id="subtowerdd" name= "subtowerdd" class="form-control validate[required]">
		                   <option value="" >[ Select One ]</option>			                                  
		                    <c:forEach items="${entityList}" var="entity">	
		                    	<c:if test="${entity.entityValue == 2}">
		                    		 <c:if test="${user.userTower == entity.parentEntity}">		                    	                    
		                    			<option value=" <c:out value="${entity.entityId}" />">  <c:out value="${entity.entityName}" /> </option>
		                    	    </c:if>	
		                    	</c:if>	
                     		 </c:forEach>                      
		                  </select>
		                  </div>
		            </div>            	
	            
	            	 <div class="form-group">
	            	 	<div class="col-xs-4">
	                  	<label>App/Server/Site Name</label>	
	                  	  </div>    
	                  	  <div class="col-xs-8">            	
	                  	 <select  id="serverdd" name= "serverdd" class="form-control">
		                      <option value="" disabled="disabled">[ Select One ]</option>
		                  </select>
		                  </div>
		            </div>  
		            
		             <div class="form-group">
		             <div class="col-xs-4">
		                <label>Date range </label>
		             </div>
		             <div class="col-xs-8">
		                <div class="input-group">
		                  <input type="hidden" name = "startdate" id = "startdate"/>
		                  <input type="hidden" name = "enddate" id = "enddate"/>
		                  <button type="button" class="btn btn-default pull-right" id="daterange-btn">
		                    <span>
		                      <i class="fa fa-calendar"></i> Date range picker
		                    </span>
		                    <i class="fa fa-caret-down"></i>
		                  </button>
		                </div>
		              </div>
		              </div>      
		                      	
	             </div>  
                
                
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                <button type="submit" id="btnSubmit" class="btn btn-info pull-right">Get Report</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
       </div>
    	<div class="col-md-2"></div>
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



	
	
	
<script src="/AppMonitor/bower_components/jquery/dist/jquery.min.js"></script>
<script src="/AppMonitor/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/AppMonitor/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/AppMonitor/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="/AppMonitor/bower_components/moment/min/moment.min.js"></script>
<script src="/AppMonitor/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<script src="/AppMonitor/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<script src="/AppMonitor/dist/js/adminlte.min.js"></script>

<script>
  $(function () {
    $('#reportTable').DataTable({
      'paging'      : true,
      'lengthChange': true,
      'searching'   : true,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : true
    });  
    
   
    
   
    
    $('#subtowerdd').change(function(){
        var subtower$ = $('#subtowerdd option:selected').val();
        $('#serverdd').find('option').remove().end().append('<option value="" >[ Select One ]</option>');       
        $.getJSON('masterDataServlet',
            {          
                subtower : subtower$
            },
            function(response){             
                $.each(response.AppNames, function(index, value){                	
                 
                		$('#serverdd').append('<option value="'+index+'" >'+value+'</option>');
                	
                });             
            });                
    }); 
    
    
  //Date range as a button
    $('#daterange-btn').daterangepicker(
      {
        ranges   : {
          'Today'       : [moment(), moment()],
          'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month'  : [moment().startOf('month'), moment().endOf('month')],
          'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate  : moment()
      },
      function (start, end) {
        $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
        $("#startdate").val(start);
        $("#enddate").val(end);
      });
  
  	
  
    
    $("#btnSubmit").click(function () {
         if ($("#subtowerdd").val() == "") {
           	 alert("Please select a Sub-Tower!");
            return false;
         }else if ($("#serverdd").val() == "") {
           	 alert("Please select a Application/Server!");
             return false;
          }else if ($("#startdate").val() == "" && $("#enddate").val() == "" ) {
            	 alert("Please select a Date Range!");
                 return false;
          }
        return true;
    });
    
    
   
  
  });
  
 
</script>

</body>
</html>