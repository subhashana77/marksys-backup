package model;

/**
 * @author dilshan.r
 * @created 6/20/2022 - 1:26 PM
 * @project Marksys Database Backup
 * @ide IntelliJ IDEA
 */
public class Report_Log {
    private int log_id;
    private String log_date;
    private String timestamp;
    private String category;
    private String description;
    private String server_ip;
    private String location_code;

    public Report_Log() {
    }

    public Report_Log(int log_id, String log_date, String timestamp, String category, String description, String server_ip, String location_code) {
        this.log_id = log_id;
        this.log_date = log_date;
        this.timestamp = timestamp;
        this.category = category;
        this.description = description;
        this.server_ip = server_ip;
        this.location_code = location_code;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public String getLocation_code() {
        return location_code;
    }

    public void setLocation_code(String location_code) {
        this.location_code = location_code;
    }

    @Override
    public String toString() {
        return "Report_Log{" +
                "log_id=" + log_id +
                ", log_date='" + log_date + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", server_ip='" + server_ip + '\'' +
                ", location_code='" + location_code + '\'' +
                '}';
    }
}
