function cercaRicevute(contextPath) {
    let emailCliente = document.getElementById("filtro-cliente").value;
    let dataInizio = document.getElementById("data-inizio").value;
    let dataFine = document.getElementById("data-fine").value;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", contextPath + "/admin/CercaRicevute", true); 
    xhr.setRequestHeader("Content-Type", "application/json");

    setTimeout(function () {
        if (xhr.readyState < 4) {
            xhr.abort();
        }
    }, 10000);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let listaRicevute = document.getElementById("lista-ricevute");
            
            listaRicevute.innerHTML = ""; 
            
            if (!data || data.length === 0) {
                listaRicevute.innerHTML = '<li class="avviso-vuoto">Nessuna ricevuta trouvata per i filtri selezionati.</li>';
            } else {
                let risultatiHTML = "";
                
                for (let i = 0; i < data.length; i++) {
                    let r = data[i];
                    
                    risultatiHTML += 
                        '<li class="scheda-elemento">' +
                        '    <span>Ricevuta n°: ' + r.IDRicevutaFiscale + '</span>' +
                        '    <span>Utente: ' + r.emailUtente + '</span>' +
                        '    <span>Emessa il: ' + r.dataEmissione + '</span>' +
                        '    <span>Pagamento: ' + r.metodoPagamento + '</span>' +
                        '    <span>Via: ' + r.via + '</span>' +
                        '    <a href="' + contextPath + 'VisualizzaRicevuta?id=' + r.IDRicevutaFiscale + '">Apri Ricevuta</a>' +
                        '</li>';
                }
                
                listaRicevute.innerHTML = risultatiHTML;
            }
        }
    };

    let dati = { 
        emailCliente: emailCliente, 
        dataInizio: dataInizio, 
        dataFine: dataFine 
    };

    xhr.send(JSON.stringify(dati));
}