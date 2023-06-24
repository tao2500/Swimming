package controller;

import dao.signUpDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.signUp;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@MapperScan("dao.signUpDao")
@RequestMapping("/singUp")
public class signUpController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(teacherController.class);

    @Resource
    private signUpDao sd;

    //143  150
    @RequestMapping("getAll")
    protected void selectedAllSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<signUp> list = new ArrayList<signUp>();
        list = sd.selectedAllSignUp();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/add")
    public void addSignUp(HttpServletRequest request, HttpServletResponse response) {
        signUp s = new signUp();
        Map<String, String[]> m = request.getParameterMap();
        s.setId(createId());
        s.setName(m.get("account[0]")[0]);
        s.setTelephone(m.get("account[1]")[0]);
        s.setCrowd(m.get("account[2]")[0]);
        s.setGender(m.get("account[3]")[0]);
        s.setType(m.get("account[4]")[0]);
        s.setClasstype(m.get("account[5]")[0]);
        if (m.get("account[0]")!=null){
            while (!sd.addSignUp(s)){
                s.setId(createId());
            }
        }

        ResponseUtils.renderJson(response, JackJsonUtils.toJson("处理完毕"));
   }

    @RequestMapping(value = "/list")
    public void findAll(@RequestParam("page") int pageNo, int limit, Object idSort, HttpServletResponse response) {
        List<signUp> list = new ArrayList<>();
        list = sd.selectedAllSignUp();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        listObject.setTotal(100);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/delete")
    public void deleteStudent(@RequestBody signUp t, HttpServletResponse response) {
        sd.deleteSignUp(t.getId());
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除成功"));
    }

    public int createId(){
        Random df = new Random();
        int i = df.nextInt(9999999);
        return i;
    }
}
