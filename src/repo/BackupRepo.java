package repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author dilshan.r
 * @created 6/20/2022 - 2:51 PM
 * @project Marksys Database Backup
 * @ide IntelliJ IDEA
 */
public interface BackupRepo {
    public ArrayList<String> checkBothEmptyTable();
    public ArrayList<String> checkBothNotEmptyTable();
    public ArrayList<String> checkTDEmptyTable();
    public ArrayList<String> checkCDEmptyTable();
    public HashMap<String, String> createDateFilter();
    public boolean backupDatabase();
    public void dropTheTableData();
}
