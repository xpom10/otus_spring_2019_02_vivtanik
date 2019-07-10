package ru.otus.batchConfig.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class BatchWriterListener implements ItemWriteListener {

    private Logger logger = LoggerFactory.getLogger(BatchWriterListener.class);

    @Override
    public void beforeWrite(List list) {
        logger.info("Before write {}", list);
    }

    @Override
    public void afterWrite(List list) {
        logger.info("After write {}", list);
    }

    @Override
    public void onWriteError(Exception e, List list) {
    }
}
