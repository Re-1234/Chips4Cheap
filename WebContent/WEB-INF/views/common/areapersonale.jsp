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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/areapersonale.css" type="text/css">
</head>
<body>

    <jsp:include page="../header.jsp" />

    <main class="contenitore-area-personale">
        <div class="modulo-area-personale">
            <h2>Area Personale</h2>
            
            <div class="sezione-info">
                <h3>I tuoi Dati</h3>
                
                <div class="campo-info">
                    <span class="etichetta">Username:</span>
                    <span class="valore">${sessionScope.account.username}</span>
                </div>

                <div class="campo-info">
                    <span class="etichetta">Email:</span>
                    <span class="valore">${sessionScope.account.email}</span>
                </div>

                <div class="campo-info">
                    <span class="etichetta">Indirizzo:</span>
                    <span class="valore">${sessionScope.account.via}</span>
                </div>

                <div class="riga-info-compatta">
                    <div class="campo-info mezzo-blocco">
                        <span class="etichetta">N° Civico:</span>
                        <span class="valore">${sessionScope.account.numeroCivico}</span>
                    </div>

                    <div class="campo-info mezzo-blocco">
                        <span class="etichetta">CAP:</span>
                        <span class="valore">${sessionScope.account.cap}</span>
                    </div>
                </div>
            </div>

            <div class="collegamenti-navigazione">
                <a href="${pageContext.request.contextPath}/common/RicevuteAccount" class="link-navigazione">Visualizza i tuoi acquisti e ricevute</a>
                <a href="${pageContext.request.contextPath}/common/ModificaAccount" class="link-navigazione link-gestione">Modifica o Cancella Account</a>
            </div>
        </div>
    </main>

    <jsp:include page="../footer.jsp" />

</body>
</html>