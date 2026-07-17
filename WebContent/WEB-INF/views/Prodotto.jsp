<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Prodotto del Catalogo</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">

</head>
<body class="product-page ${not empty sessionScope.account and sessionScope.account.amministratore ? 'modulo-admin-bg' : ''}">
	<jsp:include page="header.jsp"/>
	<main class="product-main">
    <h1 class="product-name">
        ${NomeModello}
    </h1>
    <div class="product-layout">
        <!-- COLONNA SINISTRA -->
        <section class="product-left">
            <div class="product-image-container">
                <c:choose>
                    <c:when test="${not empty Image}">
                        <img class="product-image"
                             src="${pageContext.request.contextPath}/images/${Image}"
                             alt="${NomeModello}">
                    </c:when>
                    <c:otherwise>
                        <div class="product-image-placeholder">
                            <span class="placeholder-text">
                                ${NomeModello}
                            </span>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <section class="product-description">
                <h2 class="description-title">
                    Descrizione
                </h2>
                <p class="description-text">
                    ${Descrizione}
                </p>
                <div class="product-category">
                    <span class="category-label">
                        Tipo prodotto:
                    </span>
                    <span class="category-value">
                        ${Tipo}
                    </span>
                </div>
            </section>
        </section>
        <!-- COLONNA DESTRA -->
			  <aside class="product-right">
			    <div class="product-price-box">
			        <c:choose>
			            <c:when test="${Sconto > 0}">
			                    <span class="product-discount-price">
			                        € <fmt:formatNumber value="${PrezzoScontato}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
			                    </span>
			                    <span class="product-old-price">
			                        € <fmt:formatNumber value="${Prezzo}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
			                    </span>
			                    <span class="product-discount-badge">
			                        -${Sconto}%
			                    </span>
			            </c:when>
			            <c:otherwise>
			                    <span class="product-normal-price">
			                        € <fmt:formatNumber value="${Prezzo}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
			                    </span>
			            </c:otherwise>
			        </c:choose>
			    </div>

			    <form action="${pageContext.request.contextPath}/AggiuntaProdottoCarello" method="post">
			        <input type="hidden" name="NomeModello" value="${NomeModello}">
			        <button type="submit" class="add-cart-button">
			            Aggiungi al Carrello
			        </button>
			    </form>

			    <!-- Bottone Rimuovi Prodotto, visibile solo agli amministratori -->
				<c:if test="${not empty sessionScope.account and sessionScope.account.amministratore}">
				
					   <form action="${pageContext.request.contextPath}/admin/ModificaProdotto" method="get">
						    <input type="hidden" name="codice" value="${NomeModello}">
						    <button type="submit" class="azione-admin-color bottone-pericolo">
						        Modifica Prodotto
						    </button>
						</form>
				
				    <form action="${pageContext.request.contextPath}/CancellaProdotto" method="post">
				        <input type="hidden" name="NomeModello" value="${NomeModello}">
				        <button type="submit" class="bottone-pericolo">
				            Rimuovi Prodotto
				        </button>
				    </form>
				
				</c:if>
			</aside>
    	</div>
	</main>


</body>
</html>
