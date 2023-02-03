/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrica;

/**
 *
 * @author osmar
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection createConnection() throws SQLException{
		String url = "jdbc:postgresql://localhost:5432/fb_comercio_gelo2";
		String user = "postgres"; //nome do usuário do MySQL
		String password = "Ifsp2133"; //senha do MySQL

		Connection conexao = null;
		conexao = DriverManager.getConnection(url, user, password);

		return conexao;
	}
}
