<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Chips4Cheap</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/moduli.css" type="text/css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="contenitore-pagina">
        <div class="modulo-centrato">
            <h2>Accedi al tuo Account</h2>
            
            <c:if test="${not empty requestScope.erroreServer}">
                <div class="errore-server">
                    <p>${requestScope.erroreServer}</p>
                </div>
            </c:if>

            <form id="formLogin" action="${pageContext.request.contextPath}/Autorizza" method="post" onsubmit="return validaLogin()">
                
                <div class="gruppo-campo">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="${param.email}" required>
                    <p class="messaggio-errore" id="err-email"></p>
                </div>

                <div class="gruppo-campo">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                    <p class="messaggio-errore" id="err-password"></p>
                </div>

                <button type="submit" class="bottone-azione">Accedi</button>
                
                <div id="zona-registrazione-alternativa">
                    <p>Non hai un account? <a href="${pageContext.request.contextPath}/Registrazione" id="link-vai-a-registrazione">Registrati qui</a></p>
                </div>
                
            </form>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/validazioneInfoAccount.js"></script>
</body>
</html>