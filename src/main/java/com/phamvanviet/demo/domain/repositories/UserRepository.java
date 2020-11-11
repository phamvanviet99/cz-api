package com.phamvanviet.demo.domain.repositories;

import com.phamvanviet.demo.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);



//    @Query("select u from tbl_user u inner join tbl_authen a on u.id = a.user.id where a.token = :token")
//    User findByToken(@Param("token") String accessToken);


}
