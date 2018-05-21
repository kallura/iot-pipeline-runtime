package org.orlenko.mapper;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.orlenko.excpetion.SensorBusinessException;
import org.orlenko.repository.criteria.SensorCriteria;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
public class SensorRequestToCriteriaTest {

    @Test
    public void map() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().withHour(1);
        SensorCriteria criteria = SensorRequestToCriteria.map("power", start, end);

        assertNotNull("The criteria should be not null", criteria);
        assertArrayEquals("The family should be set to 'power'", "power".getBytes(),
                criteria.getFamily());
        assertArrayEquals("The column should be set to 'power'", "power".getBytes(),
                criteria.getColumn());
        assertArrayEquals("The table should be set to 'power'", "power".getBytes(),
                criteria.getTable());
        assertEquals("The start date should be set to 'power'",
                start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                criteria.getStartTimeFilter());
        assertEquals("The end date should be set to 'power'",
                start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                criteria.getStartTimeFilter());
    }

    @Test(expected = NullPointerException.class)
    public void mapNullStartDate() {
        LocalDateTime end = LocalDateTime.now().withHour(1);
        SensorRequestToCriteria.map("power", null, end);
    }

    @Test(expected = NullPointerException.class)
    public void mapNullEndDate() {
        LocalDateTime start = LocalDateTime.now();
        SensorRequestToCriteria.map("power", start, null);
    }

    @Test(expected = SensorBusinessException.class)
    public void mapNullSensor() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().withHour(1);
        SensorRequestToCriteria.map(null, start, end);
    }
}