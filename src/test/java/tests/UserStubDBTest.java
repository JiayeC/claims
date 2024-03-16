package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import claims.*;
import claims.models.Advisor;
import claims.models.Customer;
public class UserStubDBTest{
	
	private UserStubDB userStubDB;
	//to initailize the userDB before each test
	@BeforeEach
	public void initialization() {
		userStubDB = new UserStubDB();
	}
	//to test adding an advisor to the userDB and check the infomation could be read
	@Test
		public void testAddAdvisorAndRead() {
		Advisor advisor = new Advisor(1, "JC", "Jaye", "Chen", "jiayec@my.yorku.ca", null);
		userStubDB.addAdvisor(advisor);

		String expected = "ID: 1\n" +
				"First Name: Jaye\n" +
				"Last Name: Chen\n" +
				"Email: jiayec@my.yorku.ca\n";

		assertEquals(expected, UserStubDB.readAllUsers());
	}

	//to test removing an advisor to the userDB and check the infomation is removed
	@Test
	public void testRemoveAdvisorAndRead() {
		Advisor advisor = new Advisor(1,"x","Jaye","Chen","jiayec@my.yorku.ca", null);
		userStubDB.addAdvisor(advisor);
		
		userStubDB.removeAdvisor(advisor);
		
		assertEquals("",UserStubDB.readAllUsers());
	}
	//to test adding a customer to the userDB and check the infomation could be read
	@Test
	public void testAddCustomerAndRead() {
		Customer customer = new Customer(2, "x","JJ","CC","jjcc@my.yorku.ca", "2 keele rd", "1234567890", "M", null, 23) ;
		userStubDB.addCustomer(customer);
		
		String expected = "ID: 2\n"+
				"First Name: JJ\n"+
				"Last Name: CC\n"+
				"Email: jjcc@my.yorku.ca\n"+
				"Phone Number: 1234567890\n"+
				"Sex: M\n"+
				"Age: 23\n";
		
		assertEquals(expected,UserStubDB.readAllUsers());
	}
	//to test removing a customer to the userDB and check the infomation is removed
	@Test
	public void testRemoveCustomerAndRead() {
		Customer customer = new Customer(2, "x","JJ","CC","jjcc@my.yorku.ca", "2 keele rd", "1234567890", "M", null, 23) ;
		userStubDB.addCustomer(customer);
		
		userStubDB.removeCustomer(customer);
		
		assertEquals("",UserStubDB.readAllUsers());
	}
}
