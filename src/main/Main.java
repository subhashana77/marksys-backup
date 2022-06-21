package main;

import impl.BackupRepoImpl;

/**
 * @author dilshan.r
 * @created 6/20/2022 - 4:43 PM
 * @project Marksys Database Backup
 * @ide IntelliJ IDEA
 */
public class Main {
    public static void main(String[] args) {
        BackupRepoImpl backupRepo = new BackupRepoImpl();
        backupRepo.dropTheTables();
    }
}
