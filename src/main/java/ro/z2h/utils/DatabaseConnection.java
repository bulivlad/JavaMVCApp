package ro.z2h.utils;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Buli on 11/7/2014.
 */
public class DatabaseConnection {

    private static volatile DatabaseConnection instance = null;
    private static OracleDataSource ds;

    private DatabaseConnection() {
        try {
            Properties info = new Properties();
            info.put("ORACLE_DB_USERNAME", "ZTH_01");
            info.put("ORACLE_DB_PASSWORD", "passw0rd");
            info.put("ORACLE_DB_URL","jdbc:oracle:thin:@10.6.33.102:1521:orcl");

            ds = new OracleDataSource();
            ds.setURL(info.getProperty("ORACLE_DB_URL"));
            ds.setUser(info.getProperty("ORACLE_DB_USERNAME"));
            ds.setPassword(info.getProperty("ORACLE_DB_PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

}

