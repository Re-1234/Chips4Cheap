function formattaPrezzo(numero) {
    let valore = Number(numero); // forza la conversione, anche se arriva come stringa dal server
    return valore.toLocaleString('it-IT', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }) + ' €';
}
 