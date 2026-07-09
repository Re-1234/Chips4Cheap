<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crea Annuncio - Pannello Amministratore</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/moduli.css" type="text/css">
</head>
<body class="modulo-admin-bg">

 <jsp:include page="../header.jsp" />

    <main>
        <section class="modulo-centrato">
            <h2 class="titolo-admin-color">Crea un Nuovo Annuncio</h2>
            
            <c:if test="${not empty requestScope.erroreServer}">
                <div class="errore-server">
                    <p>${requestScope.erroreServer}</p>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/CreaAnnuncio" method="POST">
                
                <div class="gruppo-campo">
                    <label for="titolo">Titolo dell'Annuncio *</label>
                    <input type="text" id="titolo" name="titolo" required placeholder="Es. Sponsor? Collaborazione? Stiamo andando in bancarotta e niente rimborsi?" maxlength="100">
                </div>

                <div class="gruppo-campo">
                    <label for="descrizione">Descrizione *</label>
                    <textarea id="descrizione" name="descrizione" rows="6" required placeholder="Scrivi qui il corpo dell'annuncio..."></textarea>
                </div>

                <button type="submit" class="bottone-azione">Pubblica Annuncio</button>
                
                <div class="zona-navigazione">
                    <a href="${pageContext.request.contextPath}/common/AreaPersonale" class="link-navigazione-indietro">
                        Annulla e torna all'Area Personale
                    </a>
                </div>
                
            </form>
        </section>
    </main>

    <jsp:include page="../footer.jsp" />

</body>
</html>