package dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pojo.clas;

import java.util.List;

@Repository("clasDao")
@Mapper
public interface clasDao {
    public boolean addClas(clas su);
    public List<clas> selectedAllClas();
    public boolean deleteClas(int id);
    public boolean upDataClas(clas c);
}
