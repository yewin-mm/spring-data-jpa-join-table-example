package com.yewin.jpajointableexample.service;

import com.yewin.jpajointableexample.model.entity.Role;
import com.yewin.jpajointableexample.model.entity.User;
import com.yewin.jpajointableexample.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Ye Win.
 * @CreatedDate 02/07/2019.
 * @Description This class is to implement business logic.
 */

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser(){

        List<User> userList = userRepository.findAll();
        logger.info("Database Response size: {}",userList.size());
        return userList;

    }


    public ResponseEntity saveUser(String name, String password, String phone, String address){

        ResponseEntity responseEntity;

        User user = new User();
        user.setUserName(name);
        user.setPassword((password)); // we can save into db as not plain text password for security purpose, we can encrypt the password with MD5, SHA256 (fast, but not recommend) or SimplePBKDF2Hasher (PBKDF2) (recommended) algorithm, but here, I just add as plain text because this is simple project.
        user.setPhone(phone); // we can validate phone number length or prefix number before adding to user object. But here I show as sample project and so, I will skip input validation.
        user.setAddress(address);
        Role role = new Role();

        /**
         *  before inserting into user table with role id 2, role table should have data with id 2,
         *  cause it's connected with users table and user table use role id (id) in role table as foreign key.
         *  If don't have data in role table, it will get error. So, please insert some data into role table first.
         *  eg. run query in your db like < insert into role (id, role_name) values (1, 'admin'); insert into role (id, role_name) values (2, 'normal user'); >
         */
        role.setId(2); // here, we can get id (this value) from api as param (if so, need to request in controller class) and here I set manual role id 2 as default for each creating user (save user). We can change role by calling change role api and each role have difference permission and it can control in UI.
        user.setRole(role);

        try {

            user = userRepository.save(user); // save into users table and get return value value from save method to get user id (which id is auto increment <auto generated>).
            String msg = "Successfully save user with id: "+user.getId();
            responseEntity = new ResponseEntity<>(msg, HttpStatus.OK);

        }catch (InvalidDataAccessApiUsageException dae) { // to catch role id is not valid (not in role table) error.
            logger.error(dae.getMessage());
            String errMsg = "Cannot save User because Role Id: " + user.getRole().getId() + " is not present in role table. Please insert Role Id: " + user.getRole().getId() + " in Role table first.!!!";
            responseEntity = new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;

    }


    public ResponseEntity changeRole(Long userId, Integer roleId){

        ResponseEntity responseEntity;
        User user;

        /**
         * we can add findById method in userRepository interface to get specific return value like User return value
         * we can also get specific return value by name query with @Query annotation and can write sql statement (eg. select * from users where id= {param id} ) in there.
         * but, here I just use build-in findById method which return value is Optional.
         */
        Optional<User> optionalUser = userRepository.findById(userId); // to get current (existing) user data from database with user id which can getting from api by parameter.

        // check findById is getting data or not, if not, we need to request valid user id again which existed in db or we need to check in db for at least one user data is existed or not. if not existed any user data, we need to call save user Api first.
        if(!optionalUser.isPresent()) {

            String errMsg = "Input User Id: " + userId + " is wrong. Please type valid User Id.!!!";
            responseEntity = new ResponseEntity(errMsg, HttpStatus.NOT_FOUND);
            logger.warn(errMsg);
            return responseEntity; // return input id invalid error response.

        }else {
            user = optionalUser.get(); // get user object from Optional <User> object.
        }

        // check role id from database and role id from input (by parameter) are equal or not.
        // if equal, we don't need to update into database and it can save time and get good performance than saving same value in db.
        // here I use .equal method when comparing two integers, because it's not primitive type (int) and it's Wrapper class (non primitive) (Integer).
        if(user.getRole().getId().equals(roleId)){
            String errMsg = "Input Role Id: " + roleId + " is already updated in database for User Id: " + userId +".!!!";
            logger.warn(errMsg);
            responseEntity = new ResponseEntity(errMsg, HttpStatus.NOT_FOUND);
            return responseEntity; // return input id invalid error response.
        }

        Role role = new Role();
        role.setId(roleId); // set roleId which is getting from api by parameter.
        user.setRole(role); // update role into user which role is getting from api by parameter. And all rest value in user are same with existing (same with database data, because we retrieve data from db in above findById method and use that response object from db) and So, we just change only role id.

        userRepository.save(user); // save into db and all existing user data are same and just only role id was changed by above code.

        String msg = "Successfully updated Role Id: " + user.getRole().getId() + " for User Id: "+ user.getId() + " .!!!";
        responseEntity = new ResponseEntity(msg, HttpStatus.OK);

        return responseEntity;

    }

}
