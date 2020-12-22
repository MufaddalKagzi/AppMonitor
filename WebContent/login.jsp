
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
  
  <link rel="stylesheet" href="/AppMonitor/dist/css/skins/skin-blue.min.css">
  <link rel="stylesheet" href="/AppMonitor/dist/css/AdminLTE.min.css">
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> 

<style type="text/css">
body {
    font-family: "Lato", sans-serif;
}
.main-head{
    height: 150px;
    background: #FFF;
}
.sidenav {
    height: 100%;
    background-color: #3C8DBC;
    overflow-x: hidden;
    padding-top: 20px;
}
.main {
    padding: 0px 100px;
}
@media screen and (max-height: 450px) {
    .sidenav {padding-top: 15px;}
}
@media screen and (max-width: 450px) {
    .login-form{
        margin-top: 10%;
    }
}
@media screen and (min-width: 768px){
    .main{
        margin-left: 40%; 
    }
.sidenav{
        width: 40%;
        position: fixed;
        z-index: 1;
        top: 0;
        left: 0;
    }
.login-form{
        margin-top: 10%;
        margin-left: 10%;       
    }
.image-form{
        margin-top: 5%;
        padding: 60px;
    }
}
.login-main-text{
    margin-top: 20%;
    padding: 60px;
    color: #fff;
}
.login-main-text h2{
    font-weight: 400;
}
</style>      
        
</head>

<body>
<div class="sidenav">
	 <div class="login-main-text">
            <h2>Engineering Application Monitoring System </h2> 
            <br>
            <small>Login Page</small>          
     </div>
</div>   

<div class="main">
         <div class="col-md-6 col-sm-12">   
           <div class="image-form"> <img src="img/tcs.jpg"  height="200" width="475" alt="Company Logo"> </div>      	
            <div class="login-form">
                <form name="loginform" method="post" action="${pageContext.request.contextPath}/login" id="loginForm" >
                	<span> <font color="red">${errormsg}</font></span>
                	  <div class="form-group has-feedback">
				        <input type="text" class="form-control" placeholder="Net Id"   name="netId" id= "netId"  required />
				        <span class="glyphicon glyphicon-user form-control-feedback"></span>
				      </div>
				      <div class="form-group has-feedback">
				        <input type="password" class="form-control" placeholder="Password"  name="password"  id="password" required />
				        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
				      </div>
				      <div class="row">
				        <div class="col-xs-8">
				        </div>
				        <div class="col-xs-4">
				          <button type="submit" class="btn btn-primary btn-block btn-flat" id="loginSubmit">Sign In</button>
				        </div>
				      </div>				      
				</form>
            </div>
         </div>
 </div>   


  

<script src="/AppMonitor/bower_components/jquery/dist/jquery.min.js"></script>
<script src="/AppMonitor/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/AppMonitor/dist/js/adminlte.min.js"></script>



</body>
</html>