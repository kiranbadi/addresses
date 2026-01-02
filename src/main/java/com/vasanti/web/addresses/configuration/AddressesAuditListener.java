package com.vasanti.web.addresses.configuration;


import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Log4j2
public class AddressesAuditListener extends AbstractMongoEventListener<Object> {
    @Override
    public void onAfterSave(AfterSaveEvent<Object> event) {
        Object obj = event.getSource();
        log.info("Saved document at time {}, {}",LocalDateTime.now(), obj);
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Object> event) {
        Object obj = event.getSource();
        log.info("Deleted document at time {}, {}",LocalDateTime.now(), obj);
    }
}