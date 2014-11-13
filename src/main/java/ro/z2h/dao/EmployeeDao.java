package ro.z2h.dao;

import ro.z2h.domain.Employee;
import ro.z2h.utils.ResultSetToPojoConverter;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by liviu.spiroiu on 11/3/14.
 */
public class EmployeeDao {

    public List<Employee> getAllEmployees(){
        List<Employee> ret=new ArrayList<Employee>();
        for(int i=0;i<50;i++){
            Employee employee=new Employee();
            employee.setId((long)i+1);
            employee.setFirstName("First Name"+i);
            employee.setLastName("Last Name"+i);
            employee.setSalary(2000d);
            employee.setPhoneNumber("02122334455");
            employee.setHireDate(new Date());
            employee.setEmail("email_"+i+"@z2h.ro");
            employee.setCommissionPoints(2004d);
            ret.add(employee);
        }

        return ret;
    }

    public Employee getById(Long id){
            Employee employee=new Employee();
            employee.setId((long)id);
            employee.setFirstName("First Name"+id);
            employee.setLastName("Last Name"+id);
            employee.setSalary(2000d);
            employee.setPhoneNumber("02122334455");
            employee.setHireDate(new Date());
            employee.setEmail("email_"+id+"@z2h.ro");
            employee.setCommissionPoints(2004d);

        return employee;
    }

    public ArrayList<Employee> getAllEmployees(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        String selectAllFromTableString = "SELECT employee_id,first_name,last_name,email," +
                "phone_number,hire_date,job_id,salary,commission_pct,manager_id,department_id FROM Employees";
        ResultSet rs = stmt.executeQuery(selectAllFromTableString);
        try {
            return ResultSetToPojoConverter.convertToEmployee(rs, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stmt.close();
        return new ArrayList<Employee>();
    }

    public Employee getEmployeeById(Connection con, Long id) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Employee> employees = new ArrayList<Employee>();

        String selectAllFromTableString = "SELECT employee_id,first_name,last_name,email,phone_number,hire_date,job_id,salary,commission_pct,manager_id,department_id " +
                "FROM Employees WHERE employee_id = " + id;

            stmt = con.prepareStatement(selectAllFromTableString);

            rs = stmt.executeQuery(selectAllFromTableString);

            employees = ResultSetToPojoConverter.convertToEmployee(rs, con);
            stmt.close();

        return employees.size() > 0 ? employees.get(0) : null;
    }
	
	private Connection getConnection(String username, String password) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@demo.teamnet.ro:1521:orcl",
                    username,
                    password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public void saveEmployee(Employee employee, Connection con){
        HashMap<String, String> insertIntoTableEmployees = new HashMap<String, String>();
        String tableName = "employees";

        insertIntoTableEmployees.put("employee_id", employee.getId().toString());
        insertIntoTableEmployees.put("first_name", employee.getFirstName());
        insertIntoTableEmployees.put("last_name", employee.getLastName());
        insertIntoTableEmployees.put("email", employee.getEmail());
        insertIntoTableEmployees.put("phone_number", employee.getPhoneNumber());
        insertIntoTableEmployees.put("hire_date", "TO_DATE('" + employee.getHireDate().toString() + "','yyyy-mm-dd')");
        insertIntoTableEmployees.put("job_id",employee.getJob().getId().toString());
        insertIntoTableEmployees.put("salary", employee.getSalary().toString());
        insertIntoTableEmployees.put("commission_pct", employee.getCommissionPoints().toString());
        insertIntoTableEmployees.put("manager_id", employee.getManager().getId().toString());
        insertIntoTableEmployees.put("department_id", employee.getDepartment().getId().toString());

        PreparedStatement stmt;
        try {

            String createTableString = "INSERT INTO " + tableName + " ( ";
            StringBuilder sqlStatement = new StringBuilder();
            sqlStatement.append(createTableString);
            Integer valuesCount = insertIntoTableEmployees.keySet().size();

            for(String value : insertIntoTableEmployees.keySet()){
                valuesCount --;
                String columnName = value + (valuesCount != 0 ? " , " : " ) ");
                sqlStatement.append(columnName);
            }

            sqlStatement.append(" VALUES ( '");
            valuesCount = insertIntoTableEmployees.keySet().size();

            for(String valueName : insertIntoTableEmployees.keySet()){
                valuesCount --;
                String columnString;
                if (valueName.equals("hire_date")) {
                    columnString = insertIntoTableEmployees.get(valueName) + (valuesCount != 0 ? " , '" : "')");
                }else if (valueName.equals("phone_number")) {
                    columnString = insertIntoTableEmployees.get(valueName) + (valuesCount != 0 ? "' , " : "')");
                }else
                {
                    columnString = insertIntoTableEmployees.get(valueName) + (valuesCount != 0 ? "' , '" : "')");
                }
                sqlStatement.append(columnString);
            }
            System.out.println(sqlStatement.toString());
            stmt = con.prepareStatement(sqlStatement.toString());
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            System.out.println("Inserted into table " + tableName + "...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee, Connection con){
        HashMap<String, String> insertIntoTableEmployees = new HashMap<String, String>();
        String tableName = "employees";

        insertIntoTableEmployees.put("employee_id", employee.getId().toString());
        insertIntoTableEmployees.put("first_name", employee.getFirstName());
        insertIntoTableEmployees.put("last_name", employee.getLastName());
        insertIntoTableEmployees.put("email", employee.getEmail());
        insertIntoTableEmployees.put("phone_number", employee.getPhoneNumber());
        insertIntoTableEmployees.put("hire_date", "TO_DATE('" + employee.getHireDate().toString() + "','yyyy-mm-dd')");
        insertIntoTableEmployees.put("job_id",employee.getJob().getId().toString());
        insertIntoTableEmployees.put("salary", employee.getSalary().toString());
        insertIntoTableEmployees.put("commission_pct", employee.getCommissionPoints().toString());
        insertIntoTableEmployees.put("manager_id", employee.getManager().getId().toString());
        insertIntoTableEmployees.put("department_id", employee.getDepartment().getId().toString());

        PreparedStatement stmt;

        try {

            String updateTableString = "UPDATE " + tableName + " SET ";
            StringBuilder sqlStatement = new StringBuilder();
            sqlStatement.append(updateTableString);
            Integer columnsCount = insertIntoTableEmployees.keySet().size();

            for(String columnName : insertIntoTableEmployees.keySet()){
                columnsCount --;
                String columnString;
                if (columnName.equals("hire_date")) {
                    columnString = columnName + " = " + insertIntoTableEmployees.get(columnName) + (columnsCount != 0 ? " , " : "' ");
                } else {
                    columnString = columnName + " = '" + insertIntoTableEmployees.get(columnName) + (columnsCount != 0 ? "' , " : "' ");
                }
                sqlStatement.append(columnString);
            }

            sqlStatement.append("WHERE employee_id = " + employee.getId());
            stmt = con.prepareStatement(sqlStatement.toString());
            stmt.executeQuery();
            stmt.close();
            System.out.println("Created table " + tableName + " in database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(Employee employee, Connection con){
        PreparedStatement stmt;
        String tableName = "employees";
        try {

            String deleteStatement = "DELETE FROM " + tableName + " WHERE employee_id = " + employee.getId();
            stmt = con.prepareStatement(deleteStatement);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Dropped table " + tableName + " from database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}
