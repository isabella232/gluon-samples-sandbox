package com.gluonhq.glisten.afterburner.testbench.service;

import javax.annotation.PostConstruct;

public class Service {

    @PostConstruct
    private void postConstruct() {
        System.out.println("this is a service");
    }
}
