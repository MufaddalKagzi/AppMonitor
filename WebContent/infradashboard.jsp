<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page import="com.tcs.appmonitor.util.Constants" %>

<c:set var="SUB_TOWER_GES" scope="page" value="<%=Constants.SUB_TOWER_GES%>"/>
<c:set var="SUB_TOWER_CLEAR_CASE" scope="page" value="<%=Constants.SUB_TOWER_CLEAR_CASE%>"/>
<c:set var="SUB_TOWER_TEAMCENTER" scope="page" value="<%=Constants.SUB_TOWER_TEAMCENTER%>"/>
<c:set var="SUB_TOWER_POLARION" scope="page" value="<%=Constants.SUB_TOWER_POLARION%>"/>
<c:set var="SUB_TOWER_PLASTIC" scope="page" value="<%=Constants.SUB_TOWER_PLASTIC%>"/>
<c:set var="SUB_TOWER_CM_SYNERGY" scope="page" value="<%=Constants.SUB_TOWER_CM_SYNERGY%>"/>
<c:set var="SUB_TOWER_DOORS" scope="page" value="<%=Constants.SUB_TOWER_DOORS%>"/>
<c:set var="SUB_TOWER_JIRA" scope="page" value="<%=Constants.SUB_TOWER_JIRA%>"/>

<c:set var="STATUS_UP" scope="page" value="<%=Constants.STATUS_UP%>"/>
<c:set var="STATUS_DOWN" scope="page" value="<%=Constants.STATUS_DOWN%>"/>


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
  <link rel="stylesheet" href="/AppMonitor/bower_components/morris.js/morris.css">
  <link rel="stylesheet" href="/AppMonitor/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/AppMonitor/dist/css/skins/skin-blue.min.css">

 <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> 
    <script>
   	function timedRefresh(timeoutPeriod) {
	setTimeout("location.reload(true);",timeoutPeriod);
	}window.onload = timedRefresh(500000);   
</script>
</head>  
 

