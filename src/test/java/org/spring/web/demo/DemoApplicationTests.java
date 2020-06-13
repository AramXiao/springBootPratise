package org.spring.web.demo;

import org.junit.jupiter.api.Test;
import org.spring.web.demo.dao.jpa.UserDao;
import org.spring.web.demo.model.Books;
import org.spring.web.demo.model.Dept;
import org.spring.web.demo.model.User;
import org.spring.web.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@EnableCaching
class DemoApplicationTests {

    @Autowired
    Books books;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
        System.out.println(books);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("password after encode for 123-->" + encoder.encode("123"));
        System.out.println("password after encode for 123-->" + encoder.encode("123"));
    }


    @Test
    //测试级联操作的不同情况 CascadeType.PERSIST
    /**
     * CascadeType.MERGE 级联更新权限，父类更新时，同时更新(如果不存在数据则新增)子类到数据库
     * CascadeType.PERSIST 级联新增权限， 父类更新时，同时新增子类到数据库，如果数据库已存在数据报异常
     * CascadeType.REFRESH 级联刷新权限
     * CascadeType.ALL 拥有以上所有级联操作权限
     */
    void updateUserJPA(){
        User user = new User();
        user.setId(11); //设置id后则每次都更新id为11的实体
        user.setUsername("小花");
        user.setAddress("广东省");
        user.setAge("18");
        user.setDept(new Dept(11,"运维部"));
        System.out.println(user.toString());
        userDao.save(user);
        System.out.println(user.toString());
    }

    @Test
    //测试级联操作的不同情况 CascadeType.PERSIST
    /**
     * CascadeType.REMOVE 级联删除权限，删除父类同时删除子类数据，如果父类存在多条外键引用子类，则删除不成功
     */
    void deleteUserJPA(){
        User user = new User();
        Optional<User> result = userDao.findById(11);
        user = result.get();
//        System.out.println(user.toString());
        System.out.println(user.getDept().getName());
//        userDao.delete(user);


    }

    //使用JPA基本c查询操作
    @Test
    void testUserJPA(){
        List<User> users = userDao.findByIdGreaterThan(1);
        System.out.println(users);

        //按User类的dept的id进行查询
        users = userDao.findByDeptId(7);
        System.out.println(users);
    }

    //分页查询使用
    @Test
    void testUserPageJPA(){
        String username = "小花";
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable =PageRequest.of(0, 3, sort);
        Page<User> userPage = userDao.findByUsernameContaining(username, pageable);
        System.out.println("page getNumber(当前页码)-->"+ userPage.getNumber());
        System.out.println("page getTotalElements(数据库总共能匹配的数量)-->"+ userPage.getTotalElements());
        System.out.println("page getContent(当前页数据)-->"+ userPage.getContent());
        System.out.println("page getTotalPages(总页数)-->"+ userPage.getTotalPages());
        System.out.println("page getSize(页面大小)-->"+ userPage.getSize());
        for(User user: userPage.getContent()){
            System.out.println(user);
        }

    }

    @Test
    void testRedis(){
        redisService.hello();
    }


}
