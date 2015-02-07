package visual;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

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
		panel.setBounds(0, 0, 566, 90);
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
		
		JTextField textFieldConta = new JTextField();
		textFieldConta.setBounds(119, 4, 136, 27);
		edicaoRestaurante.add(textFieldConta);
		textFieldConta.setColumns(10);

	}
	
	static void selecionaServico(Object servico){
		if (servico.equals("Carro")) {
			layout.show(panel, "servicoCarro");
		}
		if (servico.equals("Bab�")) {
			layout.show(panel, "servicoBaba");
		}
		if (servico.equals("Restaurante")) {
			layout.show(panel, "servicoRestaurante");
		}
	}
}