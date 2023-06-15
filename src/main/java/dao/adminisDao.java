package dao;

import pojo.adminis;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("adminisDao")
@Mapper
public interface adminisDao {
    public boolean addAdminis(adminis su);
    public List<adminis> selectedAllAdminis();
}