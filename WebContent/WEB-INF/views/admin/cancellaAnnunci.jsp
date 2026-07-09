<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Annunci - Chips4Cheap</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body class="modulo-admin-bg">

    <jsp:include page="../header.jsp" />

    <main>
        <section>
            
            <h2 class="titolo-admin-color">Elenco Globale Annunci</h2>

            <c:choose>
                <c:when test="${empty applicationScope.tuttiAnnunci}">
                    <p class="avviso-vuoto">Non ci sono annunci o comunicazioni pubblicate nel sistema.</p>
                </c:when>
                <c:otherwise>
                    <ul class="lista-elementi">
                        <c:forEach var="annuncio" items="${applicationScope.tuttiAnnunci}">
                            <li class="scheda-elemento" id="riga-annuncio-${annuncio.idAnnuncio}">
                                <span>Titolo: ${annuncio.titolo}</span>
                                <span>Data Pubblicazione: ${annuncio.dataPublicazione}</span>
                                <span>Descrizione: ${annuncio.text}</span>
                                
                                <button type="button" class="bottone-pericolo" onclick="eliminaAnnuncio(${annuncio.idAnnuncio}, '${pageContext.request.contextPath}')">
                                    Rimuovi Annuncio
                                </button>
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>

            <div class="zona-navigazione">
                <a href="${pageContext.request.contextPath}/common/AreaPersonale" class="link-navigazione-indietro azione-admin-color">
                    Torna all'Area Personale
                </a>
            </div>
            
        </section>
    </main>

    <jsp:include page="../footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/cancellaAnnunci.js"></script>
</body>
</html>