<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione - Chips4Cheap</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Footer.css" type="text/css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/registrazione.css" type="text/css">
</head>
<body>

    <jsp:include page="Header.jsp" />

    <main class="contenitore-registrazione">
        <div class="modulo-registrazione">
            <h2>Crea il tuo Account</h2>
            
            <c:if test="${not empty requestScope.erroreServer}">
                <div class="errore-server">
                    <p>${requestScope.erroreServer}</p>
                </div>
            </c:if>

            <form id="formRegistrazione" action="${pageContext.request.contextPath}/RegistraUtente" method="post" onsubmit="return validaForm()">
                
                <div class="gruppo-input">
                    <label for="email">Email *</label>
                    <input type="email" id="email" name="email" value="${param.email}" maxlength="50" required>
                    <p class="messaggio-errore" id="err-email"></p>
                </div>

                <div class="gruppo-input">
                    <label for="username">Username *</label>
                    <input type="text" id="username" name="username" value="${param.username}" maxlength="50" required>
                    <p class="messaggio-errore" id="err-username"></p>
                </div>

                <div class="gruppo-input">
                    <label for="password">Password *</label>
                    <input type="password" id="password" name="Password1" maxlength="16" required>
                    <p class="messaggio-errore" id="err-password"></p>
                </div>

                <div class="gruppo-input">
                    <label for="via">Via / Indirizzo *</label>
                    <input type="text" id="via" name="Via" value="${param.Via}" maxlength="50" required>
                    <p class="messaggio-errore" id="err-via"></p>
                </div>

                <div class="riga-input">
                    <div class="gruppo-input meta-larghezza">
                        <label for="cap">CAP *</label>
                        <input type="number" id="cap" name="Cap" value="${param.Cap}" required>
                        <p class="messaggio-errore" id="err-cap"></p>
                    </div>

                    <div class="gruppo-input meta-larghezza">
                        <label for="numeroCivico">N° Civico *</label>
                        <input type="number" id="numeroCivico" name="NumeroCivico" value="${param.NumeroCivico}" required>
                        <p class="messaggio-errore" id="err-civico"></p>
                    </div>
                </div>

                <button type="submit" class="btn-registrati">Registrati</button>
                
            </form>
        </div>
    </main>

    <jsp:include page="Footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/registrazione.js"></script>

</body>
</html>