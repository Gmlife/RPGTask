package dao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.Task;
@Repository
public interface TaskDao {
    Task getTask(@Param("taskId") int taskId);
    int addTask(Task task);
    int updateTask(Task task);
    int deleteTask(@Param("taskId") int taskId);
}
