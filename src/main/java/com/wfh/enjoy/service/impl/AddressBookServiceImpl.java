package com.wfh.enjoy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfh.enjoy.entity.AddressBook;
import com.wfh.enjoy.mapper.AddressBookMapper;
import com.wfh.enjoy.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
