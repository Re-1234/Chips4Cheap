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

    <main>
        <section class="pagina-bicolonna">
            <h1 class="titolo-admin-color">Pannello Amministratore: Ricerca Ordini</h1>
                
            <div id="area-risultati">
                <h2 class="titolo-admin-color">Ricevute Fiscali</h2>
                
                <ul class="lista-elementi" id="lista-ricevute">
                    <li class="avviso-vuoto">Utilizza i filtri laterali per cercare le ricevute.</li>
                </ul>
            </div>
            
            <div class="zona-navigazione">
                <a href="${pageContext.request.contextPath}/common/AreaPersonale" class="link-navigazione-indietro">Torna all'Area Personale</a>
            </div>
        </section>
        
        <aside>
            <h3 class="titolo-admin-color">Filtra Ricevute</h3>
            
            <div>
                <label for="filtro-cliente" class="testo-importante">Username Cliente:</label>
                <input type="email" id="filtro-cliente" placeholder="Es. john.smith@gmail.com">
            </div>
            
            <div class="margine-alto">
                <label for="data-inizio" class="testo-importante">Dalla data (Data X):</label>
                <input type="date" id="data-inizio">
            </div>
            
            <div class="margine-alto">
                <label for="data-fine" class="testo-importante">Alla data (Data Y):</label>
                <input type="date" id="data-fine">
            </div>
            
            <button type="button" class="link-navigazione-indietro azione-admin-color margine-alto" onclick="filtraRicevute()">
                Applica Filtri
            </button>
        </aside>
    </main>

    <jsp:include page="../footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/cercaRicevute.js"></script>
    
</body>
</html>