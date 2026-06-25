<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Account - Chips4Cheap</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/moduli.css" type="text/css">
</head>
<body>

    <jsp:include page="../header.jsp" />

    <main class="contenitore-pagina">
        <div class="modulo-centrato">
            <h2>Modifica Account</h2>
            
            <c:if test="${not empty requestScope.erroreServer}">
                <div class="errore-server">
                    <p>${requestScope.erroreServer}</p>
                </div>
            </c:if>

            <form id="formModificaAccount" action="${pageContext.request.contextPath}/common/ModificaAccount" method="post" onsubmit="return validaModifica()">
                
                <div class="sezione-contenuto">
                    <h3>Aggiorna i tuoi Dati</h3>
                    
                    <div class="gruppo-campo">
                        <label for="username">Username *</label>
                        <input type="text" id="username" name="username" value="${sessionScope.account.username}" maxlength="30" required>
                        <p class="messaggio-errore" id="err-username"></p>
                    </div>

                    <div class="gruppo-campo">
                        <label for="email">Email *</label>
                        <input type="email" id="email" name="email" value="${sessionScope.account.email}" maxlength="50" required>
                        <p class="messaggio-errore" id="err-email"></p>
                    </div>

                    <div class="gruppo-campo">
                        <label for="via">Via / Indirizzo *</label>
                        <input type="text" id="via" name="Via" value="${sessionScope.account.via}" maxlength="50" required>
                        <p class="messaggio-errore" id="err-via"></p>
                    </div>

                    <div class="riga-doppia">
                        <div class="gruppo-campo meta-larghezza">
                            <label for="numeroCivico">N° Civico *</label>
                            <input type="number" id="numeroCivico" name="NumeroCivico" value="${sessionScope.account.numeroCivico}" required>
                            <p class="messaggio-errore" id="err-civico"></p>
                        </div>

                        <div class="gruppo-campo meta-larghezza">
                            <label for="cap">CAP *</label>
                            <input type="number" id="cap" name="Cap" value="${sessionScope.account.cap}" required>
                            <p class="messaggio-errore" id="err-cap"></p>
                        </div>
                    </div>
                </div>

                <div id="blocco-azioni-account">
                    <button type="submit" class="bottone-azione">Salva Modifiche</button>
                    <a href="${pageContext.request.contextPath}/common/AreaPersonale" class="link-azione" id="link-modifica-cancella">Annulla</a>
                </div>
                
            </form>
        </div>
    </main>

    <jsp:include page="../footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/modifica-account.js"></script>
</body>
</html>