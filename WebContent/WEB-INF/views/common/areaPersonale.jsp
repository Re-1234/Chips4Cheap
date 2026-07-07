<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Area Personale - Chips4Cheap</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/moduli.css" type="text/css">
</head>
<body class="${sessionScope.account.amministratore ? 'modulo-admin-bg' : ''}">	<!-- un po brutto per applicare la vista admin ma funziona, senza avre strisce per via del padding -->

    <jsp:include page="../header.jsp" />

    <main class="contenitore-pagina">
        <div class="modulo-centrato">
            
            <h2 class="${sessionScope.account.amministratore ? 'titolo-admin-color' : ''}">
                <c:choose>
                    <c:when test="${sessionScope.account.amministratore}">
                        Area Personale (Amministratore)
                    </c:when>
                    <c:otherwise>
                        Area Personale
                    </c:otherwise>
                </c:choose>
            </h2>
            
            <div class="sezione-contenuto">
                <h3>I tuoi Dati</h3>
                
                <div class="gruppo-campo">
                    <span class="etichetta-fissa">Username:</span>
                    <span class="valore-fisso">${sessionScope.account.username}</span>
                </div>

                <div class="gruppo-campo">
                    <span class="etichetta-fissa">Email:</span>
                    <span class="valore-fisso">${sessionScope.account.email}</span>
                </div>

                <div class="gruppo-campo">
                    <span class="etichetta-fissa">Indirizzo:</span>
                    <span class="valore-fisso">${sessionScope.account.via}</span>
                </div>

                <div class="riga-doppia">
                    <div class="gruppo-campo meta-larghezza">
                        <span class="etichetta-fissa">N° Civico:</span>
                        <span class="valore-fisso">${sessionScope.account.numeroCivico}</span>
                    </div>

                    <div class="gruppo-campo meta-larghezza">
                        <span class="etichetta-fissa">CAP:</span>
                        <span class="valore-fisso">${sessionScope.account.cap}</span>
                    </div>
                </div>
            </div>

            <div id="blocco-azioni-account">
                <c:choose>
                    <c:when test="${sessionScope.account.amministratore}">
                        <a href="${pageContext.request.contextPath}/admin/ModificaProdotti" class="link-azione azione-admin-color">Gestione e Modifica Prodotti</a>
                        <a href="${pageContext.request.contextPath}/admin/MandaAnnuncio" class="link-azione azione-admin-color">Invia Annuncio / Comunicazione</a>
                        <a href="${pageContext.request.contextPath}/admin/CercaRicevute" class="link-azione azione-admin-color">Ricerca Ricevute Fiscali</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/common/RicevuteAccount" class="link-azione" id="link-ricevute">Visualizza i tuoi acquisti e ricevute</a>
                        <a href="${pageContext.request.contextPath}/common/ModificaAccountForm" class="link-azione" id="link-modifica-cancella">Modifica o Cancella Account</a>
                    </c:otherwise>
                </c:choose>
                
                <a href="${pageContext.request.contextPath}/common/Logout" class="link-azione" id="link-logout">Logout</a>
            </div>
        </div>
    </main>

    <jsp:include page="../footer.jsp" />

</body>
</html>