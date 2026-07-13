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

    <style>
    .zona-filtri {
        margin-bottom: 24px;
    }

    .scheda-filtro {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        gap: 10px;
        padding: 20px 24px;
        background: #f9f9f9;
        border: 1px solid #e0e0e0;
        border-radius: 8px;
        width: 900px;          /* card ancora più larga */
        max-width: 100%;
    }

    .riga-campo {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        gap: 0px;              /* gap quasi zero tra label e input */
        width: 100%;
    }

    .riga-campo label {
        font-weight: 600;
        margin-bottom: 2px;
    }

    .riga-campo input[type="text"],
    .riga-campo select {
        width: 320px;
        max-width: 100%;
        padding: 8px 10px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 0.95em;
        box-sizing: border-box;
    }

    .scheda-filtro button {
        padding: 8px 20px;
        border: none;
        border-radius: 6px;
        background: #333;
        color: #fff;
        cursor: pointer;
        font-size: 0.9em;
    }

    .scheda-filtro button:hover {
        background: #555;
    }

    /* --- Slider prezzo a doppia maniglia --- */
    .slider-prezzo-wrapper {
        position: relative;
        width: 320px;
        max-width: 100%;
        height: 40px;
        margin-top: 2px;
    }

    .slider-prezzo-track {
        position: absolute;
        top: 18px;
        left: 0;
        right: 0;
        height: 4px;
        background: #ddd;
        border-radius: 2px;
    }

    .slider-prezzo-range {
        position: absolute;
        top: 18px;
        height: 4px;
        background: #333;
        border-radius: 2px;
    }

    .slider-prezzo-wrapper input[type="range"] {
        -webkit-appearance: none !important;
        appearance: none !important;
        position: absolute;
        top: 10px;
        left: 0;
        width: 100%;
        height: 18px;
        margin: 0;
        background: transparent !important;
        pointer-events: none;
    }

    .slider-prezzo-wrapper input[type="range"]::-webkit-slider-runnable-track {
        background: transparent !important;
        border: none !important;
        height: 4px;
    }

    .slider-prezzo-wrapper input[type="range"]::-moz-range-track {
        background: transparent !important;
        border: none !important;
        height: 4px;
    }

    .slider-prezzo-wrapper input[type="range"]::-webkit-slider-thumb {
        pointer-events: auto;
        -webkit-appearance: none !important;
        appearance: none !important;
        width: 16px;
        height: 16px;
        border-radius: 50%;
        background: #333 !important;
        cursor: pointer;
        border: 2px solid #fff;
        box-shadow: 0 0 2px rgba(0,0,0,0.4);
    }

    .slider-prezzo-wrapper input[type="range"]::-moz-range-thumb {
        pointer-events: auto;
        width: 16px;
        height: 16px;
        border-radius: 50%;
        background: #333 !important;
        cursor: pointer;
        border: 2px solid #fff;
        box-shadow: 0 0 2px rgba(0,0,0,0.4);
    }

    .valori-prezzo-selezionati {
        position: relative;
        width: 320px;
        max-width: 100%;
        height: 20px;
        font-size: 0.9em;
        font-weight: 600;
        margin-top: 2px;
    }

    .valori-prezzo-selezionati span[id^="etichetta"] {
        position: absolute;
        transform: translateX(-50%);
        white-space: nowrap;
    }
</style>
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="contenitore-generico">
        <div class="blocco-contenuto">

            <h2>Catalogo Prodotti</h2>

            <div class="zona-filtri">

                <!-- FORM 2: Filtro per produttore -->
                <form class="scheda-filtro" action="${pageContext.request.contextPath}/Catalogo" method="post">
                	<label for = "nomeModello" style = "text-align: left;">Nome del Modello</label>
               		<input type="text" name="nomeModello" placeholder="Cerca per nome modello..." value="${param.nomeModello}">
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

                <!-- FORM 3: Filtro per tipo -->

                <!-- RANGE PREZZO: slider a doppia maniglia -->
               

            </div>

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

    <script>
        var contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/scripts/gestioneCarrello.js"></script>

    <script>
        (function () {
            var sliderMin = document.getElementById("prezzoMinSlider");
            var sliderMax = document.getElementById("prezzoMaxSlider");
            var hiddenMin = document.getElementById("prezzoMinHidden");
            var hiddenMax = document.getElementById("prezzoMaxHidden");
            var testoMin = document.getElementById("valoreMinVisualizzato");
            var testoMax = document.getElementById("valoreMaxVisualizzato");
            var rangeColorato = document.getElementById("rangeColorato");

            var scartoMinimo = 20;

            function aggiornaRangeVisivo() {
                var min = parseInt(sliderMin.value, 10);
                var max = parseInt(sliderMax.value, 10);
                var totale = parseInt(sliderMin.max, 10);

                var percentualeMin = (min / totale) * 100;
                var percentualeMax = (max / totale) * 100;

                rangeColorato.style.left = percentualeMin + "%";
                rangeColorato.style.right = (100 - percentualeMax) + "%";

                testoMin.innerText = min;
                testoMax.innerText = max;

                hiddenMin.value = min;
                hiddenMax.value = max;
            }

            sliderMin.addEventListener("input", function () {
                if (parseInt(sliderMax.value, 10) - parseInt(sliderMin.value, 10) < scartoMinimo) {
                    sliderMin.value = parseInt(sliderMax.value, 10) - scartoMinimo;
                }
                aggiornaRangeVisivo();
            });

            sliderMax.addEventListener("input", function () {
                if (parseInt(sliderMax.value, 10) - parseInt(sliderMin.value, 10) < scartoMinimo) {
                    sliderMax.value = parseInt(sliderMin.value, 10) + scartoMinimo;
                }
                aggiornaRangeVisivo();
            });

            aggiornaRangeVisivo();
        })();
    </script>
</body>
</html>