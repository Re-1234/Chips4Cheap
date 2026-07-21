document.addEventListener('DOMContentLoaded', function () {

    const form = document.getElementById('formRicerca');
    const container = document.getElementById('risultatiProdotti');
    const contextPath = window.contextPath || '';

    if (!form) return;

    form.addEventListener('submit', function (e) {
        e.preventDefault(); // blocca il submit classico, niente ricarica pagina
        cercaProdotti();
    });

    function cercaProdotti() {
        const params = new URLSearchParams({
            nomeModello: document.getElementById('nomeModello').value,
            produttore: document.getElementById('produttore').value,
            tipo: document.getElementById('tipo').value,
            prezzoMin: document.getElementById('prezzoMinHidden').value,
            prezzoMax: document.getElementById('prezzoMaxHidden').value
        });

        fetch(contextPath + '/RicercaProdotti', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: params.toString()
        })
        .then(response => {
            if (!response.ok) throw new Error('Errore nella richiesta al server');
            return response.json();
        })
        .then(prodotti => renderProdotti(prodotti))
        .catch(err => {
            console.error(err);
            container.innerHTML = '<p class="avviso-vuoto">Errore durante la ricerca dei prodotti.</p>';
        });
    }

	function renderProdotti(prodotti) {
	    container.innerHTML = '';

	    if (!prodotti || prodotti.length === 0) {
	        container.innerHTML = '<p class="avviso-vuoto">Nessun prodotto trovato con i filtri selezionati.</p>';
	        return;
	    }

	    const ul = document.createElement('ul');
	    ul.classList.add('lista-elementi');

	    prodotti.forEach(p => {
	        const li = document.createElement('li');
	        li.classList.add('scheda-elemento');
	        li.id = 'riga-' + p.nomeModello;

	        let html = '';

	        if (p.imagine) {
	            html += `<img src="${contextPath}/${p.imagine}" alt="${p.nomeModello}">`;
	        } else {
	            html += `<div class="no-img-placeholder">No Img</div>`;
	        }

	        html += `
	            <div class="scheda-info-prodotto">
	                <a href="${contextPath}/MostrareProdotto?id=${encodeURIComponent(p.nomeModello)}" class="link-esteso">
	                    ${p.nomeModello}
	                </a>
	                <div class="scheda-dettagli-tecnici">
	                    <span>Produttore: <strong>${p.produttore}</strong></span>
	                    <span>Tipo: <strong>${p.tipo}</strong></span>
	                </div>
	                <p class="scheda-descrizione-prodotto">${p.descrizione || ''}</p>
	            </div>
	        `;

	        html += `
	            <div class="scheda-prezzo-box">
	                <span class="scheda-prezzo-corrente">${p.prezzo} &euro;</span>
	                ${p.sconto > 0 ? `<span class="scheda-sconto-badge">-${p.sconto}%</span>` : ''}
	            </div>
	        `;

	        html += `<button type="button" class="add-cart-button-small" onclick="aggiungiAlCarrello('${p.nomeModello}')">Aggiungi al Carrello</button>`;

	        if (typeof isAdmin !== 'undefined' && isAdmin) {
	            html += `<button type="button" class="bottone-pericolo" onclick="rimuoviProdotto('${p.nomeModello}')">Rimuovi Prodotto</button>`;
	        }

	        li.innerHTML = html;
	        ul.appendChild(li);
	    });

	    container.appendChild(ul);
	}

	function rimuoviProdotto(nomeModello) {
	    if (!confirm('Sei sicuro di voler rimuovere questo prodotto?')) return;

	    fetch(contextPath + '/admin/CancellaProdotto', {
	        method: 'POST',
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	        body: new URLSearchParams({ nomeModello: nomeModello }).toString()
	    })
	    .then(response => {
	        if (!response.ok) throw new Error('Errore durante la rimozione');
	        document.getElementById('riga-' + nomeModello).remove();
	    })
	    .catch(err => {
	        console.error(err);
	        alert('Errore durante la rimozione del prodotto.');
	    });
	}

});