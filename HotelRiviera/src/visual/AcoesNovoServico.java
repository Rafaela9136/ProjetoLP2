package visual;

import hotel.Baba;
import hotel.Carro;
import hotel.Conector;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import excecoes.AddQuartoContratoException;
import excecoes.FrigobarEmListServicosException;

public class AcoesNovoServico extends JPanel {
	
	/**
	 */
	private static final long serialVersionUID = 1893934745841109722L;
	private final static CardLayout layout = new CardLayout();
	
	private static JPanel panel;

	/**
	 * Create the panel.
	 */
	public AcoesNovoServico() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 566, 114);
		add(panel);
		panel.setLayout(layout);
		
		JPanel vazio = new JPanel();
		vazio.setBackground(Color.WHITE);
		panel.add(vazio, "vazio");
		layout.show(panel, "vazio");
		
		// Carro
		JPanel servicoCarro = new JPanel();
		servicoCarro.setBackground(Color.WHITE);
		panel.add(servicoCarro, "servicoCarro");
		servicoCarro.setLayout(null);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "Executivo", "Luxo"}));
		comboBox_1.setBounds(123, 4, 151, 25);
		servicoCarro.add(comboBox_1);
		
		JRadioButton rdbtnTanqueCheio = new JRadioButton("Tanque cheio");
		rdbtnTanqueCheio.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtnTanqueCheio.setBounds(123, 43, 151, 18);
		servicoCarro.add(rdbtnTanqueCheio);
		
		JRadioButton rdbtnSeguro = new JRadioButton("Seguro");
		rdbtnSeguro.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtnSeguro.setBounds(291, 43, 151, 18);
		servicoCarro.add(rdbtnSeguro);
		
		JTextPane txtpnTipoDeCarro = new JTextPane();
		txtpnTipoDeCarro.setText("Tipo de carro:");
		txtpnTipoDeCarro.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnTipoDeCarro.setEditable(false);
		txtpnTipoDeCarro.setBounds(0, 8, 113, 21);
		servicoCarro.add(txtpnTipoDeCarro);
		
		JButton btnConfirmar_1 = new JButton("Confirmar");
		btnConfirmar_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnConfirmar_1.setBounds(421, 78, 145, 25);
		btnConfirmar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvisoErro erro = new AvisoErro();
				Carro carro = null;
				//...Baba baba = new Baba();
				try {
					AcoesGerais.contratoSelecionado.adicionaServico(carro);
				} catch (AddQuartoContratoException e1) {
					erro.setVisible(true);
				} catch (FrigobarEmListServicosException e1) {
					erro.setVisible(true);
				}
			}
		});
		servicoCarro.add(btnConfirmar_1);
		
		// Baba
		JPanel servicoBaba = new JPanel();
		servicoBaba.setBackground(Color.WHITE);
		panel.add(servicoBaba, "servicoBaba");
		servicoBaba.setLayout(null);
		
		JTextPane txtpnHoraInicial = new JTextPane();
		txtpnHoraInicial.setText("Hora inicial:");
		txtpnHoraInicial.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnHoraInicial.setEditable(false);
		txtpnHoraInicial.setBounds(0, 5, 90, 21);
		servicoBaba.add(txtpnHoraInicial);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"}));
		comboBox_2.setBounds(122, 5, 121, 25);
		servicoBaba.add(comboBox_2);
		
		JTextPane txtpnTrmino = new JTextPane();
		txtpnTrmino.setText("T\u00E9rmino:");
		txtpnTrmino.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnTrmino.setEditable(false);
		txtpnTrmino.setBounds(279, 5, 73, 21);
		servicoBaba.add(txtpnTrmino);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setFont(new Font("Verdana", Font.PLAIN, 12));
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"(selecionar)", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"}));
		comboBox_3.setBounds(362, 5, 121, 25);
		servicoBaba.add(comboBox_3);
		
		JButton btnConfirmar_2 = new JButton("Confirmar");
		btnConfirmar_2.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnConfirmar_2.setBounds(421, 78, 145, 25);
		btnConfirmar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvisoErro erro = new AvisoErro();
				Baba baba = null;
				//...Baba baba = new Baba();
				try {
					AcoesGerais.contratoSelecionado.adicionaServico(baba);
				} catch (AddQuartoContratoException e1) {
					erro.setVisible(true);
				} catch (FrigobarEmListServicosException e1) {
					erro.setVisible(true);
				}
			}
		});
		servicoBaba.add(btnConfirmar_2);
		
		// Restaurante
		JPanel edicaoRestaurante = new JPanel();
		panel.add(edicaoRestaurante, "servicoRestaurante");
		edicaoRestaurante.setBackground(Color.WHITE);
		edicaoRestaurante.setLayout(null);
		
		JTextPane txtpnTotalDaConta = new JTextPane();
		txtpnTotalDaConta.setText("Total da conta:");
		txtpnTotalDaConta.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtpnTotalDaConta.setEditable(false);
		txtpnTotalDaConta.setBounds(0, 6, 113, 21);
		edicaoRestaurante.add(txtpnTotalDaConta);
		
		final JTextField textFieldConta = new JTextField();
		textFieldConta.setBounds(119, 4, 136, 27);
		edicaoRestaurante.add(textFieldConta);
		textFieldConta.setColumns(10);
		
		JButton btnConfirmar_3 = new JButton("Confirmar");
		btnConfirmar_3.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnConfirmar_3.setBounds(421, 78, 145, 25);
		btnConfirmar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcoesGerais.contratoSelecionado.adicionaDespesa(Conector.transformaFloat(textFieldConta.getText()));
			}
		});
		edicaoRestaurante.add(btnConfirmar_3);

	}
	
	static void selecionaServico(Object servico){
		if (servico.equals("Carro")) {
			layout.show(panel, "servicoCarro");
		}
		if (servico.equals("Babá")) {
			layout.show(panel, "servicoBaba");
		}
		if (servico.equals("Restaurante")) {
			layout.show(panel, "servicoRestaurante");
		}
	}
}
