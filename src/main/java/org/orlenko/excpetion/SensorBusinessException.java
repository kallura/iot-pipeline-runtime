package org.orlenko.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Business exception, wraps native java exception into custom representation
 *
 * @author Kseniia Orlenko
 * @version 5/20/18
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SensorBusinessException extends RuntimeException {

    public SensorBusinessException(String message) {
        super(message);
    }
}
