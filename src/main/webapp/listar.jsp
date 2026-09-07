<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="cabecalho.jsp" />

<div class="busca">
    <form action="${pageContext.request.contextPath}/buscar" method="get">
        <input type="text" name="termo" placeholder="Buscar por título ou diretor..."
               value="${termoBuscado}" style="width: 300px; padding: 6px;">
        <button type="submit" class="btn">Buscar</button>
        <c:if test="${not empty termoBuscado}">
            <a href="${pageContext.request.contextPath}/filmes">Limpar busca</a>
        </c:if>
    </form>
</div>

<c:choose>
    <c:when test="${empty filmes}">
        <p>Nenhum filme encontrado.</p>
    </c:when>
    <c:otherwise>
        <table>
            <thead>
            <tr>
                <th>Título</th>
                <th>Diretor</th>
                <th>Ano</th>
                <th>Gênero</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="filme" items="${filmes}">
                <tr>
                    <td><c:out value="${filme.titulo}" /></td>
                    <td><c:out value="${filme.diretor}" /></td>
                    <td>${filme.ano}</td>
                    <td>${filme.genero}</td>
                    <td class="acoes">
                        <a href="${pageContext.request.contextPath}/detalhe?id=${filme.id}">Ver</a>
                        <a href="${pageContext.request.contextPath}/editar?id=${filme.id}">Editar</a>
                        <a href="${pageContext.request.contextPath}/excluir?id=${filme.id}"
                           class="btn-excluir"
                           onclick="return confirm('Tem certeza que deseja excluir este filme?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

<jsp:include page="rodape.jsp" />