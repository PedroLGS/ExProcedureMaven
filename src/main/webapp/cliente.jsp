<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clientes</title>
</head>
<body>
	<div align="center">
		<jsp:include page="menu.jsp"></jsp:include>
	</div>
	<br />
	<br />
	<div align="center">
		<form action="cliente" method="post">
			<table border="1">
				<tr>
					<td colspan="3">
						<input type="number" min="0" step="1" id="cpfCliente" name="cpfCliente" placeholder="#CPF"
						value='<c:out value="${cliente.cpf }"></c:out>'>
					</td>
					<td><input type="submit" id="botao" name="botao" value="CONSULTAR"></td>
				<tr>
					<td colspan="4">
						<input type="text" id="nomeCliente" name="nomeCliente" placeholder="Nome"
						value='<c:out value="${cliente.nome }"></c:out>'>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" id="emailCliente" name="emailCliente" placeholder="Email"
						value='<c:out value="${cliente.email }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" id="limitecredCliente" name="limitecredCliente" placeholder="Limite_de_Credito"
						value='<c:out value="${cliente.limite_de_credito }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" id="dtnascimentoCliente" name="dtnascimentoCliente" placeholder="Data_Nascimento"
						value='<c:out value="${cliente.dt_nascimento }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td><input type="submit" id="botao" name="botao" value="INSERIR"></td>
					<td><input type="submit" id="botao" name="botao" value="ATUALIZAR"></td>
					<td><input type="submit" id="botao" name="botao" value="EXCLUIR"></td>
					<td><input type="submit" id="botao" name="botao" value="LISTAR"></td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2><c:out value="${erro }" /></H2>
		</c:if>
		<c:if test="${not empty saida }">
			<H2><c:out value="${saida }" /></H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty clientes }">
			<table border="1">
				<thead>
					<tr>
						<th>#CPF Cliente</th>
						<th>Nome Cliente</th>
						<th>Email Cliente</th>
						<th>Limite_de_Credito Cliente</th>
						<th>Data_Nascimento Cliente</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${clientes }" var="c">
						<tr>
							<td><c:out value="${c.cpf }"></c:out></td>
							<td><c:out value="${c.nome }"></c:out></td>
							<td><c:out value="${c.email }"></c:out></td>
							<td><c:out value="${c.limite_de_credito }"></c:out></td>
							<td><c:out value="${c.dt_nascimento }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	</div>
</body>
</html>