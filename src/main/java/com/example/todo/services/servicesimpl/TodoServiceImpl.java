package com.example.todo.services.servicesimpl;

import com.example.todo.repositories.TodoRepository;
import com.example.todo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
}
