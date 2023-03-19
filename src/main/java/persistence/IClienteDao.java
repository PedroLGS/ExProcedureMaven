package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Cliente;

public interface IClienteDao {

	public String insereCliente(Cliente cliente) throws SQLException, ClassNotFoundException;
	public String atualizaCliente(Cliente cliente) throws SQLException, ClassNotFoundException;
	public String excluiCliente(Cliente cliente) throws SQLException, ClassNotFoundException;
	public Cliente consultaCliente(Cliente cliente) throws SQLException, ClassNotFoundException;
	public List<Cliente> consultaClientes() throws SQLException, ClassNotFoundException;
	
}
