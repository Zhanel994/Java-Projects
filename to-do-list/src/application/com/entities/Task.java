package application.com.entities;

import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String executor;
    private String description;
    private String status;
    private LocalDate creationDate;
    private LocalDate completionDate;

    public Task(int id, String title, String executor, String description, String status, LocalDate creationDate, LocalDate completionDate) {
        this.id = id;
        this.title = title;
        this.executor = executor;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
    }

    public Task(int id, String title, String executor, String description) {
        this.id = id;
        this.title = title;
        this.executor = executor;
        this.description = description;
        this.status = "NEW";
        this.creationDate = LocalDate.now();
        this.completionDate = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}
