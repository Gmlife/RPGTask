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
    public List<Task> getAllTask(String key) {
        return switch (key) {
            case "date_desc" -> taskDao.getTaskSortByIdDesc();
            case "award" -> taskDao.getTaskSortByAward();
            case "award_desc" -> taskDao.getTaskSortByAwardDesc();
            case "judge" -> taskDao.getJudgeTask();
            default -> taskDao.getTaskSortById();
        };
    }

    @Override
    public List<Task> searchTask(String key) {
        return taskDao.searchTask(key);
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
