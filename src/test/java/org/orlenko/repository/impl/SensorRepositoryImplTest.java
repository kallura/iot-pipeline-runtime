package org.orlenko.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.coprocessor.ColumnInterpreter;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.orlenko.repository.SensorRepository;
import org.orlenko.repository.criteria.SensorCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SensorRepositoryImplTest {

    private final ColumnInterpreter<Long, Long, HBaseProtos.EmptyMsg, HBaseProtos.LongMsg,
            HBaseProtos.LongMsg> ci = new LongColumnInterpreter();

    @Autowired
    private AggregationClient aggregationClient;
    @Autowired
    private SensorRepository sensorRepository;

    @Test
    public void averagePositiveFlow() throws Throwable {
        when(aggregationClient
                .avg(ArgumentMatchers.any(TableName.class), ArgumentMatchers
                        .<ColumnInterpreter<Long, Long, HBaseProtos.EmptyMsg, HBaseProtos.LongMsg,
                                HBaseProtos.LongMsg>>any(), ArgumentMatchers.any(Scan.class)))
                .thenReturn(34.5);
        SensorCriteria criteria = createCriteria();
        double average = sensorRepository.average(criteria);

        assertEquals("The average should be set to 34.5", 34.5, average, 1);
    }

    @Test(expected = Throwable.class)
    public void averageNegativeFlow() throws Throwable {
        when(aggregationClient
                .avg(ArgumentMatchers.any(TableName.class), ArgumentMatchers
                        .<ColumnInterpreter<Long, Long, HBaseProtos.EmptyMsg, HBaseProtos.LongMsg,
                                HBaseProtos.LongMsg>>any(), ArgumentMatchers.any(Scan.class)))
                .thenThrow(new Throwable());

        sensorRepository.average(new SensorCriteria());
    }

    @Test
    public void medianPositiveFlow() throws Throwable {
        when(aggregationClient
                .median(ArgumentMatchers.any(TableName.class), ArgumentMatchers
                        .<ColumnInterpreter<Long, Long, HBaseProtos.EmptyMsg, HBaseProtos.LongMsg,
                                HBaseProtos.LongMsg>>any(), ArgumentMatchers.any(Scan.class)))
                .thenReturn(14L);

        SensorCriteria criteria = createCriteria();
        double median = sensorRepository.median(criteria);

        assertEquals("The median should be set to 14.0", 14L, median, 1);
    }

    @Test(expected = Throwable.class)
    public void medianNegativeFlow() throws Throwable {
        when(aggregationClient
                .median(ArgumentMatchers.any(TableName.class), ArgumentMatchers
                        .<ColumnInterpreter<Long, Long, HBaseProtos.EmptyMsg, HBaseProtos.LongMsg,
                                HBaseProtos.LongMsg>>any(), ArgumentMatchers.any(Scan.class)))
                .thenThrow(new Throwable());

        sensorRepository.median(new SensorCriteria());
    }

    @Test
    public void maxPositiveFlow() throws Throwable {
        when(aggregationClient
                .max(ArgumentMatchers.any(TableName.class), ArgumentMatchers
                        .<ColumnInterpreter<Long, Long, HBaseProtos.EmptyMsg, HBaseProtos.LongMsg,
                                HBaseProtos.LongMsg>>any(), ArgumentMatchers.any(Scan.class)))
                .thenReturn(145L);

        SensorCriteria criteria = createCriteria();
        double max = sensorRepository.max(criteria);

        assertEquals("The max should be set to 145", 145L, max, 1);
    }

    @Test(expected = Throwable.class)
    public void maxNegativeFlow() throws Throwable {
        when(aggregationClient
                .max(ArgumentMatchers.any(TableName.class), ArgumentMatchers
                        .<ColumnInterpreter<Long, Long, HBaseProtos.EmptyMsg, HBaseProtos.LongMsg,
                                HBaseProtos.LongMsg>>any(), ArgumentMatchers.any(Scan.class)))
                .thenThrow(new Throwable());

        sensorRepository.max(new SensorCriteria());
    }

    @Test
    public void minPositiveFlow() throws Throwable {
        when(aggregationClient
                .min(ArgumentMatchers.any(TableName.class), ArgumentMatchers
                        .<ColumnInterpreter<Long, Long, HBaseProtos.EmptyMsg, HBaseProtos.LongMsg,
                                HBaseProtos.LongMsg>>any(), ArgumentMatchers.any(Scan.class)))
                .thenReturn(1L);

        SensorCriteria criteria = createCriteria();
        double min = sensorRepository.min(criteria);

        assertEquals("The min should be set to 1", 1L, min, 1);
    }

    @Test(expected = Throwable.class)
    public void minNegativeFlow() throws Throwable {
        when(aggregationClient
                .min(ArgumentMatchers.any(TableName.class), ArgumentMatchers
                        .<ColumnInterpreter<Long, Long, HBaseProtos.EmptyMsg, HBaseProtos.LongMsg,
                                HBaseProtos.LongMsg>>any(), ArgumentMatchers.any(Scan.class)))
                .thenThrow(new Throwable());

        sensorRepository.min(new SensorCriteria());
    }

    private SensorCriteria createCriteria() {
        SensorCriteria sensorCriteria = new SensorCriteria();
        sensorCriteria.setTable("table".getBytes());
        sensorCriteria.setColumn("column".getBytes());
        sensorCriteria.setFamily("family".getBytes());
        sensorCriteria.setStartTimeFilter(123344L);
        sensorCriteria.setEndTimeFilter(12334554L);
        return sensorCriteria;
    }

    @Configuration
    static class Context {

        @Bean
        public AggregationClient aggregationClient() {
            return mock(AggregationClient.class);
        }

        @Bean
        public SensorRepository sensorRepository(AggregationClient aggregationClient) {
            return new SensorRepositoryImpl(aggregationClient);
        }
    }
}