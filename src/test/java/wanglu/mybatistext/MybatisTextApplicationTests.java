package wanglu.mybatistext;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wanglu.mybatistext.entity.User;
import wanglu.mybatistext.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisTextApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    @Test
    public void addUser(){
        User user = new User();
        user.setName("很好");
        user.setAge(30);
        user.setEmail("wanglu@yahoo.co.jp");

        int i = userMapper.insert(user);
        System.out.println(i);
    }
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(1319544454096379905L);
        user.setAge(60);
        int i = userMapper.updateById(user);
        System.out.println(user);
        System.out.println(i);
    }
    @Test
    public void textLocker(){
        //根据ID查询数据
        User user = userMapper.selectById(1319663034112757761L);
        user.setAge(200);
        userMapper.updateById(user);

    }
    @Test
    public void testSelectDemo1(){
        List<Long> ids = new ArrayList<>();
        ids.add(1l);
        ids.add(2l);
        ids.add(3l);

        List<User> users = userMapper.selectBatchIds(ids);
        System.out.println(users);
    }
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Jone");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }
    @Test
    public void testPage(){
        //1创建对象
        //2传入两个参数；当前页和每页显示记录数
        Page<User> page = new Page<>(1,3);
        //调用mp分页查询的方法
        userMapper.selectPage(page, null);
        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页数据记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数

        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.hasPrevious());//是否有上一页
    }
}
