package com.smartroom.springServer.repositories;


import com.smartroom.springServer.documents.User;
import com.smartroom.springServer.dtos.UserMinimumDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//在反应式Spring数据中，我们只有ReactiveSortingRepository ，它继承自ReactiveCrudRepository

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByMobile(String mobile);

    @Query("select id, email, username from User a where a.accountId = ?1")
    List<UserMinimumDto> findAllUsers();
}
