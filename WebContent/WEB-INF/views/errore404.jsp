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

    <main>
    	<section class="pagina-bicolonna">
    	
        	<h1>Errore 404</h1>
        	<p>Ci dispiace, ma la pagina che stai cercando non esiste o è stata spostata.</p>
        
        	<a href="${pageContext.request.contextPath}/Home" class="link-navigazione-indietro">Torna alla Home</a>
        
        </section>
        <aside>
        	<h3>Per favore funziona</h3>
        	<p> non ci riesco più <p>
        </aside>
    </main>

    <jsp:include page="footer.jsp" />

</body>
</html>