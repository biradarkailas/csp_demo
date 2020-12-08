package com.sanjali.cspdemo;

import com.sanjali.cspdemo.util.ImportCsv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CspdemoApplication {

    public static void main(String[] args) {

        ImportCsv.readCsv();
        //SpringApplication.run(CspdemoApplication.class, args);
        //RsyncService.synchronize();
    }

}
