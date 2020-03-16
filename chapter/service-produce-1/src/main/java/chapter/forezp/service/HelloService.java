package chapter.forezp.service;

import chapter.forezp.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by JF on 2019/9/5.
 */
public interface HelloService {
    public List<User> getAllUser();

    public User getUserByUsername(String username);

    public String getAllUser1(int pageNum,int pageSize);

    public void getresult(Map map);
    public List resultlist();

}
