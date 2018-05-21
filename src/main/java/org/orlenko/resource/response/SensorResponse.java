package org.orlenko.resource.response;

/**
 * Sensor response model
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
public class SensorResponse {

    private String sensor;
    private double value;

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
