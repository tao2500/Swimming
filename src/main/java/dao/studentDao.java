package dao;
import pojo.student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pojo.teacher;

import java.util.List;

@Repository("studentDao")
@Mapper
public interface studentDao {
    public teacher selectedStudentById(Integer id);
    public List<student> selectedAllStudent();
    boolean deleteStudent(int id);
    public boolean addStudent(student su);
    boolean upDataStudent(student s);
    boolean upDataPass(student s);
}
