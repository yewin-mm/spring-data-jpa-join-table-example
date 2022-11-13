package com.yewin.jpajointableexample.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
// if not define, it still ok. But you can't use the table name with user and it can conflict (got error while running) with table which already in postgres db.

//@Getter
//@Setter
//@ToString
// We can use @Data instead of @Getter, @Setter and @ToString. Because @Data include those features.
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // the id will auto generate and so, no need to put id when saving into database.
    private Long id;

    @Column(name = "user_name") // if not define, it still ok. Column will auto create.
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;


    /**
     * @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     */
    // Here we can add Cascade in join table,
    // Cascade Type All is need to avoid Object references an unsaved transient error when saving data into db and that data don't have in parent join table
    // eg. if we added role id 3 in user table and role table don't have id 3, So, it will got the error.
    // By adding Cascade type to All, it will auto add id 3 in role table even there is no match Id value in role table.
    // but here I don't add Cascade because I want to return error like user input role id is not valid

    @ManyToOne(fetch = FetchType.LAZY)
    // many user can have one role and use fetch type to lazy to avoid EAGER loading and to get good performance
    @JoinColumn(name = "role_id")
    private Role role;

}
