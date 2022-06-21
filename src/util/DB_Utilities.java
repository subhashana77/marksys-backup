package util;

import database.MysqlConnector;
import model.Backup_Table;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author dilshan.r
 * @created 6/20/2022 - 1:52 PM
 * @project Marksys Database Backup
 * @ide IntelliJ IDEA
 */
public class DB_Utilities {
    private final String _db_username = "root";
    private final String _db_password = "root";
    private final String _db_host = "localhost";
    private final String _db_name = "dilshan_marksys_backup";

    Exception _exception = null;

    MysqlConnector mysqlConnector = new MysqlConnector(_db_host, _db_name, _db_username, _db_password);

    public void logReportFunction(String category, String description, String server_ip, String table_name) {
        //        need to insert oracle transaction (start transaction)
        try {
            LocalDate today = LocalDate.now(); // 2022-06-21
            Statement statement = mysqlConnector.getConnection().createStatement();
            String insertQuery = "" +
                    "INSERT INTO report_log (log_date, timestamp, category, description, server_ip, table_name) " +
                    "VALUES ('"+today.toString()+"', CURRENT_TIMESTAMP, '"+category+"', '"+description+"', '"+server_ip+"', '"+table_name+"')";
            if (statement.executeUpdate(insertQuery) <= 0) {
                System.out.println("Something went wrong. Data cannot update to report log!");
                JOptionPane.showMessageDialog(null, "Something went wrong. Data cannot update to report log!");
            } else {
                System.out.println("Report Data Updated!");
            }
            statement.close();
            //        need to insert oracle transaction (end transaction)
        } catch (SQLException exception) {
            System.out.println("SQL Exception : " + exception);
            JOptionPane.showMessageDialog(null,"SQL Exception : " + exception);
        } catch (Exception exception) {
            System.out.println("Exception : " + exception);
            JOptionPane.showMessageDialog(null,"Exception : " + exception);
            //        need to insert oracle transaction (abort transaction)
        }
    }

    public ArrayList<Backup_Table> checkBackupTable() {
        DB_Utilities utilities = new DB_Utilities();
        ArrayList<Backup_Table> backupTables = new ArrayList<>();
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String selectQuery = "SELECT table_name " +
                    "FROM `backup_table` " +
                    "WHERE first_col_name IS NULL AND second_col_name IS NULL";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                Backup_Table backupTable = new Backup_Table(
                        resultSet.getString("table_name")
                );
                backupTables.add(backupTable);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException exception) {
            System.out.println("ERROR - table name fetching fail!");
            utilities.logReportFunction(
                    "mysql",
                    "ERROR - table name fetching fail! \n" + exception,
                    null,
                    null
            );
            JOptionPane.showMessageDialog(null, "ERROR - table name etching fail!");
        }
        return backupTables;
    }

    public HashMap<String, String> getParameters() {
        DB_Utilities utilities = new DB_Utilities();
        HashMap<String, String> parameter = new HashMap<>();
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String selectQuery = "SELECT backup_years FROM parameters";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                parameter.put("_backup_years", resultSet.getString("backup_years"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException exception) {
            utilities.logReportFunction(
                    "mysql",
                    "ERROR - backup year parameter fetching fail! \n" + exception,
                    null,
                    "parameters"
            );
            JOptionPane.showMessageDialog(null,"ERROR - parameter fetching fail!");
        }
        return parameter;
    }

    public HashMap<String, String> getDatabaseDetails() {
        DB_Utilities utilities = new DB_Utilities();
        HashMap<String, String> dbStuff = new HashMap<>();
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String selectQuery = "SELECT db_name, db_username, db_password FROM parameters";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                dbStuff.put("_database_name", resultSet.getString("db_name"));
                dbStuff.put("_database_username", resultSet.getString("db_username"));
                dbStuff.put("_database_password", resultSet.getString("db_password"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException exception) {
            utilities.logReportFunction(
                    "mysql",
                    "ERROR - database stuff parameter fetching fail! \n" + exception,
                    null,
                    "parameters"
            );
            JOptionPane.showMessageDialog(null,"ERROR - parameter fetching fail!");
        }
        return dbStuff;
    }

    public HashMap<String, String> getBackupFileDetails() {
        DB_Utilities utilities = new DB_Utilities();
        HashMap<String, String> fileDetails = new HashMap<>();
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String selectQuery = "SELECT backup_file_path, backup_file_name FROM parameters";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                fileDetails.put("_file_path", resultSet.getString("backup_file_path"));
                fileDetails.put("_file_name", resultSet.getString("backup_file_name"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException exception) {
            utilities.logReportFunction(
                    "mysql",
                    "ERROR - file path parameter fetching fail! \n" + exception,
                    null,
                    "parameters"
            );
            JOptionPane.showMessageDialog(null,"ERROR - parameter fetching fail!");
        }
        return fileDetails;
    }
}
