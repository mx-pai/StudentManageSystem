package edu.student.studentinfo.common;

public class BizException extends RuntimeException{
    private final int httpStatus;

    public BizException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
