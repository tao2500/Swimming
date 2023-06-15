package service;

import java.util.List;
import pojo.teacher;

/**
 * 
 */
public interface TeacherService {
	public List<teacher> getAllTeacher();
	public teacher getTeacherById(int id);
}
