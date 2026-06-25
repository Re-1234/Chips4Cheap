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
<body>

    <jsp:include page="../header.jsp" />

    <main class="contenitore-pagina">
        <div class="modulo-centrato">
            <h2>Area Personale</h2>
            
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
                <a href="${pageContext.request.contextPath}/common/RicevuteAccount" class="link-azione" id="link-ricevute">Visualizza i tuoi acquisti e ricevute</a>
                <a href="${pageContext.request.contextPath}/common/ModificaAccount" class="link-azione" id="link-modifica-cancella">Modifica o Cancella Account</a>
            </div>
        </div>
    </main>

    <jsp:include page="../footer.jsp" />

</body>
</html>