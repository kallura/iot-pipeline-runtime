package org.orlenko.mapper;

import static org.orlenko.excpetion.Messages.MAPPING_ERROR_MESSAGE;

import org.orlenko.excpetion.SensorBusinessException;

import java.util.Arrays;
import java.util.Optional;

/**
 * Database columns mapping
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
public enum SensorMapping {

    POWER("power", "power", "power", "power"),
    ENERGY("energy", "energy", "energy", "energy"),
    TEMPERATURE("temperature", "temperature", "temperature", "temperature");

    private String sensor;
    private String family;
    private String table;
    private String column;

    SensorMapping(String sensor, String family, String table, String column) {
        this.sensor = sensor;
        this.family = family;
        this.table = table;
        this.column = column;
    }

    public static SensorMapping getBySensor(final String sensor) throws SensorBusinessException {
        Optional<SensorMapping> mapping = Arrays.stream(values())
                .filter(sensorMapping -> sensorMapping.sensor.equals(sensor))
                .findAny();
        return mapping.orElseThrow(() -> new SensorBusinessException(
                String.format(MAPPING_ERROR_MESSAGE.getMessage(), sensor)));
    }

    public String getFamily() {
        return family;
    }

    public String getTable() {
        return table;
    }

    public String getColumn() {
        return column;
    }
}
