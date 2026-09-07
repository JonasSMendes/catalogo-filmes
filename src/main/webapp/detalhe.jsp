<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="cabecalho.jsp" />

<h2>${filme.titulo} (${filme.ano})</h2>

<p><strong>Diretor:</strong> ${filme.diretor}</p>
<p><strong>Gênero:</strong> ${filme.genero}</p>
<p><strong>Sinopse:</strong></p>
<p>${filme.sinopse}</p>

<br>
<a href="${pageContext.request.contextPath}/editar?id=${filme.id}" class="btn">Editar</a>
<a href="${pageContext.request.contextPath}/filmes">Voltar à listagem</a>

<jsp:include page="rodape.jsp" />