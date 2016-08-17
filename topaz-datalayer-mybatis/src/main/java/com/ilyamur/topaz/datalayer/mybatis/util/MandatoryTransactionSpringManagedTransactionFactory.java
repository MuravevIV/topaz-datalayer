package com.ilyamur.topaz.datalayer.mybatis.util;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;

public class MandatoryTransactionSpringManagedTransactionFactory extends SpringManagedTransactionFactory {

    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            throw new IllegalTransactionStateException("No existing transaction found during mapper invocation");
        }
        return super.newTransaction(dataSource, level, autoCommit);
    }
}
