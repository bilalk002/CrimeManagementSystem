package crime.rec.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

public class AddCriminal extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999);

    JTextField tfname, tffname, tfaddress, tfphone, tfcnicNo, tfjailterm, tfctype;
    JDateChooser dcdob;
    JComboBox<String> cbgender;
    JComboBox<Integer> cbcrimes;
    JLabel lblcaseid;
    JButton add, back;

    JButton uploadImage; 
    JLabel imageLabel;

    public AddCriminal() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("ADD CRIMINAL DETAILS");
        heading.setBounds(260, 30, 500, 50);
        heading.setFont(new Font("calibri", Font.BOLD, 42));
        add(heading);

        JLabel labelname = new JLabel("Criminal Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);

        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 30);
        add(tfname);

        JLabel labelfname = new JLabel("Father's Name");
        labelfname.setBounds(450, 150, 150, 30);
        labelfname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(600, 150, 150, 30);
        add(tffname);

        JLabel labeldob = new JLabel("DOB");
        labeldob.setBounds(50, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(200, 200, 150, 30);
        add(dcdob);

        JLabel labelnoofcrimes = new JLabel("No. of Crimes");
        labelnoofcrimes.setBounds(450, 200, 150, 30);
        labelnoofcrimes.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelnoofcrimes);

        List<Integer> crimeNumbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        cbcrimes = createComboBox(crimeNumbers);
        cbcrimes.setBounds(600, 200, 150, 30);
        add(cbcrimes);

        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(50, 250, 150, 30);
        labeladdress.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(450, 250, 150, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);

        JLabel labeljailterm = new JLabel("Jail Term");
        labeljailterm.setBounds(50, 300, 150, 30);
        labeljailterm.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeljailterm);

        tfjailterm = new JTextField();
        tfjailterm.setBounds(200, 300, 150, 30);
        add(tfjailterm);

        JLabel labelgender = new JLabel("Gender");
        labelgender.setBounds(450, 300, 150, 30);
        labelgender.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelgender);

        List<String> genderOptions = List.of("Male", "Female", "Other");
        cbgender = createComboBox(genderOptions);
        cbgender.setBounds(600, 300, 150, 30);
        add(cbgender);

        JLabel labelctype = new JLabel("Criminal Offense Type");
        labelctype.setBounds(50, 350, 150, 30);
        labelctype.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelctype);

        tfctype = new JTextField();
        tfctype.setBounds(200, 350, 150, 30);
        add(tfctype);

        JLabel labelcnicNo = new JLabel("Cnic No");
        labelcnicNo.setBounds(450, 350, 150, 30);
        labelcnicNo.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelcnicNo);

        tfcnicNo = new JTextField();
        tfcnicNo.setBounds(600, 350, 150, 30);
        add(tfcnicNo);

        JLabel labelcaseid = new JLabel("Case ID");
        labelcaseid.setBounds(50, 400, 150, 30);
        labelcaseid.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelcaseid);

        lblcaseid = new JLabel("" + number);
        lblcaseid.setBounds(200, 400, 150, 30);
        lblcaseid.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblcaseid);

        add = new JButton("Save");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Go back");
        back.setBounds(450, 550, 150, 40);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        uploadImage = new JButton("Upload Image");
        uploadImage.setBounds(450, 400, 150, 30);
        uploadImage.addActionListener(this);
        uploadImage.setBackground(Color.BLACK);
        uploadImage.setForeground(Color.WHITE);
        add(uploadImage);

        imageLabel = new JLabel("");
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imageLabel.setBounds(600, 400, 150, 150);
        imageLabel.setFont(new Font("serif", Font.PLAIN, 14));
        add(imageLabel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/img4.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(5, 5, 1120, 630);
        add(image);

        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static <T> JComboBox<T> createComboBox(List<T> items) {
        JComboBox<T> comboBox = new JComboBox<>();
        for (T item : items) {
            comboBox.addItem(item);
        }
        return comboBox;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            add.setEnabled(false); 
            String name = tfname.getText();
            String fname = tffname.getText();
            Date dobDate = dcdob.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dob = (dobDate != null) ? sdf.format(dobDate) : null;
            Integer noofcrimes = (Integer) cbcrimes.getSelectedItem();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String jailterm = tfjailterm.getText();
            String gender = (String) cbgender.getSelectedItem();
            String crimetype = tfctype.getText();
            String cnicNo = tfcnicNo.getText();
            String caseId = lblcaseid.getText();

            new DatabaseThread(name, fname, dob, noofcrimes, address, phone, jailterm, gender, crimetype, cnicNo, caseId).start();
        } else if (ae.getSource() == uploadImage) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String imagePath = selectedFile.getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(imagePath);
                imageLabel.setIcon(imageIcon);
                imageLabel.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
                revalidate();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }

    private class DatabaseThread extends Thread {
        private String name, fname, dob, address, phone, jailterm, gender, crimetype, cnicNo, caseId;
        private Integer noofcrimes;

        public DatabaseThread(String name, String fname, String dob, Integer noofcrimes, String address, String phone, String jailterm, String gender, String crimetype, String cnicNo, String caseId) {
            this.name = name;
            this.fname = fname;
            this.dob = dob;
            this.noofcrimes = noofcrimes;
            this.address = address;
            this.phone = phone;
            this.jailterm = jailterm;
            this.gender = gender;
            this.crimetype = crimetype;
            this.cnicNo = cnicNo;
            this.caseId = caseId;
        }

        @Override
        public void run() {
            synchronized (AddCriminal.class) {
                try (Conn conn = new Conn();
                     PreparedStatement pstmt = conn.getConnection().prepareStatement("INSERT INTO criminal VALUES(?,?,?,?,?,?,?,?,?,?,?)")) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, fname);
                    pstmt.setString(3, dob);
                    pstmt.setInt(4, noofcrimes);
                    pstmt.setString(5, address);
                    pstmt.setString(6, phone);
                    pstmt.setString(7, jailterm);
                    pstmt.setString(8, gender);
                    pstmt.setString(9, crimetype);
                    pstmt.setString(10, cnicNo);
                    pstmt.setString(11, caseId);
                    pstmt.executeUpdate();

                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(AddCriminal.this, "Details added successfully");
                        AddCriminal.this.setVisible(false);
                        new Home();
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(AddCriminal.this, "Error adding details: " + e.getMessage());
                    });
                } finally {
                    SwingUtilities.invokeLater(() -> add.setEnabled(true));
                }
            }
        }
    }

    public static void main(String[] args) {
        new AddCriminal();
    }
}