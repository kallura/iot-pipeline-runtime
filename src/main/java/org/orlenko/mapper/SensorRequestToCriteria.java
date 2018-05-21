package org.orlenko.mapper;

import org.orlenko.excpetion.SensorBusinessException;
import org.orlenko.repository.criteria.SensorCriteria;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Mapper for user search parameters to criteria
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
public interface SensorRequestToCriteria {

    /**
     * Maps sensor name, start date and end date to criteria
     * Doesn't accept the null values
     *
     * @param sensor    a sensor name
     * @param startDate a start date for criteria
     * @param endDate   an end date for criteria
     * @return sensor search criteria
     * @throws SensorBusinessException, in case if can't find mapping
     */
    static SensorCriteria map(String sensor,
                              LocalDateTime startDate,
                              LocalDateTime endDate)
            throws SensorBusinessException {
        SensorCriteria sensorCriteria = new SensorCriteria();
        SensorMapping bySensor = SensorMapping.getBySensor(sensor);
        sensorCriteria.setTable(bySensor.getTable().getBytes());
        sensorCriteria.setFamily(bySensor.getFamily().getBytes());
        sensorCriteria.setColumn(bySensor.getColumn().getBytes());
        long start = startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        sensorCriteria.setStartTimeFilter(start);
        long end = endDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        sensorCriteria.setEndTimeFilter(end);
        return sensorCriteria;
    }
}
