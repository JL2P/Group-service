package com.group.api.exception;

public class GroupTodoMemberNotExistException extends RuntimeException {
    public GroupTodoMemberNotExistException(String msg, Throwable t) {
        super(msg, t);
    }
    public GroupTodoMemberNotExistException(String msg) {
        super(msg);
    }
    public GroupTodoMemberNotExistException() {
        super();
    }
}
