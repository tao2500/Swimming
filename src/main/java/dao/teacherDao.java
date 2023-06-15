package dao;

import pojo.teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("teacherDao")
@Mapper
public interface teacherDao {
    public teacher selectedTeacherById(Integer id);
    public List<teacher> selectedAll();
    boolean deleteTeacher(int id);
    boolean addTeacher(teacher s);
    boolean upDataTeacher(teacher s);
}
