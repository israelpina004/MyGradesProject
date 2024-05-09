package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs {
	Connection dbConnection;
	
	public Connection getConnection() {
		try {
            dbConnection = DriverManager.getConnection(Configs.dbURL, Configs.dbURL, Configs.dbPass);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return dbConnection;
	}
}
