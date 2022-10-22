package com.deimm.climathon;

import com.deimm.climathon.util.ReadCsv;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);

        ReadCsv readCsv = new ReadCsv();
        readCsv.fillLists();

        System.out.println("debug");

    }
}


