package com.example.springsecurity;


import com.example.springsecurity.model.Todo;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repoisory.AuthRepositry;
import com.example.springsecurity.repoisory.TodoRepository;
import com.example.springsecurity.service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    TodoService todoService;
    @Mock
    TodoRepository todoRepository;
    @Mock
    AuthRepositry authRepository;

    User user;

    Todo todo1, todo2, todo3;

    List<Todo> todos;

    @BeforeEach
    void setUp() {
        user = new User(null, "majd", "123", "Admin", null);
        todo1 = new Todo(null, "todo1", user);
        todo2 = new Todo(null, "todo2", user);
        todo3 = new Todo(null, "todo3", null);

        todos = new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);
    }


    @Test
    public void getAllTodoTest() {
        when(todoRepository.findAll()).thenReturn(todos);
        List<Todo> todoList = todoService.getAlLTodos();
        Assertions.assertEquals(3, todoList.size());
        verify(todoRepository, times(1)).findAll();

    }

//    @Test
//    public void getTodoByIdTest() {
//        when(authRepository.findUserById(user.getId())).thenReturn(user);
//        when(todoRepository.findAllByUser(user)).thenReturn(todos);
//
//
//        List<Todo> todo = todoService.getTodoById(user.getId(), todo1.getId());
//        Assertions.assertEquals(todo1, todos);
//        verify(authRepository, times(1)).findUserById(user.getId());
//        verify(todoRepository, times(1)).findAllByUser(user);
//
//    }

    @Test
    public void AddTodoTest() {

        when(authRepository.findUserById(user.getId())).thenReturn(user);

        todoService.addTodo(user.getId(), todo3);
        verify(authRepository, times(1)).findUserById(user.getId());
        verify(todoRepository, times(1)).save(todo3);
    }

    @Test
    public void updateTodoTest() {

        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);
        when(authRepository.findUserById(user.getId())).thenReturn(user);

        todoService.updateTodo( user.getId(),todo1.getId()  ,todo2);

        verify(todoRepository, times(1)).findTodoById(todo1.getId());
        verify(authRepository, times(1)).findUserById(user.getId());
        verify(todoRepository, times(1)).save(todo1);

    }


    @Test
    public void getMyTodosTest() {
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        List<Todo>myTodos = todoService.getMyTodos(user.getId());
        Assertions.assertEquals(1, myTodos.size());
        verify(authRepository, times(1)).findUserById(user.getId());
        verify(todoRepository, times(1)).save(todo1);

    }


}
