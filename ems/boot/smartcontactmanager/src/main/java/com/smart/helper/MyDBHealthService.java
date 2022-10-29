package com.smart.helper;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Controller;

@Controller
public class MyDBHealthService implements HealthIndicator {

    public static final String  DB_SERVICE="Database Service";
    public boolean isHelathGood()
    {
        return true;
    }


    @Override
    public Health health() {
      if(isHelathGood())
      {
          return  Health.up().withDetail(DB_SERVICE,"Database Service is running").build();
      }
      return Health.down().withDetail(DB_SERVICE,"DataBase Service is running Down").build();
    }
}
