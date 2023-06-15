package dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pojo.joinactivity;

import java.util.List;

@Repository("joinactivityDao")
@Mapper
public interface joinactivityDao {
    public boolean addjoinActivity(joinactivity su);
    public List<joinactivity> selectedAll();
    public boolean deletejoinActivity(int id);
    public boolean upData(joinactivity a);
}
