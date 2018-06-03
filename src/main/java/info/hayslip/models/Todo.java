package info.hayslip.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "TodoItems")
public class Todo {

    @Id
    private String id;
    private String status;
    private String text;
    private int priority;

    public Todo() {
    }

    public Todo(String status, String text, int priority) {
        this.status = status;
        this.text = text;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
