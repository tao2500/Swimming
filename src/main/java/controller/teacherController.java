package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pojo.adminis;
import pojo.signUp;
import pojo.teacher;
import dao.teacherDao;
import response.ResultResponse;
import service.impl.TeacherServiceImpl;
import util.JackJsonUtils;
import util.ListObject;
import util.ResponseUtils;
import util.StatusCode;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@Controller
@MapperScan("dao.teacherDao")
@CrossOrigin
@RequestMapping("/teacher")
public class teacherController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(teacherController.class);

    @Resource
    private teacherDao td;
    @RequestMapping("getAll")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<teacher> list = new ArrayList<teacher>();
        list = td.selectedAll();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/getById")
    public void findById(Integer id, HttpServletRequest request, HttpServletResponse response) {
        teacher inform = td.selectedTeacherById(id);
        List<teacher> list = new ArrayList<teacher>();
        list.add(inform);
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/list")
    public void findAll(@RequestParam("page") int pageNo, int limit, Object idSort, HttpServletResponse response) {
        List<teacher> list = new ArrayList<>();
        list = td.selectedAll();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        listObject.setTotal(100);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/add")
    public void addTeacher(@RequestBody teacher t, HttpServletResponse response) {
        if (t != null) {
            while (!td.addTeacher(t)) {
                t.setId(createId());
            }
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
    }

    public int createId() {
        Random df = new Random();
        int i = df.nextInt(9999999);
        return i;
    }

    @RequestMapping(value = "/delete")
    public void deleteTeacher(@RequestBody teacher t, HttpServletResponse response) {
//        System.out.println("收到的id为：" + t.getId());
        td.deleteTeacher(t.getId());
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除成功"));
    }

    @RequestMapping(value = "/upDate")
    public void upDataTeacher(@RequestBody teacher t, HttpServletResponse response) {
        System.out.println("收到参数："+t.toString());
        td.upDataTeacher(t);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改成功"));
    }
}
