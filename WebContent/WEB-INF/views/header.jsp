<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
</head>

<header>

      <nav class="nav-principale">
            <a href="${pageContext.request.contextPath}/Home" class="logo">Chips4Cheap</a>
            <a href="${pageContext.request.contextPath}/Registrazione" class="nav-link">Registrati</a>

            <a href="${pageContext.request.contextPath}/Catalogo" class="catalog-btn" title="Vai al Catalogo">Catalogo</a>
            <a href="${pageContext.request.contextPath}/common/AreaPersonale" class="user-link">Account</a>
            <a href="${pageContext.request.contextPath}/Carrello" class="user-link">Carrello</a> 
      </nav>
</header>

</html>