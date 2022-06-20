package impl;

import model.Backup_Table;
import repo.BackupRepo;
import util.DB_Utilities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * @author dilshan.r
 * @created 6/20/2022 - 3:04 PM
 * @project Marksys Database Backup
 * @ide IntelliJ IDEA
 */
public class BackupRepoImpl implements BackupRepo {

    DB_Utilities utilities = new DB_Utilities();

    @Override
    public ArrayList<String> checkTableExistingEmptyCol() {
        ArrayList<Backup_Table> backupTables = utilities.checkBackupTable();
        ArrayList<String> tableNames = new ArrayList<>();

        if (backupTables.isEmpty()) {
            tableNames.add(null);
        } else {
            for (Backup_Table backupTable : backupTables) {
                tableNames.add(backupTable.getTable_name());
            }
        }
        return tableNames;
    }

    @Override
    public void createDateFilter() {
        LocalDate now = LocalDate.now(); // 2015-11-23
        LocalDate firstDay = now.with(firstDayOfYear()); // 2015-01-01

        HashMap<String, String> parameters = utilities.getParameters();
        String backupYears = parameters.get("_backup_years");
    }
}
