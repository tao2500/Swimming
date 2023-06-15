package dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pojo.activity;
import pojo.adminis;

import java.util.List;

@Repository("activityDao")
@Mapper
public interface activityDao {
    public boolean addActivity(activity su);
    public List<activity> selectedAll();
    public boolean deleteActivity(int id);
    public boolean upData(activity a);
}
