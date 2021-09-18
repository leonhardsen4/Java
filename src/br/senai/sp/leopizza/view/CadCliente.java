package br.senai.sp.leopizza.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.SystemColor;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import br.senai.sp.leopizza.dao.ConnectionFactory;
import br.senai.sp.leopizza.dao.DAOCliente;
import br.senai.sp.leopizza.modelo.Cliente;
import br.senai.sp.leopizza.tablemodel.ClienteTableModel;
import br.senai.sp.leopizza.util.ImageUtil;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;

public class CadCliente extends JFrame {

	private JPanel contentPane;
	private JTextField tfTelefone;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JTable tbClientes;
	private JTextField tfBuscar;
	private JLabel lbID;
	private JLabel lbPontos;
	private JFormattedTextField tfCEP;
	private JTextPane taEndereco;
	private Cliente cliente;
	private JScrollPane spCliente;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	// vari�vel do tipo DAOCliente para manipular os dados no banco de dados
	private DAOCliente dao;
	// vari�vel do tipo List<Cliente> para montar a tabela
	private List<Cliente> clientes;
	private boolean insertUnico = false;

	/**
	 * Create the frame.
	 */
	
	//Atributo opcional - o construtor pode receber uma String telefone como par�metro
	public CadCliente(String... telefone) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Instancia a dao
		dao = new DAOCliente();
		
		

