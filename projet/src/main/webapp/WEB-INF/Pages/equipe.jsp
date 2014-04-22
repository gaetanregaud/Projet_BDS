<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset= utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link rel="stylesheet" href="CSS/index.css" type="text/css">
        <link rel="stylesheet" href="CSS/header.css" type="text/css">
        <title>BDS HEI</title>
</head>
<body>
	<header>
		<c:set var="pageSelectionnee" value="equipe" scope="request" />
		<%@ include file="header.jsp" %>
    </header>
    <section>
    	<p>Bienvenue sur la page de l'équipe</p>
    </section>
</body>
</html>