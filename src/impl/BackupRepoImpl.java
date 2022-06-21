package impl;

import model.Backup_Table;
import repo.BackupRepo;
import util.DB_Utilities;

import javax.swing.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * @author dilshan.r
 * @created 6/21/2022 - 11:38 AM
 * @project Marksys Database Backup
 * @ide IntelliJ IDEA
 */
public class BackupRepoImpl implements BackupRepo {

    DB_Utilities utilities = new DB_Utilities();
    InetAddress localHost = null;
    private JButton button1;

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

        System.out.println("between " + today + " and " + firstDayOfYear + " have to backup the database");
        utilities.logReportFunction(
                "date filter",
                "between " + today + " and " + firstDayOfYear + " have to backup the database",
                null,
                null
        );
        return dateParameters;
    }

    @Override
    public boolean backupDatabase() {
        DB_Utilities utilities = new DB_Utilities();

        HashMap<String, String> databaseDetails = utilities.getDatabaseDetails();
        HashMap<String, String> backupFileDetails = utilities.getBackupFileDetails();

        String path = System.getProperty("user.dir") + "\\src\\" + backupFileDetails.get("_file_path");
        System.out.println("SS : " + path);

        String databaseName = databaseDetails.get("_database_name");
        String databaseUsername = databaseDetails.get("_database_username");
        String databasePassword = databaseDetails.get("_database_password");

        Process process = null;

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        path = path.replace('\\', '/');
        path = path + "//" + backupFileDetails.get("backup_file_name") + "_" + date + ".sql";

        try {
            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec( "C:/Program Files/MySQL/MySQL Server 5.5/bin/mysqldump.exe -u"
                            + databaseUsername + " -p" + databasePassword + " --add-drop-database -B " + databaseName + " -r" + path);
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                System.out.println("Database backup successful!");
                utilities.logReportFunction(
                        "backup",
                        "Database backup successful!",
                        null,
                        null
                );
                JOptionPane.showMessageDialog(null, "Database backup successful!");
                return true;
            } else {
                System.out.println("Database backup fail!");
                utilities.logReportFunction(
                        "backup",
                        "ERROR - Database backup fail!",
                        null,
                        null
                );
                JOptionPane.showMessageDialog(null, "Database backup fail!");
                return false;
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong. Database backup fail! \n" + exception);
            utilities.logReportFunction(
                    "backup",
                    "ERROR - Something went wrong. Database backup fail!",
                    null,
                    null
            );
            JOptionPane.showMessageDialog(null, "Something went wrong. Database backup fail!");
            return false;
        }
    }
}
