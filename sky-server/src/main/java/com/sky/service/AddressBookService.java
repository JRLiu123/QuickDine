package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

/**
 * ClassName: AddressBookService
 * Package: com.sky.service
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/23 16:26
 * @Version 1.0
 */
public interface AddressBookService {
    List<AddressBook> list(AddressBook addressBook);
    /**
     * add a new address book into db
     * @param addressBook
     * @return
     */
    void save(AddressBook addressBook);

    AddressBook getById(Long id);

    void update(AddressBook addressBook);

    void setDefault(AddressBook addressBook);

    void deleteById(Long id);
}
