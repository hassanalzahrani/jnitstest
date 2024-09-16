package com.example.springsecurity.controller;


import com.example.springsecurity.model.Todo;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
    private

    final TodoService todoService;




    @GetMapping("/get-my")
    public ResponseEntity getMyTodos(@AuthenticationPrincipal User user) {

        return ResponseEntity.status(200).body(todoService.getMyTodos(user.getId()));
    }

@GetMapping("get-all")
    public ResponseEntity getAllTodos() {
       return ResponseEntity.status(200).body(todoService.getAlLTodos());

    }
@PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal User user,@Valid @RequestBody Todo todo) {
        todoService.addTodo(user.getId(), todo);
        return ResponseEntity.status(200).body("todo added successfully"+user.getUsername());

    }
    @PutMapping("/update/{todo_id}")
    public ResponseEntity updateTodo(@PathVariable Integer todo_id,@AuthenticationPrincipal User user,@Valid @RequestBody Todo todo) {
        todoService.updateTodo(todo_id, user.getId(), todo);
        return ResponseEntity.status(200).body("todo updated successfully"+user.getUsername());
    }

    @DeleteMapping("/delete/{todo_id}")
    public ResponseEntity deleteTodo(@AuthenticationPrincipal User user,@PathVariable Integer todo_id) {
        todoService.deleteTodo(user.getId(),todo_id);
        return ResponseEntity.status(200).body("todo deleted successfully"+user.getUsername());
    }

    @GetMapping("get-by-id/{todo_id}")
    public ResponseEntity getTodoById(@AuthenticationPrincipal User user, @PathVariable Integer todo_id) {
        return ResponseEntity.status(200).body(todoService.getTodoById(user.getId(),todo_id));
    }

}
