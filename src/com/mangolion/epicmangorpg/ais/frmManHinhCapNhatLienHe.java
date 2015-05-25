package com.mangolion.epicmangorpg.ais;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmManHinhCapNhatLienHe extends JFrame{
	public static void main(String[] args) {
		new frmManHinhCapNhatLienHe().setVisible(true);
	}
	
	private JTextField txtTen;
	private JTextField txtDtdd;
	private JTextField txtHinhAnh;
	private JTextField txtTenTim;
	public frmManHinhCapNhatLienHe() {
		getContentPane().setLayout(null);
		
		JLabel lblHoTen = new JLabel("Ho ten:");
		lblHoTen.setBounds(10, 73, 46, 14);
		getContentPane().add(lblHoTen);
		
		JLabel lblDtdd = new JLabel("DTDD:");
		lblDtdd.setBounds(10, 98, 46, 14);
		getContentPane().add(lblDtdd);
		
		final JLabel lblha = new JLabel("Hinh Anh:");
		lblha.setBounds(10, 123, 57, 14);
		getContentPane().add(lblha);
		
		txtTen = new JTextField();
		txtTen.setBounds(66, 70, 191, 20);
		getContentPane().add(txtTen);
		txtTen.setColumns(10);
		
		txtDtdd = new JTextField();
		txtDtdd.setColumns(10);
		txtDtdd.setBounds(66, 95, 191, 20);
		getContentPane().add(txtDtdd);
		
		txtHinhAnh = new JTextField();
		txtHinhAnh.setColumns(10);
		txtHinhAnh.setBounds(66, 120, 154, 20);
		getContentPane().add(txtHinhAnh);
		
		JButton btnBrowse = new JButton("...");
		btnBrowse.setBounds(224, 119, 33, 23);
		getContentPane().add(btnBrowse);
		
		JButton btnThem = new JButton("Them");
		btnThem.setBounds(98, 151, 89, 23);
		getContentPane().add(btnThem);
		
		txtTenTim = new JTextField();
		txtTenTim.setColumns(10);
		txtTenTim.setBounds(66, 11, 191, 20);
		getContentPane().add(txtTenTim);
		
		JLabel label = new JLabel("Ho ten:");
		label.setBounds(10, 14, 46, 14);
		getContentPane().add(label);
		
		JButton btnTimKiem = new JButton("Tim Kiem");
		btnTimKiem.setBounds(98, 36, 89, 23);
		getContentPane().add(btnTimKiem);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 65, 264, 8);
		getContentPane().add(separator);
		setSize(300, 218);

	}
}
