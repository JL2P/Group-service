package com.group.api.exception;

/**
 * 예외처리 커스텀
 * 해당 그룹이 존재하지 않을 때 발생 시키는 Exception
 * **/
public class GroupNotExistException extends RuntimeException {
    public GroupNotExistException(String msg, Throwable t) {
        super(msg, t);
    }
    public GroupNotExistException(String msg) {
        super(msg);
    }
    public GroupNotExistException() {
        super();
    }
}
