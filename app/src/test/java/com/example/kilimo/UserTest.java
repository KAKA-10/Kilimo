package com.example.kilimo;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testGetters() {
        // Arrange
        String first_name = "John";
        String last_name = "Doe";
        String ID_Number = "123456789";
        String farm_number = "Farm123";
        String center = "FarmCenter";
        int phone_number = 1234567890;
        String email = "john.doe@example.com";

        User user = new User(first_name, last_name, ID_Number, farm_number, center, phone_number, email);

        // Act and Assert
        assertEquals(first_name, user.getFirst_name());
        assertEquals(last_name, user.getLast_name());
        assertEquals(ID_Number, user.getID_Number());
        assertEquals(farm_number, user.getFarm_number());
        assertEquals(center, user.getCenter());
        assertEquals(phone_number, user.getPhone_Number());
        assertEquals(email, user.getEmail());
    }
}


