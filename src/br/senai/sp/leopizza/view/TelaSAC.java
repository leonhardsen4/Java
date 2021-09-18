package br.senai.sp.leopizza.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import br.senai.sp.leopizza.dao.DAOCliente;
import br.senai.sp.leopizza.dao.DAOPedido;
import br.senai.sp.leopizza.dao.DAOSac;
import br.senai.sp.leopizza.modelo.Assunto;
import br.senai.sp.leopizza.modelo.Cliente;
import br.senai.sp.leopizza.modelo.ItemPedido;
import br.senai.sp.leopizza.modelo.Manifestacao;
import br.senai.sp.leopizza.modelo.Pedido;
import br.senai.sp.leopizza.modelo.SAC;
import br.senai.sp.leopizza.tablemodel.ItemPedidoTableModel;
import br.senai.sp.leopizza.tablemodel.PedidoTableModel;
import br.senai.sp.leopizza.tablemodel.SACItemsTableModel;
import br.senai.sp.leopizza.tablemodel.SACTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TelaSAC extends JFrame {

	private JPanel contentPane;
	private JTextField tfTelefone;
	private JTextField tfNome;
	private JTextField tfPrazo;
	private JTable tbPedidos;
	private JTable tbItens;
	private JTextField tfStatus;
	private Cliente cliente;
	private DAOPedido daop;
	private DAOCliente daoCliente;
	private DAOSac dao;
	private SAC sac;
	private JTextArea taEndereco;
	private JComboBox combo1;
	private JComboBox combo2;
	private JTextArea taRelato;
	private JTextArea taFeedback;
	private JTextField tfData;
	private JButton Salvar;
	private JButton btCadastrar;
	private JButton btLimpar;
	private JTextField tfFuncionario;
	private SAC protocolo;
	private List<Pedido> pedidos;
	private Pedido pedSelec;
	private List<ItemPedido> itens = new ArrayList<>();
	private List<ItemPedido> itemSelec = new ArrayList<>();

	public TelaSAC() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dao = new DAOSac();
		daoCliente = new DAOCliente();
		daop = new DAOPedido();

		JLabel lbTelefone = new JLabel("Telefone");
		lbTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbTelefone.setBounds(10, 11, 67, 14);
		contentPane.add(lbTelefone);

		JLabel lbNome = new JLabel("Nome");
		lbNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNome.setBounds(192, 11, 44, 14);
		contentPane.add(lbNome);

		JLabel lbEndereco = new JLabel("Endere\u00E7o");
		lbEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbEndereco.setBounds(10, 36, 67, 14);
		contentPane.add(lbEndereco);

		tfTelefone = new JTextField();
		tfTelefone.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					cliente = daoCliente.buscarPorTel(tfTelefone.getText().trim());
					if (cliente == null) {
						int opcao = JOptionPane.showConfirmDialog(null, "Cliente não encontrado. Deseja cadastrá-lo?",
								"Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == 0) {
							CadCliente frame = new CadCliente(tfTelefone.getText());
							frame.setVisible(true);
						}
					} else {
						combo1.setEnabled(true);
						tfNome.setText(cliente.getNome());
						taEndereco.setText(cliente.getEndereco());
						tbPedidos.setVisible(true);
						tbItens.setVisible(true);
						combo2.setVisible(true);

						try {
							pedidos = daop.procurar(cliente.getNome());
							criarTabela();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if (tbPedidos.getSelectedRow() >= 0) {
							pedSelec = pedidos.get(tbPedidos.getSelectedRow());
							daop.listarItem(pedSelec.getNumero());
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		tfTelefone.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
				if (tfTelefone.getText().length() == 11) {
					e.consume();
				}

			}
		});

		tfTelefone.setToolTipText("Digite o telefone e pressione ENTER para buscar");
		tfTelefone.setBounds(94, 8, 88, 20);
		contentPane.add(tfTelefone);
		tfTelefone.setColumns(10);

		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setBounds(238, 8, 319, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		JScrollPane spEndereco = new JScrollPane();
		spEndereco.setBounds(95, 36, 462, 45);
		contentPane.add(spEndereco);

		taEndereco = new JTextArea();
		taEndereco.setEditable(false);
		taEndereco.setLineWrap(true);
		spEndereco.setViewportView(taEndereco);

		combo1 = new JComboBox();
		combo1.setEnabled(false);
		combo1.setModel(new DefaultComboBoxModel(Manifestacao.values()));
		combo1.setBounds(97, 92, 238, 22);
		combo1.setSelectedIndex(-1);
		contentPane.add(combo1);

		JLabel lbManifestacao = new JLabel("Manifesta\u00E7\u00E3o");
		lbManifestacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbManifestacao.setBounds(10, 94, 78, 20);
		contentPane.add(lbManifestacao);

		JLabel lbAssunto = new JLabel("Assunto");
		lbAssunto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbAssunto.setBounds(10, 127, 78, 14);
		contentPane.add(lbAssunto);

		combo2 = new JComboBox();
		combo2.setVisible(false);
		combo2.setModel(new DefaultComboBoxModel(Assunto.values()));
		combo2.setSelectedIndex(-1);
		combo2.setBounds(97, 125, 238, 22);
		contentPane.add(combo2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(94, 158, 241, 67);
		contentPane.add(scrollPane);

		taRelato = new JTextArea();
		taRelato.setLineWrap(true);
		scrollPane.setViewportView(taRelato);
		Set<KeyStroke> strokes1 = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("pressed TAB")));
		taRelato.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, strokes1);
		strokes1 = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("shift pressed TAB")));
		taRelato.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, strokes1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(94, 236, 241, 67);
		contentPane.add(scrollPane_1);

		taFeedback = new JTextArea();
		taFeedback.setLineWrap(true);
		scrollPane_1.setViewportView(taFeedback);
		Set<KeyStroke> strokes2 = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("pressed TAB")));
		taFeedback.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, strokes2);
		strokes2 = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("shift pressed TAB")));
		taFeedback.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, strokes2);

		JLabel lbRelato = new JLabel("Relato");
		lbRelato.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbRelato.setBounds(10, 164, 78, 14);
		contentPane.add(lbRelato);

		JLabel lbFeedback = new JLabel("Feedback");
		lbFeedback.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbFeedback.setBounds(10, 242, 78, 14);
		contentPane.add(lbFeedback);

		JLabel lblData = new JLabel("Data");
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(908, 11, 65, 17);
		contentPane.add(lblData);

		tfData = new JTextField();
		tfData.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfData.setForeground(Color.BLACK);
		tfData.setBorder(null);
		tfData.setHorizontalAlignment(SwingConstants.RIGHT);
		tfData.setEditable(false);
		tfData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		tfData.setColumns(10);
		tfData.setBounds(984, 11, 88, 20);
		contentPane.add(tfData);

		Salvar = new JButton("Salvar");
		Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (sac == null) {
						JOptionPane.showMessageDialog(null, "Não existe manifestação a ser salva", "Sem informações",
								JOptionPane.WARNING_MESSAGE);
					} else {
						dao.atualizar(sac);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Salvar.setMnemonic('S');
		Salvar.setBounds(854, 315, 107, 23);
		contentPane.add(Salvar);

		btCadastrar = new JButton("Cadastrar");
		btCadastrar.setMnemonic('C');
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cliente == null) {
					JOptionPane.showMessageDialog(null, "Informe o cliente para realizar a manifestação",
							"Cliente não informado", JOptionPane.ERROR_MESSAGE);
					tfTelefone.requestFocus();
				} else {
					sac = new SAC();
				}

				sac.setCliente(cliente);
				sac.setManifestacao((Manifestacao) combo1.getSelectedItem());
				sac.setAssunto((Assunto) combo2.getSelectedItem());
				sac.setPedido(pedSelec);
				// sac.setItens(itens.get(tbItens.getSelectedRow()));
				sac.setRelato(taRelato.getText());
				sac.setFeedback(taFeedback.getText());
				sac.setPrazo(tfPrazo.getText());
				sac.setStatus(tfStatus.getText());
				sac.setFuncionario(tfFuncionario.getText());
				try {
					dao.inserir(sac);
					JOptionPane.showMessageDialog(null, "Manifestação cadastrada com sucesso!",
							"Manifestação Cadastrada", JOptionPane.INFORMATION_MESSAGE);

//						String username = "leopizza.pizzaria@gmail.com";
//						String password = "leopizza";
//						
//						SimpleEmail email = new SimpleEmail();
//						email.setHostName("smtp.gmail.com");
//						email.setSmtpPort(465);
//						email.setAuthenticator(new DefaultAuthenticator("username", "password"));
//						email.setSSLOnConnect(true);
//						email.setFrom("leonhardsen4@gmail.com");
//						email.setSubject("TestMail");
//						email.setMsg("This is a test mail ... :-)");
//						email.addTo("leopizza.pizzaria@gmail.com");
//						email.send();

//						email.setDebug(true);
//						email.setAuthenticator(new DefaultAuthenticator(meuEmail, senha));
//						email.setHostName("smtp.gmail.com");
//						//email.setTLS(true);
//						email.setSmtpPort(465);
//						email.setSSLOnConnect(true);
//					
//						
//						try {
//							email.setFrom("leonhardsen4@gmail.com", "Teste");
//							email.setSubject("Teste");
//							email.setMsg("Mensagem testes");
//							email.addTo(meuEmail);
//							email.send();
//
//						} catch (Exception e1) {
//							e1.printStackTrace();
//						}

					limpar();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar a manifestação", "Erro",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				try {
					geraProtocolo(sac);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btCadastrar.setBounds(966, 315, 107, 23);
		contentPane.add(btCadastrar);

		btLimpar = new JButton("Limpar");
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		btLimpar.setMnemonic('L');
		btLimpar.setBounds(736, 315, 107, 23);
		contentPane.add(btLimpar);

		JLabel lbStatus = new JLabel("Prazo de Resposta");
		lbStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbStatus.setBounds(10, 315, 113, 30);
		contentPane.add(lbStatus);

		tfPrazo = new JTextField();
		tfPrazo.setBounds(133, 323, 89, 20);
		contentPane.add(tfPrazo);
		tfPrazo.setColumns(10);

		JLabel lbPedido = new JLabel("Pedidos");
		lbPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lbPedido.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbPedido.setBounds(345, 94, 336, 14);
		contentPane.add(lbPedido);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(345, 121, 336, 182);
		contentPane.add(scrollPane_2);

		tbPedidos = new JTable();
		scrollPane_2.setViewportView(tbPedidos);

		JScrollPane scrollPane_2_1 = new JScrollPane();
		scrollPane_2_1.setBounds(691, 121, 382, 182);
		contentPane.add(scrollPane_2_1);

		tbItens = new JTable();
		scrollPane_2_1.setViewportView(tbItens);

		JLabel lbItem = new JLabel("Itens do Pedido");
		lbItem.setHorizontalAlignment(SwingConstants.CENTER);
		lbItem.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbItem.setBounds(691, 94, 381, 14);
		contentPane.add(lbItem);

		JLabel lbStatus_1 = new JLabel("Status");
		lbStatus_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbStatus_1.setBounds(238, 324, 78, 14);
		contentPane.add(lbStatus_1);

		tfStatus = new JTextField();
		tfStatus.setColumns(10);
		tfStatus.setBounds(292, 323, 142, 20);
		contentPane.add(tfStatus);

		JLabel lbFunc = new JLabel("Funcion\u00E1rio");
		lbFunc.setHorizontalAlignment(SwingConstants.RIGHT);
		lbFunc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbFunc.setBounds(866, 37, 107, 19);
		contentPane.add(lbFunc);

		tfFuncionario = new JTextField();
		tfFuncionario.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfFuncionario.setForeground(Color.BLACK);
		tfFuncionario.setBorder(null);
		tfFuncionario.setHorizontalAlignment(SwingConstants.RIGHT);
		tfFuncionario.setText(TelaPrincipal.func.getCpf());
		tfFuncionario.setEditable(false);
		tfFuncionario.setColumns(10);
		tfFuncionario.setBounds(983, 36, 89, 20);
		contentPane.add(tfFuncionario);
	}

	private void limpar() {
		cliente = null;
		tfTelefone.setText(null);
		tfNome.setText(null);
		taEndereco.setText(null);
		combo1.setSelectedIndex(-1);
		combo2.setSelectedIndex(-1);
		combo1.setEnabled(false);
		combo2.setVisible(false);
		taRelato.setText(null);
		taFeedback.setText(null);
		tfPrazo.setText(null);
		tfStatus.setText(null);
		pedSelec = null;
		tbPedidos.setVisible(false);
		tbItens.setVisible(false);
	}

	private void geraProtocolo(SAC sac) throws IOException {
		FileWriter arq = new FileWriter("Protocolo" + protocolo.getProtocolo() + ".txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.println("Leo Pizza Ltda");
		gravarArq.println("\nProtocolo nº: " + protocolo.getProtocolo());
		gravarArq.println("\nData: " + sac.getData());
		gravarArq.println("Cliente: " + sac.getCliente().getNome());
		gravarArq.println("Telefone: " + sac.getCliente().getTelefone());
		gravarArq.println("Endereço: " + sac.getCliente().getEndereco());
		gravarArq.println("Manifestação: " + sac.getManifestacao().toString());
		gravarArq.println("Assunto: " + sac.getAssunto().toString());
		gravarArq.println("Prazo: " + sac.getPrazo());
		gravarArq.println("Status: " + sac.getStatus());
		gravarArq.println("Status: " + sac.getFuncionario());
		// gravarArq.println("\nFuncionário: " + TelaPrincipal.func.getNome());
		arq.close();
		Runtime.getRuntime().exec("notepad Protocolo" + protocolo.getProtocolo() + ".txt");
	}

	private void criarTabela() {
		SACTableModel modelPedido = new SACTableModel(pedidos);
		tbPedidos.setModel(modelPedido);
		tbPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbPedidos.getColumnModel().getColumn(0).setPreferredWidth(40);
		tbPedidos.getColumnModel().getColumn(1).setPreferredWidth(120);
		tbPedidos.getColumnModel().getColumn(2).setPreferredWidth(40);
		tbPedidos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tbPedidos.getSelectedRow() >= 0) {
					pedSelec = pedidos.get(tbPedidos.getSelectedRow());
					criarTabelaItens();
				} else {
					pedSelec = null;
				}
			}
		});
	}

	private void criarTabelaItens() {
		itens = pedSelec.getItens();
		SACItemsTableModel modelItem = new SACItemsTableModel(itens);
		tbItens.setModel(modelItem);
		tbItens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbItens.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbItens.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbItens.getColumnModel().getColumn(2).setPreferredWidth(80);
		tbItens.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tbItens.getSelectedRow() >= 0 && pedSelec != null) {
					itemSelec.add(itens.get(tbItens.getSelectedRow()));
					System.out.println(itemSelec.get(itemSelec.size() - 1).toString());
					pedSelec = null;
					return;
				} else {
					itemSelec = null;
				}
			}
		});
	}

}
