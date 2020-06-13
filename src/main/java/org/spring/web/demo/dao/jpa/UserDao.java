package org.spring.web.demo.dao.jpa;

import org.spring.web.demo.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    @Query(value = "select * from t_user where id=(select max(id) from t_user)",nativeQuery = true)
    User maxIdUser();


    List<User> findByIdGreaterThan(Integer id);


    List<User> findByDeptId(Integer id);

    Page<User> findTop10ByUsernameLike(String username, Pageable pageable);
    Page<User> findByUsernameContaining(String username, Pageable pageable);



}
