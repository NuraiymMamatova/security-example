package com.example.todo.apies;

import com.example.todo.model.dto.TodoRequest;
import com.example.todo.model.dto.TodoResponse;
import com.example.todo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoApi {

    private final TodoService todoService;

    @PostMapping
    public TodoResponse saveTodo(@RequestBody TodoRequest todoRequest, Authentication authentication) {
        System.out.println(authentication);
        return todoService.saveTodo(todoRequest, authentication);
    }
}
