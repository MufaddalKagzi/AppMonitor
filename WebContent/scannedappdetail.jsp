<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
  <link rel="stylesheet" href="/AppMonitor/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/AppMonitor/dist/css/skins/skin-blue.min.css">

 <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> 

<body class="hold-transition skin-blue layout-top-nav ">
<div class="wrapper">
  <div class="content-wrapper">
  
  <section class="content container-fluid">
  
  	
  	<div class="row">
      	<div class = col-md-12 >      			
      	<div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title ">  Scan Date Time :  <fmt:formatDate type = "both" value = "${masterList.get(0).scanDate}" />   </h3>      
            </div>
            <div class="box-body">
            
            	<c:forEach items="${masterList}" var="masterList">
                			<c:if test="${masterList.status==STATUS_DOWN && masterList.subTowerId==SUB_TOWER_GES}">
                				<i class="fa fa-circle text-red"></i>&nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.appName}"/><br></span>                				
                			</c:if>  
                			<c:if test="${masterList.status==STATUS_DOWN && ( masterList.subTowerId==SUB_TOWER_TEAMCENTER || masterList.subTowerId==SUB_TOWER_POLARION )}">
                				<i class="fa fa-circle text-red"></i> &nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.siteName}" /><br></span>                				
                			</c:if>  
                			<c:if test="${masterList.status==STATUS_DOWN && ( masterList.subTowerId==SUB_TOWER_CLEAR_CASE || masterList.subTowerId==SUB_TOWER_PLASTIC || masterList.subTowerId==SUB_TOWER_CM_SYNERGY || masterList.subTowerId==SUB_TOWER_DOORS || masterList.subTowerId==SUB_TOWER_JIRA)}">
                				<i class="fa fa-circle text-red"></i> &nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.serverName}" /><br></span>                				
                			</c:if>               			
                	</c:forEach> 
                	<c:forEach items="${masterList}" var="masterList">
                			<c:if test="${masterList.status==STATUS_UP && masterList.subTowerId==SUB_TOWER_GES}">
                				<i class="fa fa-circle text-green"></i> &nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.appName}" /><br></span>                				
                			</c:if>  
                			<c:if test="${masterList.status==STATUS_UP && ( masterList.subTowerId==SUB_TOWER_TEAMCENTER || masterList.subTowerId==SUB_TOWER_POLARION )}">
                				<i class="fa fa-circle text-green"></i>&nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.siteName}" /><br></span>                				
                			</c:if>  
                			<c:if test="${masterList.status==STATUS_UP && ( masterList.subTowerId==SUB_TOWER_CLEAR_CASE || masterList.subTowerId==SUB_TOWER_PLASTIC || masterList.subTowerId==SUB_TOWER_CM_SYNERGY || masterList.subTowerId==SUB_TOWER_DOORS || masterList.subTowerId==SUB_TOWER_JIRA)}">
                				<i class="fa fa-circle text-green"></i> &nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.serverName}" /><br></span>                				
                			</c:if>               			
                	</c:forEach>             	
             
            			
            </div>
        </div>
       </div>      
      </div>
  		
  		
                	
        
	
  </section>
  </div>
</div>
      
        

</body>
</html>