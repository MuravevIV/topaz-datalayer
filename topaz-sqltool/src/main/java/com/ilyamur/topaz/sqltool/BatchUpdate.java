package com.ilyamur.topaz.sqltool;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Configurable
public class BatchUpdate {

    @Autowired
    private SqlParser sqlParser;

    @Autowired
    private EntityProvider entityProvider;

    private DataSource dataSource;
    private String sql;

    public BatchUpdate(DataSource dataSource, String sql) {
        this.dataSource = dataSource;
        this.sql = sql;
    }

    class BatchEntityIterator<T> implements Iterator<List<Param>> {

        private final LinkedList<T> stack;
        private final BatchMapper<T> batchMapper;

        public BatchEntityIterator(List<T> entities, BatchMapper<T> batchMapper) {
            this.stack = Lists.newLinkedList(entities);
            this.batchMapper = batchMapper;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public List<Param> next() {
            return batchMapper.apply(stack.pop());
        }
    }

    public <T> BatchUpdateResult on(List<T> list, BatchMapper<T> batchMapper) {
        Preconditions.checkArgument(!list.isEmpty(), "Batch element list should not be empty");
        List<Integer> updateCounts;
        List<Param> initParams = batchMapper.apply(list.get(0));
        ParseResult parseResult = sqlParser.parse(sql, initParams.toArray(new Param[0]));
        BatchEntityIterator<T> iterator = new BatchEntityIterator<>(list, batchMapper);
        try {
            updateCounts = entityProvider.updateEntitiesBatch(dataSource, parseResult.getSql(), iterator);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new BatchUpdateResult(updateCounts);
    }
}
