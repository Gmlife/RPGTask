package service;

import pojo.Task;

public interface TaskService {
    Task getTask(int taskId);

    int addTask(Task task);

    int updateTask(Task task);

    int deleteTask(int taskId);
}
