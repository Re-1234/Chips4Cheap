<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Prodotto del Catalogo</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body>
	<jsp:include page = "header.jsp"/>
	
	<main>
		<c:choose>
		    <c:when test="${not empty prodotto.nomeModello}">
		        <h1>${prodotto.getNomeModello()}</h1>
		    </c:when>
		    <c:otherwise>
		        <h1>Nome Prodotto</h1>
		    </c:otherwise>
		</c:choose>
		
		<c:if test="${not empty prodotto.imagine}">
            <img src="${pageContext.request.contextPath}/images/${prodotto.imagine}" alt="${prodotto.nomeModello}" width="300">
        </c:if>
		
		<c:choose>
		    <c:when test="${not empty prodotto.Descrizione}">
		        <h2>Descrizione</h2>
		        <p>${prodotto.getDescrizione()}</p>
		    </c:when>
		    <c:otherwise>
		        <h2>Descrizione</h2>
		        <p> </p>
		    </c:otherwise>
		</c:choose>	
	</main>
</body>
</html>