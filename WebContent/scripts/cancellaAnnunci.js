function eliminaAnnuncio(idAnnuncio, contextPath) {
    if (confirm("Sei sicuro di voler eliminare definitivamente questo annuncio?")) {
        
        var xhr = new XMLHttpRequest();
        xhr.open("POST", contextPath + "/admin/CancellaAnnunci", true); 
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        setTimeout(function () {
            if(xhr.readyState < 4){
                xhr.abort();
            }
        }, 10000);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var data = JSON.parse(xhr.responseText);
                
                if (data.success) {
                    var riga = document.getElementById("riga-annuncio-" + idAnnuncio);
                    if (riga) {
                        riga.remove();
                    }
                    
                    var lista = document.querySelector(".lista-elementi");
                    if (lista && lista.children.length === 0) {
                        lista.outerHTML = '<p class="avviso-vuoto">Non ci sono annunci o comunicazioni pubblicate nel sistema.</p>';
                    }
                } else {
                    alert("Errore: impossibile eliminare l'annuncio.");
                }
            }
        };

        xhr.send("idAnnuncio=" + idAnnuncio);
    }
}