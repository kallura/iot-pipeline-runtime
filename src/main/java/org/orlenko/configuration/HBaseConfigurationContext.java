package org.orlenko.configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;


import java.io.IOException;

/**
 * HBase context configuration
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
@Configuration
public class HBaseConfigurationContext {

    /**
     * HBase context configuration
     *
     * @return Connection, connection to HBase database
     */
    @Bean
    public org.apache.hadoop.conf.Configuration hBaseConfiguration() {
        return HBaseConfiguration.create();
    }

    /**
     * HBase connection
     *
     * @param hBaseConfiguration, HBase current configuration
     * @return Connection, connection to HBase database
     */
    @Bean
    public Connection hBaseConnection(
            org.apache.hadoop.conf.Configuration hBaseConfiguration) throws IOException {
        return ConnectionFactory.createConnection(hBaseConfiguration);
    }

    /**
     * HBase aggregation client
     *
     * @param hBaseConfiguration, HBase current configuration
     * @return Connection, connection to HBase database
     */
    @Bean
    public AggregationClient hBaseAggregationClient(
            org.apache.hadoop.conf.Configuration hBaseConfiguration) {
        return new AggregationClient(hBaseConfiguration);
    }
}
