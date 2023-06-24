package controller;

import dao.joinactivityDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.joinactivity;
import util.JackJsonUtils;
import util.ListObject;
import util.ResponseUtils;
import util.StatusCode;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@MapperScan("dao.joinactivityDao")
@RequestMapping("/joinactivity")
public class joinactivityController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(teacherController.class);

    @Resource
    private joinactivityDao dd;

    //143  150
    @RequestMapping("getAll")  // 前端接口
    protected void selectedAllJoinActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<joinactivity> list = new ArrayList<joinactivity>();
        list = dd.selectedAll();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }
    @RequestMapping(value = "/list")  // 后端前台接口
    public void findAll(@RequestParam("page") int pageNo, int limit, Object idSort, HttpServletResponse response) {
        List<joinactivity> list = new ArrayList<>();
        list = dd.selectedAll();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        listObject.setTotal(100);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/add")
    public void addSignJoinActivity(HttpServletRequest request, HttpServletResponse response) {
        joinactivity s = new joinactivity();
        Map<String, String[]> m = request.getParameterMap();
        s.setId(createId());
        s.setName(m.get("account[0]")[0]);
        s.setTelephone(m.get("account[1]")[0]);
        s.setItem(m.get("account[2]")[0]);
        s.setOvertime(m.get("account[3]")[0]);
        System.out.println("成功添加"+ s.toString());
        if (m.get("account[0]")!=null){
            while (!dd.addjoinActivity(s)){
                s.setId(createId());
            }
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("处理完毕"));
   }

    @RequestMapping(value = "/addTwo")
    public void addjoinActivity(@RequestBody joinactivity t, HttpServletResponse response) {
        if (t != null) {
            while (!dd.addjoinActivity(t)) {
                t.setId(createId());
            }
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
    }

    @RequestMapping(value = "/delete")
    public void deletejoinActivity(@RequestBody joinactivity t, HttpServletResponse response) {
        dd.deletejoinActivity(t.getId());
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除成功"));
    }

    @RequestMapping(value = "/upDate")
    public void upDatajoinActivity(@RequestBody joinactivity t, HttpServletResponse response) {
        dd.upData(t);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改成功"));
    }

    public int createId(){
        Random df = new Random();
        int i = df.nextInt(999);
        return i;
    }
    //  格林威治时间转北京时间
    public static String getTimestampTimeV17(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date date = sdf.parse(str);
        String string = date.toString();
        return string;
    }
}
