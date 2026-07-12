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
        /* --- Slider prezzo a doppia maniglia --- */
        .slider-prezzo-wrapper {
            position: relative;
            width: 100%;
            max-width: 320px;
            height: 40px;
            margin-top: 8px;
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
            position: absolute;
            top: 10px;
            left: 0;
            width: 100%;
            height: 18px;
            margin: 0;
            background: none;
            pointer-events: none;
            -webkit-appearance: none;
            appearance: none;
        }

        .slider-prezzo-wrapper input[type="range"]::-webkit-slider-thumb {
            pointer-events: auto;
            -webkit-appearance: none;
            appearance: none;
            width: 16px;
            height: 16px;
            border-radius: 50%;
            background: #333;
            cursor: pointer;
            border: 2px solid #fff;
            box-shadow: 0 0 2px rgba(0,0,0,0.4);
        }

        .slider-prezzo-wrapper input[type="range"]::-moz-range-thumb {
            pointer-events: auto;
            width: 16px;
            height: 16px;
            border-radius: 50%;
            background: #333;
            cursor: pointer;
            border: 2px solid #fff;
            box-shadow: 0 0 2px rgba(0,0,0,0.4);
        }

        .valori-prezzo-selezionati {
            display: flex;
            justify-content: space-between;
            font-size: 0.9em;
            margin-top: 4px;
        }
    </style>
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="contenitore-generico">
        <div class="blocco-contenuto">

            <h2>Catalogo Prodotti</h2>

            <div class="zona-filtri">

                <!-- FORM 1: Ricerca per nome modello -->
                <form class="scheda-filtro" action="${pageContext.request.contextPath}/Catalogo" method="post">
                    <input type="text" name="nomeModello" placeholder="Cerca per nome modello..." value="${param.nomeModello}">
                    <button type="submit">Cerca</button>
                </form>

                <!-- FORM 2: Filtro per produttore -->
                <form class="scheda-filtro" action="${pageContext.request.contextPath}/Catalogo" method="post">
                    <label for="produttore">Produttore:</label>
                    <input type="text" id="produttore" name="produttore" placeholder="Es. Samsung, Apple..." value="${param.produttore}">
                    <button type="submit">Filtra</button>
                </form>

                <!-- FORM 3: Filtro per tipo -->
                <form class="scheda-filtro" action="${pageContext.request.contextPath}/Catalogo" method="post">
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

                <!-- RANGE PREZZO: slider a doppia maniglia -->
                <form class="scheda-filtro" action="${pageContext.request.contextPath}/Catalogo" method="post" id="form-prezzo">
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

                    <button type="submit">Applica filtro prezzo</button>
                </form>

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