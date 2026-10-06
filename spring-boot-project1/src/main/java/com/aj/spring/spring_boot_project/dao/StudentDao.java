package com.aj.spring.spring_boot_project.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class StudentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void readStudentFromDB(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = this.jdbcTemplate.queryForMap("select * from students where id=?", id);
		
		System.out.println(result);
	}

}
