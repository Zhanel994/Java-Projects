package application.com.services;

import application.com.entities.Task;
import application.com.repositories.JSONRepository;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TaskService {
    private final JSONRepository repository = JSONRepository.getInstance();
    private final Type type = new TypeToken<ArrayList<Task>>(){}.getType();
    private final String path = "resources/db/tasks.json";

    public List<Task> getAll() throws IOException {
        List<Task> tasks = repository.get(type, path);
        return Optional
                .ofNullable(tasks)
                .orElse(new ArrayList<>());
    }

    public void save(Task task) throws IOException {
        List<Task> tasks = getAll();
        tasks.add(task);

        repository.save(tasks, path);
    }

    public void update(List<Task> updatedTask) throws IOException {
        repository.save(updatedTask, path);
    }

    public Integer getIncrementedId() throws IOException {
        List<Task> tasks = getAll();

        if (tasks.isEmpty()) {
            return 1;
        }

        return tasks.get(tasks.size() - 1).getId() + 1;
    }

    public Task get(Integer id) throws IOException
    {
        List<Task> tasks = getAll();

        Optional<Task> task = tasks
                .stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();

        return task
                .orElseThrow(() -> new IllegalArgumentException("Task with id: " + id + " not found!"));
    }

    public void delete(Integer id) throws IOException {
        List<Task> tasks = getAll();

        tasks.removeIf(it -> Objects.equals(it.getId(), id));
        update(tasks);
    }

    public void complete(Integer id) throws IOException
    {
        List<Task> tasks = getAll();

        for(Task task : tasks) {
            if(Objects.equals(task.getId(), id)) {
                task.setStatus("DONE");
                task.setCompletionDate(LocalDate.now());
                break;
            }
        }
        update(tasks);
    }
}
