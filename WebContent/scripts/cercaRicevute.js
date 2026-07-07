function filtraRicevute() {
    // Recupera il contextPath impostato dinamicamente come data-attribute sul body della JSP
    var contextPath = document.body.getAttribute("data-context");
    
    var cliente = document.getElementById("filtro-cliente").value;
    var dataInizio = document.getElementById("data-inizio").value;
    var dataFine = document.getElementById("data-fine").value;
    var lista = document.getElementById("lista-ricevute");

    var xhr = new XMLHttpRequest();
    xhr.open("POST", contextPath + "/admin/CercaRicevute", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    setTimeout(function () {
        if (xhr.readyState < 4) {
            xhr.abort();
        }
    }, 10000);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var ricevute = JSON.parse(xhr.responseText);
            lista.innerHTML = "";

            if (ricevute.length === 0) {
                lista.innerHTML = '<p class="avviso-vuoto">Nessuna ricevuta fiscale trovata con i filtri selezionati.</p>';
            } else {
                for (var i = 0; i < ricevute.length; i++) {
                    var r = ricevute[i];
                    var li = document.createElement("li");
                    li.className = "scheda-elemento";
                    
                    li.innerHTML = 
                        '<span><strong class="testo-importante">ID Ordine:</strong> #' + r.idOrdine + '</span>' +
                        '<span><strong>Cliente:</strong> ' + r.usernameCliente + '</span>' +
                        '<span><strong>Data:</strong> ' + r.dataEmissione + '</span>' +
                        '<span><strong class="testo-importante">Totale:</strong> ' + r.totale.toFixed(2) + ' €</span>' +
                        '<a href="' + contextPath + '/admin/DettaglioRicevuta?id=' + r.idOrdine + '" class="link-nascosto-elemento azione-admin-color">Dettagli</a>';
                    
                    lista.appendChild(li);
                }
            }
        }
    };

    var datiFiltro = {
        cliente: cliente,
        dataInizio: dataInizio,
        dataFine: dataFine
    };

    xhr.send(JSON.stringify(datiFiltro));
}