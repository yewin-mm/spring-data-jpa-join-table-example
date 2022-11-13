package com.yewin.jpajointableexample.controller;

import com.yewin.jpajointableexample.model.entity.User;
import com.yewin.jpajointableexample.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Ye Win.
 * @CreatedDate 02/07/2019.
 * @Description This class is to implement api endpoint which can manage data from the Outside or to the Outside as response.
 */

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService service;

    @GetMapping("/getAllUser")
    public ResponseEntity getAllUser() {

        logger.info("==================== Start get all user method!!! ====================");
        ResponseEntity responseEntity;
        try {

            List<User> userList = service.getAllUser();
            responseEntity = new ResponseEntity<>(userList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong. Please contact to your administrator.!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End get all user method!!! ====================");

        return responseEntity;

    }

    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String address) {

        logger.info("==================== Start save user method!!! ====================");
        logger.info("Request Param - username: {}, password: {}, phone: {}, adderss: {}", username, password, phone, address);
        ResponseEntity responseEntity;

        try {

            /**
             * here we can add validation condition for require fields before calling to service class or before saving into db.
             * eg. if(username==null || username.trim().equals("")) return new ResponseEntity("Please type username!!!", HttpStatus.NOT_FOUND);
             * eg. if(password==null ....) etc... for others fields.
             * But here, I just show sample project and so, I don't add any validation logic.
             */
            responseEntity = service.saveUser(username, password, phone, address);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong. Please contact to your administrator.!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End save user method!!! ====================");

        return responseEntity;

    }

    @PostMapping("/changeRole")
    public ResponseEntity changeRole(@RequestParam Long userId, @RequestParam Integer roleId) {

        logger.info("==================== Start change role method!!! ====================");
        logger.info("Request Param - role id: {}", roleId);
        ResponseEntity responseEntity;

        try {

            /**
             * Before calling service class, we can validate input with condition like above Save User Api (eg. if userId == null -> return error ..etc ) something like that.
             * here, I just go without validation, because this is simple project.
             */
            responseEntity = service.changeRole(userId, roleId);

        } catch (InvalidDataAccessApiUsageException dae) { // to catch role id is not valid (not in role table) error.
            logger.error(dae.getMessage());
            String errMsg = "Input Role Id: " + roleId + " is not present in role table. Please insert Role Id: " + roleId + " in Role table first or type valid Role Id.!!!";
            responseEntity = new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong. Please contact to your administrator.!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End change role method!!! ====================");

        return responseEntity;

    }

}
