package crime.rec.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @Test
    public void testValidCredentials() {
        String username = "abc";  
        String password = "123";  

        
        assertTrue(checkCredentials(username, password), "Login failed! Credentials should match.");
    }

    public static boolean checkCredentials(String username, String password) {
        String query = "SELECT * FROM register WHERE username = ? AND password = ?";
        try (Conn conn = new Conn();
             Connection connection = conn.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
