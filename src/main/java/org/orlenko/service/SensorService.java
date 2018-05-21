package org.orlenko.service;

import org.orlenko.resource.response.SensorResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Sensor service retrieves sensor data from repository and does business logic
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
public interface SensorService {

    /**
     * Returns average for sensors
     *
     * @param sensors,   array of sensor names, not null
     * @param startDate, start search from, not null
     * @param endDate,   end search at, not null
     * @return list sensor responses [sensor, value]
     */
    List<SensorResponse> findAverage(final String[] sensors,
                                     final LocalDateTime startDate,
                                     final LocalDateTime endDate);

    /**
     * Returns median for sensors
     *
     * @param sensors,   array of sensor names, not null
     * @param startDate, start search from, not null
     * @param endDate,   end search at, not null
     * @return list sensor responses [sensor, value]
     */
    List<SensorResponse> findMedian(final String[] sensors,
                                    final LocalDateTime startDate,
                                    final LocalDateTime endDate);

    /**
     * Returns max for sensors
     *
     * @param sensors,   array of sensor names, not null
     * @param startDate, start search from, not null
     * @param endDate,   end search at, not null
     * @return list sensor responses [sensor, value]
     */
    List<SensorResponse> findMax(final String[] sensors,
                                 final LocalDateTime startDate,
                                 final LocalDateTime endDate);

    /**
     * Returns min for sensors
     *
     * @param sensors,   array of sensor names, not null
     * @param startDate, start search from, not null
     * @param endDate,   end search at, not null
     * @return list sensor responses [sensor, value]
     */
    List<SensorResponse> findMin(final String[] sensors,
                                 final LocalDateTime startDate,
                                 final LocalDateTime endDate);

}
