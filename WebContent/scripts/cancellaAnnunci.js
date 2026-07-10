function eliminaAnnuncio(idAnnuncio, contextPath) {
        
        let xhr = new XMLHttpRequest();
        xhr.open("POST", contextPath + "/admin/CancellaAnnunci", true); 
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        setTimeout(function () {
            if(xhr.readyState < 4){
                xhr.abort();
            }
        }, 10000);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let data = JSON.parse(xhr.responseText);
                
                if (data.successo) {
                    let riga = document.getElementById("riga-annuncio-" + idAnnuncio);
                    if (riga) {
                        riga.remove();
                    }
                    
                    let lista = document.getElementById("lista-annunci");
                    if (lista && lista.children.length === 0) {
                        lista.outerHTML = '<p class="avviso-vuoto">Non ci sono annunci o comunicazioni pubblicate nel sistema.</p>';
                    }
                } 
            }
        };

        xhr.send("idAnnuncio=" + idAnnuncio);
}