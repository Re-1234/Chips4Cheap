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
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login.css" type="text/css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="contenitore-login">
        <div class="modulo-login">
            <h2>Accedi al tuo Account</h2>
            
            <c:if test="${not empty requestScope.erroreLogin}">
                <div class="errore-server">
                    <p>${requestScope.erroreLogin}</p>
                </div>
            </c:if>

            <form id="formLogin" action="${pageContext.request.contextPath}/Autorizza" method="post" onsubmit="return validaLogin()">
                
                <div class="gruppo-input">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="${param.email}" required>
                    <p class="messaggio-errore" id="err-email"></p>
                </div>

                <div class="gruppo-input">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                    <p class="messaggio-errore" id="err-password"></p>
                </div>

                <button type="submit" class="btn-login">Accedi</button>
                
                <div class="link-utili">
                    <p>Non hai un account? <a href="${pageContext.request.contextPath}/Registrazione">Registrati qui</a></p>
                </div>
                
            </form>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/login.js"></script>

</body>
</html>