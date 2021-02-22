package service;

import dao.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Task;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    @Override
    public Task getTask(int taskId) {
        return taskDao.getTask(taskId);
    }

    @Override
    public int addTask(Task task) {
        return taskDao.addTask(task);
    }

    @Override
    public int updateTask(Task task) {
        return taskDao.updateTask(task);
    }

    @Override
    public int deleteTask(int taskId) {
        return taskDao.deleteTask(taskId);
    }
}
