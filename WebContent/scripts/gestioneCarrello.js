function modificaCarrello(modello, azione, contextPath) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST",contextPath + "/Carrello", true); //lascio a /Carrello occuparsi pure dell'AJAX oltre al GET iniziale
    xhr.setRequestHeader("Content-Type", "application/json");

    setTimeout(function () {
		if(xhr.readyState < 4){
			xhr.abort();	// non request.abort() giusto?
		}
	}, 10000);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
			
                if (data.rimosso || data.nuovaQuantita <= 0) {
                    let riga = document.getElementById("riga-" + modello);
                    if (riga) riga.parentNode.removeChild(riga); //questi if(...) permettono di far funzionare tutto anche se getElementById ritorna null, perche non esiste ad esempio se abbiamo distrutto il carrello
                } else {
                    let quantitaEl = document.getElementById("quantita-" + modello);
                    let subtotaleEl = document.getElementById("subtotale-" + modello);
                    
                    if (quantitaEl) quantitaEl.innerText = data.nuovaQuantita;
                    if (subtotaleEl) subtotaleEl.innerText = "Subtotale: " + data.nuovoSubtotale + " €";
                }

                let totaleEl = document.getElementById("totale-carrello-dinamico");
                if (totaleEl) totaleEl.innerText = data.nuovoTotale + " €";

                if (data.carrelloVuoto){
					let pagina = document.getElementById("blocco-contenuto");
					pagina.innerHTML =
						'<h2>Il tuo Carrello</h2>' +
						'<p class="avviso-vuoto">Il tuo carrello è attualmente vuoto.</p>' +
					    '<div class="zona-navigazione">' +
					    '    <a href="' + contextPath +'/Catalogo" class="link-navigazione-indietro">' +	// RISOLTO: posso passare dalla jsp 
					    '        Torna al Catalogo prodotti' +
					   	'    </a>' +
					   	'</div>'; // evito di rimandare una richiesta al server cosi, anche se è bruttissimo per via dei new-line characters
				}

        }
    };

    let dati = {
        modello: modello,
        azione: azione
    };

    xhr.send(JSON.stringify(dati));
}