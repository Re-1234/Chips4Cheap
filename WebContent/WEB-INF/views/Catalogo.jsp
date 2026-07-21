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
<body class="${not empty sessionScope.account and sessionScope.account.amministratore ? 'modulo-admin-bg' : ''}">

    <jsp:include page="header.jsp" />

    <main class="contenitore-generico">
        <div class="blocco-contenuto">
            <h2>Catalogo Prodotti</h2>

            <div class="zona-filtri">
                <!-- FORM: Filtro prodotti -->
                <form class="scheda-filtro" id="formRicerca">
                    
                    <div class="riga-campo">
                        <label for="nomeModello">Nome del Modello</label>
                        <input type="text" id="nomeModello" name="nomeModello" placeholder="Cerca per nome modello..." value="${param.nomeModello}">
                    </div>

                    <div class="riga-campo">
                        <label for="produttore">Produttore</label>
                        <input type="text" id="produttore" name="produttore" placeholder="Es. Samsung, Apple..." value="${param.produttore}">
                    </div>

                    <div class="riga-campo">
                        <label for="tipo">Tipo</label>
                        <select id="tipo" name="tipo">
                            <option value="">Tutti</option>
                            <option value="Smartphone" ${param.tipo == 'Smartphone' ? 'selected' : ''}>Smartphone</option>
                            <option value="Laptop" ${param.tipo == 'Laptop' ? 'selected' : ''}>Laptop</option>
                            <option value="Tablet" ${param.tipo == 'Tablet' ? 'selected' : ''}>Tablet</option>
                            <option value="Accessorio" ${param.tipo == 'Accessorio' ? 'selected' : ''}>Accessorio</option>
                        </select>
                    </div>

                    <div class="riga-campo">
                        <label>Fascia di prezzo</label>
                        <div class="slider-prezzo-wrapper">
                            <div class="slider-prezzo-track"></div>
                            <div class="slider-prezzo-range" id="rangeColorato"></div>

                            <input type="range" id="prezzoMinSlider" min="0" max="2000" step="10"
                                   value="${empty param.prezzoMin ? 0 : param.prezzoMin}">
                            <input type="range" id="prezzoMaxSlider" min="0" max="2000" step="10"
                                   value="${empty param.prezzoMax ? 2000 : param.prezzoMax}">
                        </div>

                        <div class="valori-prezzo-selezionati" style="display: flex; justify-content: space-between; width: 360px; max-width: 100%; font-size: 0.9rem; font-weight: 600; color: #555; margin-top: 5px;">
                            <span>€ <span id="valoreMinVisualizzato">${empty param.prezzoMin ? 0 : param.prezzoMin}</span></span>
                            <span>€ <span id="valoreMaxVisualizzato">${empty param.prezzoMax ? 2000 : param.prezzoMax}</span></span>
                        </div>
                    </div>

                    <!-- Campi nascosti per invio dati al server -->
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
                                
                                <!-- La scheda intera è cliccabile (grazie al link-esteso) ed ha effetti hover -->
                                <li class="scheda-elemento" id="riga-${prodotto.nomeModello}">
                                    
                                    <!-- Gestione Immagine / Fallback -->
                                    <c:choose>
                                        <c:when test="${not empty prodotto.imagine}">
                                            <img src="${pageContext.request.contextPath}/images/${prodotto.imagine}" alt="${prodotto.nomeModello}" size = "">
                                        </c:when>
                                        <c:otherwise>
                                            <div class="no-img-placeholder">No Img</div>
                                        </c:otherwise>
                                    </c:choose>

                                    <!-- Informazioni di prodotto -->
                                    <div class="scheda-info-prodotto">
                                        <!-- Titolo pulito che non sembra un link blu del browser, ma che copre l'intera riga al click -->
                                        <a href="${pageContext.request.contextPath}/MostrareProdotto?id=${prodotto.nomeModello}" class="link-esteso">
                                            ${prodotto.nomeModello}
                                        </a>
                                        <div class="scheda-dettagli-tecnici">
                                            <span>Produttore: <strong>${prodotto.nCAutore}</strong></span>
                                            <span>Tipo: <strong>${prodotto.tipo}</strong></span>
                                        </div>
                                        <p class="scheda-descrizione-prodotto">${prodotto.descrizione}</p>
                                    </div>

                                    <!-- Box prezzi e sconti -->
                                    <div class="scheda-prezzo-box">
                                        <span class="scheda-prezzo-corrente">${prodotto.prezzo} €</span>
                                        <c:if test="${prodotto.sconto > 0}">
                                            <span class="scheda-sconto-badge">-${prodotto.sconto}%</span>
                                        </c:if>
                                    </div>

                                    <form action="${pageContext.request.contextPath}/AggiuntaProdottoCarello" method="post">
									  <input type="hidden" name="NomeModello" value="${prodotto.nomeModello}">
									   <button type="submit" class="add-cart-button-small">
									        Aggiungi al Carrello
									   </button>
									</form>

                                    <!-- Bottone Rimuovi Prodotto, visibile solo agli amministratori -->
                                    <c:if test="${sessionScope.account.amministratore == true}">
                                        <form action="${pageContext.request.contextPath}/admin/CancellaProdotto" method="post" style="margin-top: 5px;">
                                            <input type="hidden" name="NomeModello" value="${prodotto.nomeModello}">
                                            <button type="submit" class="bottone-pericolo">
                                                Rimuovi Prodotto
                                            </button>
                                        </form>
                                    </c:if>
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
    	var isAdmin = ${not empty sessionScope.account and sessionScope.account.amministratore};
    </script>
    <script src="${pageContext.request.contextPath}/scripts/gestioneCarrello.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/ricercaProdotti.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/FiltroPrezzo.js"></script>
</body>
</html>
