<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina Non Trovata - Chips4Cheap</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="contenitore testo-centrato">
        <h1>Errore 404</h1>
        <p>Ci dispiace, ma la pagina che stai cercando non esiste o è stata spostata.</p>
        
        <a href="${pageContext.request.contextPath}/" class="bottone">Torna alla Home</a>
    </main>

    <jsp:include page="footer.jsp" />

</body>
</html>