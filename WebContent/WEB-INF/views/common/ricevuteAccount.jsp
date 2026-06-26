<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Le mie Ricevute - Chips4Cheap</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
</head>
<body>

    <jsp:include page="../header.jsp" />

    <main class="contenitore-generico" id="pagina-elenco-ricevute">
        <div class="blocco-contenuto" id="area-elenco-ricevute">
            <h2>Storico Ricevute Fiscali</h2>

            <c:choose>
                <c:when test="${empty requestScope.ricevute}">
                    <p class="avviso-vuoto" id="messaggio-nessuna-ricevuta">Non ci sono ricevute associate a questo account.</p>\n                </c:when>
                <c:otherwise>
                    <ul class="lista-elementi" id="elenco-ricevute-account">
                        <c:forEach var="ricevuta" items="${requestScope.ricevute}">
                            <li class="scheda-elemento">
                                <span class="info-id">Ricevuta n° ${ricevuta.IDRicevutaFiscale}</span>
                                <span class="info-data">Emessa il: ${ricevuta.dataEmissione}</span>
                                <span class="info-pagamento">Pagamento: ${ricevuta.metodoPagamento}</span>
                                
                                <a href="${pageContext.request.contextPath}/common/VisualizzaRicevuta?id=${ricevuta.IDRicevutaFiscale}" class="link-nascosto-elemento">
                                    Apri Ricevuta
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>

            <div class="zona-navigazione" id="blocco-ritorno-area-personale">
                <a href="${pageContext.request.contextPath}/common/AreaPersonale" class="link-navigazione-indietro">Torna all'Area Personale</a>
            </div>
        </div>
    </main>

    <jsp:include page="../footer.jsp" />

</body>
</html>