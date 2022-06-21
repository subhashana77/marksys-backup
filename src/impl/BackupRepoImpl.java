package impl;

import model.Backup_Table;
import repo.BackupRepo;

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

    UtilityImpl utilities = new UtilityImpl();
    InetAddress localHost = null;

    @Override
    public ArrayList<String> checkBothEmptyTable() {
        ArrayList<Backup_Table> backupTables = utilities.checkBothEmptyTable();
        ArrayList<String> tableNames = new ArrayList<>();

        if (backupTables.isEmpty()) {
            for (Backup_Table backupTable : backupTables) {
                tableNames.add(backupTable.getTable_name());
                utilities.logReportFunction(
                        "backup table",
                        "cannot find any date filter to drop data"
                );
            }
        } else {
            for (Backup_Table backupTable : backupTables) {
                tableNames.add(backupTable.getTable_name());
                utilities.logReportFunction(
                        "backup table",
                        backupTable.getFirst_col_name() + " " + backupTable.getSecond_col_name() + " found as the filter date"
                );
            }
        }
        return tableNames;
    }

    @Override
    public ArrayList<String> checkBothNotEmptyTable() {
        ArrayList<Backup_Table> backupTables = utilities.checkBothNotEmptyTable();
        ArrayList<String> tableNames = new ArrayList<>();

        if (backupTables.isEmpty()) {
            for (Backup_Table backupTable : backupTables) {
                tableNames.add(backupTable.getTable_name());
                utilities.logReportFunction(
                        "backup table",
                        "cannot find any date filter to drop data"
                );
            }
        } else {
            for (Backup_Table backupTable : backupTables) {
                tableNames.add(backupTable.getTable_name());
                utilities.logReportFunction(
                        "backup table",
                        backupTable.getFirst_col_name() + " " + backupTable.getSecond_col_name() + " found as the filter date"
                );
            }
        }
        return tableNames;
    }

    @Override
    public ArrayList<String> checkTDEmptyTable() {
        ArrayList<Backup_Table> backupTables = utilities.checkTDEmptyTable();
        ArrayList<String> tableNames = new ArrayList<>();

        if (backupTables.isEmpty()) {
            for (Backup_Table backupTable : backupTables) {
                tableNames.add(backupTable.getTable_name());
                utilities.logReportFunction(
                        "backup table",
                        "cannot find any date filter to drop data"
                );
            }
        } else {
            for (Backup_Table backupTable : backupTables) {
                tableNames.add(backupTable.getTable_name());
                utilities.logReportFunction(
                        "backup table",
                        backupTable.getFirst_col_name() + " " + backupTable.getSecond_col_name() + " found as the filter date"
                );
            }
        }
        return tableNames;
    }

    @Override
    public ArrayList<String> checkCDEmptyTable() {
        ArrayList<Backup_Table> backupTables = utilities.checkCDEmptyTable();
        ArrayList<String> tableNames = new ArrayList<>();

        if (backupTables.isEmpty()) {
            for (Backup_Table backupTable : backupTables) {
                tableNames.add(backupTable.getTable_name());
                utilities.logReportFunction(
                        "backup table",
                        "cannot find any date filter to drop data"
                );
            }
        } else {
            for (Backup_Table backupTable : backupTables) {
                tableNames.add(backupTable.getTable_name());
                utilities.logReportFunction(
                        "backup table",
                        backupTable.getFirst_col_name() + " " + backupTable.getSecond_col_name() + " found as the filter date"
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

//        LocalDate pastDate = today.minusDays(365 * backupYearsCount);
        LocalDate pastDate = today.minusDays(backupYearsCount);
        LocalDate firstDayOfYear = pastDate.with(firstDayOfYear()); // 2019-01-01

        dateParameters.put("_today", today);
        dateParameters.put("_firstDayOfYear", firstDayOfYear);

        System.out.println("between " + today + " and " + firstDayOfYear + " have to backup the database");
        utilities.logReportFunction(
                "date filter",
                "between " + today + " and " + firstDayOfYear + " have to backup the database"
        );
        return dateParameters;
    }

    @Override
    public boolean backupDatabase() {
        UtilityImpl utilities = new UtilityImpl();

        HashMap<String, String> databaseDetails = utilities.getDatabaseDetails();
        HashMap<String, String> backupFileDetails = utilities.getBackupFileDetails();

        String path = System.getProperty("user.dir") + "\\src\\" + backupFileDetails.get("_file_path");

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
                        "Database backup successful!"
                );
                JOptionPane.showMessageDialog(
                        null,
                        "Database backup successful!"
                );
                return true;
            } else {
                System.out.println("Database backup fail!");
                utilities.logReportFunction(
                        "backup",
                        "ERROR - Database backup fail!"
                );
                JOptionPane.showMessageDialog(
                        null,
                        "Database backup fail!"
                );
                return false;
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong. Database backup fail! \n" + exception);
            utilities.logReportFunction(
                    "backup",
                    "ERROR - Something went wrong. Database backup fail!"
            );
            JOptionPane.showMessageDialog(
                    null,
                    "Something went wrong. Database backup fail!"
            );
            return false;
        }
    }

    @Override
    public void dropTheTables() {
        ArrayList<String> bothEmptyTable = checkBothEmptyTable();
        ArrayList<String> bothNotEmptyTable = checkBothNotEmptyTable();
        ArrayList<String> cdEmptyTable = checkCDEmptyTable();
        ArrayList<String> tdEmptyTable = checkTDEmptyTable();
        HashMap<String, LocalDate> dateFilter = createDateFilter();
        boolean isBackupDatabase = backupDatabase();

        if (isBackupDatabase) {
            // is there both dates empty in the tables?
            if (!bothEmptyTable.isEmpty()) {
                for (String bothEmptyTables: bothEmptyTable) {
                    System.out.println(bothEmptyTables + " have not enough data to filter the date!");
                    utilities.logReportFunction(
                            "delete",
                            bothEmptyTables + " have not enough data to filter the date!"
                    );
                    JOptionPane.showMessageDialog(
                            null,
                            bothEmptyTables + " have not enough data to filter the date!"
                    );
                }
            }

            // is there both dates in the table?
            if (!bothNotEmptyTable.isEmpty()) {
                int alertVal = 0;
                for (String bothNotEmptyTables: bothNotEmptyTable) {
                    boolean isDeleted = utilities.deleteDataFromTableByTD(bothNotEmptyTables, dateFilter.get("_today"), dateFilter.get("_firstDayOfYear"));
                    if (isDeleted) {
                        System.out.println(bothNotEmptyTables + " is deleted!");
                        utilities.logReportFunction(
                                "delete",
                                bothNotEmptyTables + " is deleted!"
                        );
                        alertVal += 1;
                    } else {
                        System.out.println(bothNotEmptyTables + " is delete fail!");
                        utilities.logReportFunction(
                                "delete",
                                bothNotEmptyTables + " is delete fail!"
                        );
                    }
                }
                if (alertVal > 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Data has deleted!"
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Data delete fail!"
                    );
                }
            }

            // is there create date in the table?
            if (!cdEmptyTable.isEmpty()) {
                int alertVal = 0;
                for (String cdEmptyTables: cdEmptyTable) {
                    boolean isDeleted = utilities.deleteDataFromTableByTD(cdEmptyTables, dateFilter.get("_today"), dateFilter.get("_firstDayOfYear"));
                    if (isDeleted) {
                        System.out.println(cdEmptyTables + " is deleted!");
                        utilities.logReportFunction(
                                "delete",
                                cdEmptyTables + " is deleted!"
                        );
                        alertVal += 1;
                    } else {
                        System.out.println(cdEmptyTables + " is delete fail!");
                        utilities.logReportFunction(
                                "delete",
                                cdEmptyTables + " is delete fail!"
                        );
                    }
                }
                if (alertVal > 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Data has deleted!"
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Data delete fail!"
                    );
                }
            }

            if (!tdEmptyTable.isEmpty()) {
                int alertVal = 0;
                for (String tdEmptyTables: cdEmptyTable) {
                    boolean isDeleted = utilities.deleteDataFromTableByTD(tdEmptyTables, dateFilter.get("_today"), dateFilter.get("_firstDayOfYear"));
                    if (isDeleted) {
                        System.out.println(tdEmptyTables + " is deleted!");
                        utilities.logReportFunction(
                                "delete",
                                tdEmptyTables + " is deleted!"
                        );
                        alertVal += 1;
                    } else {
                        System.out.println(tdEmptyTables + " is delete fail!");
                        utilities.logReportFunction(
                                "delete",
                                tdEmptyTables + " is delete fail!"
                        );
                    }
                }
                if (alertVal > 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Data has deleted!"
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Data delete fail!"
                    );
                }
            }
        } else {
            System.out.println("ERROR : Can not delete the data from table \nREASON : Data backup did not word properly");
            utilities.logReportFunction(
                    "delete",
                    "ERROR : Can not delete the data from table \nREASON : Data backup did not word properly"
            );
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR : Can not delete the data from table \nREASON : Data backup did not word properly"
            );
        }
    }
}
