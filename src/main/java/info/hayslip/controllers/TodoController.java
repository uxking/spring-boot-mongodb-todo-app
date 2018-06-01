package info.hayslip.controllers;

import info.hayslip.TodoItemsRepository;
import info.hayslip.models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController

@RequestMapping("/todos")
public class TodoController {

    private TodoItemsRepository todoItemsRepository;

    public TodoController(TodoItemsRepository todoItemsRepository) {
        this.todoItemsRepository = todoItemsRepository;
    }

    @GetMapping("/all")
    public List<Todo> getAll() {
        List<Todo> todos = this.todoItemsRepository.findAll();

        return todos;
    }

    @PostMapping
    public void insert(@RequestBody Todo todo) {
        this.todoItemsRepository.insert(todo);
    }

    @PutMapping
    public void update(@RequestBody Todo todo) {
        this.todoItemsRepository.save(todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.todoItemsRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Todo findSingleTodo(@PathVariable("id") String id) {
        Optional<Todo> todo = this.todoItemsRepository.findById(id);
        if(!todo.isPresent()) {
            //throw new NoSuchElementException(("Invalid id: " + id));
            throw new IdNotFoundException("Invalid id: " + id);
        }
        return todo.get();

    }

    @ExceptionHandler
    void handleIllegalArguments(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),
                "illegal argument");
    }

    @ExceptionHandler
    void handleBadRequests(IdNotFoundException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),
                "No such Todo with specified id. " + ex.getMessage());
    }

    public class IdNotFoundException extends NoSuchElementException {
        IdNotFoundException(String exception) {
            super(exception);
        }
    }

}
