package com.yewin.jpajointableexample.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role") // if not define, it still ok. Table will auto create.

/**
 * @Data -> @Data include getter, setter, toString features.
 * here we don't use @Data cause of to avoid @ToString and So, we need to add @Getter, @Setter properties to get Get and Set features or you can manually add get and set method for each fields eg. public Long getId(), etc.
 * and we need to add @ToString (to print in console) with exclude (remove when print in console) userList (which has reference type User) to avoid stackoverflow error or You can manually add toString method which don't include userList field like below comment code.
 */
@Getter
@Setter
@ToString(exclude = "userList") // to avoid stackoverflow error which printing out infinite data (eg. when we add userList to print -> it will print User entity too because it has reference type User and User will call Role entity to print too (because User entity include Role as field) and it will never end calling each others until reach maximum size.)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // to ignore (hide) hibernate initializer filed in response
public class Role {

    @Id
    private Integer id; // here I don't generate as auto increment sequence because when we delete one row unfortunately, increment will not back sequence and goes next sequence value ever and so, the id will not be sequential. (except we can run query to start sequence from current value, but I recommend not to add as auto increment in here)

    @Column(name = "role_name") // if not define, it still ok. Column will auto create.
    private String roleName;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role") // one role can have many users and use fetch type to lazy to avoid EAGER loading and to get good performance
    private List<User> userList;

    /**
     * instead of @ToString(exclude = "userList"), we can add toString() method as manually like below.
      */
    /*@Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }*/
}
