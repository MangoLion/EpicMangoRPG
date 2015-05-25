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

public class ThManHinhHienThi extends JFrame{
	public static void main(String[] args) {
		new ThManHinhHienThi().setVisible(true);
	}
	
	private JTextField txtTen;
	private JTextField txtDtdd;
	private JTextField txtHinhAnh;
	public ThManHinhHienThi() {
		getContentPane().setLayout(null);
		
		JLabel lblThongTinLien = new JLabel("Thong tin lien he");
		lblThongTinLien.setBounds(10, 11, 176, 14);
		getContentPane().add(lblThongTinLien);
		
		JLabel lblHoTen = new JLabel("Ho ten:");
		lblHoTen.setBounds(10, 36, 46, 14);
		getContentPane().add(lblHoTen);
		
		JLabel lblDtdd = new JLabel("DTDD:");
		lblDtdd.setBounds(10, 61, 46, 14);
		getContentPane().add(lblDtdd);
		
		final JLabel lblha = new JLabel("Hinh Anh:");
		lblha.setBounds(10, 86, 57, 14);
		getContentPane().add(lblha);
		
		txtTen = new JTextField();
		txtTen.setBounds(66, 33, 191, 20);
		getContentPane().add(txtTen);
		txtTen.setColumns(10);
		
		txtDtdd = new JTextField();
		txtDtdd.setColumns(10);
		txtDtdd.setBounds(66, 58, 191, 20);
		getContentPane().add(txtDtdd);
		
		txtHinhAnh = new JTextField();
		txtHinhAnh.setColumns(10);
		txtHinhAnh.setBounds(66, 83, 191, 20);
		getContentPane().add(txtHinhAnh);
		
		final JLabel lblHinhAnh = new JLabel("");
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setBounds(10, 159, 270, 201);
		getContentPane().add(lblHinhAnh);
		setSize(300, 450);
		
		JButton btnHienThi = new JButton("Hien Thi");
		btnHienThi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon icon = new ImageIcon(txtHinhAnh.getText());
				lblHinhAnh.setIcon(icon);
			}
		});
		btnHienThi.setBounds(97, 114, 89, 23);
		getContentPane().add(btnHienThi);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 148, 276, 14);
		getContentPane().add(separator);

	}
}
