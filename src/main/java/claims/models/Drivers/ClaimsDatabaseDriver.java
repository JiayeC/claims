package claims.models.Drivers;

import java.sql.Statement;
import java.time.LocalDate;

import claims.models.Model;
import claims.models.Customer;
import claims.models.NewUser;
import claims.models.Policy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClaimsDatabaseDriver {
    private Connection conn;
    private static ClaimsDatabaseDriver claimsDatabaseDriver;

    public ClaimsDatabaseDriver() {
        try {
        	Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:Databases/claims.db");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ClaimsDatabaseDriver getInstance() {
        if (claimsDatabaseDriver == null) {
            claimsDatabaseDriver = new ClaimsDatabaseDriver();
        }
        return claimsDatabaseDriver;
    }

    public void addClaim(int claimID, String claimStatus, int atFault, String dateFiled, String accidentTime, String damage, int totalledVehicle, String description, String payInfo, String closureCond, int closed) {
        String sql = "INSERT INTO Claims (ClaimID, ClaimStatus, At_Fault, DateFiled, Accident_Time, Damage, Totalled_Vehicle, Description, PayInfo, ClosureCond, Closed) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
        	pstmt.setInt(1, claimID);
        	pstmt.setString(2, claimStatus);
            pstmt.setInt(3, atFault);
            pstmt.setString(4, dateFiled);
            pstmt.setString(5, accidentTime);
            pstmt.setString(6, damage);
            pstmt.setInt(7, totalledVehicle);
            pstmt.setString(8, description);
            pstmt.setString(9, payInfo);
            pstmt.setString(10, closureCond);
            pstmt.setInt(11, closed);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Claim added successfully.");
            } else {
                System.out.println("Failed to add claim. No rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    //Customer Section
    
    
    
    public void addCustomer(String username, String password, String firstName, String lastName, int age, String sex, String email, String phone, String address, String DOB) {
        String sql = "INSERT INTO Customers (Username, Password, FirstName, LastName, Age, Sex, Email, Phone, Address, DOB) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                     try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
                        pstmt.setString(1, username);
                        pstmt.setString(2, password);
                        pstmt.setString(3, firstName);
                        pstmt.setString(4, lastName);
                        pstmt.setInt(5, age);
                        pstmt.setString(6, sex);
                        pstmt.setString(7, email);
                        pstmt.setString(8, phone);
                        pstmt.setString(9, address);
                        pstmt.setString(10, DOB);
                        int affectedRows = pstmt.executeUpdate();
                        
                        if (affectedRows > 0) {
                            System.out.println("Update successful.");
                        } else {
                            System.out.println("Update failed. No rows affected.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
	}

    public void addAdvisor(String username, String password, String firstName, String lastName, String sex, String email, String phone, String address, String DOB) {
        String sql = "INSERT INTO Advisors (Username, Password, FirstName, LastName, Sex, Email, Phone, Address, DOB) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                     try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
                        pstmt.setString(1, username);
                        pstmt.setString(2, password);
                        pstmt.setString(3, firstName);
                        pstmt.setString(4, lastName);
                        pstmt.setString(5, sex);
                        pstmt.setString(6, email);
                        pstmt.setString(7, phone);
                        pstmt.setString(8, address);
                        pstmt.setString(9, DOB);
                        int affectedRows = pstmt.executeUpdate();
                        
                        if (affectedRows > 0) {
                            System.out.println("Update successful.");
                        } else {
                            System.out.println("Update failed. No rows affected.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
	}
 
    public void addAdmin(String username, String password, String firstName, String lastName, String sex, String email, String phone, String address, String DOB) {
        String sql = "INSERT INTO Admins (Username, Password, FirstName, LastName, Sex, Email, Phone, Address, DOB) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                     try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
                        pstmt.setString(1, username);
                        pstmt.setString(2, password);
                        pstmt.setString(3, firstName);
                        pstmt.setString(4, lastName);
                        pstmt.setString(5, sex);
                        pstmt.setString(6, email);
                        pstmt.setString(7, phone);
                        pstmt.setString(8, address);
                        pstmt.setString(9, DOB);
                        int affectedRows = pstmt.executeUpdate();
                        
                        if (affectedRows > 0) {
                            System.out.println("Update successful.");
                        } else {
                            System.out.println("Update failed. No rows affected.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
	}
    
    public ResultSet searchAllCustomers(){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Customers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet searchCustomerByAdvisorID(int ID) {
        String sql = "SELECT DISTINCT Customers.* " +
                 "FROM Customers " +
                 "JOIN Claims ON Customers.ClientID = Claims.ClientID " +
                 "WHERE Claims.AdvisorID = ?";
        ResultSet resultSet = null;
        try (PreparedStatement pstmt = this.conn.prepareStatement(sql)){
            pstmt.setInt(1, ID);
             resultSet = pstmt.executeQuery();  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public ResultSet getCustomerDetails(String username, String password){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Customers WHERE Username='"+username+"' AND Password='"+password+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public void removeCustomer(int clientID) {
        String sql = "DELETE FROM Customers WHERE ClientID=?";
        try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientID);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Deletion successful.");
            } else {
                System.out.println("Update failed. No rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(String username, String password, String firstName, String lastName, int age, String sex, String email, String phone, String address, int clientId) {
        String sql = "UPDATE Customers SET Username=?, Password=?, FirstName=?, LastName=?, " +
        "Age=?, Sex=?, Email=?, Phone=?, Address=? WHERE ClientID=?";
        try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setInt(5, age);
            pstmt.setString(6, sex);
            pstmt.setString(7, email);
            pstmt.setString(8, phone);
            pstmt.setString(9, address);
            pstmt.setInt(10, clientId);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Update successful.");
            } else {
                System.out.println("Update failed. No rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerClaim(int atFault, String accidentFiled, String accidentTime, String damage, int totalledVehicle, String description) {
        String sql = "INSERT INTO Claims (ClientID, ClaimStatus, At_Fault, DateFiled, Accident_Time, Damage, Totalled_Vehicle, Description, Closed) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Customer customer = Model.getInstance().getCustomer();
        try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
            pstmt.setInt(1, customer.getUserID());
            pstmt.setString(2, "Pending");
            pstmt.setInt(3, atFault);
            pstmt.setString(4, LocalDate.now().toString());
            pstmt.setString(5, accidentFiled+", "+accidentTime);
            pstmt.setString(6, damage);
            pstmt.setInt(7, totalledVehicle);
            pstmt.setString(8, description);
            pstmt.setInt(9, 0);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Update successful.");
            } else {
                System.out.println("Update failed. No rows affected.");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    //Advisor Section

    public void updateAdvisor(String username, String password, String firstName, String lastName, String DOB, String sex, String email, String phone, String address, int advisorId) {
        String sql = "UPDATE Advisors SET Username=?, Password=?, FirstName=?, LastName=?, " +
        "DOB=?, Sex=?, Email=?, Phone=?, Address=? WHERE AdvisorID=?";
        try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, DOB);
            pstmt.setString(6, sex);
            pstmt.setString(7, email);
            pstmt.setString(8, phone);
            pstmt.setString(9, address);
            pstmt.setInt(10, advisorId);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Update successful.");
            } else {
                System.out.println("Update failed. No rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addAdvisor(NewUser user) {

    	String sql = "INSERT INTO Advisors"
				+ "(Username, Password, Firstname, Lastname,Email)\r\n"
				+ "VALUES ('"+user.getUsername()+"', '"+user.getPasswordKey()+"', '"+user.getFirstName()+"', '"+user.getLastName()+"','"
						+user.getEmail()+"')";
		
		try {
			Statement st = conn.createStatement();
			int rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

    public ResultSet getAllAdvisors(){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Advisors");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getAdvisorDetails(String username, String password){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Advisors WHERE Username='"+username+"' AND Password='"+password+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public void removeAdvisor(int advisorID) {
        try {
            Statement statement = this.conn.createStatement();
            statement.executeUpdate("DELETE FROM Advisors WHERE AdvisorID=" + advisorID + ";");
            System.out.println("Advisor with ID " + advisorID + " removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Vehicle Section

    public ResultSet getAllVehicles(){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Vehicles");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public ResultSet getCustomerVehicles(Customer customer){
    	Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Vehicles WHERE ownerID='"+customer.getUserID()+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public void removeVehicle(int vehicleID) {
        try {
            Statement statement = this.conn.createStatement();
            statement.executeUpdate("DELETE FROM Vehicles WHERE VehicleID=" + vehicleID + ";");
            System.out.println("Vehicles with ID " + vehicleID + " removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Claim Section

    public ResultSet getAllClaims(){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Claims");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public ResultSet getClaimDetailsByClient(int clientID){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Claims WHERE ClientID="+clientID+";");  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getClaimDetailsByAdvisor(int advisorID){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Claims WHERE AdvisorID="+advisorID+";");  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public void removeClaim(int claimID) {
        try {
            Statement statement = this.conn.createStatement();
            statement.executeUpdate("DELETE FROM Claims WHERE ClaimID=" + claimID + ";");
            System.out.println("Claim with ID " + claimID + " removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Policy Section

public void addPolicy(Policy policy) {
    	String sql = "INSERT INTO Policies"
				+ "(PolicyNumber, StartDate, EndDate, Premium, Deductible, CoverageType, PolicyStatus, PolicyName)\r\n"
				+ "VALUES ('"+policy.getPolicyNumber()+"', '"+policy.getStartDate()+"', '"+policy.getEndDate()+"', '"+policy.getPremium()+"','"
						+policy.getDeductible()+"', '"+policy.getCoverageType()+"', '"+policy.getPolicyStatus()+"', '"+policy.policyName+"')";
		
		try {
			Statement st = conn.createStatement();
			int rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	
    public ResultSet getAllPolicies(){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Policies");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public ResultSet getPolicyDetails(int policyNumber){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Policies WHERE PolicyNumber='"+policyNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //Admin Section
    
    public void addAdmin(NewUser user) {

    	String sql = "INSERT INTO Admins"
				+ "(Username, Password, Firstname, Lastname,Email)\r\n"
				+ "VALUES ('"+user.getUsername()+"', '"+user.getPasswordKey()+"', '"+user.getFirstName()+"', '"+user.getLastName()+"','"
						+user.getEmail()+"')";
		
		try {
			Statement st = conn.createStatement();
			int rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
    
    public ResultSet getAllAdmins(){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Admins");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public ResultSet getAdminDetails(String username, String password){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Admins WHERE Username='"+username+"' AND Password='"+password+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public ResultSet getAllCustomers() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Customers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public void deleteCustomer(int customerID) {
        String sql = "DELETE FROM Customers WHERE ClientID = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:Databases/claims.db");
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, customerID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer with ID " + customerID + " deleted successfully.");
            } else {
                System.out.println("Customer with ID " + customerID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdvisor(int advisorID) {
        String sql = "DELETE FROM Advisors WHERE AdvisorID = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:Databases/claims.db");
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, advisorID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Advisor with ID " + advisorID + " deleted successfully.");
            } else {
                System.out.println("Advisor with ID " + advisorID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetAdvisorPassword(int userID) {
        String newPassword = "password";
        String sql = "UPDATE Advisors SET Password = ? WHERE AdvisorID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, userID);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Password reset for advisor with ID " + userID + " successful.");
            } else {
                System.out.println("Advisor with ID " + userID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetCustomerPassword(int userID) {
        String newPassword = "password";
        String sql = "UPDATE Customers SET Password = ? WHERE ClientID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, userID);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Password reset for customer with ID " + userID + " successful.");
            } else {
                System.out.println("Customer with ID " + userID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
