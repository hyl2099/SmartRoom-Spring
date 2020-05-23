package com.smartroom.springServer.repositories;


import com.smartroom.springServer.documents.User;
import com.smartroom.springServer.dtos.UserMinimumDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//反应式编程:Flux 和 Mono 是 Reactor 中的两个基本概念。
// Flux 表示的是包含 0 到 N 个元素的异步序列;Mono 表示的是包含 0 或者 1 个元素的异步序列
//在反应式Spring数据中，我们只有ReactiveSortingRepository ，它继承自ReactiveCrudRepository

public interface UserRepository extends CrudRepository<User, Long> {
    //Optional用以解决程序中常见的NullPointerException异常问题,也就是说查询到位null不会引发异常。
    //Optional<User> findByEmail(String email);

    User findByEmail(String email);

//    @Query("select id, email, username from User a where a.accountId = ?1")
//    List<User> findAllUsers();

//    User findByEmailOrUsername(User user);

}
