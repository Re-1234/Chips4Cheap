function modificaCarrello(modello, azione) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "Carrello", true); //lascio a /Carrello occuparsi pure dell'AJAX oltre al GET iniziale
    xhr.setRequestHeader("Content-Type", "application/json");

    setTimeout(function () {
		if(xhr.readyState < 4){
			request.abort();
		}
	}, 10000);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var data = JSON.parse(xhr.responseText);
			
                if (data.rimosso || data.nuovaQuantita <= 0) {
                    var riga = document.getElementById("riga-" + modello);
                    if (riga) riga.parentNode.removeChild(riga); //questi if(...) permettono di far funzionare tutto anche se getElementById ritorna null, perche non esiste ad esempio se abbiamo distrutto il carrello
                } else {
                    var quantitaEl = document.getElementById("quantita-" + modello);
                    var subtotaleEl = document.getElementById("subtotale-" + modello);
                    
                    if (quantitaEl) quantitaEl.innerText = data.nuovaQuantita;
                    if (subtotaleEl) subtotaleEl.innerText = "Subtotale: " + data.nuovoSubtotale + " €";
                }

                var totaleEl = document.getElementById("totale-carrello-dinamico");
                if (totaleEl) totaleEl.innerText = data.nuovoTotale + " €";

                if (data.carrelloVuoto){/* window.location.reload();  Ricarico cosi tutta la pagina e mando un altra richiesta? (facendo vedere il messaggio che il carrello è vuoto) */
					var pagina = document.getElementById("blocco-contenuto");
					var contextPath = "${pageContext.request.contextPath}"; // Mi serve per l'hyperlink
					pagina.innerHTML =
						'<h2>Il tuo Carrello</h2>' +
						'<p class="avviso-vuoto">Il tuo carrello è attualmente vuoto.</p>' +
					    '<div class="zona-navigazione">' +
					    '    <a href=' + contextPath + '/Catalogo class="link-navigazione-indietro">' +
					    '        Torna al Catalogo prodotti' +
					   	'    </a>' +
					   	'</div>'; // evito di rimandare una richiesta al server cosi, anche se è bruttissimo per via dei new-line characters
				}

        }
    };

    var dati = {
        modello: modello,
        azione: azione
    };

    xhr.send(JSON.stringify(dati));
}