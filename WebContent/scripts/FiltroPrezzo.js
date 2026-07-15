// Gestione dello slider a doppio cursore per il filtro "Fascia di prezzo"
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