<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettaglio Ricevuta - Chips4Cheap</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body class="${sessionScope.account.amministratore ? 'modulo-admin-bg' : ''}">

    <jsp:include page="../header.jsp" />

    <main>
        <section>
            
            <h2 class="${sessionScope.account.amministratore ? 'titolo-admin-color' : ''}">
                Dettaglio Ricevuta Fiscale n° ${ricevuta.idRicevutaFiscale}
            </h2>

            <ul class="lista-elementi">
                <li class="scheda-elemento">
                    <span>Numero Identificativo: ${ricevuta.idRicevutaFiscale}</span>
                </li>
                <li class="scheda-elemento">
                    <span>Data di Emissione: ${ricevuta.localDate}</span>
                </li>
                <li class="scheda-elemento">
                    <span>Metodo di Pagamento: ${ricevuta.metodoPagamento}</span>
                </li>
                <li class="scheda-elemento">
                    <span>Intestatario (Email): ${ricevuta.email}</span>
                </li>
                <li class="scheda-elemento">
                    <span>Via: ${ricevuta.via}</span>
                </li>
                <li class="scheda-elemento">
                    <span>CAP: ${ricevuta.cap}</span>
                </li>
                <li class="scheda-elemento">
                    <span>N° Civico: ${ricevuta.numeroCivico}</span>
                </li>
            </ul>

            <h2 class="${sessionScope.account.amministratore ? 'titolo-admin-color' : ''}">
                Articoli Acquistati
            </h2>

            <ul class="lista-elementi">
                <c:forEach var="prodotto" items="${requestScope.listaProdotti}">
                    <li class="scheda-elemento">
                        <span>Modello: ${prodotto.nomeModello}</span>
                        <span>Autore/Produttore: ${prodotto.nCAutore}</span>
                        <span>Tipo: ${prodotto.tipo}</span>
                        <span>Quantità: ${prodotto.quantità}</span>
                        <span>Prezzo Unitario: € ${prodotto.prezzo}</span>
                    </li>
                </c:forEach>
            </ul>

            <div class="zona-navigazione">
                <c:choose>
                    <c:when test="${sessionScope.account.amministratore}">
                        <a href="${pageContext.request.contextPath}/admin/CercaRicevute" class="link-navigazione-indietro azione-admin-color">
                            Torna alla Ricerca Ricevute
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/common/RicevuteAccount" class="link-navigazione-indietro">
                            Torna allo Storico Ricevute
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
            
        </section>
    </main>

    <jsp:include page="../footer.jsp" />

</body>
</html>