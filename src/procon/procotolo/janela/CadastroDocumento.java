package procon.procotolo.janela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.SystemColor;

public class CadastroDocumento extends JFrame {

	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfData;
	private JTextField tfTipo;
	private JTextField tfRef;
	private JTable tbCadastro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDocumento frame = new CadastroDocumento();
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
	public CadastroDocumento() {
		setTitle("Cadastro de Documento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 529);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		lblData.setBounds(10, 36, 46, 14);
		contentPane.add(lblData);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		lblTipo.setBounds(10, 61, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblReferncia = new JLabel("Refer\u00EAncia");
		lblReferncia.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		lblReferncia.setBounds(10, 86, 75, 14);
		contentPane.add(lblReferncia);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		lblDescrio.setBounds(10, 111, 75, 14);
		contentPane.add(lblDescrio);
		
		JLabel lblOrigem = new JLabel("Origem");
		lblOrigem.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		lblOrigem.setBounds(10, 167, 75, 20);
		contentPane.add(lblOrigem);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		lblDestino.setBounds(10, 198, 75, 14);
		contentPane.add(lblDestino);
		
		tfID = new JTextField();
		tfID.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		tfID.setBounds(90, 9, 86, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfData = new JTextField();
		tfData.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		tfData.setColumns(10);
		tfData.setBounds(90, 34, 86, 20);
		contentPane.add(tfData);
		
		tfTipo = new JTextField();
		tfTipo.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		tfTipo.setColumns(10);
		tfTipo.setBounds(90, 59, 259, 20);
		contentPane.add(tfTipo);
		
		tfRef = new JTextField();
		tfRef.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		tfRef.setColumns(10);
		tfRef.setBounds(90, 84, 502, 20);
		contentPane.add(tfRef);
		
		JComboBox cbOrigem = new JComboBox();
		cbOrigem.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		cbOrigem.setBounds(90, 167, 502, 22);
		contentPane.add(cbOrigem);
		
		JComboBox cbDestino = new JComboBox();
		cbDestino.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		cbDestino.setBounds(90, 198, 502, 22);
		contentPane.add(cbDestino);
		
		JScrollPane spDescricao = new JScrollPane();
		spDescricao.setBounds(90, 111, 502, 45);
		contentPane.add(spDescricao);
		
		JTextArea tfDescricao = new JTextArea();
		tfDescricao.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		spDescricao.setViewportView(tfDescricao);
		tfDescricao.setLineWrap(true);
		
		JScrollPane spTabela = new JScrollPane();
		spTabela.setBounds(10, 231, 582, 221);
		contentPane.add(spTabela);
		
		tbCadastro = new JTable();
		spTabela.setViewportView(tbCadastro);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(503, 460, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(404, 460, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(305, 460, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(206, 460, 89, 23);
		contentPane.add(btnLimpar);
	}
}
