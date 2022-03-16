<%@ page import="model.JavaBeans"%>



<%@ page import="java.util.ArrayList"%>



<%
@ SuppressWarnings ("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("nomes");
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Cadastro de Alunos</h1>
	<a href="novo.html" class="Botao">Adicionar Aluno</a>
	<table id="tabela">
		<!-- cabeçalho da tabela -->
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>
		</thead>
		<!-- corpo da tabela -->
		<tbody>
		<%
for (int i = 0; i < lista.size(); i++) {
%>
<tr>
<td><%=lista.get(i).getId()%></td>
<td><%=lista.get(i).getNome()%></td>
<td><%=lista.get(i).getFone()%></td>
<td><%=lista.get(i).getEmail()%></td>
<td>
<a href="javascript: confirmar(<%=lista.get(i).getId()%>)" 
id="botao2">Excluir</a>
</td>
</tr>
<%
}
%>
			<!-- conteúdo dinâmico -->
		</tbody>
	</table>
	<!-- link com o documento javascript -->
<script src="valida.js"></script>
</body>
</html>