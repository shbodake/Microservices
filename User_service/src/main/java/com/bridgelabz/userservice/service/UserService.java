package com.bridgelabz.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.userservice.VO.Department;
import com.bridgelabz.userservice.VO.ResponseTemplateVO;
import com.bridgelabz.userservice.entity.User;
import com.bridgelabz.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://localhost:9005/departments/" + user.getDepartmentId()
                        ,Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }

    
}
