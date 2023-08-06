package com.example.todo.services.servicesimpl;

import com.example.todo.model.dto.TodoRequest;
import com.example.todo.model.dto.TodoResponse;
import com.example.todo.model.entities.Todo;
import com.example.todo.model.entities.User;
import com.example.todo.repositories.TodoRepository;
import com.example.todo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoResponse saveTodo(TodoRequest todoRequest, Authentication authentication) {
        Todo todo = new Todo();
        todo.setCompleted(false);
        todo.setTitle(todoRequest.getTitle());
        System.out.println((User) authentication.getPrincipal());
        todoRepository.save(todo);
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.getCompleted());
    }
}
