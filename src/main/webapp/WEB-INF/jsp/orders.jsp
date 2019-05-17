<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>

	<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />


</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Secured</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>
					<li><a href="/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="jumbotron">
	    <div class="container">
           <p>
           ${jwt}
           </p>
    	</div>
    </div>


	<div class="container">
		<table class="table">
                    <thead>
                    <tr>
                         <th scope="col" >ID</th>
                         <th scope="col" >Product</th>
                         <th scope="col" >Customer</th>
                         <th scope="col" >Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="order">
                                    <tr>
                                        <td scope="row" >${order.id}</td>
                                        <td>${order.product}</td>
                                        <td>${order.customer}</td>
                                        <td>${order.price}</td>
                                    </tr>
                                </c:forEach>
                    </tbody>

        </table>



	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>