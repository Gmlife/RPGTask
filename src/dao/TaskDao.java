package dao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.Task;

import java.util.List;

@Repository
public interface TaskDao {
    List<Task> getAllTask();
    Task getTask(@Param("taskId") int taskId);
    int addTask(Task task);
    int updateTask(Task task);
    int deleteTask(@Param("taskId") int taskId);
}
