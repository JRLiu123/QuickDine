package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "Employee related api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "employee login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("Employees login：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * logout
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("employee logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * Add new employee
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add new employee")
    // In the Spring framework, the @RequestBody annotation is used to map the HTTP request body to a Java object.
    // It tells Spring to read the request body and convert it into a specified Java object.
    public Result save(@RequestBody EmployeeDTO employeeDTO){
        log.info("Add new employee: {}", employeeDTO); // {} is a placeholder, and the employeeDTO data will be dynamically added to it.
        System.out.println("Current thread id: " + Thread.currentThread().getId());
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * Employee Pagination Query
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Employee Pagination Query")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("Employee Pagination Query, and parameters are: {}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Start or Stop the employee account
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Start or Stop the employee account")
    public Result startOrStop(@PathVariable Integer status, Long id){
        log.info("Start or Stop the employee account: {}, {}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

}
