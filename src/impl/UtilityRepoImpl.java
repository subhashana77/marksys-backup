package impl;

import database.MysqlConnector;
import model.Backup_Table;
import repo.UtilityRepo;

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
public class UtilityRepoImpl implements UtilityRepo {
    private final String _db_username = "root";
    private final String _db_password = "root";
    private final String _db_host = "localhost";
    //    private final String _db_name = "dilshan_marksys_backup";
    private final String _db_name = "marksys_new";

    Exception _exception = null;

    MysqlConnector mysqlConnector = new MysqlConnector(_db_host, _db_name, _db_username, _db_password);

    @Override
    public void logReportFunction(String category, String description) {
        //        need to insert oracle transaction (start transaction)
        try {
            LocalDate today = LocalDate.now(); // 2022-06-21
            Statement statement = mysqlConnector.getConnection().createStatement();
            String insertQuery = "" +
                    "INSERT INTO report_log (log_date, timestamp, category, description) " +
                    "VALUES ('"+today.toString()+"', CURRENT_TIMESTAMP, '"+category+"', '"+description+"')";
            if (statement.executeUpdate(insertQuery) <= 0) {
                System.out.println("Something went wrong. Data cannot update to report log!");
                JOptionPane.showMessageDialog(
                        null,
                        "Something went wrong. Data cannot update to report log!"
                );
            } else {
                System.out.println("Report Data Updated!");
            }
            statement.close();
            //        need to insert oracle transaction (end transaction)
        } catch (SQLException exception) {
            System.out.println("SQL Exception : " + exception);
            JOptionPane.showMessageDialog(
                    null,
                    "SQL Exception : " + exception
            );
        } catch (Exception exception) {
            System.out.println("Exception : " + exception);
            JOptionPane.showMessageDialog(
                    null,
                    "Exception : " + exception
            );
            //        need to insert oracle transaction (abort transaction)
        }
    }

