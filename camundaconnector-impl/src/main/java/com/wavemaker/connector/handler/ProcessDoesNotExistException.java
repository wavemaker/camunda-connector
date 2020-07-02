package com.wavemaker.connector.handler;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 6/3/20
 */
public class ProcessDoesNotExistException extends RuntimeException {

    public ProcessDoesNotExistException(String message) {
        super(message);
    }
}
