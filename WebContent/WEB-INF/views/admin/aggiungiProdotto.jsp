<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Prodotto - Pannello Amministratore</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/moduli.css" type="text/css">
</head>
<body class="modulo-admin-bg">

    <jsp:include page="../header.jsp" />

    <main>
        <section class="modulo-centrato">
            <h2 class="titolo-admin-color">Aggiungi un Nuovo Prodotto</h2>
            
            <c:if test="${not empty requestScope.erroreServer}">
                <div class="errore-server">
                    <p>${requestScope.erroreServer}</p>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/GestioneProdotto" method="POST" enctype="multipart/form-data" onsubmit="return validaProdotto()">
                
                <input type="hidden" name="action" value="add"> <!-- cosi GestioneProdotto sa cosa fare e riciclo codice -->

                <div class="gruppo-campo">
                    <label for="nomeModello">Nome Modello (Codice Univoco) *</label>
                    <input type="text" id="nomeModello" name="nomeModello" required maxlength="50" placeholder="Es. AMD Ryzen 5 5600X" onchange="validaNomeModello()">
                    <p class="messaggio-errore" id="err-nomeModello"></p>
                </div>

				<div class="gruppo-campo">
                    <label for="nCAutore">Produttore / Marca *</label>
                    <input type="text" id="nCAutore" name="nCAutore" required maxlength="50" placeholder="Es. AMD, Intel, Nvidia" onchange="validaNCAutore()">
                    <p class="messaggio-errore" id="err-nCAutore"></p>
                </div>

                <div class="riga-doppia">
                    <div class="gruppo-campo meta-larghezza">
                        <label for="prezzo">Prezzo (€) *</label>
                        <input type="number" id="prezzo" name="prezzo" step="0.01" min="0" required placeholder="Es. 199.99" onchange="validaPrezzo()">
                        <p class="messaggio-errore" id="err-prezzo"></p>
                    </div>

                    <div class="gruppo-campo meta-larghezza">
                        <label for="tipo">Tipo / Categoria *</label>
                        <input type="text" id="tipo" name="tipo" required maxlength="50" placeholder="Es. CPU, GPU, Scheda Madre" onchange="validaTipo()">
                        <p class="messaggio-errore" id="err-tipo"></p>
                    </div>
                </div>

                <div class="riga-doppia">
                    <div class="gruppo-campo meta-larghezza">
                        <label for="quantita">Quantità in Magazzino *</label>
                        <input type="number" id="quantita" name="quantita" min="0" required placeholder="Es. 50" onchange="validaQuantita()">
                        <p class="messaggio-errore" id="err-quantita"></p>
                    </div>

                    <div class="gruppo-campo meta-larghezza">
                        <label for="sconto">Sconto (%) *</label>
                        <input type="number" id="sconto" name="sconto" min="0" max="100" required value="0" onchange="validaSconto()">
                        <p class="messaggio-errore" id="err-sconto"></p>
                    </div>
                </div>

                <div class="gruppo-campo">
                    <label for="descrizione">Descrizione *</label>
                    <textarea id="descrizione" name="descrizione" rows="5" required placeholder="Scrivi qui le specifiche tecniche e la descrizione del prodotto..." onchange="validaDescrizione()"></textarea>
                    <p class="messaggio-errore" id="err-descrizione"></p>
                </div>

                <div class="gruppo-campo">
                    <label for="imagine">Immagine del Prodotto</label>
                    <input type="file" id="imagine" name="imagine" accept="image/png, image/jpeg, image/jpg, image/webp">
                    <p>Opzionale. Se non carichi nulla, verrà usato il placeholder predefinito.</p>
                </div>

                <button type="submit" class="bottone-azione azione-admin-color">Aggiungi al Catalogo</button>
                
                <a href="${pageContext.request.contextPath}/Catalogo" class="link-azione azione-admin-color">
                    Annulla e torna al Catalogo
                </a>
                
            </form>
        </section>
    </main>

    <jsp:include page="../footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/validazioneProdotto.js"></script>
</body>
</html>