package info.hayslip.controllers;

import info.hayslip.interfaces.TodoItemsRepository;
import info.hayslip.models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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
//    @ResponseBody
    public Todo findSingleTodo(@PathVariable("id") String id) {
        Optional<Todo> todo = this.todoItemsRepository.findById(id);
        if(!todo.isPresent()) {
            throw new IllegalArgumentException("no Todos with id: " + id);
        }
        return todo.get();

    }

    @GetMapping("/priority/{priority}")
    public List<Todo> getByPriority(@PathVariable int priority) {
        List<Todo> todos = this.todoItemsRepository.findByPriority(priority);
        if (todos.isEmpty()) {
            throw new IllegalArgumentException("no Todos with priority: " + priority);
        }
        return todos;
    }

    @GetMapping("/priority/{priority}/lower")
    public List<Todo> getLowerPriority(@PathVariable int priority) {
        List<Todo> todos = this.todoItemsRepository.findByPriorityLessThanEqual(priority);
        if (todos.isEmpty()) {
            throw new IllegalArgumentException("no Todos with priority lower than : " + priority);
        }
        return todos;
    }

    @GetMapping("/priority/{priority}/higher")
    public List<Todo> getHigherPriority(@PathVariable int priority) {
        List<Todo> todos = this.todoItemsRepository.findByPriorityGreaterThanEqual(priority);
        if (todos.isEmpty()) {
            throw new IllegalArgumentException("no Todos with priority greater than : " + priority);
        }
        return todos;
    }


    @ExceptionHandler
    void handleIllegalArguments(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),
                "illegal argument. " + ex.getMessage());
    }

    /* Was playing with custom exception handlers, but really only need the one. */
    /*
    @ExceptionHandler
    void handleIdNotFound(IdNotFoundException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),
                "No such Todo with specified id. " + ex.getMessage());
    }

    @ExceptionHandler
    void handlePriorityNotFound(PriorityNotFoundException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),
                "No such Todos with specified priority. " + ex.getMessage());
    }

    public class IdNotFoundException extends NoSuchElementException {
        IdNotFoundException(String exception) {
            super(exception);
        }
    }

    public class PriorityNotFoundException extends NoSuchElementException {
        PriorityNotFoundException(String exception) {
            super(exception);
        }
    }
    */
}
