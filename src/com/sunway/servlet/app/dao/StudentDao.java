package com.sunway.servlet.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunway.servlet.app.dto.Gender;
import com.sunway.servlet.app.dto.Student;
import com.sunway.servlet.app.util.ConnectionUtil;

public class StudentDao {

	public List<Student> findAll() {
		List<Student> students = new ArrayList<>();
		try {
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from student");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getLong("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getByte("age"));
				student.setGender(Gender.getGender(rs.getString("gender")));
				student.setAddress(rs.getString("address"));
				student.setPhoneNo(rs.getString("phoneno"));
				students.add(student);
			}
			ConnectionUtil.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public void save(Student student) {
		try {
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into student values (null, ?, ?, ?, ?, ?)");
			ps.setString(1, student.getName());
			ps.setLong(2, student.getAge());
			ps.setString(3, student.getGender().getName());
			ps.setString(4, student.getAddress());
			ps.setString(5, student.getPhoneNo());
			ps.executeUpdate();
			ConnectionUtil.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
