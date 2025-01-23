package crime.rec.management.system;

import java.sql.*;

public class Conn implements AutoCloseable {

    public Connection c;
    public Statement s;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/crimerecordmanagementsystem", "root", "Rootroot12345");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() throws SQLException {
        synchronized (this) {
            if (s == null || s.isClosed()) {
                s = c.createStatement();
            }
            return s;
        }
    }

    public Connection getConnection() {
        return c;
    }

    @Override
    public void close() throws SQLException {
        synchronized (this) {
            if (s != null) {
                s.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }
}
