package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import negocio.Autor;
import negocio.AutorDAO;
import negocio.AutorDAOException;

public class AutoresDAOderby implements AutorDAO{

	@Override
	public List<Autor> buscarTodos() throws AutorDAOException {
		List<Autor> autores = new ArrayList<>();
        try {
            Connection conexao = DBStarter.conectarBd();
            Statement comando = conexao.createStatement();
            ResultSet resultado = comando.executeQuery("select * from autores");
            while (resultado.next()) {
                Autor autor = new Autor(
                        resultado.getInt("codigo"),
                        resultado.getString("primeironome"),
                        resultado.getString("ultimonome"));
                autores.add(autor);
            }
            comando.close();
            conexao.close();
            return autores;
        } catch (Exception e) {
            throw new AutorDAOException("Falha na busca", e);
        }
	}

	@Override
	public Autor buscarPorCodigo(int codigo) throws AutorDAOException {
		Autor autor = null;
        try {
            Connection conexao = DBStarter.conectarBd();
            PreparedStatement comando = conexao.prepareStatement("select * from autores where codigo = ?");
            comando.setInt(1,codigo);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                autor = new Autor(
                        resultado.getInt("codigo"),
                        resultado.getString("primeironome"),
                        resultado.getString("ultimonome"));
            }
            comando.close();
            conexao.close();
            return autor;
        } catch (Exception e) {
            throw new AutorDAOException("Falha na busca", e);
        }
	}

	@Override
	public List<Autor> buscarPorUltimoNome(String nome) throws AutorDAOException {
		List<Autor> autores = new ArrayList<Autor>();
        try {
            Connection conexao = DBStarter.conectarBd();
            PreparedStatement comando = conexao.prepareStatement("select * from autores where ultimonome = ?");
            comando.setString(2,nome);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Autor autor = new Autor(
                        resultado.getInt("codigo"),
                        resultado.getString("primeironome"),
                        resultado.getString("ultimonome"));
                autores.add(autor);
            }
            comando.close();
            conexao.close();
            return autores;
        } catch (Exception e) {
            throw new AutorDAOException("Falha na busca", e);
        }
	}

	@Override
	public void inserir(Autor autor) throws AutorDAOException {
        Connection conexao = null;
        PreparedStatement comando = null;
        String sql = "insert into autores(codigo,primeironome,ultimonome) values(?,?,?)";
        int resultado = 0;
        try {
            conexao = DBStarter.conectarBd();
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, autor.getCodigo());
            comando.setString(2, autor.getPrimeiroNome());
            comando.setString(3, autor.getUltimoNome());
            resultado = comando.executeUpdate();
            comando.close();
            conexao.close();
        } catch (Exception e) {
            throw new AutorDAOException("Falha na inserção", e);
        }
        if(resultado == 0) {
            throw new AutorDAOException("Falha na inserção");
        }
    }

    @Override
    public void alterar(Autor autor) throws AutorDAOException {
        Connection conexao = null;
        PreparedStatement comando = null;
        String sql = "update autores set primeironome=?, ultimonome=? where codigo = ?";
        int resultado = 0;
        try {
            conexao = DBStarter.conectarBd();
            comando = conexao.prepareStatement(sql);
            comando.setString(1, autor.getPrimeiroNome());
            comando.setString(2, autor.getUltimoNome());
            comando.setInt(3, autor.getCodigo());
            resultado = comando.executeUpdate();
            comando.close();
            conexao.close();
        } catch (Exception e) {
            throw new AutorDAOException("Falha na alteração", e);
        }
        if(resultado == 0) {
            throw new AutorDAOException("Falha na alteração");
        }
    }

}
