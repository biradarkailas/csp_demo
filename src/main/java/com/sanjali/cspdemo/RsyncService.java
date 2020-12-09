package com.sanjali.cspdemo;

import com.github.fracpete.rsync4j.RSync;

public class RsyncService {


    public static void synchronize() {

        String source = "Documents/data/";
        String destination = "Documents/rsyncData";
        RSync rsync = new RSync().passwordFile("password")
                .source(source)
                .destination(destination)
                .update(true)
                .verbose(true)
                .recursive(true);
        try {
            rsync.execute();
            System.out.println("Jenkins Test comleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
