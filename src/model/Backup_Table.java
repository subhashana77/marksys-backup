package model;

/**
 * @author dilshan.r
 * @created 6/20/2022 - 1:32 PM
 * @project Marksys Database Backup
 * @ide IntelliJ IDEA
 */
public class Backup_Table {
    private int table_id;
    private String table_name;
    private String backuped_file_path;
    private String first_col_name;
    private String second_col_name;

    public Backup_Table() {
    }

    public Backup_Table(String table_name) {
        this.table_name = table_name;
    }

    public Backup_Table(int table_id, String table_name, String backuped_file_path, String first_col_name, String second_col_name) {
        this.table_id = table_id;
        this.table_name = table_name;
        this.backuped_file_path = backuped_file_path;
        this.first_col_name = first_col_name;
        this.second_col_name = second_col_name;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getBackuped_file_path() {
        return backuped_file_path;
    }

    public void setBackuped_file_path(String backuped_file_path) {
        this.backuped_file_path = backuped_file_path;
    }

    public String getFirst_col_name() {
        return first_col_name;
    }

    public void setFirst_col_name(String first_col_name) {
        this.first_col_name = first_col_name;
    }

    public String getSecond_col_name() {
        return second_col_name;
    }

    public void setSecond_col_name(String second_col_name) {
        this.second_col_name = second_col_name;
    }

    @Override
    public String toString() {
        return "Backup_Table{" +
                "table_id=" + table_id +
                ", table_name='" + table_name + '\'' +
                ", backuped_file_path='" + backuped_file_path + '\'' +
                ", first_col_name='" + first_col_name + '\'' +
                ", second_col_name='" + second_col_name + '\'' +
                '}';
    }
}
