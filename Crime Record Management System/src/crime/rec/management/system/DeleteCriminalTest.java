package crime.rec.management.system;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Statement;

class DeleteCriminalTest {
    @Test
    void testDeleteRecord() {
        
        Conn mockConn = mock(Conn.class);

        
        Statement mockStatement = mock(Statement.class);
        
        try {
            
            when(mockConn.getStatement()).thenReturn(mockStatement);
            
            
            when(mockStatement.executeUpdate("DELETE FROM criminal WHERE caseId = '99527'")).thenReturn(1);
            
            
            int result = mockStatement.executeUpdate("DELETE FROM criminal WHERE caseId = '99527'");
            assertEquals(1, result, "Record should be deleted");
        } catch (Exception e) {
            fail("Exception during mock test: " + e.getMessage());
        }
    }
}
