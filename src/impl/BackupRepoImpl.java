package impl;

import model.Backup_Table;
import repo.BackupRepo;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * @author dilshan.r
 * @created 6/21/2022 - 11:38 AM
 * @project Marksys Database Backup
 * @ide IntelliJ IDEA
 */
public class BackupRepoImpl implements BackupRepo {

    UtilityRepoImpl utilities = new UtilityRepoImpl();

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
    public HashMap<String, String> createDateFilter() {

        HashMap<String, String> dateParameters = new HashMap<>();

        LocalDate today = LocalDate.now(); // 2022-06-21
        String formattedToday = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 2022-06-21

        HashMap<String, String> parameters = utilities.getParameters();
        int backupYearsCount = 0;

        if (Integer.parseInt(parameters.get("_backup_years")) >= 3) {
            backupYearsCount = Integer.parseInt(parameters.get("_backup_years"));
        } else {
            if (JOptionPane.showConfirmDialog(null,"You can not backup and delete data for less than 3 years. So would you like to backup and delete 3 years of data?",
                    "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {

                backupYearsCount = 3;

                System.out.println("Data backup and delete year count was less than 3. There for set it to 3 years when user is approved.");
                utilities.logReportFunction(
                        "date filter",
                        "Data backup and delete year count was less than 3. There for set it to 3 years when user is approved."
                );
            } else {
                System.out.println("Data backup and delete year count was less than 3. User did not agree to set it to 3 years. Then we have to terminate the programme.");
                utilities.logReportFunction(
                        "date filter",
                        "Data backup and delete year count was less than 3. User did not agree to set it to 3 years. Then we have to terminate the programme."
                );
                JOptionPane.showMessageDialog(null, "You have did not agree to backup and delete to 3 years data. Then we have to terminate the programme.");

                System.exit(0);
            }
        }

        LocalDate pastDate = today.minusDays(365 * backupYearsCount);
        LocalDate firstDayOfYear = pastDate.with(firstDayOfYear()); // 2019-01-01
        String formattedFirstDayOfYear = firstDayOfYear.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 2019-01-01

        dateParameters.put("_today", formattedToday);
        dateParameters.put("_first_day_of_year", formattedFirstDayOfYear);
        dateParameters.put("_backup_time_count", String.valueOf(backupYearsCount));

        System.out.println("between " + formattedToday + " and " + formattedFirstDayOfYear + " have to backup the database");
        utilities.logReportFunction(
                "date filter",
                "between " + formattedToday + " and " + formattedFirstDayOfYear + " have to backup the database"
        );
        return dateParameters;
    }

//    @Override
//    public boolean backupDatabase() {
//        UtilityRepoImpl utilities = new UtilityRepoImpl();
//
//        HashMap<String, String> databaseDetails = utilities.getDatabaseDetails();
//        HashMap<String, String> backupFileDetails = utilities.getBackupFileDetails();
//
//        String path = System.getProperty("user.dir") + "\\src\\" + backupFileDetails.get("_file_path");
//
//        String databaseName = databaseDetails.get("_database_name");
//        String databaseUsername = databaseDetails.get("_database_username");
//        String databasePassword = databaseDetails.get("_database_password");
//
//        Process process = null;
//
//        String date = new SimpleDateFormat("yyyy-MMM-dd").format(new Date());
//        path = path.replace('\\', '/');
//        path = path + "//" + backupFileDetails.get("_file_name") + "_" + date + ".sql";
//
//        try {
//            Runtime runtime = Runtime.getRuntime();
//            process = runtime.exec( "C:/Program Files/MySQL/MySQL Server 5.5/bin/mysqldump.exe -u"
//                            + databaseUsername + " -p" + databasePassword + " --add-drop-database -B " + databaseName + " -r" + path);
//            int processComplete = process.waitFor();
//            if (processComplete == 0) {
//                System.out.println("Database backup successful!");
//                utilities.logReportFunction(
//                        "backup",
//                        "Database backup successful!"
//                );
//                JOptionPane.showMessageDialog(
//                        null,
//                        "Database backup successful!"
//                );
//                return true;
//            } else {
//                System.out.println("Database backup fail!");
//                utilities.logReportFunction(
//                        "backup",
//                        "ERROR - Database backup fail!"
//                );
//                JOptionPane.showMessageDialog(
//                        null,
//                        "Database backup fail!"
//                );
//                return false;
//            }
//        } catch (Exception exception) {
//            System.out.println("Something went wrong. Database backup fail! \n" + exception);
//            utilities.logReportFunction(
//                    "backup",
//                    "ERROR - Something went wrong. Database backup fail!"
//            );
//            JOptionPane.showMessageDialog(
//                    null,
//                    "Something went wrong. Database backup fail!"
//            );
//            return false;
//        }
//    }

    @Override
    public void dropTheTableData() {
        ArrayList<String> bothEmptyTable = checkBothEmptyTable();
        ArrayList<String> bothNotEmptyTable = checkBothNotEmptyTable();
        ArrayList<String> cdEmptyTable = checkCDEmptyTable();
        ArrayList<String> tdEmptyTable = checkTDEmptyTable();
        HashMap<String, String> dateFilter = createDateFilter();
//        boolean isBackupDatabase = backupDatabase();

//        if (isBackupDatabase) {
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
                String message = "";
                StringBuilder tableNameList = new StringBuilder();
                String tableNameListBuffer = "";

                for (String bothNotEmptyTables: bothNotEmptyTable) {

                    HashMap<String, String> columnName = utilities.getColumnName(bothNotEmptyTables);

                    boolean isDeleted = utilities.deleteDataFromTableByTDNCD(bothNotEmptyTables, dateFilter.get("_today"), dateFilter.get("_first_day_of_year"), columnName.get("_first_col_name"), columnName.get("_second_col_name"));
                    tableNameList.append(bothNotEmptyTables).append(", ");
                    tableNameListBuffer = tableNameList.substring(0, tableNameList.length() - 2);

                    if (isDeleted) {
                        System.out.println("before " + dateFilter.get("_backup_time_count") + " years data has been deleted from " + "\u201C" + tableNameListBuffer + "\u201C" + " tables!");
                        utilities.logReportFunction(
                                "delete",
                                "before " + dateFilter.get("_backup_time_count") + " years data has been deleted from " + "\u201C" + tableNameListBuffer + "\u201C" + " tables!"
                        );

                        alertVal += 1;

                    } else {
                        System.out.println("before " + dateFilter.get("_backup_time_count") + " years data not found in " + "\u201C" + tableNameListBuffer + "\u201C" + " tables");
                        utilities.logReportFunction(
                                "delete",
                                "before " + dateFilter.get("_backup_time_count") + " years data not found in " + "\u201C" + tableNameListBuffer + "\u201C" + " tables"
                        );
                    }
                }
                if (alertVal > 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "before " + dateFilter.get("_backup_time_count") + " years data has been deleted from " + "\u201C" + tableNameListBuffer + "\u201C" + " tables!"
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "before " + dateFilter.get("_backup_time_count") + " years data not found in " + "\u201C" + tableNameListBuffer + "\u201C" + " tables"
                    );
                }
            }

