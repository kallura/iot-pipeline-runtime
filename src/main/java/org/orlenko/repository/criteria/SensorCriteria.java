package org.orlenko.repository.criteria;

/**
 * Sensor search criteria
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
public class SensorCriteria {
    private byte[] table;
    private byte[] family;
    private byte[] column;
    private long startTimeFilter;
    private long endTimeFilter;

    public byte[] getTable() {
        return table;
    }

    public void setTable(byte[] table) {
        this.table = table;
    }

    public byte[] getFamily() {
        return family;
    }

    public void setFamily(byte[] family) {
        this.family = family;
    }

    public byte[] getColumn() {
        return column;
    }

    public void setColumn(byte[] column) {
        this.column = column;
    }

    public long getStartTimeFilter() {
        return startTimeFilter;
    }

    public void setStartTimeFilter(long startTimeFilter) {
        this.startTimeFilter = startTimeFilter;
    }

    public long getEndTimeFilter() {
        return endTimeFilter;
    }

    public void setEndTimeFilter(long endTimeFilter) {
        this.endTimeFilter = endTimeFilter;
    }
}
