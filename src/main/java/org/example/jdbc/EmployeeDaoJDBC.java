//package org.example.repository;
//
//import org.example.model.Employee;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EmployeeDao {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    static final String DBURL ="jdbc:postgresql://localhost:5431/deals_dev";
//    static final String USER = "admin";
//    static final String PASS = "password";
//
//    public List<Employee> getEmployees(){
//        List<Employee> employees = new ArrayList();
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//                //STEP 2: Open a connection
//            logger.debug("Connecting to database...");
//            conn = DriverManager.getConnection(DBURL, USER, PASS);
//                //STEP 3: Execute a query
//            logger.info("Creating statement..");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT * FROM employees";
//            rs = stmt.executeQuery(sql);
//            logger.info("Convirting data..");
//                //STEP 4: Extract data from result set
//            while(rs.next()) {
//                    //Retrieve by column name
//                Long id  = rs.getLong("id");
//                String name = rs.getString("name");
//                String firstName = rs.getString("first_name");
//                String lastName = rs.getString("last_name");
//                String email = rs.getString("email");
//                String address = rs.getString("address");
//                String hiredDate = rs.getString("hired_date");
//
//                    //Fill the object
//                Employee employee = new Employee();
//                employee.setId(id);
//                employee.setName(name);
//                employee.setFirstName(firstName);
//                employee.setLastName(lastName);
//                employee.setEmail(email);
//                employee.setAddress(address);
//                employee.setHiredDate(hiredDate);
//                employees.add(employee);
//            }
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        finally {
//                //STEP 6: finally block used to close resources
//            try {
//                if(rs != null) rs.close();
//                if(stmt != null) stmt.close();
//                if(conn != null) conn.close();
//            }
//            catch(SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        return employees;
//
//    }
//}
