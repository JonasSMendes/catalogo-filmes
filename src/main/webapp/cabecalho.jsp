<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Catálogo de Filmes</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 900px; margin: 30px auto; padding: 0 20px; color: #222; }
        h1 { color: #333; border-bottom: 2px solid #444; padding-bottom: 8px; }
        nav a { margin-right: 15px; text-decoration: none; color: #0056b3; font-weight: bold; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { text-align: left; padding: 10px; border-bottom: 1px solid #ddd; }
        th { background-color: #f4f4f4; }
        tr:hover { background-color: #f9f9f9; }
        .acoes a { margin-right: 10px; font-size: 0.9em; }
        .btn { display: inline-block; padding: 8px 16px; background: #0056b3; color: white; text-decoration: none; border-radius: 4px; }
        .btn-excluir { color: #c0392b; }
        form.formulario label { display: block; margin-top: 12px; font-weight: bold; }
        form.formulario input, form.formulario select, form.formulario textarea {
            width: 100%; padding: 8px; margin-top: 4px; box-sizing: border-box;
        }
        .erro { background: #fdecea; color: #c0392b; padding: 10px; border-radius: 4px; margin-top: 15px; }
        .busca { margin: 15px 0; }
    </style>
</head>
<body>
<h1>🎬 Catálogo de Filmes</h1>
<nav>
    <a href="${pageContext.request.contextPath}/filmes">Listar Todos</a>
    <a href="${pageContext.request.contextPath}/cadastrar">Cadastrar Novo Filme</a>
</nav>
<hr>