function validaEmail() {
    const el = document.getElementById("email");
    if (!el) return true;

    const email = el.value.trim();
    const errEl = document.getElementById("err-email");
    
    if (!email) {
        errEl.innerText = "L'email è obbligatoria.";
        return false;
    } else if (!/^\S+@\S+\.\S+$/.test(email)) {
        errEl.innerText = "Formato email non valido.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaUsername() {
    const el = document.getElementById("username");
    if (!el) return true;

    const username = el.value.trim();
    const errEl = document.getElementById("err-username");
    
    if (!username) {
        errEl.innerText = "L'username è obbligatorio.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaPassword() {
    const el = document.getElementById("password");
    if (!el) return true;

    const password = el.value;
    const errEl = document.getElementById("err-password");
    
    if (!password) {
        errEl.innerText = "La password è obbligatoria.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaVecchiaPassword() {	/* è praticamente identico al controllo password ma con messaggi diversi */
    const el = document.getElementById("vecchiaPassword");
    if (!el) return true;

    const vecchiaPassword = el.value;
    const errEl = document.getElementById("err-vecchiaPassword");
    
    if (!vecchiaPassword) {
        errEl.innerText = "La vecchia password è obbligatoria.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaVia() {
    const el = document.getElementById("via");
    if (!el) return true;

    const via = el.value.trim();
    const errEl = document.getElementById("err-via");
    
    if (!via) {
        errEl.innerText = "La via/indirizzo è obbligatoria.";
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
        errEl.innerText = "Il CAP deve essere di 5 cifre.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaCivico() {
    const el = document.getElementById("numeroCivico");
    if (!el) return true;

    const numerocivico = el.value.trim();
    const errEl = document.getElementById("err-civico");
    
    if (!numerocivico) {
        errEl.innerText = "Il numero civico è obbligatorio.";
        return false;
    } else if (!/^\d+$/.test(numerocivico)) {
        errEl.innerText = "Il numero civico deve essere un numero.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaLogin() {
    const isEmailValid = validaEmail();
    const isPasswordValid = validaPassword();
    return isEmailValid && isPasswordValid;
}

function validaForm() {
    const isEmailValid = validaEmail();
    const isUsernameValid = validaUsername();
    const isPasswordValid = validaPassword();
    const isViaValid = validaVia();
    const isCapValid = validaCap();
    const isCivicoValid = validaCivico();
    return isEmailValid && isUsernameValid && isPasswordValid && isViaValid && isCapValid && isCivicoValid;
}

function validaModifica() {
    const isEmailValid = validaEmail();
    const isUsernameValid = validaUsername();
    const isVecchiaPasswordValid = validaVecchiaPassword();
    const isPasswordValid = validaPassword();
    const isViaValid = validaVia();
    const isCapValid = validaCap();
    const isCivicoValid = validaCivico();
    return isEmailValid && isUsernameValid && isVecchiaPasswordValid && isPasswordValid && isViaValid && isCapValid && isCivicoValid;
}
