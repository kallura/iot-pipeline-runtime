package org.orlenko.service.impl;

import static org.orlenko.excpetion.Messages.AVERAGE_COMMAND_ERROR_MESSAGE;
import static org.orlenko.excpetion.Messages.AVERAGE_COMMAND_ERROR_MESSAGE_LOGGER;
import static org.orlenko.excpetion.Messages.MAX_COMMAND_ERROR_MESSAGE;
import static org.orlenko.excpetion.Messages.MAX_COMMAND_ERROR_MESSAGE_LOGGER;
import static org.orlenko.excpetion.Messages.MEDIAN_COMMAND_ERROR_MESSAGE;
import static org.orlenko.excpetion.Messages.MEDIAN_COMMAND_ERROR_MESSAGE_LOGGER;
import static org.orlenko.excpetion.Messages.MIN_COMMAND_ERROR_MESSAGE;
import static org.orlenko.excpetion.Messages.MIN_COMMAND_ERROR_MESSAGE_LOGGER;

import org.orlenko.excpetion.SensorBusinessException;
import org.orlenko.mapper.SensorRequestToCriteria;
import org.orlenko.repository.SensorRepository;
import org.orlenko.repository.criteria.SensorCriteria;
import org.orlenko.resource.response.SensorResponse;
import org.orlenko.service.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Sensor service retrieves sensor data from HBase repository and does business logic
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
@Component
public class SensorServiceImpl implements SensorService {

    private static final Logger logger = LoggerFactory.getLogger(SensorServiceImpl.class);

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    /**
     * Maps search parameters to criteria, gets average from HBase datasource, maps to response
     *
     * @param sensors,   array of sensor names, not null
     * @param startDate, start search from, not null
     * @param endDate,   end search at, not null
     * @return list sensor responses [sensor, value]
     */
    @Override
    public List<SensorResponse> findAverage(final String[] sensors,
                                            final LocalDateTime startDate,
                                            final LocalDateTime endDate) {
        List<SensorResponse> sensorResponses = new ArrayList<>();
        for (String sensor : sensors) {
            SensorCriteria criteria = SensorRequestToCriteria.map(sensor, startDate, endDate);
            try {
                double average = sensorRepository.average(criteria);
                sensorResponses.add(createSensorResponse(sensor, average));
            } catch (Throwable throwable) {
                logger.error(AVERAGE_COMMAND_ERROR_MESSAGE_LOGGER.getMessage(), sensor, throwable);
                String message = String.format(AVERAGE_COMMAND_ERROR_MESSAGE.getMessage(), sensor);
                throw new SensorBusinessException(message);
            }
        }
        return sensorResponses;
    }

    /**
     * Maps search parameters to criteria, gets median from HBase datasource, maps to response
     *
     * @param sensors,   array of sensor names, not null
     * @param startDate, start search from, not null
     * @param endDate,   end search at, not null
     * @return list sensor responses [sensor, value]
     */
    @Override
    public List<SensorResponse> findMedian(String[] sensors,
                                           LocalDateTime startDate,
                                           LocalDateTime endDate) {
        List<SensorResponse> sensorResponses = new ArrayList<>();
        for (String sensor : sensors) {
            SensorCriteria criteria = SensorRequestToCriteria.map(sensor, startDate, endDate);
            try {
                double average = sensorRepository.median(criteria);
                sensorResponses.add(createSensorResponse(sensor, average));
            } catch (Throwable throwable) {
                logger.error(MEDIAN_COMMAND_ERROR_MESSAGE_LOGGER.getMessage(), sensor, throwable);
                String message = String.format(MEDIAN_COMMAND_ERROR_MESSAGE.getMessage(), sensor);
                throw new SensorBusinessException(message);
            }
        }
        return sensorResponses;
    }

    /**
     * Maps search parameters to criteria, gets max from HBase datasource, maps to response
     *
     * @param sensors,   array of sensor names, not null
     * @param startDate, start search from, not null
     * @param endDate,   end search at, not null
     * @return list sensor responses [sensor, value]
     */
    @Override
    public List<SensorResponse> findMax(String[] sensors,
                                        LocalDateTime startDate,
                                        LocalDateTime endDate) {
        List<SensorResponse> sensorResponses = new ArrayList<>();
        for (String sensor : sensors) {
            SensorCriteria criteria = SensorRequestToCriteria.map(sensor, startDate, endDate);
            try {
                double average = sensorRepository.max(criteria);
                sensorResponses.add(createSensorResponse(sensor, average));
            } catch (Throwable throwable) {
                logger.error(MAX_COMMAND_ERROR_MESSAGE_LOGGER.getMessage(), sensor, throwable);
                String message = String.format(MAX_COMMAND_ERROR_MESSAGE.getMessage(), sensor);
                throw new SensorBusinessException(message);
            }
        }
        return sensorResponses;
    }

    /**
     * Maps search parameters to criteria, gets min from HBase datasource, maps to response
     *
     * @param sensors,   array of sensor names, not null
     * @param startDate, start search from, not null
     * @param endDate,   end search at, not null
     * @return list sensor responses [sensor, value]
     */
    @Override
    public List<SensorResponse> findMin(String[] sensors,
                                        LocalDateTime startDate,
                                        LocalDateTime endDate) {
        List<SensorResponse> sensorResponses = new ArrayList<>();
        for (String sensor : sensors) {
            SensorCriteria criteria = SensorRequestToCriteria.map(sensor, startDate, endDate);
            try {
                double average = sensorRepository.min(criteria);
                sensorResponses.add(createSensorResponse(sensor, average));
            } catch (Throwable throwable) {
                logger.error(MIN_COMMAND_ERROR_MESSAGE_LOGGER.getMessage(), sensor, throwable.getMessage());
                String message = String.format(MIN_COMMAND_ERROR_MESSAGE.getMessage(), sensor);
                throw new SensorBusinessException(message);
            }
        }
        return sensorResponses;
    }

    private SensorResponse createSensorResponse(String sensor, double average) {
        SensorResponse sensorResponse = new SensorResponse();
        sensorResponse.setSensor(sensor);
        sensorResponse.setValue(average);
        return sensorResponse;
    }
}
