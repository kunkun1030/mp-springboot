package com.example.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.mysql.jdbc.UpdatableResultSet;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.Log;

import java.sql.Wrapper;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectAll() {
        System.out.println("稳哥在改bug");
        System.out.println("他还在改bug");
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(20);
        user.setEmail("test@bigdata.com");
        user.setName("曹操");
        user.setUserName("caocao");
        user.setPassword("123456");
        int insert = this.userMapper.insert(user);
        System.out.println("result" + insert);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateById() {

        User user = new User();
        user.setId(6L);
        user.setAge(21);
        this.userMapper.updateById(user);
    }

    @Test
    public void update() {
        User user = new User();
        user.setAge(30);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", "5");
        this.userMapper.update(user, queryWrapper);

    }

    @Test
    public void testUpdate2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", "6").set("user_name", "mike");
        this.userMapper.update(null, wrapper);
    }

    @Test
    public void testDeleteById(){

        this.userMapper.deleteById(6L);
    }
    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("age",20);
        map.put("name","李四");
        this.userMapper.deleteByMap(map);
    }
    @Test
    public void testDelete(){
        User user = new User();
        user.setAge(18);
        user.setName("张三");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        this.userMapper.delete(wrapper);
    }
    @Test
    public void testDeleteBatchIds(){
        this.userMapper.deleteBatchIds(Arrays.asList(3L,4L));
    }
    @Test
    public void testSelectById(){
        User user = this.userMapper.selectById(7L);
        System.out.println(user);
    }
    @Test
    public void testBatchlds(){
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(6L, 7L));
        for (User u : users){
            System.out.println(u);
        }
    }
    @Test
    public void testSelectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","坤坤");
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }
    @Test
    public void testSelectCount(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age",20);//年龄大于二十岁
        Integer count = this.userMapper.selectCount(wrapper);
        System.out.println("count="+count);
    }
    @Test
    public void testSelectList(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",23);
        List<User>userList = this.userMapper.selectList(wrapper);
        for (User u : userList){
            System.out.println(u);
        }
    }
    @Test
    public void testSelectPage(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age",18);
        Page<User> page = new Page<>(1,2);
        IPage<User> userIPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("总数为："+userIPage.getTotal());
        List<User>userList = userIPage.getRecords();
        for (User u :userList){
            System.out.println(u);
        }
    }
    @Test
    public void testWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","孙七");
        map.put("age","30");
        map.put("password",null);
        List<User> userList = this.userMapper.selectList(wrapper);
        for (User u :userList){
            System.out.println(u);
        }
    }
}