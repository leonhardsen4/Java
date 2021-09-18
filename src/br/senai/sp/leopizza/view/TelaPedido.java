package br.senai.sp.leopizza.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileView;

import br.senai.sp.leopizza.dao.DAOCliente;
import br.senai.sp.leopizza.dao.DAOPedido;
import br.senai.sp.leopizza.dao.DAOProduto;
import br.senai.sp.leopizza.modelo.Cliente;
import br.senai.sp.leopizza.modelo.FormaPgto;
import br.senai.sp.leopizza.modelo.ItemPedido;
import br.senai.sp.leopizza.modelo.Pedido;
import br.senai.sp.leopizza.modelo.Produto;
import br.senai.sp.leopizza.modelo.TipoProduto;
import br.senai.sp.leopizza.tablemodel.ItemPedidoTableModel;
import br.senai.sp.leopizza.tablemodel.ProdutoTableModel;
import br.senai.sp.leopizza.util.ImageUtil;

import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TelaPedido extends JInternalFrame {

	private JPanel contentPane;
	private JTextField tfNumero;
	private JTextField tfTelefone;
	private JLabel lblNome;
	private JLabel lblEndereo;
	private JTextField tfBuscar;
	private JTable tbProdutos;
	private JTable tbItens;
	private JTextField tfObs;
	private JTextField tfTroco;
	private JTextArea taEndereco;
	private JTextField tfNome;
	private JSpinner spQtd;
	// vari√°vel para o total do pedido
	private JLabel lbTotal;
	private JLabel lblTroco;
	private JComboBox cbFormaPgto;
	private JCheckBox chkRetirada;
	private JLabel lbFoto;
	private JTextField tfTxEntrega;
	// Declara√ß√£o das DAO's
	private DAOProduto daoProduto;
	private DAOCliente daoCliente;
	// Declara√ß√£o da vari√°vel para lista de produtos
	private List<Produto> produtos;

	// Vari√°vel para o produto selecionado na lista
	private Produto prodSelec;
	private Produto auxProdSelec;
	private byte[] imgProduto = null; 
	private JButton btMeia;
	private String descricao = null;

	private ItemPedido item;// cria um novo item do pedido
	// Vari√°vel para o cliente do pedido
	private Cliente cliente;
	private DAOPedido daoPedido;

	// auxiliar para meia pizza
	private String auxMeiaPizza = null;
	private boolean promocaoOK = true;

	// variavel para o pedido que sera gerado
	private Pedido pedido;

	// variavel para lista de pedidos de itens
	private List<ItemPedido> itens = new ArrayList<>();

	// variavel para o tablemodel ItemPedido
	private ItemPedidoTableModel modelItem;

	// variavel para itemSelec
	private ItemPedido itemSelec;
	private JComboBox cbProduto;
	private JButton btBrinde;
	private JButton brPedidos;
	private VerPedidos VerPedidos;
	private JButton btFinaliza;
	private JButton btPromo;
	private JButton btRemove;
	private JButton btAdiciona;
	private int contador = 0;
	private CadCliente cadCliente;

	public TelaPedido() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				limpar();
			}
		});
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);

		// Instancia as DAO's
		daoProduto = new DAOProduto();
		daoCliente = new DAOCliente();
		daoPedido = new DAOPedido();

		setResizable(false);
		setTitle("Pedidos");
		setBounds(100, 100, 1065, 595);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setForeground(Color.BLACK);
		lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNmero.setBounds(10, 11, 73, 20);
		contentPane.add(lblNmero);

		tfNumero = new JTextField();
		tfNumero.setBackground(new Color(250, 235, 215));
		tfNumero.setHorizontalAlignment(SwingConstants.CENTER);
		tfNumero.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfNumero.setEditable(false);
		tfNumero.setColumns(10);
		tfNumero.setBounds(93, 11, 116, 20);
		contentPane.add(tfNumero);

		JLabel lbTelefone = new JLabel("Telefone");
		lbTelefone.setForeground(Color.BLACK);
		lbTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbTelefone.setBounds(219, 11, 73, 20);
		contentPane.add(lbTelefone);

		tfTelefone = new JTextField();
		tfTelefone.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					// Guarda na vari√°vel cliente o retorno da busca pelo telefone
					cliente = daoCliente.buscarPorTel(tfTelefone.getText().trim());
					// verifica se √© nulo o cliente
					if (cliente == null) {
						// Informa o usu√°rio que o cliente n√£o foi encontrado
						int opcao = JOptionPane.showConfirmDialog(null, "Cliente n„o encontrado. Deseja cadastr·-lo?",
								"Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == 0) {
							// Se a op√ß√£o for de cadastrar
							CadCliente frame = new CadCliente(tfTelefone.getText());
							frame.setVisible(true);

						}
					} else {
						// Preenche o formul√°rio com o nome e endere√ßo do cliente
						tfNome.setText(cliente.getNome());
						taEndereco.setText(cliente.getEndereco());
						// Verifica se o cliente possui brinde
						if (cliente.isBrinde()) {
							// Deixa o bot√£o de brinde vis√≠vel
							btBrinde.setVisible(true);
						} else {
							btBrinde.setVisible(false);
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
		tfTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		tfTelefone.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(302, 11, 143, 20);
		contentPane.add(tfTelefone);

		lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.BLACK);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(455, 7, 54, 29);
		contentPane.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBackground(new Color(250, 235, 215));
		tfNome.setEditable(false);
		tfNome.setToolTipText("");
		tfNome.setHorizontalAlignment(SwingConstants.CENTER);
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfNome.setColumns(10);
		tfNome.setBounds(509, 11, 368, 20);
		contentPane.add(tfNome);

		lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setForeground(Color.BLACK);
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereo.setBounds(10, 42, 73, 29);
		contentPane.add(lblEndereo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(93, 42, 784, 45);
		contentPane.add(scrollPane);

		taEndereco = new JTextArea();
		taEndereco.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(taEndereco);

		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setForeground(Color.BLACK);
		lblProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProdutos.setBounds(10, 119, 73, 20);
		contentPane.add(lblProdutos);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 181, 499, 226);
		contentPane.add(scrollPane_1);

		tbProdutos = new JTable();
		tbProdutos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane_1.setViewportView(tbProdutos);
		// Carrega a lista de produtos
		try {
			produtos = daoProduto.listar();
			// Cria a tabela de produtos
			criarTabelaProdutos();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}

		cbProduto = new JComboBox();
		cbProduto.setBackground(new Color(255, 255, 255));
		cbProduto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbProduto.setModel(new DefaultComboBoxModel(TipoProduto.values()));
		cbProduto.setBounds(10, 150, 165, 20);
		cbProduto.setSelectedIndex(-1);
		contentPane.add(cbProduto);

		tfBuscar = new JTextField();
		tfBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Verifica se tem um tipo de produto selecionado
					if (cbProduto.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Selecione o tipo de produto", "Aviso",
								JOptionPane.WARNING_MESSAGE);
					} else {
						// Carrega a lista de produtos com a busca personalizada
						produtos = daoProduto.buscar(tfBuscar.getText(), (TipoProduto) cbProduto.getSelectedItem());
						// Recria a tabela com os produtos
						criarTabelaProdutos();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		tfBuscar.setToolTipText("Digite e pressione ENTER para buscar");
		tfBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		tfBuscar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfBuscar.setColumns(10);
		tfBuscar.setBounds(186, 150, 323, 20);
		contentPane.add(tfBuscar);

		btAdiciona = new JButton(">>");

		// bot√£o acrescentar pizza
		btAdiciona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verifica se existe um produto selecionado
				if (prodSelec == null) {
					JOptionPane.showMessageDialog(null, "Selecione um produto para adicionar ao pedido", "Erro",
							JOptionPane.ERROR_MESSAGE);
					// verifica se foi informada a quantidade
				} else if ((int) spQtd.getValue() <= 0) {
					JOptionPane.showMessageDialog(null, "Informe a quantidade a ser adicionada", "Erro",
							JOptionPane.ERROR_MESSAGE);
					// caso esteja ok a quantidade e produto selecionado...
				} else {
					item = new ItemPedido();
					// atribui o produto selecionado ao item
					item.setProduto(prodSelec);
					// atribui a quantidade ao item
					item.setQtd((int) spQtd.getValue());
					// atribui uma observa√ß√£o ao item
					String observacao = JOptionPane.showInputDialog(null, "ObservaÁ„o do item " + prodSelec.getNome(),
							"ObservaÁ„o", JOptionPane.QUESTION_MESSAGE);
					item.setObservacao(observacao);
					// adiciona o item √† lista de itens
					itens.add(item);
					// avisa ao table model que a lista foi atualizada
					modelItem.fireTableDataChanged();
					// resetar o valor da spQtd
					spQtd.setValue(0);
				
					contadorPizza();
					// zerar a vari√°vel prodSelec
					// prodSelec = null;
					// limpar a sele√ß√£o da tbProdutos
					tbProdutos.clearSelection();
					// Atualiza o total
					atualizaTotal();
					// verifica se deu a promo√ß√£o
					refrigerantePromocao();
				}

			}
		});

		btAdiciona.setBounds(519, 249, 54, 45);
		contentPane.add(btAdiciona);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(583, 181, 458, 226);
		contentPane.add(scrollPane_1_1);

		tbItens = new JTable();
		scrollPane_1_1.setViewportView(tbItens);

		JLabel lblItensDoPedido = new JLabel("Itens do Pedido");
		lblItensDoPedido.setForeground(Color.BLACK);
		lblItensDoPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItensDoPedido.setBounds(583, 148, 136, 20);
		contentPane.add(lblItensDoPedido);

		btRemove = new JButton("<<");
		btRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (itemSelec == null) {
					JOptionPane.showMessageDialog(null, "Selecione um item do pedido para removÍ-lo", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// Verifica se √© brinde
					if (itemSelec.getTotal() == 0) {
						btBrinde.setVisible(true);
						cliente.adicionaPontos(15);
					}
					// Verifica se √© o Dolly
					if (itemSelec.getTotal() == 0.01) {
						btBrinde.setVisible(true);
						btPromo.setVisible(true);
					}

					if (itemSelec.getProduto().getTipo() == TipoProduto.PIZZA_DOCE
							|| itemSelec.getProduto().getTipo() == TipoProduto.PIZZA_SALGADA
							|| itemSelec.getProduto().getTipo() == TipoProduto.BORDA_RECHEADA) {
						contador -= itemSelec.getQtd();
					}

					if (contador < 2) {
						btPromo.setVisible(false);
						promocaoOK = true;
						for (ItemPedido b : itens) {
							if (b.getTotal() == 0.01) {
								itens.remove(b);
							}
						}
					}
					{
						// Remove o item da lista
					}
					itens.remove(itemSelec);
					// Avisa o table model que os dados foram alterados
					modelItem.fireTableDataChanged();
					// Zera a vari√°vel ItemSelec
					itemSelec = null;
					// Limpa a sele√ß√£o da tabela
					tbItens.clearSelection();
					// Atualiza o total
					atualizaTotal();

				}
			}
		});
		btRemove.setBounds(519, 361, 54, 45);
		contentPane.add(btRemove);

		btFinaliza = new JButton("Finaliza");
		btFinaliza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verifica se foi escolhido um cliente
				if (cliente == null) {
					JOptionPane.showMessageDialog(null, "Informe o cliente para realizar o pedido",
							"Cliente n„o informado", JOptionPane.ERROR_MESSAGE);
					tfTelefone.requestFocus();
					return;
				} else {
					if (taEndereco.getText().trim() == null && !chkRetirada.isSelected()) {
						JOptionPane.showMessageDialog(null, "Informe o endereÁo de entrega", "EndereÁo n„o informado",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else if (itens.size() == 0) {
						JOptionPane.showMessageDialog(null, "O pedido deve ter pelo menos um item",
								"Itens do pedido vazio", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						// Instanciar a vari√°vel pedido
						pedido = new Pedido();
						// Setar o cliente no pedido
						pedido.setCliente(cliente);
						// Endere√ßo de entrega
						pedido.setEndEntrega(taEndereco.getText());
						// Taxa de entrega
						pedido.setTxEntrega(Double.parseDouble(tfTxEntrega.getText()));
						// Forma de pagamento
						pedido.setFormaPgto((FormaPgto) cbFormaPgto.getSelectedItem());
						// Observa√ß√£o
						pedido.setObservacao(tfObs.getText());
						// data
						Calendar data = Calendar.getInstance();
						pedido.setData(data);
						// Retirada
						pedido.setRetirada(chkRetirada.isSelected());
						// Verifica se tem troco digitado
						if (!tfTroco.getText().isEmpty()) {
							pedido.setTroco(Double.parseDouble(tfTroco.getText()));
						}
						// Itens do pedido
						pedido.setItens(itens);
						// Valor total
						pedido.setvTotal(atualizaTotal());
						// Atualiza os pontos do pedido

						try {
							if (pedido.getvTotal() == 7.01 || pedido.getvTotal() == 0.01) {
								JOptionPane.showMessageDialog(null, "N„o pode haver um pedido de R$ 0,01!", "AtenÁ„oo",
										JOptionPane.WARNING_MESSAGE);
								itens.clear();
								atualizaTotal();
							} else {
								// Insere o pedido
								daoPedido.inserir(pedido);
								// Adiciona os pontos ao cliente
								cliente.adicionaPontos(pedido.getPontos());
								// Atualiza os pontos do cliente no banco de dados
								daoCliente.atualizaPontos(cliente.getId(), cliente.getPontos());
								// Mensagem informando que o pedido foi realizado
								JOptionPane.showMessageDialog(null,
										"Pedido realizado com sucesso! - Pedido n∫: " + pedido.getNumero(),
										"Pedido realizado", JOptionPane.INFORMATION_MESSAGE);
								geraPedido(pedido);
								limpar();
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Erro ao inserir o pedido", "Erro",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btFinaliza.setMnemonic('F');
		btFinaliza.setForeground(Color.WHITE);
		btFinaliza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btFinaliza.setFocusable(false);
		btFinaliza.setBorder(null);
		btFinaliza.setBackground(Color.RED);
		btFinaliza.setBounds(935, 513, 91, 32);
		contentPane.add(btFinaliza);

		lbTotal = new JLabel("R$ 0.00");
		lbTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTotal.setForeground(new Color(60, 179, 113));
		lbTotal.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbTotal.setBounds(842, 444, 199, 58);
		contentPane.add(lbTotal);

		cbFormaPgto = new JComboBox();
		cbFormaPgto.setBackground(new Color(255, 255, 255));
		cbFormaPgto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbFormaPgto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verifica se a forma de pagamento √© dinheiro
				if (cbFormaPgto.getSelectedItem() == FormaPgto.DINHEIRO) {
					lblTroco.setVisible(true);
					tfTroco.setVisible(true);
				} else {
					lblTroco.setVisible(false);
					tfTroco.setVisible(false);
				}
			}
		});
		cbFormaPgto.setModel(new DefaultComboBoxModel(FormaPgto.values()));
		cbFormaPgto.setBounds(235, 463, 165, 20);

		contentPane.add(cbFormaPgto);

		JLabel lblPagamento = new JLabel("Pagamento");
		lblPagamento.setForeground(Color.BLACK);
		lblPagamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPagamento.setBounds(125, 461, 101, 20);
		contentPane.add(lblPagamento);

		JLabel lblObservacao = new JLabel("Observa\u00E7\u00E3o");
		lblObservacao.setForeground(Color.BLACK);
		lblObservacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblObservacao.setBounds(10, 418, 102, 20);
		contentPane.add(lblObservacao);

		tfObs = new JTextField();
		tfObs.setToolTipText("Digite o telefone e pressione ENTER para buscar");
		tfObs.setHorizontalAlignment(SwingConstants.CENTER);
		tfObs.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfObs.setColumns(10);
		tfObs.setBounds(93, 424, 948, 20);
		contentPane.add(tfObs);

		JLabel lblRetirada = new JLabel("Retirada");
		lblRetirada.setForeground(Color.BLACK);
		lblRetirada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRetirada.setBounds(10, 461, 73, 20);
		contentPane.add(lblRetirada);

		chkRetirada = new JCheckBox("");
		chkRetirada.setBackground(new Color(250, 235, 215));
		chkRetirada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verifica se est√° selecionada
				if (chkRetirada.isSelected()) {
					tfTxEntrega.setText("0.00");
					tfTxEntrega.setEnabled(false);
				} else {
					tfTxEntrega.setText("7.00");
					tfTxEntrega.setEnabled(true);
				}
				atualizaTotal();
			}
		});
		chkRetirada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkRetirada.setBounds(84, 460, 35, 20);
		contentPane.add(chkRetirada);

		lblTroco = new JLabel("Troco");
		lblTroco.setForeground(Color.BLACK);
		lblTroco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTroco.setBounds(448, 461, 61, 20);
		contentPane.add(lblTroco);

		tfTroco = new JTextField();
		tfTroco.setToolTipText("Digite o telefone e pressione ENTER para buscar");
		tfTroco.setHorizontalAlignment(SwingConstants.CENTER);
		tfTroco.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfTroco.setColumns(10);
		tfTroco.setBounds(509, 463, 101, 20);
		contentPane.add(tfTroco);

		spQtd = new JSpinner();
		spQtd.setBounds(519, 193, 54, 45);
		contentPane.add(spQtd);

		btPromo = new JButton("Promo\u00E7\u00E3o");
		btPromo.setVisible(false);
		btPromo.setBackground(new Color(50, 205, 50));
		btPromo.setForeground(Color.WHITE);
		btPromo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btPromo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (prodSelec == null) {
					JOptionPane.showMessageDialog(null, "Selecione um produto para adicionar ao pedido", "Erro",
							JOptionPane.ERROR_MESSAGE);
					// verifica se foi informada a quantidade
				} else {
					if ((int) spQtd.getValue() <= 0 && prodSelec.getTipo() == TipoProduto.BEBIDAS
							&& prodSelec.isPromocao()) {
						item = new ItemPedido();
						item.setProduto(prodSelec);
						item.setQtd(1);
						item.setTotal(0.01);
						String observacao = "Item da PromoÁ„o";
						item.setObservacao(observacao);
						itens.add(item);
						modelItem.fireTableDataChanged();
						spQtd.setValue(0);
						prodSelec = null;
						tbProdutos.clearSelection();
						atualizaTotal();

						liberaBotao();

					} else {
						JOptionPane.showMessageDialog(null,
								"O item selecionado n„o corresponde ao item da promoÁ„o!", "Erro",
								JOptionPane.ERROR_MESSAGE);
						// prodSelec = null;
					}
				}
			}
		});
		btPromo.setBounds(685, 138, 100, 32);
		contentPane.add(btPromo);

		lbFoto = new JLabel("");
		lbFoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbFoto.setBounds(891, 11, 150, 150);
		contentPane.add(lbFoto);

		JLabel lblTxEntrega = new JLabel("Tx entrega");
		lblTxEntrega.setForeground(Color.BLACK);
		lblTxEntrega.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTxEntrega.setBounds(638, 461, 81, 20);
		contentPane.add(lblTxEntrega);

		tfTxEntrega = new JTextField();
		tfTxEntrega.setText("7.00");
		tfTxEntrega.setToolTipText("Digite o telefone e pressione ENTER para buscar");
		tfTxEntrega.setHorizontalAlignment(SwingConstants.CENTER);
		tfTxEntrega.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfTxEntrega.setColumns(10);
		tfTxEntrega.setBounds(731, 463, 101, 20);
		contentPane.add(tfTxEntrega);

		JButton btLimpar = new JButton("Limpar");
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btLimpar.setMnemonic('L');
		btLimpar.setForeground(Color.WHITE);
		btLimpar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btLimpar.setFocusable(false);
		btLimpar.setBorder(null);
		btLimpar.setBackground(new Color(255, 140, 0));
		btLimpar.setBounds(849, 513, 91, 32);
		contentPane.add(btLimpar);

		btBrinde = new JButton("Brinde");
		btBrinde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verifica se est√° selecionado um produto e se este √© uma pizza
				if (prodSelec == null || (prodSelec.getTipo() != TipoProduto.PIZZA_DOCE
						&& prodSelec.getTipo() != TipoProduto.PIZZA_SALGADA)) {
					JOptionPane.showMessageDialog(null, "Selecione uma pizza para o brinde", "Selecione",
							JOptionPane.WARNING_MESSAGE);
				} else {
					// Cria um item do pedido com o prodSelec
					ItemPedido item = new ItemPedido();
					item.setBrinde(prodSelec);
					// atribui uma observa√ß√£o ao item
					String observacao = JOptionPane.showInputDialog(null, "ObservaÁ„o do item " + prodSelec.getNome(),
							"ObservaÁ„o", JOptionPane.QUESTION_MESSAGE);
					item.setObservacao(observacao);
					// adiciona o item √† lista de itens
					itens.add(item);
					// avisa ao table model que a lista foi atualizada
					modelItem.fireTableDataChanged();
					// zerar a vari√°vel prodSelec
					// prodSelec = null;
					// limpar a sele√ß√£o da tbProdutos
					tbProdutos.clearSelection();
					// Atualiza o total
					atualizaTotal();
					// Deixa o bot√£o invis√≠vel
					btBrinde.setVisible(false);
					// Tira os pontos do cliente
					cliente.retiraBrinde();
				}
			}
		});
		btBrinde.setVisible(false);
		btBrinde.setMnemonic('B');
		btBrinde.setForeground(Color.WHITE);
		btBrinde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btBrinde.setFocusable(false);
		btBrinde.setBorder(null);
		btBrinde.setBackground(new Color(100, 149, 237));
		btBrinde.setBounds(786, 138, 91, 32);
		contentPane.add(btBrinde);

		JLabel lbFuncionario = new JLabel("Funcion\u00E1rio");
		lbFuncionario.setForeground(Color.GRAY);
		lbFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbFuncionario.setBounds(10, 519, 721, 20);
		contentPane.add(lbFuncionario);
		lbFuncionario.setText("Funcion·rio: " + TelaPrincipal.func.getNome().toString() + " - "
				+ TelaPrincipal.func.getCargo().toString());

		btMeia = new JButton("1 / 2");
		btMeia.setFont(new Font("Tahoma", Font.PLAIN, 9));

		// bot√£o de Meia Pizza
		btMeia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btAdiciona.setEnabled(false);
				btRemove.setEnabled(false);
				btBrinde.setEnabled(false);
				spQtd.setVisible(false);
				Produto objProduto = null;

				try {
					if (!daoProduto.buscarPizza(prodSelec.getNome(), prodSelec.getTipo())) {

						// 1/2 pizza
						if (!(prodSelec != null && (prodSelec.getTipo() == TipoProduto.PIZZA_DOCE
								|| prodSelec.getTipo() == TipoProduto.PIZZA_SALGADA
								|| prodSelec.getTipo() == TipoProduto.BORDA_RECHEADA))) {
							JOptionPane.showMessageDialog(null, "Selecione um produto para adicionar ao pedido!",
									"Erro", JOptionPane.ERROR_MESSAGE);
							if (auxMeiaPizza != null) {
								liberaBotao();
							}
						} else {
							item = new ItemPedido();
							// verifica se foi informada a quantidade (esta diferente de 0)
							if (auxMeiaPizza == null) {
								auxProdSelec = new Produto();
								// CRIANDO A PRIMEIRA PARTE DA PIZZA
								String aux[] = prodSelec.getNome().split(" ");
								auxMeiaPizza = aux[0] + " " + aux[1] + " + ";
								auxProdSelec.setPreco(prodSelec.getPreco());
								descricao = prodSelec.getDescricao();

								JOptionPane.showMessageDialog(null, "Selecione a outra parte!!", "Meia Pizza",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								if (!prodSelec.getNome().toString().startsWith(auxMeiaPizza.replace("+", "").trim())) {
									String aux[] = prodSelec.getNome().split(" ");
									auxMeiaPizza += aux[0] + " " + aux[1];
									if (auxProdSelec.getPreco() <= prodSelec.getPreco()) {
										auxProdSelec.setPreco(prodSelec.getPreco());
									}
									// criando objeto para inserir no banco
									auxProdSelec.setNome(auxMeiaPizza);

									// acrescentando a duas descri√ß√µes
									descricao += " + " + prodSelec.getDescricao();

									auxProdSelec.setDescricao(descricao);
									auxProdSelec.setTipo((TipoProduto) prodSelec.getTipo());
									auxProdSelec.setPromocao(prodSelec.isPromocao());

									try {
										objProduto = daoProduto.buscarPizzaId(auxProdSelec.getNome());
										if (objProduto == null) {
											daoProduto.inserir(auxProdSelec);
											produtos = daoProduto.listar();
											// Carrega a lista de produtos com a busca personalizada
											criarTabelaProdutos();
											objProduto = daoProduto.buscarPizzaId(auxProdSelec.getNome());
										}

									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									ItemPedido item = new ItemPedido();
									// atrivui o produto QUANTIDADE ao item
									item.setProduto(objProduto);
									item.setQtd(1);
									String observacao = JOptionPane.showInputDialog(null,
											"ObservaÁ„o do tem " + prodSelec.getNome(), "ObservaÁ„o",
											JOptionPane.QUESTION_MESSAGE);
									item.setObservacao(observacao);
									itens.add(item);

									// acrescenta pizza
									contadorPizza();

									// comando para autalizar a tabela se acesso ao banco de dados
									// isso faz com que a tabela seja avisa que houve atualizacao
									modelItem.fireTableDataChanged();
									spQtd.setValue(0);
									prodSelec = null;
									auxProdSelec = null;
									auxMeiaPizza = null;
									tbProdutos.clearSelection();
									// atualiza pedido
									atualizaTotal();
									// verifica se tem promo√ß√£o
									refrigerantePromocao();
								} else {
									JOptionPane.showMessageDialog(null,
											"Selecione uma pizza diferente!\nAs duas partes s„o iguais.", "AtenÁ„o",
											JOptionPane.INFORMATION_MESSAGE);
									prodSelec = null;
								}
							}
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Foi selecionado uma meia pizza, favor selecionar outro item!!", "AtenÁ„o",
								JOptionPane.INFORMATION_MESSAGE);
						prodSelec = null;
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btMeia.setBounds(519, 305, 54, 45);
		contentPane.add(btMeia);

		criarTabelaItens();

	}

	private void liberaBotao() {
		btAdiciona.setEnabled(true);
		//btBrinde.setEnabled(true);
		btRemove.setEnabled(true);
		spQtd.setVisible(true);
		btPromo.setVisible(false);
		btMeia.setVisible(true);
		btAdiciona.setVisible(true);
		btRemove.setVisible(true);

	}

	private void criarTabelaProdutos() {
		// Cria o tablemodel sobre a lista de produtos
		ProdutoTableModel model = new ProdutoTableModel(produtos);
		// Aplica o model na tabela de produtos
		tbProdutos.setModel(model);
		tbProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbProdutos.getColumnModel().getColumn(0).setPreferredWidth(300);
		tbProdutos.getColumnModel().getColumn(2).setPreferredWidth(40);
		// Define o comportamento ao selecionar uma linha na tabela
		tbProdutos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Verifica se existe uma linha selecionada
				if (tbProdutos.getSelectedRow() >= 0) {
					// Guarda na vari√°vel prodSelec o produto da linha correspondente na lista de
					// produtos
					prodSelec = produtos.get(tbProdutos.getSelectedRow());
					// Verifica se o produto possui imagem
					if (prodSelec.getImagem() != null) {
						// Criar um ImageIcon atrav√©s do vetor de bytes
						ImageIcon fotoProduto = new ImageIcon(prodSelec.getImagem());
						// Joga na label a foto do produto
						lbFoto.setIcon(ImageUtil.redimensiona(fotoProduto, lbFoto.getWidth(), lbFoto.getHeight()));
					} else {
						// Joga um √≠cone nulo na label
						lbFoto.setIcon(null);
					}
				}

			}
		});
	}

	private void criarTabelaItens() {
		// instanciando a tabela
		modelItem = new ItemPedidoTableModel(itens);
		// aplica o model na tabela de itens
		tbItens.setModel(modelItem);
		// ajustar a largura das colunas
		// tbItens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbItens.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbItens.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbItens.getColumnModel().getColumn(2).setPreferredWidth(190);
		tbItens.getColumnModel().getColumn(3).setPreferredWidth(70);

		tbItens.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tbItens.getSelectedRow() >= 0) {
					itemSelec = itens.get(tbItens.getSelectedRow());
				}
			}
		});
	}

	private double atualizaTotal() {
		// Vari√°vel para o valor total
		double total = 0;
		// Percorre todos os itens da lista de itens do pedido
		for (ItemPedido i : itens) {
			// Acrescentar na vari√°vel total o total do item
			total += i.getTotal();
		}
		// Vari√°vel para taxa de entrega convertendo texto da tfTxEntrega
		double txEntrega = Double.parseDouble(tfTxEntrega.getText());
		// Acrescenta a taxa de entrega ao total
		total += txEntrega;
		// Joga o valor total na label
		lbTotal.setText(String.format("R$ %7.2f", total));
		return total;
	}

	private void limpar() {
		try {
			cliente = null;
			prodSelec = null;
			tfNumero.setText(null);
			produtos = daoProduto.listar();
			tfTelefone.setText(null);
			tfNome.setText(null);
			taEndereco.setText(null);
			cbProduto.setSelectedIndex(-1);
			tfBuscar.setText(null);
			lbFoto.setIcon(null);
			btBrinde.setVisible(false);
			tfObs.setText(null);
			chkRetirada.setSelected(false);
			// cbFormaPgto.setSelectedIndex(-1);
			tfTroco.setText(null);
			itens = new ArrayList<>();
			tfTxEntrega.setText("7.00");
			criarTabelaItens();
			criarTabelaProdutos();
			atualizaTotal();
			tfTelefone.requestFocus();
			contador = 0;
			auxMeiaPizza = null;
			auxProdSelec = null;
			liberaBotao();
			promocaoOK = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void refrigerantePromocao() {
		if (contador >= 2 && promocaoOK) {
			JOptionPane.showMessageDialog(null,
					"Por pedir 2 pizzas, o cliente tem direito a um item de promoÁ„o por R$ 0,01!", "AtenÁ„o",
					JOptionPane.INFORMATION_MESSAGE);
			btPromo.setVisible(true);
			btAdiciona.setVisible(false);
			btRemove.setVisible(false);
			spQtd.setVisible(false);
			btMeia.setVisible(false);
			promocaoOK = false;
		} else {
			liberaBotao();
		}

	}

	private void contadorPizza() {
		if (prodSelec.getTipo() == TipoProduto.PIZZA_DOCE || prodSelec.getTipo() == TipoProduto.PIZZA_SALGADA
				|| prodSelec.getTipo() == TipoProduto.BORDA_RECHEADA) {
			contador += item.getQtd();
		}
	}

	private void geraPedido(Pedido pedido) throws IOException {
		FileWriter arq = new FileWriter("Pedido" + pedido.getNumero() + ".txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		gravarArq.printf("%-49s%-30s", "Leo Pizza Ltda", "Data/Hora : " + df.format(pedido.getData().getTime()));
		gravarArq.println("\nPedido n∫: " + pedido.getNumero());
		gravarArq.println("Cliente: " + pedido.getCliente().getNome());
		gravarArq.println("Telefone: " + pedido.getCliente().getTelefone());
		if (pedido.isRetirada()) {
			gravarArq.println("Cliente vai retirar");
		} else {
			gravarArq.println("EndereÁo de entrega: " + pedido.getEndEntrega());
			gravarArq.printf("Taxa de entrega: R$ %3.2f%n", pedido.getTxEntrega());
		}
		gravarArq.println("\nITENS");
		gravarArq.printf("%-5s|%-30s|%-30s|%-10s%n", "QTD", "Produto", "ObservaÁ„o", "Total");
		for (int i = 0; i < pedido.getItens().size(); i++) {
			gravarArq.printf("%-5d|%-30s|%-30s|R$ %5.2f%n", pedido.getItens().get(i).getQtd(),
					pedido.getItens().get(i).getProduto().getNome(), pedido.getItens().get(i).getObservacao(),
					pedido.getItens().get(i).getTotal());
		}

		gravarArq.printf("%nValor total: R$ %3.2f%n", pedido.getvTotal());
		gravarArq.printf("Troco para: R$ %3.2f%n", pedido.getTroco());

		if (!pedido.getObservacao().isEmpty()) {
			gravarArq.println("\nObservaÁ„o: " + pedido.getObservacao());
		}
		gravarArq.println("\nFuncion·rio: " + TelaPrincipal.func.getNome());
		arq.close();
		Runtime.getRuntime().exec("notepad pedido" + pedido.getNumero() + ".txt");
	}
}
