package br.senai.sp.leopizza.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JComboBox;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;


import java.time.LocalDate;
import java.time.Period;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGraficos extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCheckBox chckbxPromocao;
	private JButton btnLimpar;
	private JButton btnGerar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGraficos frame = new TelaGraficos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaGraficos() {
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {

				limpar();

			}
		});

		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setTitle("Tela de Graficos");
		setBounds(100, 100, 902, 580);
		contentPane = new JPanel();

		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEixox = new JLabel("Eixo x");
		lblEixox.setFocusable(false);
		lblEixox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEixox.setBounds(135, 337, 84, 25);
		contentPane.add(lblEixox);

		JLabel lblEixoy = new JLabel("Eixo y");
		lblEixoy.setFocusable(false);
		lblEixoy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEixoy.setBounds(408, 337, 97, 25);
		contentPane.add(lblEixoy);



		btnLimpar = new JButton("Limpar");

		btnLimpar.setMnemonic('L');
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLimpar.setFocusable(false);
		btnLimpar.setBorder(null);
		btnLimpar.setBackground(new Color(255, 140, 0));
		btnLimpar.setBounds(400, 496, 118, 32);
		contentPane.add(btnLimpar);

		btnGerar = new JButton("Gerar Grafico");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Period of = Period.ofDays(5);
				System.out.println(of);
				LocalDate d1 = LocalDate.now();
				LocalDate d2 = d1.plusWeeks(2).plusDays(5);
				Period p = Period.between(d1, d2);
				System.out.println(p);
			}
		});

		btnGerar.setMnemonic('E');
		btnGerar.setForeground(Color.WHITE);
		btnGerar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGerar.setFocusable(false);
		btnGerar.setBorder(null);
		btnGerar.setBackground(Color.RED);
		btnGerar.setBounds(528, 496, 128, 32);
		contentPane.add(btnGerar);

	}

	private void limpar() {

	}

}
