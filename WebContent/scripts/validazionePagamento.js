function validaVia() {
    const el = document.getElementById("via");
    if (!el) return true;

    const via = el.value.trim();
    const errEl = document.getElementById("err-via");
    
    if (!via) {
        errEl.innerText = "La via/indirizzo è obbligatoria.";
        return false;
    } else if (via.length > 50) {
        errEl.innerText = "La via non può superare i 50 caratteri.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaCap() {
    const el = document.getElementById("cap");
    if (!el) return true;

    const cap = el.value.trim();
    const errEl = document.getElementById("err-cap");
    
    if (!cap) {
        errEl.innerText = "Il CAP è obbligatorio.";
        return false;
    } else if (!/^\d{5}$/.test(cap)) {
        errEl.innerText = "Il CAP deve essere composto da esattamente 5 cifre numeriche.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaCivico() {
    const el = document.getElementById("numeroCivico");
    if (!el) return true;

    const civico = el.value.trim();
    const errEl = document.getElementById("err-civico");
    
    if (!civico) {
        errEl.innerText = "Il numero civico è obbligatorio.";
        return false;
    } else if (!/^\d+$/.test(civico)) {
        errEl.innerText = "Il numero civico deve essere un numero valido.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaMetodo() {
    const el = document.getElementById("metodoPagamento");
    if (!el) return true;

    const metodo = el.value;
    const errEl = document.getElementById("err-metodoPagamento");
    
    if (!metodo || metodo === "") {
        errEl.innerText = "Seleziona un metodo di pagamento valido.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaPagamento() {
    const isViaValid = validaVia();
    const isCapValid = validaCap();
    const isCivicoValid = validaCivico();
    const isMetodoValid = validaMetodo();
    
    return isViaValid && isCapValid && isCivicoValid && isMetodoValid;
}