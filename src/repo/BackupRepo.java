package repo;

import model.Backup_Table;

import java.util.ArrayList;

/**
 * @author dilshan.r
 * @created 6/20/2022 - 2:51 PM
 * @project Marksys Database Backup
 * @ide IntelliJ IDEA
 */
public interface BackupRepo {
    public ArrayList<String> checkTableExistingEmptyCol();
    public void createDateFilter();
}
