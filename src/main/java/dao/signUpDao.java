package dao;

import pojo.signUp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("signUpDao")
@Mapper
public interface signUpDao {
    public boolean addSignUp(signUp su);
    public List<signUp> selectedAllSignUp();
    public boolean deleteSignUp(int id);
}
