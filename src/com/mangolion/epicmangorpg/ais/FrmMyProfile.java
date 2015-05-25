package com.mangolion.epicmangorpg.ais;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class FrmMyProfile extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtCardNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMyProfile frame = new FrmMyProfile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmMyProfile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(10, 28, 107, 103);
		contentPane.add(lblIcon);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(127, 28, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(127, 53, 46, 14);
		contentPane.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(127, 78, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblCardNumber = new JLabel("Card number:");
		lblCardNumber.setBounds(127, 103, 66, 14);
		contentPane.add(lblCardNumber);
		
		JButton btnChoosePicture = new JButton("Choose Picture");
		btnChoosePicture.setBounds(54, 128, 119, 23);
		contentPane.add(btnChoosePicture);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(144, 176, 89, 23);
		contentPane.add(btnSave);
		
		txtName = new JTextField();
		txtName.setBounds(198, 28, 152, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(198, 50, 152, 20);
		contentPane.add(txtPhone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(198, 75, 152, 20);
		contentPane.add(txtEmail);
		
		txtCardNumber = new JTextField();
		txtCardNumber.setColumns(10);
		txtCardNumber.setBounds(198, 100, 152, 20);
		contentPane.add(txtCardNumber);
	}
}
