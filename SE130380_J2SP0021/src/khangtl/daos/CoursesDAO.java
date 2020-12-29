/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import khangtl.db.MyConnection;
import khangtl.dtos.CoursesDTO;

/**
 *
 * @author Peter
 */
public class CoursesDAO implements Serializable {

    Connection conn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;

    ArrayList<CoursesDTO> Courses;

    public ArrayList<CoursesDTO> getCourses() {
        return Courses;
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public void getAllCourses() throws Exception {
        String code, name;
        int credit;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT courseCode, courseName, courseCredit FROM dbo.courses";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                code = rs.getString(1);
                name = rs.getString(2);
                credit = rs.getInt(3);

                CoursesDTO course = new CoursesDTO(code, name, credit);
                if (this.getCourses() == null) {
                    this.Courses = new ArrayList<>();
                }
                this.Courses.add(course);
            }
        } finally {
            closeConnection();
        }
    }

    public boolean insertNewCourse(String code, String name, int credit) throws Exception {
        boolean checkInsert = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "INSERT INTO dbo.courses (courseCode, courseName, courseCredit) VALUES(?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, code);
            preStm.setString(2, name);
            preStm.setInt(3, credit);
            checkInsert = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkInsert;
    }

    public CoursesDTO searchCourseByCode(String search) throws Exception {
        CoursesDTO course = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT courseCode, courseName, courseCredit FROM dbo.courses WHERE courseCode LIKE ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, search);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString(2);
                int credit = rs.getInt(3);

                course = new CoursesDTO(search, name, credit);
            }
        } finally {
            closeConnection();
        }
        return course;
    }
}
