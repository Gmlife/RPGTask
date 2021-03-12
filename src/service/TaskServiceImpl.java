package service;

import pojo.Task;

import java.util.List;

public interface TaskServiceImpl {
    List<Task> getAllTask();

    Task getTask(int taskId);

    int addTask(Task task);

    int updateTask(Task task);

    int deleteTask(int taskId);
}
