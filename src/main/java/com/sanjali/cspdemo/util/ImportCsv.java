package com.sanjali.cspdemo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ImportCsv {

    public static void readCsv() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Lenovo\\Documents\\data\\bomd.csv"));
            Connection connection = DBConnection.getConnection();
            // String insertQuery = "Insert into stg_bill_of_material (FG_CODE, COLUMN_B, COLUMN_C, COMPONENT_CODE, LINK_QUANTITY, WASTE_PERCENT, APPLICABLE_FROM, APPLICABLE_TO, ACTIVE, DAYS, COLUMN_K, COLUMN_L, COLUMN_M, CREATED_BY, CREATED_DATE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String insertQuery = "Insert into stg_bill_of_material values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            // PreparedStatement pstmt = connection.prepareStatement(insertQuery);
            String line = "";
            PreparedStatement ps = connection.prepareStatement(insertQuery);
            int count = 0;
            int batchSize = 10000;
            System.out.println("Starting Time::" + LocalDateTime.now());
            while ((line = reader.readLine()) != null) {
                try {
                    connection.setAutoCommit(false);
                   // System.out.println("Batch Start Time::" + LocalDateTime.now());
                    if (line != null) {
                        String[] array = line.split(",+");
                        ps.setString(1, array[0]);
                        ps.setString(2, array[1]);
                        ps.setString(3, array[2]);
                        ps.setString(4, array[3]);
                        ps.setString(5, array[4]);
                        ps.setString(6, array[5]);
                        ps.setString(7, array[6]);
                        ps.setString(8, array[7]);
                        ps.setString(9, array[8]);
                        ps.setString(10, array[9]);
                        ps.setString(11, array[10]);
                        ps.setString(12, array[11]);
                        ps.setString(13, array[12]);
                        ps.setString(14, array[13]);
                        ps.setString(15, array[14]);
                        //ps.executeUpdate();
                        ps.addBatch();
                        if (++count % batchSize == 0) {
                            System.out.println("Batch Completed Time::" + LocalDateTime.now());
                            int[] result = ps.executeBatch();
                            System.out.println("Insertion Completed Time::" + LocalDateTime.now());
                            System.out.println("Number of rows inserted: " + result.length);
                            connection.commit();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Completed Time::" + LocalDateTime.now());
            try {
                ps.close();
                if (reader == null)
                    reader.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Data Successfully Uploaded");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
