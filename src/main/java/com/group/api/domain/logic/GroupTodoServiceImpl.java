package com.group.api.domain.logic;

import com.group.api.domain.GroupTodo;
import com.group.api.domain.service.GroupTodoService;
import com.group.api.exception.GroupNotExistException;
import com.group.api.repository.GroupTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GroupTodoServiceImpl implements GroupTodoService {

    private final GroupTodoRepository todoRepository;

    @Override
    public List<GroupTodo> getTodos() throws NoSuchElementException {
        return todoRepository.findAll(sortByCreatedAsc());
    }

    private Sort sortByCreatedAsc() {
        return Sort.by(Sort.Direction.DESC, "created");
    }

    @Override
    public GroupTodo getTodo(Long todoId) throws NoSuchElementException {
        //데이터가 하나도 없을 경우 빈 group객체 반환
        if (!isExist(todoId)) return new GroupTodo();

        return todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public void addTodo(GroupTodo todo) throws GroupNotExistException {
        todoRepository.save(todo);
    }

    @Override
    public GroupTodo modifyTodo(GroupTodo todo) throws NoSuchElementException {
        //DB에 todo가 존재하는지 확인
        if (!isExist(todo.getId())) throw new NoSuchElementException(todo.getId().toString());

        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long todoId) throws NoSuchElementException {
        if (!isExist(todoId)) throw new NoSuchElementException(todoId.toString());

        todoRepository.deleteById(todoId);
    }

    @Override
    public boolean isExist(Long todoId) {
        Optional<GroupTodo> todoOpt = todoRepository.findById(todoId);
        //Optional안에 todo객체가 존재하는 경우
        if (todoOpt.isPresent()) return true;

        //Optional안에 todo객체가 존재하지 않는 경우
        return false;
    }
}
