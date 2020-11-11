package com.phamvanviet.demo.domain.entities.user;

import com.phamvanviet.demo.domain.entities.BaseEntity;
import com.phamvanviet.demo.domain.entities.profile.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(unique = true, length = 50)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String email;

//    @OneToMany(mappedBy="user")
//    private Set<Rate> rates;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Profile profile;
//
//    @OneToMany(mappedBy="user")
//    private Set<Comment> comments;


//    public User user(){
//        User user =new User();
//        return user;
//    }


//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "authen_id")
//    private Authen authen;
//postman insert onetoone
//https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-one-mapping-example/
}
