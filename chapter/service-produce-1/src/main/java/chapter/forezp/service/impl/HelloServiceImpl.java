package chapter.forezp.service.impl;

import chapter.forezp.dao.UserMapper;
import chapter.forezp.entity.User;
import chapter.forezp.service.HelloService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by JF on 2019/9/5.
 */
@Service
public class HelloServiceImpl implements HelloService{

    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getAllUser() {
        PageHelper.startPage(1, 2);
        return userMapper.getAllUser();
    }

    @Override
    public String getAllUser1(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<User> page = new PageInfo<>(userMapper.getAllUser());
        int currentPage = page.getPageNum();
        int prePage = page.getPageNum()-1;
        int nextPage = page.getPageNum()+1;
        int allPage = page.getPages();
        String aPre = "/getUser/"+prePage+"/"+pageSize;
        String aNext = "/getUser/"+nextPage+"/"+pageSize;
        String downHref = "/downloadTemplate";
        String download = "<a href='"+downHref+"'>下载</a>";
        String html = "<span>当前页："+currentPage+"<a  href='"+aPre+"'>上一页</a> <a href='"+aNext+"'>下一页</a> 总页数:"+allPage+" </span>";
        return download+html;
    }

    @Override
    public void getresult(Map map) {
         userMapper.getresult(map);
    }

    @Override
    public List resultlist() {
        return userMapper.resultlist();
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
