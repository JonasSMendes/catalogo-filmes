<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="cabecalho.jsp" />

<h2><c:choose>
    <c:when test="${not empty filme}">Editar Filme</c:when>
    <c:otherwise>Cadastrar Novo Filme</c:otherwise>
</c:choose></h2>

<c:if test="${not empty erro}">
    <div class="erro">${erro}</div>
</c:if>

<form class="formulario" method="post"
      action="${pageContext.request.contextPath}${not empty filme ? '/editar' : '/cadastrar'}">

    <c:if test="${not empty filme}">
        <input type="hidden" name="id" value="${filme.id}">
    </c:if>

    <label for="titulo">Título *</label>
    <input type="text" id="titulo" name="titulo" required value="<c:out value="${filme.titulo}" />">

    <label for="diretor">Diretor *</label>
    <input type="text" id="diretor" name="diretor" required value="<c:out value="${filme.titulo}" />">

    <label for="ano">Ano *</label>
    <input type="number" id="ano" name="ano" required min="1888" max="2100" value="${filme.ano}">

    <label for="genero">Gênero</label>
    <input type="text" id="genero" name="genero" value="${filme.genero}">

    <label for="sinopse">Sinopse</label>
    <textarea id="sinopse" name="sinopse" rows="5">${filme.sinopse}</textarea>

    <br><br>
    <button type="submit" class="btn">Salvar</button>
    <a href="${pageContext.request.contextPath}/filmes">Cancelar</a>
</form>

<jsp:include page="rodape.jsp" />