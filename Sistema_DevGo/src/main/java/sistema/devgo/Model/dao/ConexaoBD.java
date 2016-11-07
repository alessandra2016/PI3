package sistema.devgo.Model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Erik
 *
 * A classe teve como base o site http://www.mballem.com/
 *
 */
public class ConexaoBD {

    public static Connection getConnection(){
        Connection con = null;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_curso","root","");
        System.out.println(" Conectado com sucesso");
        }
        catch(SQLException e){
            System.out.println("Erro na conexão" + e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println(" Erro no Driver" +  e.getMessage());
        }
    return con;
    }

}
