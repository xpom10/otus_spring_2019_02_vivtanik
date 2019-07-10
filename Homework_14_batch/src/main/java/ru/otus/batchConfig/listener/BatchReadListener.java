package ru.otus.batchConfig.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class BatchReadListener implements ItemReadListener {

    private Logger logger = LoggerFactory.getLogger(BatchReadListener.class);

    @Override
    public void beforeRead() {
    }

    @Override
    public void afterRead(Object o) {
        logger.info("Read {}", o);
    }

    @Override
    public void onReadError(Exception e) {

    }
}
