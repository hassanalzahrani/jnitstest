package com.example.springsecurity.service;

import com.example.springsecurity.API.ApiException;
import com.example.springsecurity.model.Todo;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repoisory.AuthRepositry;
import com.example.springsecurity.repoisory.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final AuthRepositry authRepositry;




    public List<Todo> getMyTodos(Integer id) {
        User user = authRepositry.findUserById(id);

        return todoRepository.findAllByUser(user);


    }
    public List<Todo>getAlLTodos(){
        return todoRepository.findAll();
    }

    public void addTodo(Integer user_id, Todo todo) {
        User user = authRepositry.findUserById(user_id);

        todo.setUser(user);
        todoRepository.save(todo);
    }

    public void updateTodo(Integer user_id, Integer todo_id,Todo todo) {
Todo t=todoRepository.findTodoById(todo_id);
User u = authRepositry.findUserById(user_id);
if(u==null){
    throw new ApiException("User not found");
}
if(t==null){
    throw new ApiException("Todo not found");
}if (t.getUser().getId() != u.getId()){
    throw new ApiException("User and Todo don't match");
        }
t.setTitle(todo.getTitle());
t.setUser(u);
todoRepository.save(t);


    }
    public void deleteTodo(Integer user_id, Integer todo_id) {
        User user = authRepositry.findUserById(user_id);
        if (user==null){
            throw new ApiException("user not found");
        }
        Todo todoOldTodo = todoRepository.findTodoById(todo_id);
        if (todoOldTodo==null){
            throw new ApiException("todo not found");
        }else if (todoOldTodo.getUser().getId()!=user_id) {
            throw new ApiException("you dont have permission");
        }
        todoRepository.delete(todoOldTodo);
    }

    public Todo getTodoById(Integer user_id, Integer todo_id) {
        User user = authRepositry.findUserById(user_id);
        if (user==null){
            throw new ApiException("user not found");
        }
        Todo todoOldTodo = todoRepository.findTodoById(todo_id);
        if (todoOldTodo==null){
            throw new ApiException("todo not found");
        }else if (todoOldTodo.getUser().getId()!=user_id) {
            throw new ApiException("you dont have permission");
        }

      return todoOldTodo;
    }
}
