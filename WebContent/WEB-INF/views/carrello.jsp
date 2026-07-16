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

    <main>
        <section>
            
            <h2>Il tuo Carrello</h2>
			
			<c:if test="${not empty requestScope.erroreServer}">
                <div class="errore-server">
                    <p>${requestScope.erroreServer}</p>
                </div>
            </c:if>
			
            <c:choose>
                <c:when test="${empty sessionScope.carrello}"> <!-- SE IL CARRELLO è INIZIALIZZATO MA VUOTO? controlla sia se è vuoto sia se non esiste, figo-->
                    <p class="avviso-vuoto">Il tuo carrello è attualmente vuoto.</p>
                    
                    <div class="zona-navigazione">
                        <a href="${pageContext.request.contextPath}/Catalogo" class="link-navigazione-indietro">
                            Torna al Catalogo prodotti
                        </a>
                    </div>
                </c:when>
                
                <c:otherwise>
                
                	<c:set var="totaleCarrello" value="0" />
                	
                    <ul class="lista-elementi">
                        <c:forEach var="prodotto" items="${sessionScope.carrello}">
                            <li class="scheda-elemento" id="riga-${prodotto.nomeModello}">
        
        						<c:set var="totaleCarrello" value="${totaleCarrello + prodotto.subtotale}" />
        
        						<c:if test="${not empty prodotto.imagine}">
            						<img src="${pageContext.request.contextPath}/${prodotto.imagine}" alt="${prodotto.nomeModello}" width="60"> <!-- SERVE IL SEPARATORE QUI / tra le 2 EL no? -->
        						</c:if>
        
        						<span class=testo-importante>
                                    <a href="${pageContext.request.contextPath}/Prodotto?id=${prodotto.nomeModello}">
                                        ${prodotto.nomeModello}
                                    </a>
                                </span>
        						<span>Produttore: ${prodotto.nCAutore}</span>
        						<span>Prezzo unitario: ${prodotto.prezzoScontato} €</span>
        
        						<span>
            						Quantità: 
            						<button type="button" onclick="modificaCarrello('${prodotto.nomeModello}', 'sottrai', '${pageContext.request.contextPath}')">-</button>
            						<span id="quantita-${prodotto.nomeModello}">${prodotto.quantità}</span>
            						<button type="button" onclick="modificaCarrello('${prodotto.nomeModello}', 'aggiungi', '${pageContext.request.contextPath}')">+</button>
        						</span>
        
        						<span id="subtotale-${prodotto.nomeModello}">Subtotale: ${prodotto.subtotale} €</span>
        
       							 <button type="button" onclick="modificaCarrello('${prodotto.nomeModello}', 'rimuovi', '${pageContext.request.contextPath}')">Rimuovi</button>
        
    						</li>
                        </c:forEach>
                    </ul>

                    <div class="scheda-elemento separatore-alto">
                        <span class="testo-importante">Totale Complessivo:</span>
                        <span id="totale-carrello-dinamico" class="testo-importante">${totaleCarrello} €</span>
                    </div>
                    
                    <div class="zona-navigazione margine-alto">
                        <a href="${pageContext.request.contextPath}/Catalogo" class="link-navigazione-indietro">
                            Continua lo Shopping
                        </a>
                        <a href="${pageContext.request.contextPath}/common/Pagamento" class="link-navigazione-indietro">
                            Procedi all'Ordine
                        </a>
                        <button type="button" class="link-navigazione-indietro" onclick="modificaCarrello('', 'svuota')"> <!-- lo stile funziona con un button? -->
                            Svuota il Carrello
                        </button>
                    </div>
                </c:otherwise>
            </c:choose>
            
        </section>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="${pageContext.request.contextPath}/scripts/gestioneCarrello.js"></script>
</body>
</html>