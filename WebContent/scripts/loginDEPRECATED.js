function validaEmail() {
    // Recupera e ripulisce il valore dell'email dagli spazi
    const email = document.getElementById("email").value.trim(); 
    const errEl = document.getElementById("err-email");
    
    if (!email) {
        errEl.innerText = "L'email è obbligatoria.";
        return false;
    } else if (!/^\S+@\S+\.\S+$/.test(email)) { // Controllo regex base
        errEl.innerText = "Formato email non valido.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaPassword() {
    // Per le password è preferibile non usare il trim() per permettere gli spazi se voluti
    const password = document.getElementById("password").value; 
    const errEl = document.getElementById("err-password");
    
    if (!password) {
        errEl.innerText = "La password è obbligatoria.";
        return false;
    }
    
    errEl.innerText = "";
    return true;
}

function validaLogin() {
    // Esegue tutte le validazioni
    const isEmailValid = validaEmail();
    const isPasswordValid = validaPassword();

    // Ritorna true solo se tutti i campi sono validi, permettendo il submit del form
    return isEmailValid && isPasswordValid; 
}

document.addEventListener("DOMContentLoaded", function() {
    // Aggiunge i listener per validare i campi non appena l'utente cambia il loro contenuto
    document.getElementById("email").addEventListener("change", validaEmail);
    document.getElementById("password").addEventListener("change", validaPassword);
});