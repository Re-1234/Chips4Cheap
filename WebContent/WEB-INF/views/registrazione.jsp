<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione - Chips4Cheap</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/moduli.css" type="text/css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="contenitore-pagina">
        <div class="modulo-centrato">
            <h2>Crea il tuo Account</h2>
            
            <c:if test="${not empty requestScope.erroreServer}">
                <div class="errore-server">
                    <p>${requestScope.erroreServer}</p>
                </div>
            </c:if>

            <form id="formRegistrazione" action="${pageContext.request.contextPath}/RegistraUtente" method="post" onsubmit="return validaForm()">
                
                <div class="gruppo-campo">
                    <label for="email">Email *</label>
                    <input type="email" id="email" name="email" value="${param.email}" maxlength="50" onchange="validaEmail()" required>
                    <p class="messaggio-errore" id="err-email"></p>
                </div>

                <div class="gruppo-campo">
                    <label for="username">Username *</label>
                    <input type="text" id="username" name="username" value="${param.username}" maxlength="30" onchange="validaUsername()" required>
                    <p class="messaggio-errore" id="err-username"></p>
                </div>

                <div class="gruppo-campo">
                    <label for="password">Password *</label>
                    <input type="password" id="password" name="Password1" maxlength="16" onchange="validaPassword()" required>
                    <p class="messaggio-errore" id="err-password"></p>
                </div>

                <div class="gruppo-campo">
                    <label for="via">Via / Indirizzo *</label>
                    <input type="text" id="via" name="Via" value="${param.Via}" maxlength="50" onchange="validaVia()" required>
                    <p class="messaggio-errore" id="err-via"></p>
                </div>

                <div class="riga-doppia">
                    <div class="gruppo-campo meta-larghezza">
                        <label for="cap">CAP *</label>
                        <input type="number" id="cap" name="Cap" value="${param.Cap}" onchange="validaCap()" required>
                        <p class="messaggio-errore" id="err-cap"></p>
                    </div>

                    <div class="gruppo-campo meta-larghezza">
                        <label for="numeroCivico">N° Civico *</label>
                        <input type="number" id="numeroCivico" name="NumeroCivico" value="${param.NumeroCivico}" onchange="validaCivico()" required>
                        <p class="messaggio-errore" id="err-civico"></p>
                    </div>
                </div>

                <button type="submit" class="bottone-azione">Registrati</button>
                
            </form>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/validazioneInfoAccount.js"></script>
</body>
</html>