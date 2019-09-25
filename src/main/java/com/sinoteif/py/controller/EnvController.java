package com.sinoteif.py.controller;

import com.sinoteif.py.conf.EnvConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2019/9/25.
 */
@RestController
public class EnvController {
    @Autowired
    private EnvConfig envConfig;
    @GetMapping("env")
    public Object getEnv(){
        return envConfig;
    }
}
