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
                html += `<img src="${contextPath}/images/${p.imagine}" alt="${p.nomeModello}" width="60">`;
            }

            html += `
                <span class="testo-modello">
                    <a href="${contextPath}/MostrareProdotto?id=${encodeURIComponent(p.nomeModello)}">${p.nomeModello}</a>
                </span>
                <span>Produttore: ${p.produttore}</span>
                <span>Tipo: ${p.tipo}</span>
                <span>Prezzo: ${p.prezzo} &euro;</span>
            `;

            if (p.sconto > 0) {
                html += `<span class="testo-importante">Sconto: ${p.sconto}%</span>`;
            }

            html += `<span>${p.descrizione || ''}</span>`;
            html += `<button type="button" onclick="aggiungiAlCarrello('${p.nomeModello}')">Aggiungi al Carrello</button>`;

            li.innerHTML = html;
            ul.appendChild(li);
        });

        container.appendChild(ul);
    }

});