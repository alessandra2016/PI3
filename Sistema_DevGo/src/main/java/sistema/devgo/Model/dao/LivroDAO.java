/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.devgo.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistema.devgo.java.Livro;

/**
 *
 * @author Alessandra
 */
public class LivroDAO extends GenericaDAO {

    public void salvar(Livro livro) throws SQLException {
        String sql = "INSERT INTO LV_IDIOMA(TIPO_IDIOMA,PRECO,QTDE_ATUAL) "
                + "VALUES(?,?,?)";
        insert(sql, livro.getIdioma(), livro.getPreco(), livro.getQuantidade());
    }

    public void editar(Livro livro) throws SQLException {

        String sql = "UPDATE LV_IDIOMA "
                + "SET PRECO = ?, QTDE_ATUAL = ?  WHERE COD_IDIOMA = ?";

        update(sql ,livro.getCod_idioma(),livro.getPreco(), livro.getQuantidade());

    }

    public List<Livro> findLivro() throws SQLException {
        List<Livro> livros = new ArrayList<Livro>();

        String sql = "SELECT * FROM LV_IDIOMA";

        PreparedStatement stmt
                = getConnection().prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Livro livro = new Livro();
            livro.setCod_idioma(rs.getInt("COD_IDIOMA"));
            livro.setIdioma(rs.getString("TIPO_IDIOMA"));
            livro.setPreco(rs.getDouble("PRECO"));
            livro.setQuantidade(rs.getInt("QTDE_ATUAL"));

            livros.add(livro);
        }

        rs.close();
        stmt.close();

        return livros;
    }
//saber o que passa como parametro
    public Livro findByName(String xx) throws SQLException {
        String sql = "SELECT * FROM LV_IDIOMA WHERE TIPO_IDIOMA = ?";
        Livro livro = null;
        PreparedStatement stmt
                = getConnection().prepareStatement(sql);

        stmt.setString(1, xx);// ???
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            livro.setCod_idioma(rs.getInt("COD_IDIOMA"));
            livro.setIdioma(rs.getString("TIPO_IDIOMA"));
            livro.setPreco(rs.getDouble("PRECO"));
            livro.setQuantidade(rs.getInt("QTDE_ATUAL"));

        }

        rs.close();
        stmt.close();
        return livro;
    }

}