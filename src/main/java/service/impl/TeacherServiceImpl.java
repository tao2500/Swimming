package service.impl;

import java.util.List;

import dao.teacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.teacher;
import service.TeacherService;

/**
 * 
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Qualifier("teacherDao")
	@Autowired
	private teacherDao td;


	@Override
	@Transactional
	public List<teacher> getAllTeacher() {
		// TODO Auto-generated method stub
		List<teacher> t = td.selectedAll();
		return t;
	}

	@Override
	@Transactional
	public teacher getTeacherById(int id) {
		return td.selectedTeacherById(id);
	}
}
