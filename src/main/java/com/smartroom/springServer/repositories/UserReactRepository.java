package com.smartroom.springServer.repositories;


import com.smartroom.springServer.documents.User;
import com.smartroom.springServer.dtos.UserMinimumDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserReactRepository extends ReactiveSortingRepository<User, Long> {

    Mono<User> findByMobile(String mobile);

    @Query("select id, email, username from User a where a.accountId = ?1")
    Flux<UserMinimumDto> findAllUsers();

    Flux<User> findByMobileOrUsernameOrDniOrAddress(String mobile, String username, String dni, String address);
}
