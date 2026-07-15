document.addEventListener('DOMContentLoaded', function() {
    // Selezioniamo il contenitore principale dei risultati
    const contenitoreRisultati = document.getElementById('risultatiProdotti');
    
    if (contenitoreRisultati) {
        // Applichiamo un unico Listener sul contenitore (Event Delegation)
        contenitoreRisultati.addEventListener('click', function(event) {
            
            // 1. Controlla se l'utente ha cliccato sul pulsante "Aggiungi al Carrello" (o su un suo elemento interno)
            const bottoneCarrello = event.target.closest('.add-cart-button-small');
            if (bottoneCarrello) {
                event.stopPropagation(); // Blocca la propagazione del click alla riga madre (evita il cambio pagina)
                
                const nomeProdotto = bottoneCarrello.getAttribute('data-nome');
                if (nomeProdotto && typeof aggiungiAlCarrello === 'function') {
                    aggiungiAlCarrello(nomeProdotto);
                }
                return; // Esce dalla funzione per non attivare il reindirizzamento
            }
            
            // 2. Altrimenti, controlla se ha cliccato in un qualsiasi altro punto della scheda prodotto
            const schedaProdotto = event.target.closest('.scheda-elemento');
            if (schedaProdotto) {
                const nomeProdotto = schedaProdotto.getAttribute('data-nome');
                if (nomeProdotto) {
                    // Reindirizza l'utente alla pagina di dettaglio del prodotto corretto
                    window.location.href = contextPath + "/MostrareProdotto?id=" + encodeURIComponent(nomeProdotto);
                }
            }
        });
    }
});