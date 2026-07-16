function modificaCarrello(modello, azione, contextPath) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", contextPath + "/Carrello", true); // lascio a /Carrello occuparsi pure dell'AJAX oltre al GET iniziale
    xhr.setRequestHeader("Content-Type", "application/json");

    setTimeout(function () {
        if (xhr.readyState < 4) {
            xhr.abort(); // sì, xhr.abort() è corretto: si chiama sull'oggetto XMLHttpRequest stesso
        }
    }, 10000);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);

            if (data.rimosso || data.nuovaQuantita <= 0) {
                let riga = document.getElementById("riga-" + modello);
                if (riga) riga.parentNode.removeChild(riga); // questi if(...) permettono di far funzionare tutto anche se getElementById ritorna null, perché non esiste ad esempio se abbiamo svuotato il carrello
            } else {
                let quantitaEl = document.getElementById("quantita-" + modello);
                let subtotaleEl = document.getElementById("subtotale-" + modello);

                if (quantitaEl) quantitaEl.innerText = data.nuovaQuantita;
                if (subtotaleEl) subtotaleEl.innerText = "Subtotale: " + data.nuovoSubtotale + " €";
            }

            let totaleEl = document.getElementById("totale-carrello-dinamico");
            if (totaleEl) totaleEl.innerText = data.nuovoTotale + " €";

            if (data.carrelloVuoto) {
                let pagina = document.getElementById("blocco-contenuto");
                pagina.innerHTML =
                    '<h2>Il tuo Carrello</h2>' +
                    '<p class="avviso-vuoto">Il tuo carrello è attualmente vuoto.</p>' +
                    '<div class="zona-navigazione">' +
                    '    <a href="' + contextPath + '/Catalogo" class="link-navigazione-indietro">' +
                    '        Torna al Catalogo prodotti' +
                    '    </a>' +
                    '</div>';
            }
        }
    };

    let dati = {
        modello: modello,
        azione: azione
    };

    xhr.send(JSON.stringify(dati));
}


function aggiungiAlCarrello(nomeModello) {
    fetch(contextPath + '/AggiuntaProdottoCarello', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ NomeModello: nomeModello }).toString()
    })
    .then(function (response) {
        if (!response.ok) throw new Error('Errore durante l\'aggiunta al carrello');
    })
    .catch(function (err) {
        console.error(err);
        alert('Errore durante l\'aggiunta al carrello.');
    });
}