    @Override
    public ArrayList<Backup_Table> checkBothEmptyTable() {
        UtilityRepoImpl utilities = new UtilityRepoImpl();
        ArrayList<Backup_Table> backupTables = new ArrayList<>();
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String selectQuery = "SELECT table_name " +
                    "FROM `backup_table` " +
                    "WHERE (second_col_name IS NULL AND length(trim(first_col_name))=0) " +
                    "OR (first_col_name IS NULL AND length(trim(second_col_name))=0) " +
                    "OR (first_col_name IS NULL AND second_col_name IS NULL) " +
                    "OR ((length(trim(first_col_name))=0) AND (length(trim(second_col_name))=0))";
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
                    "ERROR - table name fetching fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR - table name fetching fail!"
            );
        }
        return backupTables;
    }

    @Override
    public ArrayList<Backup_Table> checkFirstColEmptyTable() {
        UtilityRepoImpl utilities = new UtilityRepoImpl();
        ArrayList<Backup_Table> backupTables = new ArrayList<>();
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String selectQuery = "SELECT table_name " +
                    "FROM `backup_table` " +
                    "WHERE (first_col_name IS NOT NULL AND length(trim(first_col_name))>0) " +
                    "AND (second_col_name IS NULL OR length(trim(second_col_name))=0)";
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
                    "ERROR - table name fetching fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR - table name fetching fail!"
            );
        }
        return backupTables;
    }

    @Override
    public ArrayList<Backup_Table> checkSecondColEmptyTable() {
        UtilityRepoImpl utilities = new UtilityRepoImpl();
        ArrayList<Backup_Table> backupTables = new ArrayList<>();
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String selectQuery = "SELECT table_name " +
                    "FROM `backup_table` " +
                    "WHERE (first_col_name IS NULL OR length(trim(first_col_name))=0) " +
                    "AND (second_col_name IS NOT NULL AND length(trim(second_col_name))>0)";
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
                    "ERROR - table name fetching fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR - table name fetching fail!"
            );
        }
        return backupTables;
    }

    @Override
    public ArrayList<Backup_Table> checkBothNotEmptyTable() {
        UtilityRepoImpl utilities = new UtilityRepoImpl();
        ArrayList<Backup_Table> backupTables = new ArrayList<>();
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String selectQuery = "SELECT table_name " +
                    "FROM `backup_table` " +
                    "WHERE (first_col_name IS NOT NULL AND length(trim(first_col_name))>0) " +
                    "AND (second_col_name IS NOT NULL AND length(trim(second_col_name))>0)";

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
                    "ERROR - table name fetching fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR - table name fetching fail!"
            );
        }
        return backupTables;
    }

    @Override
    public HashMap<String, String> getParameters() {
        UtilityRepoImpl utilities = new UtilityRepoImpl();
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
                    "ERROR - backup year parameter fetching fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR - parameter fetching fail!"
            );
        }
        return parameter;
    }

    @Override
    public HashMap<String, String> getDatabaseDetails() {
        UtilityRepoImpl utilities = new UtilityRepoImpl();
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
                    "ERROR - database stuff parameter fetching fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR - parameter fetching fail!"
            );
        }
        return dbStuff;
    }

    @Override
    public HashMap<String, String> getBackupFileDetails() {
        UtilityRepoImpl utilities = new UtilityRepoImpl();
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
                    "ERROR - file path parameter fetching fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR - parameter fetching fail!"
            );
        }
        return fileDetails;
    }

    @Override
    public HashMap<String, String> getColumnName(String tableName) {
        UtilityRepoImpl utilities = new UtilityRepoImpl();
        HashMap<String, String> colNames = new HashMap<>();

        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String selectQuery = "SELECT first_col_name, second_col_name FROM backup_table WHERE table_name = '"+tableName+"'";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                colNames.put("_first_col_name", resultSet.getString("first_col_name"));
                colNames.put("_second_col_name", resultSet.getString("second_col_name"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException exception) {
            utilities.logReportFunction(
                    "mysql",
                    "ERROR - file path parameter fetching fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR - parameter fetching fail!"
            );
        }
        return colNames;
    }

    @Override
    public boolean deleteDataFromTableByFC(String tableName, String today, String firstDayOfYear, String firstColumnName) {

        UtilityRepoImpl utilities = new UtilityRepoImpl();
        boolean isDeleted = false;
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String deleteQuery = "DELETE FROM "+tableName+" " +
                    "WHERE "+firstColumnName+" " +
                    "< '"+firstDayOfYear+"'";
            int update = statement.executeUpdate(deleteQuery);
            if (update >= 1) {
                isDeleted = true;
            } else {
                isDeleted = false;
            }
            statement.close();
        } catch (SQLException exception) {
            utilities.logReportFunction(
                    "mysql",
                    "ERROR - table data delete fail! \n" + exception
            );
             JOptionPane.showMessageDialog(
                    null,
                    "FC- ERROR - table data delete fail!"
            );
        }
        return isDeleted;
    }

    @Override
    public boolean deleteDataFromTableBySC(String tableName, String today, String firstDayOfYear, String secondColumnName) {
        UtilityRepoImpl utilities = new UtilityRepoImpl();
        boolean isDeleted = false;
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String deleteQuery = "DELETE FROM "+tableName+" " +
                    "WHERE "+secondColumnName+" " +
                    "< '"+firstDayOfYear+"'";
            int update = statement.executeUpdate(deleteQuery);
            if (update >= 1) {
                isDeleted = true;
            } else {
                isDeleted = false;
            }
            statement.close();
        } catch (SQLException exception) {
            utilities.logReportFunction(
                    "mysql",
                    "ERROR - table data delete fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "SC- ERROR - table data delete fail!"
            );
        }
        return isDeleted;
    }

    @Override
    public boolean deleteDataFromTableByFCNSC(String tableName, String today, String firstDayOfYear, String firstColName, String secondColName) {

        UtilityRepoImpl utilities = new UtilityRepoImpl();
        boolean isDeleted = false;
        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String deleteQuery = "DELETE FROM "+tableName+" " +
                    "WHERE "+firstColName+" " +
                    "< '"+firstDayOfYear+"' " +
                    "AND "+secondColName+" " +
                    "< '"+today+"'";
            int update = statement.executeUpdate(deleteQuery);
            if (update >= 1) {
                isDeleted = true;
            } else {
                isDeleted = false;
            }
            statement.close();
        } catch (SQLException exception) {
            utilities.logReportFunction(
                    "mysql",
                    "ERROR - table data delete fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "FCnSC- ERROR - table data delete fail!"
            );
        }
        return isDeleted;
    }

    @Override
    public ArrayList<String> getDateList(String tableName, String columnName) {
        UtilityRepoImpl utilities = new UtilityRepoImpl();
        ArrayList<String> dateList = new ArrayList<>();

        try {
            Statement statement = mysqlConnector.getConnection().createStatement();
            String selectQuery = "SELECT "+columnName+" FROM "+tableName+" ";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                dateList.add(resultSet.getString(columnName));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException exception) {
            utilities.logReportFunction(
                    "mysql",
                    "ERROR - " + columnName + " fetching fail! \n" + exception
            );
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR - " + columnName + " fetching fail!"
            );
        }
        return dateList;
    }
}
