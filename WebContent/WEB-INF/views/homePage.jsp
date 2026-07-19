<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Chips4Cheap</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main>

        <section class="pagina-bicolonna">

            <h1>Benvenuti su Chips4Cheap</h1>

            <p>
                Il tuo punto di riferimento per l'acquisto di componenti hardware ai prezzi più competitivi sul mercato.
                Esplora la nostra selezione di processori, schede video, memorie e molto altro per assemblare o aggiornare il tuo PC.
            </p>

            <h2>Perché scegliere noi</h2>
            <p>
                Offriamo solo prodotti garantiti e delle migliori marche, affiancati da spedizioni veloci e un servizio clienti sempre a tua disposizione.
            </p>

        </section>

        <jsp:include page="annunci.jsp" />

    </main>

    <jsp:include page="footer.jsp" />

</body>
</html>