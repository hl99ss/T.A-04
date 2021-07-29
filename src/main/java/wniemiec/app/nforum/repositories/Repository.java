package wniemiec.app.nforum.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import wniemiec.app.nforum.config.DatabaseConfig;

public abstract class Repository {
	
	static {
		loadDatasetDriver();
	}

	private static void loadDatasetDriver() {
		try {
			Class.forName(DatabaseConfig.getDriver());
		}
		catch (ClassNotFoundException e) {
			System.err.println(e.toString());
		}
	}
	
	public Connection buildDatabaseConnection() throws SQLException {
		return DriverManager.getConnection(
			DatabaseConfig.getUri(), 
			DatabaseConfig.getUsername(), 
			DatabaseConfig.getPassword()
		);
	}
	
	public String buildQuery(String... statements) {
		StringBuilder query = new StringBuilder();
		
		for (String statement : statements) {
			query.append(statement);
			query.append(' ');
		}
		
		return query.toString();
	}
}