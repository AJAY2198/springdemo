package com.aj.spring.spring_boot_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aj.spring.spring_boot_project.dao.StudentDao;

@Service
public class StudentService {
	
 @Autowired	
  private StudentDao studentDao;
 
 public void fetchStudet() {
	 studentDao.readStudentFromDB(2);
 }

}
