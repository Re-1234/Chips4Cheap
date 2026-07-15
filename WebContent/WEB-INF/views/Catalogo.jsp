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

                <!-- FORM: Filtro prodotti -->
                <form class="scheda-filtro" id="formRicerca">
                    <label for="nomeModello" style="text-align: left;">Nome del Modello</label>
                    <input type="text" id="nomeModello" name="nomeModello" placeholder="Cerca per nome modello..." value="${param.nomeModello}">

                    <label for="produttore">Produttore:</label>
                    <input type="text" id="produttore" name="produttore" placeholder="Es. Samsung, Apple..." value="${param.produttore}">

                    <label for="tipo">Tipo:</label>
                    <select id="tipo" name="tipo">
                        <option value="">Tutti</option>
                        <option value="Smartphone" ${param.tipo == 'Smartphone' ? 'selected' : ''}>Smartphone</option>
                        <option value="Laptop" ${param.tipo == 'Laptop' ? 'selected' : ''}>Laptop</option>
                        <option value="Tablet" ${param.tipo == 'Tablet' ? 'selected' : ''}>Tablet</option>
                        <option value="Accessorio" ${param.tipo == 'Accessorio' ? 'selected' : ''}>Accessorio</option>
                    </select>

                    <label>Fascia di prezzo:</label>

                    <div class="slider-prezzo-wrapper">
                        <div class="slider-prezzo-track"></div>
                        <div class="slider-prezzo-range" id="rangeColorato"></div>

                        <input type="range" id="prezzoMinSlider" min="0" max="2000" step="10"
                               value="${empty param.prezzoMin ? 0 : param.prezzoMin}">
                        <input type="range" id="prezzoMaxSlider" min="0" max="2000" step="10"
                               value="${empty param.prezzoMax ? 2000 : param.prezzoMax}">
                    </div>

                    <div class="valori-prezzo-selezionati">
                        <span>€ <span id="valoreMinVisualizzato">${empty param.prezzoMin ? 0 : param.prezzoMin}</span></span>
                        <span>€ <span id="valoreMaxVisualizzato">${empty param.prezzoMax ? 2000 : param.prezzoMax}</span></span>
                    </div>

                    <!-- campi nascosti: sono questi che vengono realmente inviati al server -->
                    <input type="hidden" name="prezzoMin" id="prezzoMinHidden" value="${empty param.prezzoMin ? 0 : param.prezzoMin}">
                    <input type="hidden" name="prezzoMax" id="prezzoMaxHidden" value="${empty param.prezzoMax ? 2000 : param.prezzoMax}">

                    <button type="submit">Search</button>
                </form>
            </div>

            <div id="risultatiProdotti">
                <c:choose>
                    <c:when test="${empty prodotti}">
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
                                        <a href="${pageContext.request.contextPath}/MostrareProdotto?id=${prodotto.nomeModello}">${prodotto.nomeModello}</a>
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

        </div>
    </main>

    <jsp:include page="footer.jsp" />

    <script>
        var contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/scripts/gestioneCarrello.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/ricercaProdotti.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/filtroPrezzo.js"></script>
</body>
</html>