<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="it">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Gestione Prodotti</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/styles/main.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/styles/header.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/styles/footer.css">

</head>

<body class="modulo-admin-bg">

<header>
    <jsp:include page="../header.jsp"/>
</header>

<main>

<section class="modulo-admin-bg">

    <h1 class="titolo-admin-color">
        Gestione Prodotti
    </h1>

    <p style="color:white;">
        Seleziona il prodotto che desideri modificare.
    </p>

    <c:choose>

        <c:when test="${empty prodotti}">

            <p class="avviso-vuoto">
                Nessun prodotto presente nel catalogo.
            </p>

        </c:when>

        <c:otherwise>

            <ul class="lista-elementi">

                <c:forEach var="prodotto" items="${prodotti}">

                    <li class="scheda-elemento">

                        <c:choose>

                            <c:when test="${not empty prodotto.imagine}">

                                <img
                                    src="${pageContext.request.contextPath}/Image?codice=${prodotto.nCAutore}"
                                    alt="${prodotto.nomeModello}">

                            </c:when>

                            <c:otherwise>

                                <div class="no-img-placeholder">
                                    Nessuna immagine
                                </div>

                            </c:otherwise>

                        </c:choose>

                        <div class="scheda-info-prodotto">

                            <h3>
                                ${prodotto.nomeModello}
                            </h3>

                            <p class="scheda-descrizione-prodotto">
                                ${prodotto.descrizione}
                            </p>

                            <div class="scheda-dettagli-tecnici">

                                <span>
                                    <strong>Codice:</strong>
                                    ${prodotto.nCAutore}
                                </span>

                                <span>
                                    <strong>Categoria:</strong>
                                    ${prodotto.tipo}
                                </span>

                                <span>
                                    <strong>Disponibilità:</strong>
                                    ${prodotto.quantità}
                                </span>

                            </div>

                        </div>

                        <div class="scheda-prezzo-box">

                            <span class="scheda-prezzo-corrente">
                                € ${prodotto.prezzo}
                            </span>

                            <span class="scheda-sconto-badge">
                                ${prodotto.sconto}%
                            </span>

                            <form action="${pageContext.request.contextPath}/admin/ModificaProdotto" method="post">
                                <input type="hidden" name="codice" value="${prodotto.nomeModello}">
                                <button type="submit"
                                        class="azione-admin-color">
                                    Modifica
                                </button>

                            </form>

                        </div>

                    </li>

                </c:forEach>

            </ul>

        </c:otherwise>

    </c:choose>

</section>

</main>

<footer>
    <jsp:include page="../footer.jsp"/>
</footer>

</body>

</html>

