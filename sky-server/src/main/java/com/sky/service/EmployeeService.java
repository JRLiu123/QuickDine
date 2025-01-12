package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * Employee login
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * Add new employee
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * Employee Pagination Query
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Start or Stop the employee account
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * Get employee info by Id
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * Modify employee info
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
