function validaEmail() {
    const email = document.getElementById("email").value.trim();
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
    const username = document.getElementById("username").value.trim();
    const errEl = document.getElementById("err-username");
    
    if (!username) {
        errEl.innerText = "L'username è obbligatorio.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaPassword() {
    const password = document.getElementById("password").value;
    const errEl = document.getElementById("err-password");
    
    if (!password) {
        errEl.innerText = "La password è obbligatoria.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaVia() {
    const via = document.getElementById("via").value.trim();
    const errEl = document.getElementById("err-via");
    
    if (!via) {
        errEl.innerText = "L'indirizzo è obbligatorio.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaCap() {
    const cap = document.getElementById("cap").value.trim();
    const errEl = document.getElementById("err-cap");
    
    if (!cap) {
        errEl.innerText = "Il CAP è obbligatorio.";
        return false;
    } else if (!/^\d+$/.test(cap)) {
        errEl.innerText = "Il CAP deve contenere solo numeri.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaCivico() {
    const numerocivico = document.getElementById("numeroCivico").value.trim();
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

function validaForm() {
    const isEmailValid = validaEmail();
    const isUsernameValid = validaUsername();
    const isPasswordValid = validaPassword();
    const isViaValid = validaVia();
    const isCapValid = validaCap();
    const isCivicoValid = validaCivico();

    return isEmailValid && isUsernameValid && isPasswordValid && isViaValid && isCapValid && isCivicoValid;
}

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("email").addEventListener("change", validaEmail);
    document.getElementById("username").addEventListener("change", validaUsername);
    document.getElementById("password").addEventListener("change", validaPassword);
    document.getElementById("via").addEventListener("change", validaVia);
    document.getElementById("cap").addEventListener("change", validaCap);
    document.getElementById("numeroCivico").addEventListener("change", validaCivico); // AGGIUNGO QUA  I LISTENER INVECE DI ONCHANGE SUI TAG HTML
});