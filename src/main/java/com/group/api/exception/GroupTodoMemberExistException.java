package com.group.api.exception;

public class GroupTodoMemberExistException  extends RuntimeException {
    public GroupTodoMemberExistException(String msg, Throwable t) {
        super(msg, t);
    }
    public GroupTodoMemberExistException(String msg) {
        super(msg);
    }
    public GroupTodoMemberExistException() {
        super();
    }
}