            // is there create date in the table?
            if (!cdEmptyTable.isEmpty()) {
                int alertVal = 0;
                StringBuilder tableNameList = new StringBuilder();
                String tableNameListBuffer = "";

                for (String cdEmptyTables: cdEmptyTable) {

                    HashMap<String, String> columnName = utilities.getColumnName(cdEmptyTables);

                    boolean isDeleted = utilities.deleteDataFromTableByCD(cdEmptyTables, dateFilter.get("_today"), dateFilter.get("_first_day_of_year"), columnName.get("_second_col_name"));
                    tableNameList.append(cdEmptyTables).append(", ");
                    tableNameListBuffer = tableNameList.substring(0, tableNameList.length() - 2);

                    if (isDeleted) {
                        System.out.println("before " + dateFilter.get("_backup_time_count") + " years data has been deleted from " + "\u201C" + tableNameListBuffer + "\u201C" + " tables!");
                        utilities.logReportFunction(
                                "delete",
                                "before " + dateFilter.get("_backup_time_count") + " years data has been deleted from " + "\u201C" + tableNameListBuffer + "\u201C" + " tables!"
                        );

                        alertVal += 1;

                    } else {
                        System.out.println("before " + dateFilter.get("_backup_time_count") + " years data not found in " + "\u201C" + tableNameListBuffer + "\u201C" + " tables");
                        utilities.logReportFunction(
                                "delete",
                                "before " + dateFilter.get("_backup_time_count") + " years data not found in " + "\u201C" + tableNameListBuffer + "\u201C" + " tables"
                        );
                    }
                }
                if (alertVal > 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "before " + dateFilter.get("_backup_time_count") + " years data has been deleted from " + "\u201C" + tableNameListBuffer + "\u201C" + " tables!"
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "before " + dateFilter.get("_backup_time_count") + " years data not found in " + "\u201C" + tableNameListBuffer + "\u201C" + " tables"
                    );
                }
            }

            // is there transfer date in the table?
            if (!tdEmptyTable.isEmpty()) {
                int alertVal = 0;
                StringBuilder tableNameList = new StringBuilder();
                String tableNameListBuffer = "";

                for (String tdEmptyTables: tdEmptyTable) {

                    HashMap<String, String> parameters = utilities.getParameters();
                    String backupYearCount = parameters.get("_backup_years");

                    HashMap<String, String> columnName = utilities.getColumnName(tdEmptyTables);
                    String firstColName = columnName.get("_first_col_name");

                    ArrayList<String> dateList = utilities.getDateList(tdEmptyTables, firstColName);

                    LocalDate today = LocalDate.now(); // 2022-06-21
                    String formattedToday = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 2022-06-21
                    LocalDate pastDate = today.minusDays(365 * Integer.parseInt(backupYearCount));
                    LocalDate firstDayOfYear = pastDate.with(firstDayOfYear()); // 2019-01-01
                    String formattedFirstDayOfYear = firstDayOfYear.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 2019-01-01


                    for (String dateLists : dateList) {
                        if (Integer.parseInt(backupYearCount) > 20) {
                            System.out.println("20 year more");
                        } else if (Integer.parseInt(backupYearCount) > 10) {
                            System.out.println("10 year more");
                        } else if (Integer.parseInt(backupYearCount) > 5) {
                            try {
                                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateLists);
                                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(firstDayOfYear));
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            System.out.println("5 year less");
                        }
                    }

//                    boolean isDeleted = utilities.deleteDataFromTableByTD(tdEmptyTables, dateFilter.get("_today"), dateFilter.get("_first_day_of_year"), firstColName);
//                    tableNameList.append(tdEmptyTables).append(", ");
//                    tableNameListBuffer = tableNameList.substring(0, tableNameList.length() - 2);
//
//                    if (isDeleted) {
//                        System.out.println("before " + dateFilter.get("_backup_time_count") + " years data has been deleted from " + "\u201C" + tableNameListBuffer + "\u201C" + " tables!");
//                        utilities.logReportFunction(
//                                "delete",
//                                "before " + dateFilter.get("_backup_time_count") + " years data has been deleted from " + "\u201C" + tableNameListBuffer + "\u201C" + " tables!"
//                        );
//
//                        alertVal += 1;
//
//                    } else {
//                        System.out.println("before " + dateFilter.get("_backup_time_count") + " years data not found in " + "\u201C" + tableNameListBuffer + "\u201C" + " tables");
//                        utilities.logReportFunction(
//                                "delete",
//                                "before " + dateFilter.get("_backup_time_count") + " years data not found in " + "\u201C" + tableNameListBuffer + "\u201C" + " tables"
//                        );
//                    }
                }
                if (alertVal > 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "before " + dateFilter.get("_backup_time_count") + " years data has been deleted from " + "\u201C" + tableNameListBuffer + "\u201C" + " tables!"
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "before " + dateFilter.get("_backup_time_count") + " years data not found in " + "\u201C" + tableNameListBuffer + "\u201C" + " tables"
                    );
                }
            }
//        } else {
//            System.out.println("ERROR : Can not delete the data from table \nREASON : Data backup did not work properly");
//            utilities.logReportFunction(
//                    "delete",
//                    "ERROR : Can not delete the data from table \nREASON : Data backup did not work properly"
//            );
//            JOptionPane.showMessageDialog(
//                    null,
//                    "ERROR : Can not delete the data from table \nREASON : Data backup did not work properly"
//            );
//        }
    }
}
