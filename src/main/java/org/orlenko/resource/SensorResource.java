package org.orlenko.resource;

import org.orlenko.resource.response.SensorResponse;
import org.orlenko.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Sensor resource returns average/median/max/min/sum/count values for sensors
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
@RestController
@RequestMapping("/sensor")
public class SensorResource {

    private final SensorService sensorService;

    @Autowired
    public SensorResource(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    /**
     * Returns average for sensors
     *
     * @param sensors, array of sensor names
     * @return list sensor responses [sensor, value]
     */
    @RequestMapping("/average")
    public List<SensorResponse> getAverageForSensors(
            @RequestParam(value = "sensor_list") String[] sensors,
            @RequestParam(value = "time_start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "time_end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return sensorService.findAverage(sensors, startDate, endDate);
    }

    /**
     * Returns median for sensors
     *
     * @param sensors, array of sensor names
     * @return list sensor responses [sensor, value]
     */
    @RequestMapping("/median")
    public List<SensorResponse> getMedianForSensors(
            @RequestParam(value = "sensor_list") String[] sensors,
            @RequestParam(value = "time_start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "time_end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return sensorService.findMedian(sensors, startDate, endDate);
    }

    /**
     * Returns max for sensors
     *
     * @param sensors, array of sensor names
     * @return list sensor responses [sensor, value]
     */
    @RequestMapping("/max")
    public List<SensorResponse> getMaxForSensors(
            @RequestParam(value = "sensor_list") String[] sensors,
            @RequestParam(value = "time_start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "time_end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return sensorService.findMax(sensors, startDate, endDate);
    }

    /**
     * Returns min for sensors
     *
     * @param sensors, array of sensor names
     * @return list sensor responses [sensor, value]
     */
    @RequestMapping("/min")
    public List<SensorResponse> getMinForSensors(
            @RequestParam(value = "sensor_list") String[] sensors,
            @RequestParam(value = "time_start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "time_end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return sensorService.findMin(sensors, startDate, endDate);
    }
}

