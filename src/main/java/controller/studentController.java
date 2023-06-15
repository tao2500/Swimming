package controller;

import dao.studentDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.student;
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
@RequestMapping("/student")
public class studentController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(teacherController.class);

    @Resource
    private studentDao sd;

    //143  150
    @RequestMapping("getAll")
    protected void selectedAllSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<student> list = new ArrayList<student>();
        list = sd.selectedAllStudent();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/add")
    public void addSignUp(String[] account,HttpServletRequest request, HttpServletResponse response) {
        student s = new student();
        s.setId(createId());
        s.setPassword(account[0]);
        s.setCrowd(account[1]);
        s.setGender(account[2]);
        s.setName(account[3]);
        s.setItem(account[4]);
        s.setMember(account[5]);
        s.setOvertime(account[6]);
        s.setTelephone(account[7]);
        s.setClasstype(account[8]);
        if (account!=null){
            while (!sd.addStudent(s)){
                s.setId(createId());
            }
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
        }
   }

    @RequestMapping(value = "/upPass")
    public void upPass(String[] account,HttpServletRequest request, HttpServletResponse response) {
        student s = new student();
        s.setName(account[0]);
        // 实际存储旧密码
        s.setTelephone(account[1]);
        s.setPassword(account[2]);
        if (sd.upDataPass(s)){
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改成功"));
        }else {
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("原账户信息错误"));
        }
    }

    @RequestMapping(value = "/list")
    public void findAll(@RequestParam("page") int pageNo, int limit, Object idSort, HttpServletResponse response) {
        List<student> list = new ArrayList<>();
        list = sd.selectedAllStudent();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        listObject.setTotal(100);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    public int createId(){
        Random df = new Random();
        int i = df.nextInt(9999999);
        return i;
    }

    @RequestMapping(value = "/addTwo")
    public void addStudent(@RequestBody student s, HttpServletResponse response) {
        System.out.println("待插入的对象为：" + s.toString());
        if (s != null) {
            while (!sd.addStudent(s)) {
                s.setId(createId());
            }
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
    }

    @RequestMapping(value = "/delete")
    public void deleteStudent(@RequestBody student t, HttpServletResponse response) {
        sd.deleteStudent(t.getId());
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除成功"));
    }

    @RequestMapping(value = "/upDate")
    public void upDataStudent(@RequestBody student t, HttpServletResponse response) {
        System.out.println("收到参数："+t.toString());
        sd.upDataStudent(t);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改成功"));
    }
}
