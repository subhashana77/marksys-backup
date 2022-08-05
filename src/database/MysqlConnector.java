package database;

import javax.swing.*;
import java.sql.*;

/**
 * @author dilshan.r
 * @created 6/20/2022 - 12:39 PM
 * @project Marksys Database Backup
 * @ide IntelliJ IDEA
 */
public class MysqlConnector {
    private Connection connection;
    private Statement statement;
    private String _dbType;

    public MysqlConnector(String p_ip_address, String p_db_name, String p_db_username, String p_db_password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + p_ip_address + "/" + p_db_name + "?user=" + p_db_username + "&password=" + p_db_password);
//            System.out.println("Connecting to mysql server...");
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            set_dbType("MySql");
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException exception) {
            System.out.println("Could not connect to " + p_ip_address + " " + exception.getMessage());
            JOptionPane.showMessageDialog(
                    null,
                    "Could not connect to " + p_ip_address + " " + exception.getMessage()
            );
            exception.printStackTrace();
        }
    }

    public void executeUpdate(String sqlCommand) throws SQLException {
        statement.executeUpdate(sqlCommand);
    }

    public ResultSet executeQuery(String sqlCommand) throws SQLException {
        return statement.executeQuery(sqlCommand);
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return getConnection().prepareCall(sql);
    }

    public void commit() throws SQLException {
        getConnection().commit();
    }

    public void rollback() throws SQLException {
        getConnection().rollback();
    }

    public void finalize() throws SQLException {
        statement.close();
        getConnection().close();
    }

    public String get_dbType() {
        return _dbType;
    }

    public void set_dbType(String _dbType) {
        this._dbType = _dbType;
    }

    public Connection getConnection() {
        return connection;
    }
}
