package com.example.transactional.replication;

import static java.util.stream.Collectors.toList;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    private static final String DATASOURCE_KEY_MASTER = "master";
    private static final String DATASOURCE_KEY_SLAVE = "slave";

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        if (isReadOnly) {
            logger.info("Connection Slave");
            return DATASOURCE_KEY_SLAVE;
        }

        logger.info("Connection Master");
        return DATASOURCE_KEY_MASTER;
    }
}