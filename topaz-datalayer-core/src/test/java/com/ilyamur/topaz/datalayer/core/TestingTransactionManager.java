package com.ilyamur.topaz.datalayer.core;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TestingTransactionManager extends AbstractPlatformTransactionManager {

    @Override
    protected boolean isExistingTransaction(Object transaction) throws TransactionException {
        return TransactionSynchronizationManager.isActualTransactionActive();
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        return new Object();
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
        TransactionSynchronizationManager.setActualTransactionActive(true);
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        TransactionSynchronizationManager.setActualTransactionActive(false);
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        TransactionSynchronizationManager.setActualTransactionActive(false);
    }
}
