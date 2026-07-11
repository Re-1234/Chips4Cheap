function validaNomeModello() {
    const el = document.getElementById("nomeModello");
    if (!el) return true; // Nel caso in cui il campo sia disabilitato o rimosso

    const nome = el.value.trim();
    const errEl = document.getElementById("err-nomeModello");
    
    if (!nome) {
        errEl.innerText = "Il nome modello è obbligatorio.";
        return false;
    } else if (nome.length > 50) {
        errEl.innerText = "Il nome modello non può superare i 50 caratteri.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaPrezzo() {
    const el = document.getElementById("prezzo");
    if (!el) return true;

    const prezzo = el.value.trim();
    const errEl = document.getElementById("err-prezzo");
    
    if (!prezzo) {
        errEl.innerText = "Il prezzo è obbligatorio.";
        return false;
    } else if (!/^\d+(\.\d+)?$/.test(prezzo)) {
        errEl.innerText = "Il prezzo deve essere un numero positivo (usa il punto per i decimali).";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaTipo() {
    const el = document.getElementById("tipo");
    if (!el) return true;

    const tipo = el.value.trim();
    const errEl = document.getElementById("err-tipo");
    
    if (!tipo) {
        errEl.innerText = "Il tipo/categoria è obbligatorio.";
        return false;
    } else if (tipo.length > 50) {
        errEl.innerText = "Il tipo non può superare i 50 caratteri.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaQuantita() {
    const el = document.getElementById("quantita");
    if (!el) return true;

    const quantita = el.value.trim();
    const errEl = document.getElementById("err-quantita");
    
    if (!quantita) {
        errEl.innerText = "La quantità è obbligatoria.";
        return false;
    } else if (!/^\d+$/.test(quantita)) {
        errEl.innerText = "La quantità deve essere un numero intero positivo o zero.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaSconto() {
    const el = document.getElementById("sconto");
    if (!el) return true;

    const sconto = el.value.trim();
    const errEl = document.getElementById("err-sconto");
    
    if (!sconto) {
        errEl.innerText = "Lo sconto è obbligatorio.";
        return false;
    } else if (!/^([0-9]|[1-9][0-9]|100)$/.test(sconto)) {
        errEl.innerText = "Lo sconto deve essere una percentuale intera compresa tra 0 e 100.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaDescrizione() {
    const el = document.getElementById("descrizione");
    if (!el) return true;

    const descrizione = el.value.trim();
    const errEl = document.getElementById("err-descrizione");
    
    if (!descrizione) {
        errEl.innerText = "La descrizione è obbligatoria.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaProdotto() {
    const isNomeValid = validaNomeModello();
    const isPrezzoValid = validaPrezzo();
    const isTipoValid = validaTipo();
    const isQuantitaValid = validaQuantita();
    const isScontoValid = validaSconto();
    const isDescrizioneValid = validaDescrizione();
    
    return isNomeValid && isPrezzoValid && isTipoValid && isQuantitaValid && isScontoValid && isDescrizioneValid;
}