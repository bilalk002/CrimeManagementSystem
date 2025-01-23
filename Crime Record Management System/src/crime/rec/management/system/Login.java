package crime.rec.management.system;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

  JTextField tfusername;
  JButton login, back;
  JPasswordField tfpassword;
  private boolean loginSuccess = false;
  Conn conn = new Conn();

  private static Font loadJostFont() {
    Font font = null;
    try (InputStream is = Login.class.getResourceAsStream("Jost-Regular.ttf")) {
      if (is != null) {
        font = Font.createFont(Font.TRUETYPE_FONT, is);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
      }
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }
    return font;
  }

  Login() {
    getContentPane().setBackground(Color.GRAY);
    setLayout(null);

    

    Font customFont = loadJostFont(); 

    JLabel lblusername = new JLabel("Username");
    if (customFont != null) {
      lblusername.setFont(customFont.deriveFont(20f)); 
    } else {
      lblusername.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
    }
    lblusername.setBounds(200, 20, 100, 30);
    lblusername.setForeground(Color.WHITE); 
    add(lblusername);

    tfusername = new JTextField();
    tfusername.setFont(
      customFont != null
        ? customFont.deriveFont(20f)
        : new Font("Tahoma", Font.PLAIN, 20)
    ); 
    tfusername.setBounds(400, 20, 200, 30);
    add(tfusername);

    JLabel lblpassword = new JLabel("Password");
    if (customFont != null) {
      lblpassword.setFont(customFont.deriveFont(20f)); 
    } else {
      lblpassword.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
    }
    lblpassword.setBounds(200, 70, 100, 30);
    lblpassword.setForeground(Color.WHITE); 
    add(lblpassword);

    tfpassword = new JPasswordField();
    tfpassword.setBounds(400, 70, 200, 30);
    add(tfpassword);

    login = new JButton("LOGIN");
login.setBounds(300, 140, 150, 30);
login.setBackground(Color.BLACK);
login.setForeground(Color.WHITE);


if (customFont != null) {
    login.setFont(customFont.deriveFont(20f)); 
} else {
    login.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
}

login.addActionListener(this);
add(login);


    ImageIcon i1 = new ImageIcon(
      ClassLoader.getSystemResource("icons/img2.jpg")
    );
    Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(5, 5, 1120, 630);
    add(image);

    setSize(1000, 300);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == login) {
      final String username = tfusername.getText();
      final String password = new String(tfpassword.getPassword());

      login.setEnabled(false); 

      LoginThread loginThread = new LoginThread(username, password); 
      loginThread.start(); 
    }
  }

  
  class LoginThread extends Thread {

    private final String username;
    private final String password;

    public LoginThread(String username, String password) {
      this.username = username;
      this.password = password;
    }

    @Override
    public void run() {
      Conn connectionInstance = new Conn();
      try (
        Connection c = connectionInstance.c;
        PreparedStatement pstmt = c.prepareStatement(
          "SELECT * FROM register WHERE username = ? AND password = ?"
        )
      ) {
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();

        loginSuccess = rs.next();
      } catch (SQLException e) {
        e.printStackTrace();
        loginSuccess = false;
      } finally {
        SwingUtilities.invokeLater(() -> {
          login.setEnabled(true); 

          if (loginSuccess) {
            setVisible(false); 
            new Home(); 
          } else {
            JOptionPane.showMessageDialog(
              Login.this,
              "Invalid username or password"
            );
          }
        });
      }
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Login());
  }
}
