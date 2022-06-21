package impl;

import model.Backup_Table;
import repo.BackupRepo;
import util.DB_Utilities;

import java.net.InetAddress;
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
    InetAddress localHost = null;

    @Override
    public ArrayList<String> checkTableExistingEmptyCol() {
        ArrayList<Backup_Table> backupTables = utilities.checkBackupTable();
        ArrayList<String> tableNames = new ArrayList<>();

        if (backupTables.isEmpty()) {
            tableNames.add(null);
            for (Backup_Table backupTable : backupTables) {
                utilities.logReportFunction(
                        "backup table",
                        "cannot find any date filter to drop data",
                        localHost.getHostAddress(),
                        backupTable.getTable_name() + ", "
                );
            }
        } else {
            for (Backup_Table backupTable : backupTables) {
                tableNames.add(backupTable.getTable_name());
                utilities.logReportFunction(
                        "backup table",
                        backupTable.getFirst_col_name() + " " + backupTable.getSecond_col_name() + " found as the filter date",
                        localHost.getHostAddress(),
                        backupTable.getTable_name() + ", "
                );
            }
        }
        return tableNames;
    }

    @Override
    public HashMap<String, LocalDate> createDateFilter() {

        HashMap<String, LocalDate> dateParameters = new HashMap<>();

        LocalDate today = LocalDate.now(); // 2022-06-21

        HashMap<String, String> parameters = utilities.getParameters();
        int backupYearsCount = Integer.parseInt(parameters.get("_backup_years"));

        LocalDate pastDate = today.minusDays(365 * backupYearsCount);
        LocalDate firstDayOfYear = pastDate.with(firstDayOfYear()); // 2019-01-01

        dateParameters.put("_today", today);
        dateParameters.put("_firstDayOfYear", firstDayOfYear);

        utilities.logReportFunction(
                "date filter",
                "between " + today + " and " + firstDayOfYear + " have to backup the data",
                localHost.getHostAddress(),
                null
        );

        return dateParameters;
    }

    @Override
    public void backupDatabase() {
        
    }
}
