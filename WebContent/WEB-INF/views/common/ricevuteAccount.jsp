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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body>

    <jsp:include page="../header.jsp" />

    <main>
        <section>
            
            <h2>Storico Ricevute Fiscali</h2>

            <c:choose>
                <c:when test="${empty requestScope.ricevute}">
                    <p class="avviso-vuoto">Non ci sono ricevute associate a questo account.</p>
                </c:when>
                <c:otherwise>
                    <ul class="lista-elementi">
                        <c:forEach var="ricevuta" items="${requestScope.ricevute}">
                            <li class="scheda-elemento">
                                <span>Ricevuta n°:  ${ricevuta.IDRicevutaFiscale}</span>
                                <span>Emessa il:  ${ricevuta.dataEmissione}</span>
                                <span>Pagamento:  ${ricevuta.metodoPagamento}</span>
                                <span>Pagamento:  ${ricevuta.via}</span>	<!-- non faccio vedere numeroCivico, cap, visualizzo il resto nella singola ricevuta -->
                                
                                <a href="${pageContext.request.contextPath}/VisualizzaRicevuta?id=${ricevuta.IDRicevutaFiscale}" class="link-nascosto-elemento">
                                    Apri Ricevuta
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>

            <div class="zona-navigazione">
                <a href="${pageContext.request.contextPath}/common/AreaPersonale" class="link-navigazione-indietro">
                    Torna all'Area Personale
                </a>
            </div>
            
        </section>
    </main>

    <jsp:include page="../footer.jsp" />

</body>
</html>