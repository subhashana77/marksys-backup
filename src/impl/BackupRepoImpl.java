package impl;

import model.Backup_Table;
import repo.BackupRepo;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    public ArrayList<String> checkFirstColEmptyTable() {
        ArrayList<Backup_Table> backupTables = utilities.checkFirstColEmptyTable();
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
    public ArrayList<String> checkSecondColEmptyTable() {
        ArrayList<Backup_Table> backupTables = utilities.checkSecondColEmptyTable();
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

        LocalDateTime todayWithTime = LocalDateTime.now();
        String formattedTodayWithTime = todayWithTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 2022-06-21 12:11:58

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

        LocalDateTime pastDateAndTime = todayWithTime.minusDays(365 * backupYearsCount);
        LocalDateTime firstDayOfYearAndTime = pastDateAndTime.with(firstDayOfYear()); // 2019-01-01

        LocalDateTime dateTime = firstDayOfYearAndTime.truncatedTo(ChronoUnit.DAYS);
        String formattedFirstTimeOfYear = dateTime.format(DateTimeFormatter.ISO_TIME);

        dateParameters.put("_today", formattedTodayWithTime);

        dateParameters.put("_first_day_of_year", formattedFirstDayOfYear + " " + formattedFirstTimeOfYear);
        dateParameters.put("_backup_time_count", String.valueOf(backupYearsCount));

        System.out.println("between " + formattedToday + " and " + formattedFirstDayOfYear + " have to backup the database");
        utilities.logReportFunction(
                "date filter",
                "between " + formattedToday + " and " + formattedFirstDayOfYear + " have to backup the database"
        );
        return dateParameters;
    }

    @Override
    public void dropTheTableData() {
        ArrayList<String> bothEmptyTable = checkBothEmptyTable();
        ArrayList<String> bothNotEmptyTable = checkBothNotEmptyTable();
        ArrayList<String> secondColEmptyTable = checkSecondColEmptyTable();
        ArrayList<String> firstColEmptyTable = checkFirstColEmptyTable();
        HashMap<String, String> dateFilter = createDateFilter();

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

                int getDeletedTableRowCountFromFCNSC = utilities.getDeletedTableRowCountFromFCNSC(bothNotEmptyTables, dateFilter.get("_today"), dateFilter.get("_first_day_of_year"), columnName.get("_first_col_name"), columnName.get("_second_col_name"));

                boolean isDeleted = utilities.deleteDataFromTableByFCNSC(bothNotEmptyTables, dateFilter.get("_today"), dateFilter.get("_first_day_of_year"), columnName.get("_first_col_name"), columnName.get("_second_col_name"));
                tableNameList.append(bothNotEmptyTables).append(", ");
                tableNameListBuffer = tableNameList.substring(0, tableNameList.length() - 2);

                if (isDeleted) {
                    System.out.println("before " + dateFilter.get("_backup_time_count") + " years data has been deleted " + getDeletedTableRowCountFromFCNSC + " rows from "  + bothNotEmptyTables  + " tables!");
                    utilities.logReportFunction(
                            "delete",
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was deleted " + getDeletedTableRowCountFromFCNSC + " rows from "  + bothNotEmptyTables  + " tables!"
                    );
                    JOptionPane.showMessageDialog(
                            null,
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was deleted " + getDeletedTableRowCountFromFCNSC + " rows from "  + bothNotEmptyTables  + " tables!"
                    );
                } else {
                    System.out.println("before " + dateFilter.get("_backup_time_count") + " years data not found in "  + bothNotEmptyTables  + " tables");
                    utilities.logReportFunction(
                            "delete",
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was not found in "  + bothNotEmptyTables  + " tables"
                    );
                    JOptionPane.showMessageDialog(
                            null,
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was not found in "  + bothNotEmptyTables  + " tables"
                    );
                }
            }
        }

        // is there create date in the table?
        if (!secondColEmptyTable.isEmpty()) {
            int alertVal = 0;
            StringBuilder tableNameList = new StringBuilder();
            String tableNameListBuffer = "";

            for (String secondColEmptyTables: secondColEmptyTable) {

                HashMap<String, String> columnName = utilities.getColumnName(secondColEmptyTables);

                int getDeletedTableRowCountFromSC = utilities.getDeletedTableRowCountFromSC(secondColEmptyTables, dateFilter.get("_first_day_of_year"), columnName.get("_second_col_name"));

                boolean isDeleted = utilities.deleteDataFromTableBySC(secondColEmptyTables, dateFilter.get("_today"), dateFilter.get("_first_day_of_year"), columnName.get("_second_col_name"));
                tableNameList.append(secondColEmptyTables).append(", ");
                tableNameListBuffer = tableNameList.substring(0, tableNameList.length() - 2);

                if (isDeleted) {
                    System.out.println("before " + dateFilter.get("_backup_time_count") + " years data has been deleted " + getDeletedTableRowCountFromSC + " rows from "  + secondColEmptyTables  + " tables!");
                    utilities.logReportFunction(
                            "delete",
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was deleted " + getDeletedTableRowCountFromSC + " rows from "  + secondColEmptyTables  + " tables!"
                    );
                    JOptionPane.showMessageDialog(
                            null,
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was deleted "  + getDeletedTableRowCountFromSC +" rows from "  + secondColEmptyTables  + " tables!"
                    );
                } else {
                    System.out.println("before " + dateFilter.get("_backup_time_count") + " years data not found in "  + secondColEmptyTables  + " tables");
                    utilities.logReportFunction(
                            "delete",
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was not found in "  + secondColEmptyTables  + " tables"
                    );
                    JOptionPane.showMessageDialog(
                            null,
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was deleted from "  + secondColEmptyTables  + " tables!"
                    );
                }
            }
        }

        // is there transfer date in the table?
        if (!firstColEmptyTable.isEmpty()) {
            int alertVal = 0;
            StringBuilder tableNameList = new StringBuilder();
            String tableNameListBuffer = "";

            for (String firstColEmptyTables: firstColEmptyTable) {

                HashMap<String, String> parameters = utilities.getParameters();
                String backupYearCount = parameters.get("_backup_years");

                HashMap<String, String> columnName = utilities.getColumnName(firstColEmptyTables);
                String firstColName = columnName.get("_first_col_name");

                int getDeletedTableRowCountFromFC = utilities.getDeletedTableRowCountFromFC(firstColEmptyTables, dateFilter.get("_first_day_of_year"), firstColName);

                boolean isDeleted = utilities.deleteDataFromTableByFC(firstColEmptyTables, dateFilter.get("_today"), dateFilter.get("_first_day_of_year"), firstColName);
                tableNameList.append(firstColEmptyTables).append(", ");
                tableNameListBuffer = tableNameList.substring(0, tableNameList.length() - 2);

                if (isDeleted) {
                    System.out.println("before " + dateFilter.get("_backup_time_count") + " years data has been deleted " + getDeletedTableRowCountFromFC + " rows from "  + firstColEmptyTables  + " tables!");
                    utilities.logReportFunction(
                            "delete",
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was deleted " + getDeletedTableRowCountFromFC + " rows from "  + firstColEmptyTables  + " tables!"
                    );
                    JOptionPane.showMessageDialog(
                            null,
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was deleted " + getDeletedTableRowCountFromFC + " rows from "  + firstColEmptyTables  + " tables!"
                    );
                } else {
                    System.out.println("before " + dateFilter.get("_backup_time_count") + " years data not found in "  + firstColEmptyTables  + " tables");
                    utilities.logReportFunction(
                            "delete",
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was not found in "  + firstColEmptyTables  + " tables"
                    );
                    JOptionPane.showMessageDialog(
                            null,
                            "all data prior to  " + dateFilter.get("_first_day_of_year") + " was not found in "  + firstColEmptyTables  + " tables"
                    );
                }
            }
        }
    }
}
