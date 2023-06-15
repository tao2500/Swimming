package controller;

import dao.activityDao;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.activity;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@MapperScan("dao.activityDao")
@RequestMapping("/activity")
public class activityController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(teacherController.class);

    @Resource
    private activityDao dd;

    //143  150
    @RequestMapping("getAll")  // 前端接口
    protected void selectedAllActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<activity> list = new ArrayList<activity>();
        list = dd.selectedAll();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }
    @RequestMapping(value = "/list")  // 后端前台接口
    public void findAll(@RequestParam("page") int pageNo, int limit, Object idSort, HttpServletResponse response) {
        List<activity> list = new ArrayList<>();
        list = dd.selectedAll();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        listObject.setTotal(100);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/add")
    public void addSignActivity(String[] account,HttpServletRequest request, HttpServletResponse response) {
        activity s = new activity();
        s.setId(createId());
        s.setName(account[0]);
        s.setMessige(account[1]);
        s.setStartime(account[2]);
        s.setOvertime(account[3]);
        s.setImgsrc(account[4]);
        if (account!=null){
            while (!dd.addActivity(s)){
                s.setId(createId());
            }
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("处理完毕"));
   }

    @RequestMapping(value = "/addTwo")
    public void addActivity(@RequestBody activity t, HttpServletResponse response) {
        if (t != null) {
            while (!dd.addActivity(t)) {
                t.setId(createId());
            }
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
    }

    @RequestMapping(value = "/delete")
    public void deleteActivity(@RequestBody activity t, HttpServletResponse response) {
        dd.deleteActivity(t.getId());
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除成功"));
    }

    @RequestMapping(value = "/upDate")
    public void upDataActivity(@RequestBody activity t, HttpServletResponse response) {
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
