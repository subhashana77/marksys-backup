package repo;

import model.Backup_Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author dilshan.r
 * @created 6/21/2022 - 4:08 PM
 * @project Marksys_Database_Backup
 * @ide IntelliJ IDEA
 */
public interface UtilityRepo {
    public void logReportFunction(String category, String description);
    public ArrayList<Backup_Table> checkBothEmptyTable();
    public ArrayList<Backup_Table> checkTDEmptyTable();
    public ArrayList<Backup_Table> checkCDEmptyTable();
    public ArrayList<Backup_Table> checkBothNotEmptyTable();
    public HashMap<String, String> getParameters();
    public HashMap<String, String> getDatabaseDetails();
    public HashMap<String, String> getBackupFileDetails();
    public HashMap<String, String> getColumnName(String tableName);
    public boolean deleteDataFromTableByTD(String tableName, String today, String firstDayOfYear, String columnName);
    public boolean deleteDataFromTableByCD(String tableName, String today, String firstDayOfYear, String columnName);
    public boolean deleteDataFromTableByTDNCD(String tableName, String today, String firstDayOfYear, String firstColName, String secondColName);
    public ArrayList<String> getDateList(String tableName, String columnName);
}
