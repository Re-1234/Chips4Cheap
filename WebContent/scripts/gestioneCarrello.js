function modificaCarrello(autore, modello, azione) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "Carrello", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var data = JSON.parse(xhr.responseText);

                if (data.success) {
                    if (data.rimosso || data.nuovaQuantita <= 0) {
                        var riga = document.getElementById("riga-" + modello);
                        if (riga) {
                            riga.parentNode.removeChild(riga);
                        }
                    } else {
                        var quantitaEl = document.getElementById("quantita-" + modello);
                        var subtotaleEl = document.getElementById("subtotale-" + modello);
                        
                        if (quantitaEl) {
                            quantitaEl.innerText = data.nuovaQuantita;
                        }
                        if (subtotaleEl) {
                            subtotaleEl.innerText = "Subtotale: " + data.nuovoSubtotale + " €";
                        }
                    }

                    var totaleEl = document.getElementById("totale-carrello-dinamico");
                    if (totaleEl) {
                        totaleEl.innerText = data.nuovoTotale + " €";
                    }

                    if (data.carrelloVuoto) {
                        window.location.reload();
                    }
                } else {
                    console.error("Errore dal server: " + data.messaggio);
                }
            } else {
                console.error("Errore AJAX. Status: " + xhr.status);
            }
        }
    };

    var dati = {
        autore: autore,
        modello: modello,
        azione: azione
    };

    xhr.send(JSON.stringify(dati));
}