package org.orlenko.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.orlenko.excpetion.SensorBusinessException;
import org.orlenko.repository.SensorRepository;
import org.orlenko.repository.criteria.SensorCriteria;
import org.orlenko.resource.response.SensorResponse;
import org.orlenko.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SensorServiceImplTest {
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private SensorService sensorService;

    @Test
    public void findAveragePositiveFlow() throws Throwable {
        when(sensorRepository.average(ArgumentMatchers.any(SensorCriteria.class))).thenReturn(12.0);

        List<SensorResponse> average = sensorService.findAverage(new String[] {"power", "energy"},
                LocalDateTime.now(), LocalDateTime.now());

        assertNotNull("The sensor response should be not null", average);
        assertEquals("The response size should be 2", 2, average.size());
    }

    @Test(expected = SensorBusinessException.class)
    public void findAverageNegativeFlow() throws Throwable {
        when(sensorRepository.average(ArgumentMatchers.any(SensorCriteria.class)))
                .thenThrow(new SensorBusinessException("Fail!"));

        sensorService.findAverage(new String[] {"power", "energy"}, LocalDateTime.now(),
                LocalDateTime.now());
    }

    @Test
    public void findMedianPositiveFlow() throws Throwable {
        when(sensorRepository.median(ArgumentMatchers.any(SensorCriteria.class))).thenReturn(11.0);

        List<SensorResponse> median = sensorService.findMedian(new String[] {"power", "energy"},
                LocalDateTime.now(), LocalDateTime.now());

        assertNotNull("The sensor response should be not null", median);
        assertEquals("The response size should be 2", 2, median.size());
    }

    @Test(expected = SensorBusinessException.class)
    public void findMedianNegativeFlow() throws Throwable {
        when(sensorRepository.median(ArgumentMatchers.any(SensorCriteria.class)))
                .thenThrow(new SensorBusinessException("Fail!"));

        sensorService.findMedian(new String[] {"power", "energy"}, LocalDateTime.now(),
                LocalDateTime.now());
    }

    @Test
    public void findMaxPositiveFlow() throws Throwable {
        when(sensorRepository.max(ArgumentMatchers.any(SensorCriteria.class))).thenReturn(10.0);

        List<SensorResponse> max = sensorService.findMax(new String[] {"power", "energy"},
                LocalDateTime.now(), LocalDateTime.now());

        assertNotNull("The sensor response should be not null", max);
        assertEquals("The response size should be 2", 2, max.size());
    }

    @Test(expected = SensorBusinessException.class)
    public void findMaxNegativeFlow() throws Throwable {
        when(sensorRepository.max(ArgumentMatchers.any(SensorCriteria.class)))
                .thenThrow(new SensorBusinessException("Fail!"));

        sensorService.findMax(new String[] {"power", "energy"}, LocalDateTime.now(),
                LocalDateTime.now());
    }

    @Test
    public void findMinPositiveFlow() throws Throwable {
        when(sensorRepository.min(ArgumentMatchers.any(SensorCriteria.class))).thenReturn(9.0);

        List<SensorResponse> min = sensorService.findMin(new String[] {"power", "energy"},
                LocalDateTime.now(), LocalDateTime.now());

        assertNotNull("The sensor response should be not null", min);
        assertEquals("The response size should be 2", 2, min.size());
    }

    @Test(expected = SensorBusinessException.class)
    public void findMinNegativeFlow() throws Throwable {
        when(sensorRepository.min(ArgumentMatchers.any(SensorCriteria.class)))
                .thenThrow(new SensorBusinessException("Fail!"));

        sensorService.findMin(new String[] {"power", "energy"}, LocalDateTime.now(),
                LocalDateTime.now());
    }

    @Configuration
    static class Context {

        @Bean
        public SensorRepository sensorRepository() {
            return mock(SensorRepository.class);
        }

        @Bean
        public SensorService sensorService(SensorRepository sensorRepository) {
            return new SensorServiceImpl(sensorRepository);
        }
    }
}