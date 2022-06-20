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
    private String backuped_date_time;
    private String from_date;
    private String to_date;

    public Backup_Table() {
    }

    public Backup_Table(int table_id, String table_name, String backuped_file_path, String backuped_date_time, String from_date, String to_date) {
        this.table_id = table_id;
        this.table_name = table_name;
        this.backuped_file_path = backuped_file_path;
        this.backuped_date_time = backuped_date_time;
        this.from_date = from_date;
        this.to_date = to_date;
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

    public String getBackuped_date_time() {
        return backuped_date_time;
    }

    public void setBackuped_date_time(String backuped_date_time) {
        this.backuped_date_time = backuped_date_time;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    @Override
    public String toString() {
        return "Backup_Table{" +
                "table_id=" + table_id +
                ", table_name='" + table_name + '\'' +
                ", backuped_file_path='" + backuped_file_path + '\'' +
                ", backuped_date_time='" + backuped_date_time + '\'' +
                ", from_date='" + from_date + '\'' +
                ", to_date='" + to_date + '\'' +
                '}';
    }
}
