/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.devgo.Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sistema.devgo.Model.dao.ConexaoBD.getConnection;
import sistema.devgo.java.UsuarioSistema;

/**
 *
 * @author Alessandra
 */
public class UsuarioDAO {
private Connection con = ConexaoBD.getConnection();
    
     public UsuarioSistema autenticacao(UsuarioSistema usuario){
        UsuarioSistema usuRetorno = null;
     String sql = "SELECT * FROM PERMISSAO WHERE USUARIO = ? AND SENHA = ? ";
     
   try{
       PreparedStatement preparador = con.prepareStatement(sql);
       preparador.setString(1, usuario.getNome());
        preparador.setInt(2, usuario.getSenha());
        
        
        
        ResultSet resultado = preparador.executeQuery();
        
        //se possui registro
        if(resultado.next()){
            usuRetorno = new UsuarioSistema();
            
           
            usuRetorno.setNome(resultado.getString("nome"));
            usuRetorno.setSenha(resultado.getInt("senha"));
            
        }
   }    catch (SQLException ex) {  
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    return usuRetorno;
}

}
