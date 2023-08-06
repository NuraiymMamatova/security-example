package com.example.todo.services;

import com.example.todo.model.dto.TodoRequest;
import com.example.todo.model.dto.TodoResponse;
import org.springframework.security.core.Authentication;

public interface TodoService {

    TodoResponse saveTodo(TodoRequest todoRequest, Authentication authentication);
}
