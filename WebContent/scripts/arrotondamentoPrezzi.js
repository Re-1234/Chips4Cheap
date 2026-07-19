function formattaPrezzo(numero) {
    return numero.toLocaleString('it-IT', { 
        minimumFractionDigits: 2, 
        maximumFractionDigits: 2 
    }) + ' €';
}