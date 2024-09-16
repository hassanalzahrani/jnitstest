package com.example.springsecurity;

import com.example.springsecurity.model.Todo;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repoisory.AuthRepositry;
import com.example.springsecurity.repoisory.TodoRepository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;
    ////تحضير ال DATA الخاصة لاختبارها

    @Autowired
  private AuthRepositry authRepositry;

    User user;
    /////added to the list if we have list
    Todo todo1,todo2,todo3;


    List<Todo>todos;


    Todo todo;


    @BeforeEach
    void setUp() {
        user=new User(null,"hassan" , "12345" , "ADMIN" , null);
        todo1 = new Todo( null,"todo1",user);
        todo2 = new Todo(null, "todo2" , user );
        todo3 = new Todo(null, "todo3" , user );

    }

    @Test
    public void findTodoByIdtest(){
        authRepositry.save(user);
        todoRepository.save(todo1);
     Todo todo=  todoRepository.findTodoById(todo1.getId());
       Assertions.assertThat(todo).isEqualTo(todo1);
    }



    @Test
    public void findAllByMyUserTesting(){
        authRepositry.save(user);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);
        List<Todo> todos= todoRepository.findAllByUser(user);
        Assertions.assertThat(todos.get(0).getUser().getId()).isEqualTo(user.getId());
    }


}