		setResizable(false);
		setTitle("Cadastro de Cliente");
		setBounds(100, 100, 901, 344);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setFocusTraversalPolicy(null);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFocusable(false);
		lblTelefone.setForeground(Color.DARK_GRAY);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(10, 11, 76, 29);
		contentPane.add(lblTelefone);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFocusable(false);
		lblNome.setForeground(Color.DARK_GRAY);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 43, 56, 29);
		contentPane.add(lblNome);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setFocusable(false);
		lblCep.setForeground(Color.DARK_GRAY);
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCep.setBounds(10, 83, 56, 29);
		contentPane.add(lblCep);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFocusable(false);
		lblEndereo.setForeground(Color.DARK_GRAY);
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereo.setBounds(10, 123, 76, 29);
		contentPane.add(lblEndereo);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFocusable(false);
		lblEmail.setForeground(Color.DARK_GRAY);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(10, 226, 56, 29);
		contentPane.add(lblEmail);

		lbPontos = new JLabel("Pontos: ");
		lbPontos.setFocusable(false);
		lbPontos.setForeground(new Color(0, 128, 0));
		lbPontos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbPontos.setBounds(257, 11, 132, 29);
		contentPane.add(lbPontos);

		lbID = new JLabel("ID: ");
		lbID.setFocusable(false);
		lbID.setForeground(new Color(0, 128, 0));
		lbID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbID.setBounds(171, 11, 76, 29);
		contentPane.add(lbID);

		tfTelefone = new JTextField();
		tfTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Guarda na vari�vel cliente o retorno da busca pelo telefone
					cliente = dao.buscarPorTel(tfTelefone.getText().trim());
					// verifica se � nulo o cliente
					if (cliente == null) {
						// Informa o usu�rio que o cliente n�o foi encontrado
						JOptionPane.showMessageDialog(null, "Cliente n�o encontrado", "Aviso",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// popula o formul�rio com o objeto cliente
						popular();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		tfTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent tecla) {
				// Verifica se n�o � um d�gito (caractere num�rico)
				if (!Character.isDigit(tecla.getKeyChar())) {
					// "consome" o caractere (n�o aceita caractere inv�lido)
					tecla.consume();
				}
				// Verifica se j� tem 11 caracteres na caixa de texto
				if (tfTelefone.getText().length() == 11) {
					tecla.consume();
				}
			}
		});
		tfTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		tfTelefone.setForeground(Color.DARK_GRAY);
		tfTelefone.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tfTelefone.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfTelefone.setBounds(76, 17, 85, 20);
		contentPane.add(tfTelefone);
		tfTelefone.setColumns(10);		

		tfNome = new JTextField();
		tfNome.setForeground(Color.DARK_GRAY);
		tfNome.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfNome.setColumns(10);
		tfNome.setBounds(76, 51, 313, 20);
		contentPane.add(tfNome);

		tfEmail = new JTextField();
		tfEmail.setForeground(Color.DARK_GRAY);
		tfEmail.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfEmail.setColumns(10);
		tfEmail.setBounds(76, 232, 313, 20);
		contentPane.add(tfEmail);

		JScrollPane spEndereco = new JScrollPane();
		spEndereco.setFocusable(false);
		spEndereco.setBorder(new LineBorder(Color.LIGHT_GRAY));
		spEndereco.setBounds(76, 123, 313, 95);
		contentPane.add(spEndereco);

		taEndereco = new JTextPane();
		taEndereco.setForeground(Color.DARK_GRAY);
		spEndereco.setViewportView(taEndereco);
		taEndereco.setBorder(new LineBorder(new Color(192, 192, 192), 0));
		taEndereco.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Set<KeyStroke> strokes = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("pressed TAB")));
		taEndereco.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, strokes);
		strokes = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("shift pressed TAB")));
		taEndereco.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, strokes);

		// M�scara para a caixa formatada de texto
		MaskFormatter mskCep = null;
		try {
			mskCep = new MaskFormatter("#####-###");
			// Retira caractere n�o num�rico da p�gina
			mskCep.setValueContainsLiteralCharacters(false);
			mskCep.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tfCEP = new JFormattedTextField(mskCep);
		tfCEP.setHorizontalAlignment(SwingConstants.CENTER);
		tfCEP.setForeground(Color.DARK_GRAY);
		tfCEP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tfCEP.setBounds(76, 89, 65, 20);
		contentPane.add(tfCEP);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setMnemonic('S');
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verifica se os campos obrigat�rios est�o preenchidos
				if (tfTelefone.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o telefone", "Aviso", JOptionPane.WARNING_MESSAGE);
					// Coloca o foco na caixa de texto
					tfTelefone.requestFocus();
				} else if (tfNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o nome", "Aviso", JOptionPane.WARNING_MESSAGE);
					tfNome.requestFocus();
				} else if (taEndereco.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o endere�o", "Aviso", JOptionPane.WARNING_MESSAGE);
					taEndereco.requestFocus();
				} else if (tfCEP.getValue() == null) {
					JOptionPane.showMessageDialog(null, "Informe o CEP", "Aviso", JOptionPane.WARNING_MESSAGE);
					tfCEP.requestFocus();
				} else {
					// verifica se o objeto � nulo
					if (cliente == null) {
						// Instancia o objeto cliente
						cliente = new Cliente();
						// popula o objeto cliente
						cliente.setTelefone(tfTelefone.getText().trim());
					}
					// popula o objeto cliente
					cliente.setNome(tfNome.getText().trim());
					cliente.setEndereco(taEndereco.getText().trim());
					cliente.setEmail(tfEmail.getText().trim());
					cliente.setCep(tfCEP.getValue().toString());

					try {
						if (cliente.getId() == 0) {

							// Envia o cliente para a dao inserir no banco de dados
							dao.inserir(cliente);
						} else {
							// envia o cliente para a dao atualizar no banco de dados
							dao.atualizar(cliente);
						}

						// recarrega a lista de clientes
						clientes = dao.listar();
						// cria a tabela novamente
						CriarTabela();
						// limpa o formul�rio
						limpar();

					} catch (SQLException erro) {
						erro.printStackTrace();
						JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}

					// limpar();
				}
			}
		});
		btnSalvar.setBorder(null);
		btnSalvar.setForeground(new Color(255, 255, 255));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvar.setBackground(new Color(34, 139, 34));
		btnSalvar.setBounds(298, 263, 91, 32);
		contentPane.add(btnSalvar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verifica se existe um cliente selecionado
				if (cliente == null) {
					// exibe mensagem pedindo para selecionar o cliente
					JOptionPane.showMessageDialog(null, "Selecione um cliente para excluir", "Selecione",
							JOptionPane.WARNING_MESSAGE);
				} else {
					// Caso exista um cliente selecionado...
					// Emite um beep
					java.awt.Toolkit.getDefaultToolkit().beep();
					// exibe uma caixa de confirma��o e guarda o bot�o clicado na vari�vel bot�o
					int botao = JOptionPane.showConfirmDialog(null,
							"Deseja excluir o cliente " + cliente.getNome() + "?", "Confirmar exclus�o",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					// verifica se o bot�o � igual a zero, ou seja, op��o YES
					if (botao == 0) {
						try {
							// chama o m�todo excluir da DAO
							dao.excluir(cliente);
							// recarrega a lista de clientes
							clientes = dao.listar();
							// cria a tabela novamente
							CriarTabela();
							// limpa o formul�rio
							limpar();
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showInternalMessageDialog(null, e1.getMessage(), "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				}
			}
		});
		btnExcluir.setMnemonic('E');
		btnExcluir.setFocusable(false);
		btnExcluir.setBorder(null);
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setBounds(188, 263, 91, 32);
		contentPane.add(btnExcluir);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setMnemonic('L');
		btnLimpar.setFocusable(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBorder(null);
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLimpar.setBackground(new Color(255, 140, 0));
		btnLimpar.setBounds(76, 263, 91, 32);
		contentPane.add(btnLimpar);

		spCliente = new JScrollPane();
		spCliente.setFocusable(false);
		spCliente.setBounds(399, 52, 475, 243);
		contentPane.add(spCliente);

		tbClientes = new JTable();
		tbClientes.setFocusable(false);
		spCliente.setViewportView(tbClientes);

		try {
			// Carregar os clientes atrav�s da DAO
			clientes = dao.listar();
			// Chama o CriarTabela para montar a table
			CriarTabela();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel carregar os clientes", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}

		tfBuscar = new JTextField();
		tfBuscar.setBounds(399, 17, 437, 20);
		contentPane.add(tfBuscar);
		tfBuscar.setColumns(10);

		// Cria um ImageIcon atrav�s da imagem dentro da pasta imagens
		ImageIcon iconeLupa = new ImageIcon(getClass().getResource("/iconeLupa.png"));
		//ImageIcon iconePizza = new ImageIcon(getClass().getResource("/pizza.png"));

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// carrega a lista de clientes
					clientes = dao.listar(tfBuscar.getText().trim());
					// cria a tabela de novo
					CriarTabela();
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setFocusable(false);
		btnBuscar.setBackground(new Color(250, 235, 215));
		btnBuscar.setBorder(null);

		// Define o �cone do bot�o redimensionando o �cone criado anteriormente
		btnBuscar.setBounds(842, 17, 32, 29);
		btnBuscar.setIcon(ImageUtil.redimensiona(iconeLupa, btnBuscar.getWidth(), btnBuscar.getHeight()));
		contentPane.add(btnBuscar);
		
		// verifica se foi passado um telefone no construtor
		if(telefone.length > 0) {
			tfTelefone.setText(telefone[0]);
			tfNome.requestFocus();
			//troca a variavel de controle para verdadeira
			insertUnico = true;

		}

	}

	public void limpar() {
		tfTelefone.setText(null);
		tfNome.setText(null);
		taEndereco.setText(null);
		tfEmail.setText(null);
		tfCEP.setValue(null);
		cliente = null;
		tfTelefone.requestFocus();
		tbClientes.clearSelection();
		tfTelefone.setEditable(true);
		lbID.setText("ID: ");
		lbPontos.setText("Pontos: ");
	}

	// Cria uma tabela e aplica no JTable
	private void CriarTabela() {
		// Cria um tableModel com a lista de clientes
		ClienteTableModel model = new ClienteTableModel(clientes);
		// Aplica o tableModel � tbClientes
		tbClientes.setModel(model);
		// Define que apenas uma linha � selecion�vel
		tbClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Define a largura da coluna nome
		tbClientes.getColumnModel().getColumn(0).setPreferredWidth(300);
		// define o comportamento da tabela ao selecionar objetos nela
		tbClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Verificar se existe uma linha selecionada
				if (tbClientes.getSelectedRow() >= 0) {
					// Pego o cliente na lista e guardo na vari�vel cliente
					cliente = clientes.get(tbClientes.getSelectedRow());
					// Popula o formul�rio com o objeto ciente
					popular();
				}

			}
		});
	}

	/**
	 * popula o formul�rio com as informa��es do objeto cliente
	 */

	private void popular() {
		tfTelefone.setText(cliente.getTelefone());
		tfNome.setText(cliente.getNome());
		tfEmail.setText(cliente.getEmail());
		tfCEP.setValue(cliente.getCep());
		taEndereco.setText(cliente.getEndereco());
		lbID.setText("ID: " + cliente.getId());
		lbPontos.setText("Pontos: " + cliente.getPontos());
		tfTelefone.setEditable(false);
	}

}
