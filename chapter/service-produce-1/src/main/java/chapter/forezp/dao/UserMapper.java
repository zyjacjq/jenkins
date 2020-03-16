package chapter.forezp.dao;

import chapter.forezp.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by JF on 2019/9/4.
 */
@Repository
public interface UserMapper {
    public List<User> getAllUser();

    public User getUserByUsername(@Param("username") String username);

    public void getresult(Map map);
    public List resultlist();
}
