package service;

import dao.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Task;

import java.util.List;

@Service("taskService")
@Transactional
public class TaskService implements TaskServiceImpl {
    @Autowired
    private TaskDao taskDao;


    @Override
    public List<Task> getAllTask() {
        return taskDao.getAllTask();
    }

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