<body class="hold-transition skin-blue layout-top-nav ">
<div class="wrapper">

  <header class="main-header">

     <%@ include file = "header.jsp" %>    
    
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  
 <section class="content-header">
      <h1>
        Infra Dashboard
        <small>Version 1.0</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
      </ol>
    </section>
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
   

    <!-- Main content -->
    <section class="content container-fluid">
			
	   <div class="col-md-12">
	   
	   		<div class="nav-tabs-custom">
		            <ul class="nav nav-tabs">
		              <li class="active" ><a href="#tab_1" data-toggle="tab">${entityMap[SUB_TOWER_CLEAR_CASE]}</a></li>
		              <li ><a href="#tab_2" data-toggle="tab">${entityMap[SUB_TOWER_TEAMCENTER]}</a></li>
		              <li><a href="#tab_3" data-toggle="tab">${entityMap[SUB_TOWER_GES]}</a></li> 
		              <li><a href="#tab_4" data-toggle="tab">${entityMap[SUB_TOWER_POLARION]}</a></li> 
		              <li><a href="#tab_5" data-toggle="tab">${entityMap[SUB_TOWER_PLASTIC]}</a></li>  
		              <li><a href="#tab_6" data-toggle="tab">${entityMap[SUB_TOWER_DOORS]}</a></li> 
		              <li><a href="#tab_7" data-toggle="tab">${entityMap[SUB_TOWER_CM_SYNERGY]}</a></li> 
		              <li><a href="#tab_8" data-toggle="tab">${entityMap[SUB_TOWER_JIRA]}</a></li>  
		                       
		            </ul>
		            <div class="tab-content">
		              <div class="tab-pane active" id="tab_1">	
		              	<div class="box">				            
				            <div class="box-body">
				                <c:if test="${fn:length(cchtml) == 0 }">	
				                	 <h4><i class="icon fa fa-warning"></i>   <font color="red" >Latest file not available.</font> </h4>	
				                 </c:if> 
				            	
				            	<c:forEach items="${cchtml}" var="cchtml">	
				            		  	<div class="col-md-6">	
				            		  	   <div class="box">
				            		  		 <div class="box-header with-border"> 
				            		  		 	<h3 class="box-title"><i class="fa fa-server"></i>&nbsp;<font color= "blue"><c:out value="${cchtml.value.get(0)}"/></font></h3>
				            		  		  </div> 				            		  		  
				            		  		  <div class="box-body">
				            		  		  		<i class="fa fa-laptop"></i>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${cchtml.value.get(1)}"/><br>
				            		  		  		<i class="fa fa-floppy-o">Available Disk </i>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${cchtml.value.get(2)}"/> <br>
				            		  		  		<i class="fa fa-refresh"> Running </i>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${cchtml.value.get(3)}"/>
				            		  		  </div>  		
				            			 	</div> 				            			 
				            			</div>
				            		</c:forEach>
				            
				            
				            </div>
				        </div>
				      </div>
				      
				      
				      <div class="tab-pane" id="tab_2">	
		              	<div class="box">				            
				            <div class="box-body">	
				            		
				            		<c:if test="${fn:length(tchtml) == 0 }">	
				                	 <h4><i class="icon fa fa-warning"></i>   <font color="red" > Latest file not available.</font> </h4>	
				                    </c:if> 			            	         
				            		<c:forEach items="${tchtml}" var="tchtml">	
				            		  	<div class="col-md-6">	
				            		  	   <div class="box">
				            		  		 <div class="box-header with-border"> 
				            		  		 	<h3 class="box-title"><i class="fa fa-server"></i>&nbsp;<font color= "blue"><c:out value="${tchtml.value.get(0)}"/></font></h3>
				            		  		  </div> 				            		  		  
				            		  		  <div class="box-body">
				            		  		  		<i class="fa fa-laptop"></i>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${tchtml.value.get(1)}"/><br>
				            		  		  		<i class="fa fa-floppy-o">Available Disk </i>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${tchtml.value.get(2)}"/> <br>
				            		  		  		<i class="fa fa-refresh"> Running </i>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${tchtml.value.get(3)}"/>
				            		  		  </div>  		
				            			 	</div> 				            			 
				            			</div>
				            		</c:forEach>
				            </div>
				        </div>
				      </div>
				      
				      <div class="tab-pane" id="tab_3">	
		              	<div class="box">				            
				            <div class="box-body">
				            
				           		 <c:if test="${fn:length(geshtml) == 0 }">	
				                	 <h4><i class="icon fa fa-warning"></i>   <font color="red" > Latest file not available.</font> </h4>	
				                    </c:if> 	
				                    <c:set var="count" value="0" scope="page" />		            	         
				            		<c:forEach items="${geshtml}" var="geshtml">	
				            		  	<div class="col-md-12">	
				            		  	   <div class="box">
				            		  	     <c:set var="serverName" value="${fn:split(geshtml.key,'-') }"/>
				            		  	     <div class="box-header with-border"> 
				            		  		 	<h3 class="box-title"><i class="fa fa-server"></i>&nbsp;<font color= "blue">Server : <c:out value="${serverName[2]}"/> </font></h3>
				            		  		  </div> 				            		  		  
				            		  		  <div class="box-body">				            		  		  		
				            		  		  		
				            		  		  		<c:forTokens var= "token" items="${geshtml.key}" delims="#x#">
				            		  		  			<b><c:out value="${token}"/></b> <br>
				            		  		  		</c:forTokens>
				            		  		  		
				            		  		  		
				            		  		  		 <table  class="table table-bordered table-striped">
										                <thead>
										                <tr>
										                  <th>File System </th>
										                  <th>Size</th>                   
										                  <th>Used</th>
										                  <th>Available</th>
										                  <th>Use%</th> 
										                  <th> Mountedon </th>										                           
										                </tr>
										                </thead>
										                <tbody>
										                 <c:forEach items="${geshtml.value}" var="disklist">
											                <tr>
											                 	
											                  <c:forTokens var= "token" items="${disklist}" delims="#x#">
						            		  		  			<td><c:out value="${token}"/> </td>						            		  		  			
						            		  		  		  </c:forTokens>									                               
											                </tr>
										                </c:forEach>
										                </tbody>										               
										              </table>

				            		  		  </div>
				            		  		  
				            		  		    		
				            			 	</div> 				            			 
				            			</div>
				            		</c:forEach>
				            	 
				            </div>
				        </div>
				      </div>
				      
				      
				      <div class="tab-pane" id="tab_4">	
		              	<div class="box">				            
				            <div class="box-body">
				              <c:if test="${fn:length(polhtml) == 0 }">	
				                	 <h4><i class="icon fa fa-warning"></i>   <font color="red" > Latest file not available.</font> </h4>	
				              </c:if>  	
				            	<c:forEach items="${polhtml}" var="polhtml">
				            	  <c:set var = "theString" value = "${polhtml.key}"/>
				            	  <c:if test="${fn:containsIgnoreCase(theString, 'Polarion')}">	
				            		  	<div class="col-md-12">	
				            		  	   <div class="box">
				            		  	     <c:set var="serverName" value="${fn:split(polhtml.key,'-') }"/>
				            		  		 <div class="box-header with-border"> 
				            		  		 	<h3 class="box-title"><i class="fa fa-server"></i>&nbsp;<font color= "blue">Server : <c:out value="${serverName[2]}"/> </font></h3>
				            		  		  </div> 				            		  		  
				            		  		  <div class="box-body">
				            		  		  		
				            		  		  		
				            		  		  		<c:forTokens var= "token" items="${polhtml.key}" delims="#x#">
				            		  		  			<b><c:out value="${token}"/></b> <br>
				            		  		  		</c:forTokens>
				            		  		  		
				            		  		  		
				            		  		  		 <table  class="table table-bordered table-striped">
										                <thead>
										                <tr>
										                  <th>File System </th>
										                  <th>Size</th>                   
										                  <th>Used</th>
										                  <th>Available</th>
										                  <th>Use%</th> 
										                  <th> Mountedon </th>										                           
										                </tr>
										                </thead>
										                <tbody>
										                 <c:forEach items="${polhtml.value}" var="disklist">
											                <tr>
											                 	
											                  <c:forTokens var= "token" items="${disklist}" delims="#x#">
						            		  		  			<td><c:out value="${token}"/> </td>						            		  		  			
						            		  		  		  </c:forTokens>									                               
											                </tr>
										                </c:forEach>
										                </tbody>										               
										              </table>
				            		  		  		
				            		  		  </div>  		
				            			 	</div> 				            			 
				            			</div>
				            			</c:if>
				            		</c:forEach>
				                 
				            </div>
				        </div>
				      </div>
				      
				      <div class="tab-pane" id="tab_5">	
		              	<div class="box">				            
				            <div class="box-body">
				            	<c:if test="${fn:length(polhtml) == 0 }">	
				                	 <h4><i class="icon fa fa-warning"></i>   <font color="red" > Latest file not available.</font> </h4>	
				                 </c:if>
				            	<c:forEach items="${polhtml}" var="polhtml">
				            	  <c:set var = "theString" value = "${polhtml.key}"/>
				            	  <c:if test="${fn:containsIgnoreCase(theString, 'PlasticSCM')}">	
				            		  	<div class="col-md-12">	
				            		  	   <div class="box">
				            		  	    <c:set var="serverName" value="${fn:split(polhtml.key,'-') }"/>
				            		  		 <div class="box-header with-border"> 
				            		  		 	<h3 class="box-title"><i class="fa fa-server"></i>&nbsp;<font color= "blue">Server : <c:out value="${serverName[2]}"/> </font></h3>
				            		  		  </div> 				            		  		  
				            		  		  <div class="box-body">
				            		  		  		<c:forTokens var= "token" items="${polhtml.key}" delims="#x#">
				            		  		  			<b><c:out value="${token}"/></b> <br>
				            		  		  		</c:forTokens>
				            		  		  		
				            		  		  		
				            		  		  		 <table  class="table table-bordered table-striped">
										                <thead>
										                <tr>
										                  <th>File System </th>
										                  <th>Size</th>                   
										                  <th>Used</th>
										                  <th>Available</th>
										                  <th>Use%</th> 
										                  <th> Mountedon </th>										                           
										                </tr>
										                </thead>
										                <tbody>
										                 <c:forEach items="${polhtml.value}" var="disklist">
											                <tr>
											                 	
											                  <c:forTokens var= "token" items="${disklist}" delims="#x#">
						            		  		  			<td><c:out value="${token}"/> </td>						            		  		  			
						            		  		  		  </c:forTokens>									                               
											                </tr>
										                </c:forEach>
										                </tbody>										               
										              </table>
				            		  		  	
				            		  		  </div>  		
				            			 	</div> 				            			 
				            			</div>
				            			</c:if>
				            		</c:forEach>
				            
				            </div>
				        </div>
				      </div>
				      
				      
				      <div class="tab-pane" id="tab_6">	
		              	<div class="box">				            
				            
				            <div class="box-body">
				            	<c:if test="${fn:length(polhtml) == 0 }">	
				                	 <h4><i class="icon fa fa-warning"></i>   <font color="red" > Latest file not available.</font> </h4>	
				                 </c:if>
				            	<c:forEach items="${polhtml}" var="polhtml">
				            	  <c:set var = "theString" value = "${polhtml.key}"/>
				            	  <c:if test="${fn:containsIgnoreCase(theString, 'Doors')}">	
				            		  	<div class="col-md-12">	
				            		  	   <div class="box">
				            		  	     <c:set var="serverName" value="${fn:split(polhtml.key,'-') }"/>
				            		  		 <div class="box-header with-border"> 
				            		  		 	<h3 class="box-title"><i class="fa fa-server"></i>&nbsp;<font color= "blue">Server : <c:out value="${serverName[2]}"/></font></h3>
				            		  		  </div> 				            		  		  
				            		  		  <div class="box-body">
				            		  		  		
				            		  		  		<c:forTokens var= "token" items="${polhtml.key}" delims="#x#">
				            		  		  			<b><c:out value="${token}"/></b> <br>
				            		  		  		</c:forTokens>
				            		  		  		
				            		  		  		
				            		  		  		 <table  class="table table-bordered table-striped">
										                <thead>
										                <tr>
										                  <th>File System </th>
										                  <th>Size</th>                   
										                  <th>Used</th>
										                  <th>Available</th>
										                  <th>Use%</th> 
										                  <th> Mountedon </th>										                           
										                </tr>
										                </thead>
										                <tbody>
										                 <c:forEach items="${polhtml.value}" var="disklist">
											                <tr>
											                 	
											                  <c:forTokens var= "token" items="${disklist}" delims="#x#">
						            		  		  			<td><c:out value="${token}"/> </td>						            		  		  			
						            		  		  		  </c:forTokens>									                               
											                </tr>
										                </c:forEach>
										                </tbody>										               
										              </table>
				            		  		  	
				            		  		  </div>  		
				            			 	</div> 				            			 
				            			</div>
				            			</c:if>
				            		</c:forEach>
				            
				            </div>
				            
				      	</div>
				      </div>
				      
				      <div class="tab-pane" id="tab_7">	
		              	<div class="box">				            
				          <div class="box-body">
				            	<c:if test="${fn:length(polhtml) == 0 }">	
				                	 <h4><i class="icon fa fa-warning"></i>   <font color="red" > Latest file not available.</font> </h4>	
				                 </c:if>
				            	<c:forEach items="${polhtml}" var="polhtml">
				            	  <c:set var = "theString" value = "${polhtml.key}"/>
				            	  <c:if test="${fn:containsIgnoreCase(theString, 'Synergy')}">	
				            		  	<div class="col-md-12">	
				            		  	   <div class="box">
				            		  	   <c:set var="serverName" value="${fn:split(polhtml.key,'-') }"/>
				            		  		 <div class="box-header with-border"> 
				            		  		 	<h3 class="box-title"><i class="fa fa-server"></i>&nbsp;<font color= "blue">Server : <c:out value="${serverName[2]}"/></font></h3>
				            		  		  </div> 				            		  		  
				            		  		  <div class="box-body">
				            		  		  		
				            		  		  		<c:forTokens var= "token" items="${polhtml.key}" delims="#x#">
				            		  		  			<b><c:out value="${token}"/></b> <br>
				            		  		  		</c:forTokens>
				            		  		  		
				            		  		  		
				            		  		  		 <table  class="table table-bordered table-striped">
										                <thead>
										                <tr>
										                  <th>File System </th>
										                  <th>Size</th>                   
										                  <th>Used</th>
										                  <th>Available</th>
										                  <th>Use%</th> 
										                  <th> Mountedon </th>										                           
										                </tr>
										                </thead>
										                <tbody>
										                 <c:forEach items="${polhtml.value}" var="disklist">
											                <tr>
											                 	
											                  <c:forTokens var= "token" items="${disklist}" delims="#x#">
						            		  		  			<td><c:out value="${token}"/> </td>						            		  		  			
						            		  		  		  </c:forTokens>									                               
											                </tr>
										                </c:forEach>
										                </tbody>										               
										              </table>
				            		  		  	
				            		  		  </div>  		
				            			 	</div> 				            			 
				            			</div>
				            			</c:if>
				            		</c:forEach>
				            
				            </div>
				      </div>				      
				      </div>
				      
				      
				      <div class="tab-pane" id="tab_8">	
		              	<div class="box">				            
				            <div class="box-body">
				            	<c:if test="${fn:length(polhtml) == 0 }">	
				                	 <h4><i class="icon fa fa-warning"></i>   <font color="red" > Latest file not available.</font> </h4>	
				                 </c:if>
				            	<c:forEach items="${polhtml}" var="polhtml">
				            	  <c:set var = "theString" value = "${polhtml.key}"/>
				            	  <c:if test="${fn:containsIgnoreCase(theString, 'JIRA')}">	
				            		  	<div class="col-md-12">	
				            		  	   <div class="box">
				            		  	   <c:set var="serverName" value="${fn:split(polhtml.key,'-') }"/>
				            		  		 <div class="box-header with-border"> 
				            		  		 	<h3 class="box-title"><i class="fa fa-server"></i>&nbsp;<font color= "blue">Server : <c:out value="${serverName[2]}"/></font></h3>
				            		  		  </div> 				            		  		  
				            		  		  <div class="box-body">
				            		  		  		
				            		  		  		<c:forTokens var= "token" items="${polhtml.key}" delims="#x#">
				            		  		  			<b><c:out value="${token}"/></b> <br>
				            		  		  		</c:forTokens>
				            		  		  		
				            		  		  		
				            		  		  		 <table  class="table table-bordered table-striped">
										                <thead>
										                <tr>
										                  <th>File System </th>
										                  <th>Size</th>                   
										                  <th>Used</th>
										                  <th>Available</th>
										                  <th>Use%</th> 
										                  <th> Mountedon </th>										                           
										                </tr>
										                </thead>
										                <tbody>
										                 <c:forEach items="${polhtml.value}" var="disklist">
											                <tr>
											                 	
											                  <c:forTokens var= "token" items="${disklist}" delims="#x#">
						            		  		  			<td><c:out value="${token}"/> </td>						            		  		  			
						            		  		  		  </c:forTokens>									                               
											                </tr>
										                </c:forEach>
										                </tbody>										               
										              </table>
				            		  		  	
				            		  		  </div>  		
				            			 	</div> 				            			 
				            			</div>
				            			</c:if>
				            		</c:forEach>
				            
				            </div>
				        </div>
				      </div>
				      
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

<script src="/AppMonitor/bower_components/jquery/dist/jquery.min.js"></script>
<script src="/AppMonitor/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<script src="/AppMonitor/bower_components/raphael/raphael.min.js"></script>
<script src="/AppMonitor/bower_components/morris.js/morris.min.js"></script>
<script src="/AppMonitor/bower_components/fastclick/lib/fastclick.js"></script>
<script src="/AppMonitor/dist/js/adminlte.min.js"></script>

<script>
  $(function () {
	  
  });
</script>

</body>
</html>