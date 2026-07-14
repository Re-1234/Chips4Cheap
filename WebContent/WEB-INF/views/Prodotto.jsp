<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Prodotto del Catalogo</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">

</head>


<body class="product-page">


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
                            € ${PrezzoScontato}
                        </span>



                        <span class="product-old-price">
                            € ${Prezzo}
                        </span>



                        <span class="product-discount-badge">
                            -${Sconto}%
                        </span>



                    </c:when>



                    <c:otherwise>


                        <span class="product-normal-price">
                            € ${Prezzo}
                        </span>


                    </c:otherwise>


                </c:choose>


            </div>




            <a class="add-cart-link" href="">


                <button class="add-cart-button">

                    Aggiungi al carrello

                </button>


            </a>



        </aside>


    </div>


</main>


</body>

</html>