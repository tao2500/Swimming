package controller;

import dao.adminisDao;
import dao.studentDao;
import dao.teacherDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.adminis;
import pojo.student;
import pojo.teacher;
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

@Controller
@MapperScan("dao.adminisDao")
@RequestMapping("/adminis")
public class adminisController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(teacherController.class);

    @Resource
    private adminisDao sd;

    @Resource
    private teacherDao td;

    @Resource
    private studentDao std;

    //143  150
    @RequestMapping("getAll")
    protected void selectedAllAdminis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<adminis> list = sd.selectedAllAdminis();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/add")
    public void addSignAdminis(String[] account,HttpServletRequest request, HttpServletResponse response) {
        adminis s = new adminis();
        s.setId(createId());
        s.setPassword(account[0]);
        s.setName(account[1]);
        if (account!=null){
            while (!sd.addAdminis(s)){
                s.setId(createId());
            }
        }

        ResponseUtils.renderJson(response, JackJsonUtils.toJson("处理完毕"));
   }

    @RequestMapping(value = "/Verify")
    public void verifyAdminis(String[] account,HttpServletRequest request, HttpServletResponse response) {
        int state = 202;
        List<adminis> list = sd.selectedAllAdminis();
        if (account!=null){
            for (int l = 0;l<list.size();l++){
                if(list.get(l).getName().equals(account[0]) && list.get(l).getPassword().equals(account[1])){
                    account = null;
                    state = 200;
                    ResponseUtils.renderJson(response, JackJsonUtils.toJson("管理员"));
                    break;
                }
            }
        };
        List<teacher> listt = td.selectedAll();
        if (account!=null){
            for (int l = 0;l<listt.size();l++){
                if(listt.get(l).getName().equals(account[0]) && listt.get(l).getPassword().equals(account[1])){
                    account = null;
                    state = 200;
                    String s = listt.get(l).getName();
                    ResponseUtils.renderJson(response, JackJsonUtils.toJson(s));
                    break;
                }
            }
        };
        List<student> lists = std.selectedAllStudent();
        if (account!=null){
            for (int l = 0;l<lists.size();l++){
                if(lists.get(l).getName().equals(account[0]) && lists.get(l).getPassword().equals(account[1])){
                    state = 200;
                    String s = lists.get(l).getName();
                    ResponseUtils.renderJson(response, JackJsonUtils.toJson(s));
                    break;
                }
            }
        }
        if (state == 202)
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("查无此账号"));
    }

    public int createId(){
        Random df = new Random();
        return df.nextInt(9999999);
    }
}
