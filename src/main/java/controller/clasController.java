package controller;

import dao.clasDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.clas;
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

@Controller
@MapperScan("dao.clasDao")
@RequestMapping("/clas")
public class clasController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(teacherController.class);

    @Resource
    private clasDao sd;

    //143  150
    @RequestMapping("getAll")
    protected void selectedAllClas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<clas> list = new ArrayList<clas>();
        list = sd.selectedAllClas();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/add")
    public void addClas(String[] account,HttpServletRequest request, HttpServletResponse response) {
        clas s = new clas();
        s.setId(createId());
        s.setCrowd(account[0]);
        s.setType(account[1]);
        s.setStartime(account[2]);
        s.setEndtime(account[3]);
        s.setMessage(account[4]);
        if (account!=null){
            while (!sd.addClas(s)){
                s.setId(createId());
            }
        }

        ResponseUtils.renderJson(response, JackJsonUtils.toJson("处理完毕"));
   }

    @RequestMapping(value = "/addTwo")
    public void addStudent(@RequestBody clas t, HttpServletResponse response) {
        if (t != null) {
            while (!sd.addClas(t)) {
                t.setId(createId());
            }
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
    }

    @RequestMapping(value = "/list")
    public void findAll(@RequestParam("page") int pageNo, int limit, Object idSort, HttpServletResponse response) {
        List<clas> list = new ArrayList<>();
        list = sd.selectedAllClas();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        listObject.setTotal(100);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/delete")
    public void deleteStudent(@RequestBody clas t, HttpServletResponse response) {
        sd.deleteClas(t.getId());
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除成功"));
    }

    @RequestMapping(value = "/upDate")
    public void upDataStudent(@RequestBody clas t, HttpServletResponse response) {
        sd.upDataClas(t);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改成功"));
    }

    public int createId(){
        Random df = new Random();
        int i = df.nextInt(9999999);
        return i;
    }
}
