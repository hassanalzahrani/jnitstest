package com.example.springsecurity;

import com.example.springsecurity.API.ApiResponse;
import com.example.springsecurity.controller.TodoController;
import com.example.springsecurity.model.Todo;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TodoController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class TodoControllerTest {
    @MockBean
    TodoService todoService;

    @Autowired
    MockMvc mockMvc;

    Todo todo1,todo2,todo3;
    User myUser;

//     ApiResponse apiResponse;

    List<Todo> todos,todoList;

    @BeforeEach
    void setUp() {
        myUser=new User(1,"Maha" , "12345" , "ADMIN" , null);
        todo1 = new Todo(1, "todo1", myUser );
        todo2 = new Todo(2 , "todo2",  myUser );
        todo3 = new Todo(3 , "todo3", myUser );
        todos= Arrays.asList(todo1);
        todoList= Arrays.asList(todo2);



    }

    @Test

    public void GetAllTodo() throws Exception {
        Mockito.when(todoService.getAlLTodos()).thenReturn(todos);
      mockMvc.perform(MockMvcRequestBuilders.get(("/api/v1/todo/get-all")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("todo1"));
    }


    @Test
    public void testAddTodo() throws  Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(todo2)))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteTodo() throws Exception{
        mockMvc.perform(delete("/api/v1/todo/{todoId}",todo1.getId()))
                .andExpect(status().isOk());

    }








    }