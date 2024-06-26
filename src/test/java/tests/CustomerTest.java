package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import claims.*;
import claims.models.Customer;
import claims.models.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        ObservableList<Vehicle> vehicleObservableList = FXCollections.observableArrayList();
        // customer = new Customer(1, "John", "pass123", "John", "Doe", "johndoe@example.com", "123 Main St", "555-1234",
        //         "Male", 30, vehicleObservableList);
    }

    @Test
    void testDefaultConstructor() {
        Customer defaultCustomer = new Customer();
        assertNotNull(defaultCustomer);
        assertTrue(defaultCustomer.getVehicles().isEmpty());
    }

    @Test
    void testConstructorWithParameters() {
        assertEquals(1, customer.getUserID());
        assertEquals("pass123", customer.getPassword());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("johndoe@example.com", customer.getEmail());
        assertEquals("123 Main St", customer.getAddress());
        assertEquals("555-1234", customer.getPhoneNumber());
        assertEquals("Male", customer.getGender());
        assertEquals(30, customer.getAge());
    }

    @Test
    void testSetAddress() {
        String newAddress = "456 Main St";
        customer.setAddress(newAddress);
        assertEquals(newAddress, customer.getAddress());
    }

    @Test
    void testSetPhoneNumber() {
        String newPhoneNumber = "555-6789";
        customer.setPhoneNumber(newPhoneNumber);
        assertEquals(newPhoneNumber, customer.getPhoneNumber());
    }

    @Test
    void testSetgender() {
        String newgender = "Female";
        customer.setGender(newgender);
        assertEquals(newgender, customer.getGender());
    }

    @Test
    void testSetAge() {
        int newAge = 35;
        customer.setAge(newAge);
        assertEquals(newAge, customer.getAge());
    }

    // @Test
    // void testAddVehicle() {
    //     Vehicle vehicle = new Vehicle();
    //     customer.addVehicle(vehicle);
    //     assertNotNull(customer.getVehicles().get());
    //     assertFalse(customer.getVehicles().get().isEmpty());
    //     assertEquals(1, customer.getVehicles().get().size());
    // }

    // @Test
    // void testRemoveVehicle() {
    //     Vehicle vehicle = new Vehicle(); // Assuming default constructor is defined
    //     customer.addVehicle(vehicle);
    //     customer.removeVehicle(vehicle);
    //     assertTrue(customer.getVehicles().isEmpty());
    // }

    @Test
    void testToString() {
        String expectedString = "Customer{" +
                "userID=1" +
                ", passwordKey='pass123'" +
                ", firstName='John'" +
                ", lastName='Doe'" +
                ", email='johndoe@example.com'" +
                ", address='123 Main St'" +
                ", phoneNumber='555-1234'" +
                ", gender='Male'" +
                ", age=30" +
                ", vehicles=\n" +
                '}';
        assertEquals(expectedString, customer.toString().trim());
    }
}
