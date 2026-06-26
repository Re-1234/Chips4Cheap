<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Errore di Sistema - Chips4Cheap</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="contenitore-generico" id="pagina-errore-generica">
        <div class="blocco-contenuto" id="area-errore-500">
            <h1 class="titolo-errore">Si è verificato un errore</h1>
            <p class="messaggio-errore">Spiacenti, si è verificato un problema interno al server. I nostri tecnici sono stati informati.</p>
            
            <div class="zona-navigazione" id="blocco-ritorno-prev">
                <a href="${pageContext.request.contextPath}/" class="link-navigazione-indietro">Torna alla Home</a>
            </div>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

</body>
</html>