<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina Non Trovata - Chips4Cheap</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="contenitore-generico" id="pagina-errore-404">
        <div class="blocco-contenuto" id="area-errore-404">
            <h1 class="titolo-errore">Errore 404 - Pagina Non Trovata</h1>
            <p class="messaggio-errore">Ci dispiace, ma la pagina che stai cercando non esiste o è stata spostata.</p>
            
            <div class="zona-navigazione" id="blocco-ritorno-home">
                <a href="${pageContext.request.contextPath}/" class="link-navigazione-indietro">Torna alla Home</a>
            </div>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

</body>
</html>