function filtraRicevute() {
    var emailCliente = document.getElementById("filtro-cliente").value;
    var dataInizio = document.getElementById("data-inizio").value;
    var dataFine = document.getElementById("data-fine").value;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "CercaRicevute", true); 
    xhr.setRequestHeader("Content-Type", "application/json");

    setTimeout(function () {
        if(xhr.readyState < 4){
            xhr.abort();
        }
    }, 10000);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var data = JSON.parse(xhr.responseText);
            var listaRicevute = document.getElementById("lista-ricevute");
            
            listaRicevute.innerHTML = ""; 
            
            if (!data || data.length === 0) {
                listaRicevute.innerHTML = '<li class="avviso-vuoto">Nessuna ricevuta trovata per i filtri selezionati.</li>';
            } else {
                var risultatiHTML = "";
                
                for (var i = 0; i < data.length; i++) {
                    var r = data[i];
                    
                    risultatiHTML += 
                        '<li class="scheda-elemento">' +
                        '    <span>Ricevuta n°: ' + r.IDRicevutaFiscale + '</span>' +
                        '    <span>Utente: ' + r.emailUtente + '</span>' +
                        '    <span>Emessa il: ' + r.dataEmissione + '</span>' +
                        '    <span>Pagamento: ' + r.metodoPagamento + '</span>' +
                        '    <span>Via: ' + r.via + '</span>' +
                        '    <a href="/Chips4Cheap/VisualizzaRicevuta?id=' + r.IDRicevutaFiscale + '">Apri Ricevuta</a>' +
                        '</li>';
                }
                
                listaRicevute.innerHTML = risultatiHTML;
            }
        }
    };

    var dati = { 
        emailCliente: emailCliente, 
        dataInizio: dataInizio, 
        dataFine: dataFine 
    };
    
    xhr.send(JSON.stringify(dati));
}