function validaForm() {
    let valido = true;

    document.querySelectorAll(".messaggio-errore").forEach(function(el) {
        el.innerText = "";
    });

    const email = document.getElementById("email").value.trim();
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;
    const via = document.getElementById("via").value.trim();
    const cap = document.getElementById("cap").value.trim();
    const numerocivico = document.getElementById("numeroCivico").value.trim();

    if (!email) {
        document.getElementById("err-email").innerText = "L'email è obbligatoria.";
        valido = false;
    } else if (!/^\S+@\S+\.\S+$/.test(email)) {
        document.getElementById("err-email").innerText = "Formato email non valido.";
        valido = false;
    }

    if (!username) {
        document.getElementById("err-username").innerText = "L'username è obbligatorio.";
        valido = false;
    }

    if (!password) {
        document.getElementById("err-password").innerText = "La password è obbligatoria.";
        valido = false;
    }

    if (!via) {
        document.getElementById("err-via").innerText = "L'indirizzo è obbligatorio.";
        valido = false;
    }

    if (!cap) {
        document.getElementById("err-cap").innerText = "Il CAP è obbligatorio.";
        valido = false;
    } else if (!/^\d+$/.test(cap)) {
        document.getElementById("err-cap").innerText = "Il CAP deve contenere solo numeri.";
        valido = false;
    }

    if (!numerocivico) {
        document.getElementById("err-civico").innerText = "Il numero civico è obbligatorio.";
        valido = false;
    } else if (!/^\d+$/.test(numerocivico)) {
        document.getElementById("err-civico").innerText = "Il numero civico deve essere un numero.";
        valido = false;
    }

    return valido;
}