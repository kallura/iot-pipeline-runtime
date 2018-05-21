package org.orlenko.repository.impl;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.coprocessor.ColumnInterpreter;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos;
import org.orlenko.repository.SensorRepository;
import org.orlenko.repository.criteria.SensorCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Sensor repository implementation based on HBase datasource
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
@Component
public class SensorRepositoryImpl implements SensorRepository {

    private final ColumnInterpreter<Long, Long, HBaseProtos.EmptyMsg, HBaseProtos.LongMsg,
            HBaseProtos.LongMsg> ci = new LongColumnInterpreter();
    private final AggregationClient hBaseAggregationClient;

    @Autowired
    public SensorRepositoryImpl(AggregationClient hBaseAggregationClient) {
        this.hBaseAggregationClient = hBaseAggregationClient;
    }

    /**
     * Calculates average using criteria using HBase aggregation client
     *
     * @param criteria a search parameters, can't be null
     * @return average
     * @throws Throwable, in case of fails with datasource connection
     */
    @Override
    public double average(SensorCriteria criteria) throws Throwable {
        Scan scan = new Scan();
        scan.addColumn(criteria.getFamily(), criteria.getColumn());
        scan.setTimeRange(criteria.getStartTimeFilter(), criteria.getEndTimeFilter());
        return hBaseAggregationClient.avg(TableName.valueOf(criteria.getTable()), ci, scan);
    }

    /**
     * Calculates median using criteria using HBase aggregation client
     *
     * @param criteria a search parameters, can't be null
     * @return median
     * @throws Throwable, in case of fails with datasource connection
     */
    @Override
    public double median(SensorCriteria criteria) throws Throwable {
        Scan scan = new Scan();
        scan.addColumn(criteria.getFamily(), criteria.getColumn());
        scan.setTimeRange(criteria.getStartTimeFilter(), criteria.getEndTimeFilter());
        return hBaseAggregationClient.median(TableName.valueOf(criteria.getTable()), ci, scan);
    }

    /**
     * Calculates max using criteria using HBase aggregation client
     *
     * @param criteria a search parameters. can't be null
     * @return max
     * @throws Throwable, in case of fails with datasource connection
     */
    @Override
    public double max(SensorCriteria criteria) throws Throwable {
        Scan scan = new Scan();
        scan.addColumn(criteria.getFamily(), criteria.getColumn());
        scan.setTimeRange(criteria.getStartTimeFilter(), criteria.getEndTimeFilter());
        return hBaseAggregationClient.max(TableName.valueOf(criteria.getTable()), ci, scan);
    }

    /**
     * Calculates min using criteria using HBase aggregation client
     *
     * @param criteria a search parameters, can't be null
     * @return min
     * @throws Throwable, in case of fails with datasource connection
     */
    @Override
    public double min(SensorCriteria criteria) throws Throwable {
        Scan scan = new Scan();
        scan.addColumn(criteria.getFamily(), criteria.getColumn());
        scan.setTimeRange(criteria.getStartTimeFilter(), criteria.getEndTimeFilter());
        return hBaseAggregationClient.min(TableName.valueOf(criteria.getTable()), ci, scan);
    }
}
