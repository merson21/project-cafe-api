package com.project.cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.project.cafe.POJO.Bill;

public interface BillService {

	ResponseEntity<String> generateReport(Map<String, Object> requestMap);

	ResponseEntity<List<Bill>> getBills();

	ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

	ResponseEntity<String> deleteBill(Integer id);

}
