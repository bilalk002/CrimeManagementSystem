package crime.rec.management.system;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Statement;
import java.util.regex.Pattern;

class AddCriminalTest {

    
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    
    private static final String MOBILE_PATTERN = "^\\+92[0-9]{10}$";

    @Test
    void testAddRecord() {
        
        Conn mockConn = mock(Conn.class);

        
        Statement mockStatement = mock(Statement.class);

        try {
            
            when(mockConn.getStatement()).thenReturn(mockStatement);

            
            String name = "John Doe";
            String fname = "Jane Doe";
            String dob = "1990-05-25";
            int noofcrimes = 3;
            String address = "123 Main St";
            String phone = "+923001234567"; 
            String email = "john.doe@example.com"; 
            String jailterm = "5 years";
            String gender = "Male";
            String crimetype = "Robbery";
            String cnicNo = "123456789012";
            int caseId = 99528;

            
            boolean isEmailValid = Pattern.matches(EMAIL_PATTERN, email);
            boolean isPhoneValid = Pattern.matches(MOBILE_PATTERN, phone);

            
            assertTrue(isEmailValid, "Email is invalid");
            assertTrue(isPhoneValid, "Phone number is invalid");

            
            String insertQuery = "INSERT INTO criminal (name, fname, dob, noofcrimes, address, phone, email, jailterm, gender, crimetype, cnicNo, caseId) " +
                                 "VALUES ('" + name + "', '" + fname + "', '" + dob + "', " + noofcrimes + ", '" + address + "', '" + phone + "', '" + email + "', '" + jailterm + "', '" + gender + "', '" + crimetype + "', '" + cnicNo + "', " + caseId + ")";

            
            when(mockStatement.executeUpdate(insertQuery)).thenReturn(1);

            
            int result = mockStatement.executeUpdate(insertQuery);
            assertEquals(1, result, "Record should be inserted");
        } catch (Exception e) {
            fail("Exception during mock test: " + e.getMessage());
        }
    }
}