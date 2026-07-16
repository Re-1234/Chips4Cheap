<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<aside>
    <h2>Annunci e Novità</h2>
    
    <c:choose>
        <c:when test="${not empty applicationScope.tuttiAnnunci}">
            <c:forEach var="annuncio" items="${applicationScope.tuttiAnnunci}">
                <article>
                    <h3>${annuncio.titolo}</h3>
                    <p>${annuncio.dataPublicazione}</p>
                    <p>${annuncio.text}</p>
                </article>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>Nessun annuncio presente al momento.</p>
        </c:otherwise>
    </c:choose>
    
</aside>