package com.mangolion.epicmangorpg.ais;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmBookingMovieTicket extends JFrame {

	private JPanel contentPane;
	private JTextField txtSeat;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBookingMovieTicket frame = new FrmBookingMovieTicket();
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
	public FrmBookingMovieTicket() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeats = new JLabel("Seat(s):");
		lblSeats.setBounds(10, 11, 46, 14);
		contentPane.add(lblSeats);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 36, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblMovies = new JLabel("Movies:");
		lblMovies.setBounds(10, 61, 46, 14);
		contentPane.add(lblMovies);
		
		txtSeat = new JTextField();
		txtSeat.setBounds(55, 8, 86, 20);
		contentPane.add(txtSeat);
		txtSeat.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(55, 33, 209, 20);
		contentPane.add(txtName);
		
		JComboBox cbbMovies = new JComboBox();
		cbbMovies.setBounds(55, 61, 209, 76);
		contentPane.add(cbbMovies);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBook.setBounds(89, 148, 89, 23);
		contentPane.add(btnBook);
	}
}
