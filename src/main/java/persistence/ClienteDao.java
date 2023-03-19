package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDao implements IClienteDao {
	
	private GenericDao gDao;

	public ClienteDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	public String callProcedureCliente(String opcao, Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection C = gDao.getConnection();
		
		String sql = "{CALL sp_pessoa(?,?,?,?,?,?,?)}";
		CallableStatement cs = C.prepareCall(sql);
		cs.setString(1, opcao);
		cs.setString(2, cliente.getCpf());
		cs.setString(3, cliente.getNome());
		cs.setString(4, cliente.getEmail());
		cs.setFloat(5, cliente.getLimite_de_credito());
		cs.setString(6, cliente.getDt_nascimento());
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(7);
		
		cs.close();
		C.close();
		
		return saida;
	}
	
	@Override
	public String insereCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
		String saida = callProcedureCliente("I", cliente);
		return saida;
	}

	@Override
	public String atualizaCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
		String saida = callProcedureCliente("A", cliente);
		return saida;
	}

	@Override
	public String excluiCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection C = gDao.getConnection();
		String sql = "{CALL sp_pessoa(?,?,?,?,?,?,?)}";
		CallableStatement cs = C.prepareCall(sql);
		cs.setString(1, "D");
		cs.setString(2, cliente.getCpf());
		cs.setNull(3, Types.VARCHAR);
		cs.setNull(4, Types.VARCHAR);
		cs.setNull(5, Types.DECIMAL);
		cs.setNull(6, Types.DATE);
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(7);
		
		cs.close();
		C.close();
		
		return saida;
	}

	@Override
	public Cliente consultaCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection C = gDao.getConnection();
		String sql = "SELECT cpf, nome, email, limite_de_credito, dt_nascimento FROM cliente WHERE cpf = ?";
		PreparedStatement ps = C.prepareStatement(sql);
		ps.setString(1, cliente.getCpf());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			cliente.setNome(rs.getString("nome"));
			cliente.setEmail(rs.getString("email"));
			cliente.setLimite_de_credito(rs.getFloat("limite_de_credito"));
			cliente.setDt_nascimento(rs.getString("dt_nascimento"));
		}
		rs.close();
		ps.close();
		C.close();
		return cliente;
	}

	@Override
	public List<Cliente> consultaClientes() throws SQLException, ClassNotFoundException {
		List<Cliente> clientes = new ArrayList<>();
		
		Connection C = gDao.getConnection();
		String sql = "SELECT cpf, nome, email, limite_de_credito, dt_nascimento FROM cliente";
		PreparedStatement ps = C.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setCpf(rs.getString("cpf"));
			cliente.setNome(rs.getString("nome"));
			cliente.setEmail(rs.getString("email"));
			cliente.setLimite_de_credito(rs.getFloat("limite_de_credito"));
			cliente.setDt_nascimento(rs.getString("dt_nascimento"));
			clientes.add(cliente);
		}
		rs.close();
		ps.close();
		C.close();
		return clientes;
	}
}
