<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Il tuo Carrello - Chips4Cheap</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main class="contenitore-generico">
        <div class="blocco-contenuto">
            
            <h2>Il tuo Carrello</h2>

            <c:choose>
                <c:when test="${empty sessionScope.carrello}"> <!-- SE IL CARRELLO è INIZIALIZZATO MA VUOTO? -->
                    <p class="avviso-vuoto">Il tuo carrello è attualmente vuoto.</p>
                    
                    <div class="zona-navigazione">
                        <a href="${pageContext.request.contextPath}/Catalogo" class="link-navigazione-indietro">
                            Torna al Catalogo prodotti
                        </a>
                    </div>
                </c:when>
                
                <c:otherwise>
                    <ul class="lista-elementi">
                        <c:forEach var="item" items="${sessionScope.carrello}">
                            <li class="scheda-elemento" id="riga-${item.prodotto.nomeModello}">
        
        						<c:if test="${not empty item.prodotto.imagine}">
            						<img src="${pageContext.request.contextPath}/images/${item.prodotto.imagine}" alt="${item.prodotto.nomeModello}" width="60">
        						</c:if>
        
        						<span class="testo-modello">
                                    <a href="${pageContext.request.contextPath}/Prodotto?id=${item.prodotto.nomeModello}">
                                        ${item.prodotto.nomeModello}
                                    </a>
                                </span>
        						<span>Produttore: ${item.prodotto.nCAutore}</span>
        						<span>Prezzo unitario: ${item.prodotto.prezzo} €</span>
        
        						<span>
            						Quantità: 
            						<button type="button" onclick="modificaCarrello('${item.prodotto.nomeModello}', 'diminuisci')">-</button>
            						<span id="quantita-${item.prodotto.nomeModello}">${item.quantita}</span>
            						<button type="button" onclick="modificaCarrello('${item.prodotto.nomeModello}', 'aumenta')">+</button>
        						</span>
        
        						<span id="subtotale-${item.prodotto.nomeModello}">Subtotale: ${item.prodotto.prezzo * item.quantita} €</span>
        
       							 <button type="button" onclick="modificaCarrello('${item.prodotto.nomeModello}', 'rimuovi')">Rimuovi</button>
        
    						</li>
                        </c:forEach>
                    </ul>

                    <div class="scheda-elemento separatore-alto">
                        <span class="testo-importante">Totale Complessivo:</span>
                        <span id="totale-carrello-dinamico" class="testo-importante">${sessionScope.carrello.prezzoTotale} €</span>
                    </div>
                    
                    <div class="zona-navigazione margine-alto">
                        <a href="${pageContext.request.contextPath}/Catalogo" class="link-navigazione-indietro">
                            Continua lo Shopping
                        </a>
                        <a href="${pageContext.request.contextPath}/common/Pagamento" class="link-navigazione-indietro margine-sinistra">
                            Procedi all'Ordine
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
            
        </div>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/gestioneCarrello.js"></script>
</body>
</html>