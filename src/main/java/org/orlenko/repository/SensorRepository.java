package org.orlenko.repository;

import org.orlenko.repository.criteria.SensorCriteria;

/**
 * Sensor repository, must be implemented by all data sources
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
public interface SensorRepository {

    /**
     * Calculates average using criteria
     *
     * @param criteria a search parameters, not null
     * @return average
     * @throws Throwable, in case of fails with datasource connection
     */
    double average(SensorCriteria criteria) throws Throwable;

    /**
     * Calculates median using criteria
     *
     * @param criteria a search parameters, not null
     * @return median
     * @throws Throwable, in case of fails with datasource connection
     */
    double median(SensorCriteria criteria) throws Throwable;

    /**
     * Calculates max using criteria
     *
     * @param criteria a search parameters, not null
     * @return max
     * @throws Throwable, in case of fails with datasource connection
     */
    double max(SensorCriteria criteria) throws Throwable;

    /**
     * Calculates min using criteria
     *
     * @param criteria a search parameters, not null
     * @return min
     * @throws Throwable, in case of fails with datasource connection
     */
    double min(SensorCriteria criteria) throws Throwable;
}
