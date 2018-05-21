package org.orlenko.excpetion;

/**
 * Sensor business exception messages
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
public enum Messages {
    AVERAGE_COMMAND_ERROR_MESSAGE("Application was failed to run average command on sensor: [%s]"),
    MEDIAN_COMMAND_ERROR_MESSAGE("Application was failed to run median command on sensor: [%s]"),
    MAX_COMMAND_ERROR_MESSAGE("Application was failed to run max command on sensor: [%s]"),
    MIN_COMMAND_ERROR_MESSAGE("Application was failed to run min command on sensor: [%s]"),
    MAPPING_ERROR_MESSAGE("Can't find mapping for sensor: [%s]"),
    AVERAGE_COMMAND_ERROR_MESSAGE_LOGGER("Application was failed to run average command on sensor:[{}]"),
    MEDIAN_COMMAND_ERROR_MESSAGE_LOGGER("Application was failed to run median command on sensor:[{}]"),
    MAX_COMMAND_ERROR_MESSAGE_LOGGER("Application was failed to run max command on sensor:[{}]"),
    MIN_COMMAND_ERROR_MESSAGE_LOGGER("Application was failed to run min command on sensor:[{}]");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
