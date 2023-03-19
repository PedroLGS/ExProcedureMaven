package controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import persistence.ClienteDao;
import persistence.GenericDao;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ClienteServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String botao = request.getParameter("botao");
			
			GenericDao gDao = new GenericDao();
			ClienteDao cDao = new ClienteDao(gDao);
			
			Cliente cliente = new Cliente();
			List<Cliente> clientes = new ArrayList<>();
			String erro = "";
			String saida = "";
			
			try {
				if (botao.equalsIgnoreCase("listar")) {
					clientes = cDao.consultaClientes();
				} else {
					cliente.setCpf(request.getParameter("cpfCliente"));
					if (botao.equalsIgnoreCase("consultar") || botao.equalsIgnoreCase("excluir")) {
						if (botao.equalsIgnoreCase("consultar")) {
							cliente = cDao.consultaCliente(cliente);
						} else {
							saida = cDao.excluiCliente(cliente);
							cliente = new Cliente();
						}
					} else {
						cliente.setNome(request.getParameter("nomeCliente"));
						cliente.setEmail(request.getParameter("emailCliente"));
						cliente.setLimite_de_credito(Float.parseFloat(request.getParameter("limitecredCliente")));
						cliente.setDt_nascimento(request.getParameter("dtnascimentoCliente"));
						if (botao.equalsIgnoreCase("inserir")) {
							saida = cDao.insereCliente(cliente);
							cliente = new Cliente();
						} else {
							saida = cDao.atualizaCliente(cliente);
							cliente = new Cliente();
						}
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				erro = e.getMessage();
			} finally {
				request.setAttribute("saida", saida);
				request.setAttribute("erro", erro);
				request.setAttribute("cliente", cliente);
				request.setAttribute("clientes", clientes);

				RequestDispatcher dispatcher = request.getRequestDispatcher("cliente.jsp");
				dispatcher.forward(request, response);
			}
		}

	}