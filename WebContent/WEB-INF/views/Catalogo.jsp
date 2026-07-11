<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catalogo Prodotti - Chips4Cheap</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="contenitore-generico">
        <div class="blocco-contenuto">

            <h2>Catalogo Prodotti</h2>

            <div class="zona-filtri">

                <!-- FORM 1: Ricerca per nome modello -->
                <form class="scheda-filtro" action="${pageContext.request.contextPath}/Catalogo" method="get">
                    <input type="text" name="nomeModello" placeholder="Cerca per nome modello..." value="${param.nomeModello}">
                    <button type="submit">Cerca</button>
                </form>

                <!-- FORM 2: Filtro per produttore -->
                <form class="scheda-filtro" action="${pageContext.request.contextPath}/Catalogo" method="get">
                    <label for="produttore">Produttore:</label>
                    <input type="text" id="produttore" name="produttore" placeholder="Es. Samsung, Apple..." value="${param.produttore}">
                    <button type="submit">Filtra</button>
                </form>

                <!-- FORM 3: Filtro per tipo -->
                <form class="scheda-filtro" action="${pageContext.request.contextPath}/Catalogo" method="get">
                    <label for="tipo">Tipo:</label>
                    <select id="tipo" name="tipo">
                        <option value="">Tutti</option>
                        <option value="Smartphone" ${param.tipo == 'Smartphone' ? 'selected' : ''}>Smartphone</option>
                        <option value="Laptop" ${param.tipo == 'Laptop' ? 'selected' : ''}>Laptop</option>
                        <option value="Tablet" ${param.tipo == 'Tablet' ? 'selected' : ''}>Tablet</option>
                        <option value="Accessorio" ${param.tipo == 'Accessorio' ? 'selected' : ''}>Accessorio</option>
                    </select>
                    <button type="submit">Filtra</button>
                </form>

                <!-- RANGE PREZZO: prezzo minimo e massimo -->
                <form class="scheda-filtro" action="${pageContext.request.contextPath}/Catalogo" method="get">
                    <label for="prezzoMin">Prezzo minimo:</label>
                    <input type="number" id="prezzoMin" name="prezzoMin" min="0" step="1" value="${empty param.prezzoMin ? 0 : param.prezzoMin}">

                    <label for="prezzoMax">Prezzo massimo:</label>
                    <input type="number" id="prezzoMax" name="prezzoMax" min="0" step="1" value="${empty param.prezzoMax ? 1000 : param.prezzoMax}">

                    <button type="submit">Applica filtro prezzo</button>
                </form>

            </div>

            <c:choose>
                <c:when test="${empty prodotti}"> <!-- SE LA LISTA NON è STATA IMPOSTATA O è VUOTA -->
                    <p class="avviso-vuoto">Nessun prodotto trovato con i filtri selezionati.</p>
                </c:when>

                <c:otherwise>
                    <ul class="lista-elementi">
                        <c:forEach var="prodotto" items="${prodotti}">
                            <li class="scheda-elemento" id="riga-${prodotto.nomeModello}">

                                <c:if test="${not empty prodotto.imagine}">
                                    <img src="${pageContext.request.contextPath}/images/${prodotto.imagine}" alt="${prodotto.nomeModello}" width="60">
                                </c:if>

                                <span class="testo-modello">
                                    <a href="${pageContext.request.contextPath}/Prodotto?id=${prodotto.nomeModello}">
                                        ${prodotto.nomeModello}
                                    </a>
                                </span>
                                <span>Produttore: ${prodotto.nCAutore}</span>
                                <span>Tipo: ${prodotto.tipo}</span>
                                <span>Prezzo: ${prodotto.prezzo} €</span>
                                <c:if test="${prodotto.sconto > 0}">
                                    <span class="testo-importante">Sconto: ${prodotto.sconto}%</span>
                                </c:if>
                                <span>${prodotto.descrizione}</span>

                                <button type="button" onclick="aggiungiAlCarrello('${prodotto.nomeModello}')">Aggiungi al Carrello</button>
					
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>

        </div>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/gestioneCarrello.js"></script>
</body>
</html>