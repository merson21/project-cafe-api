package com.project.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cafe.POJO.Bill;

public interface BillDao extends JpaRepository<Bill, Integer>{

}
