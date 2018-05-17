package com.cmaotai.service.main;

import com.cmaotai.service.service.StartService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CmaotaiStart {

    public static void main(String[] args) throws Exception {
        new StartService().start();
    }
}
