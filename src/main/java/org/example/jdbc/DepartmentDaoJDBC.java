//package org.example.jdbc;
//
//import org.example.model.Department;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DepartmentDao {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    static final String DBURL ="jdbc:postgresql://localhost:5431/deals_dev";
//    static final String USER = "admin";
//    static final String PASS = "password";
//
//    public List<Department> getDepartments(){
//        List<Department> departments = new ArrayList();
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            //STEP 2: Open a connection
//            logger.debug("Connecting to database...");
//            conn = DriverManager.getConnection(DBURL, USER, PASS);
//            //STEP 3: Execute a query
//            logger.info("Creating statement..");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT * FROM departments";
//            rs = stmt.executeQuery(sql);
//            logger.info("Convirting data..");
//            //STEP 4: Extract data from result set
//            while(rs.next()) {
//                //Retrieve by column name
//                Long id  = rs.getLong("id");
//                String name = rs.getString("name");
//                String description = rs.getString("description");
//                String location = rs.getString("location");
//                //Fill the object
//                Department department = new Department();
//                department.setId(id);
//                department.setName(name);
//                department.setDescription(description);
//                department.setLocation(location);
//                departments.add(department);
//            }
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        finally {
//            //STEP 6: finally block used to close resources
//            try {
//                if(rs != null) rs.close();
//                if(stmt != null) stmt.close();
//                if(conn != null) conn.close();
//            }
//            catch(SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        return departments;
//
//    }
//    public boolean save(Department department) {
//        Connection conn = null;
//        Statement stmt = null;
//        PreparedStatement ps = null;
//
//        try {
//            conn = DriverManager.getConnection(DBURL, USER, PASS);  //STEP 2: Open a connection
//            stmt = conn.createStatement();  //STEP 3: Execute a query
//
//            String sql = "INSERT INTO departments (id,name,description,location) " +
//                    "VALUES (?,?,?,?)";
//
//            ps = conn.prepareStatement(sql);
//            ps.setLong(1, department.getId());
//            ps.setString(2, department.getName());
//            ps.setString(3, department.getDescription());
//            ps.setString(4, department.getLocation());
//
//            int rowInserted = ps.executeUpdate();
//            if(rowInserted > 0){
//                logger.debug("A new department record was inserted successfully");
//                return true;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            //STEP 6: finally block used to close resources
//            try {
////                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//                if(ps != null) ps.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        return false;
//    }
//
//    public boolean update(String oldDepartmentName, Department department){
//        Connection conn = null;
//        Statement stmt = null;
//        PreparedStatement ps = null;
//
//        try {
//            conn = DriverManager.getConnection(DBURL, USER, PASS);  //STEP 2: Open a connection
//            stmt = conn.createStatement();  //STEP 3: Execute a query
//
//            String sql = "UPDATE departments SET name=?, description=?, location=?, " +
//                    "customers_id=?,WHERE name=?";
//            ps = conn.prepareStatement(sql);
//
//            ps.setLong(1, department.getId());
//            ps.setString(2, department.getName());
//            ps.setString(3, department.getDescription());
//            ps.setString(4, department.getLocation());
//            ps.setString(5, oldDepartmentName);
//
//            int rowUpdated = ps.executeUpdate();
//            if(rowUpdated > 0){
//                logger.debug("An existing department record was updated successfully");
//                return true;
//            }
//        } catch(SQLException e){
////            logger.error("Delete unsucessful!");
//            e.printStackTrace();
//        } finally {
//            //STEP 6: finally block used to close resources
//            try {
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//                if(ps != null) ps.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        return false;
//    }
//
//    public boolean delete(String name) {
//        Connection conn = null;
//        Statement stmt = null;
//        PreparedStatement ps = null;
//
//        try {
//            conn = DriverManager.getConnection(DBURL, USER, PASS);  //STEP 2: Open a connection
//            stmt = conn.createStatement();  //STEP 3: Execute a query
//
//            String sql= "DELETE FROM departments WHERE name = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1,name);
//
//            int rowsDeleted = ps.executeUpdate();
//            if (rowsDeleted > 0){
//                logger.debug("A department was deleted successfully!");
//                return true;
//            }
//
//        }catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//                if(ps!= null) ps.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        return false;
//    }
//}
