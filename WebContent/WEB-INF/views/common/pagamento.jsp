<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - Chips4Cheap</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/moduli.css" type="text/css">
</head>
<body>

    <jsp:include page="../header.jsp" />

    <main class="contenitore-pagina">
        <div class="modulo-centrato">
            
            <h2>Completamento Ordine</h2>
            
            <c:if test="${not empty requestScope.erroreServer}">
                <div class="errore-server">
                    <p>${requestScope.erroreServer}</p>
                </div>
            </c:if>

            <form id="formPagamento" action="${pageContext.request.contextPath}/common/CreaRicevuta" method="post" onsubmit="return validaPagamento()">
                
                <div class="sezione-contenuto">
                    <h3>Indirizzo di Spedizione</h3>
                    
                    <div class="gruppo-campo">
                        <label for="via">Via / Indirizzo *</label>
                        <input type="text" id="via" name="Via" value="${sessionScope.account.via}" maxlength="50" onchange="validaVia()" required>
                        <p class="messaggio-errore" id="err-via"></p>
                    </div>

                    <div class="riga-doppia">
                        <div class="gruppo-campo meta-larghezza">
                            <label for="cap">CAP *</label>
                            <input type="text" id="cap" name="Cap" value="${sessionScope.account.cap}" maxlength="5" onchange="validaCap()" required>
                            <p class="messaggio-errore" id="err-cap"></p>
                        </div>

                        <div class="gruppo-campo meta-larghezza">
                            <label for="numeroCivico">N° Civico *</label>
                            <input type="number" id="numeroCivico" name="NumeroCivico" value="${sessionScope.account.numeroCivico}" onchange="validaCivico()" required>
                            <p class="messaggio-errore" id="err-civico"></p>
                        </div>
                    </div>
                </div>

                <div class="sezione-contenuto">
                    <h3>Strumento di Pagamento</h3>
                    
                    <div class="gruppo-campo">
                        <label for="metodoPagamento">Seleziona Metodo *</label>
                        <select id="metodoPagamento" name="metodoPagamento" onchange="validaMetodo()" required>
                            <option value="" disabled selected>-- Scegli un'opzione --</option>
                            <option value="Carta di Credito/Debito">Carta di Credito / Debito</option>
                            <option value="Gift Card">Gift Card</option>
                        </select>
                        <p class="messaggio-errore" id="err-metodoPagamento"></p>
                    </div>
                </div>

                <button type="submit" class="bottone-azione">Conferma e Paga</button>
                
                <div>
                    <a href="${pageContext.request.contextPath}/Carrello" class="link-azione">Annulla e torna al Carrello</a>
                </div>

            </form>
            
        </div>
    </main>

    <jsp:include page="../footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/validazionePagamento.js"></script>
</body>
</html>