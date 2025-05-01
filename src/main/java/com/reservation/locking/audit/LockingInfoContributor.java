package com.reservation.locking.audit;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

import java.util.HashMap;
import java.util.Map;

public class LockingInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> map = new HashMap<>();
        map.put("description", "An application demonstrating optimistic and " +
                "pessimistic locking ");
        map.put("version", "v1");
        builder.withDetail("app-info", map);
    }
}
