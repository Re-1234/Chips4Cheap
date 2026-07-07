<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amministrazione - Ricerca Ricevute</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body class="modulo-admin-bg" data-context="${pageContext.request.contextPath}">

    <jsp:include page="../header.jsp" />

    <main class="contenitore-generico">
        <div class="blocco-contenuto">
            <h1 class="titolo-admin-color">Pannello Amministratore: Ricerca Ordini</h1>
            
            <div class="area-bicolonna">
                
                <div class="testo-principale" id="area-risultati">
                    <h2 class="titolo-admin-color">Ricevute Fiscali</h2>
                    
                    <ul class="lista-elementi" id="lista-ricevute">
                        <p class="avviso-vuoto" id="messaggio-stato">
                            Configura i filtri nella barra laterale e clicca su "Applica Filtri" per visualizzare le ricevute.
                        </p>
                    </ul>
                </div>
                
                <aside class="barra-laterale">
                    <h3>Filtri di Ricerca</h3>
                    
                    <div class="margine-alto gruppo-filtro-admin">
                        <label for="filtro-cliente" class="testo-importante">Username Cliente:</label>
                        <input type="text" id="filtro-cliente" placeholder="Es. utente99" class="input-filtro-admin">
                    </div>
                    
                    <div class="margine-alto gruppo-filtro-admin">
                        <label for="data-inizio" class="testo-importante">Dalla data (Data X):</label>
                        <input type="date" id="data-inizio" class="input-filtro-admin">
                    </div>
                    
                    <div class="margine-alto gruppo-filtro-admin">
                        <label for="data-fine" class="testo-importante">Alla data (Data Y):</label>
                        <input type="date" id="data-fine" class="input-filtro-admin">
                    </div>
                    
                    <button type="button" class="link-navigazione-indietro azione-admin-color margine-alto bottone-filtro-admin" onclick="filtraRicevute()">
                        Applica Filtri
                    </button>
                </aside>
                
            </div>
            
            <div class="zona-navigazione">
                <a href="${pageContext.request.contextPath}/common/AreaPersonale" class="link-navigazione-indietro">Torna all'Area Personale</a>
            </div>
        </div>
    </main>

    <jsp:include page="../footer.jsp" />

    <script src="${pageContext.request.contextPath}/js/cercaRicevute.js"></script>
</body>
</html>