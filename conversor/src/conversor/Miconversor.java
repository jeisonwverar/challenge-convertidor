package conversor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Miconversor {

	private JFrame frame;
	private JButton btn;
	private JComboBox cmb;
	private JLabel label;
	private JLabel lblNewLabel;
	
	
	public enum Moneda{
		
		COP_USD,
		COP_EUR,
		COP_MXN,
		USD_COP,
		EUR_COP,
		MXN_COP,
	}
	//variable 
	public double dolar=3938.3;
	public double euro=4338.2;
	public double pesoMexicano=236.02;
	
	//valor de la caja de texto
	public double valorInput=0.00;
	private JTextField txt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Miconversor window = new Miconversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Miconversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(SystemColor.control);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(38, 67, 162, 20);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel(Moneda.values()));
		cmb.setBackground(SystemColor.activeCaption);
		cmb.setForeground(SystemColor.windowText);
		cmb.setBounds(38, 123, 135, 22);
		frame.getContentPane().add(cmb);
		//evento boton
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBackground(SystemColor.activeCaption);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn.setBounds(183, 123, 89, 23);
		frame.getContentPane().add(btn);
		
		label = new JLabel("00.00");
		label.setBounds(210, 63, 98, 29);
		frame.getContentPane().add(label);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\wilde\\Downloads\\forma.png"));
		lblNewLabel.setBounds(233, 11, 191, 239);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public void Convertir() {
		
		if(Validar(txt.getText())) {
			
			Moneda moneda=(Moneda) cmb.getSelectedItem();
			
			switch (moneda) {
			
			
			case COP_USD:
				PesosAMoneda(dolar);
				break;
			case COP_EUR:
				PesosAMoneda(euro);
				break;
			case COP_MXN:
				PesosAMoneda(pesoMexicano);
				break;
			case USD_COP:
				MonedaAPesos(dolar);
				break;
			case EUR_COP:
				MonedaAPesos(euro);
				break;
			case MXN_COP:
				MonedaAPesos(pesoMexicano);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			}
		}
		
	}
	
	public void PesosAMoneda(double moneda) {
		double res= valorInput / moneda;
		label.setText(Redondear(res));
		
	}
	
	public void MonedaAPesos(double moneda) {
		double res= valorInput * moneda;
		label.setText(Redondear(res));
	}
	
	public String Redondear(double valor) {
		
		DecimalFormat df= new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		return df.format(valor);
	}
	
	public boolean Validar(String texto) {
		
		try {
			double x = Double.parseDouble(texto);
			
			if(x > 0);
			
			valorInput=x;
			return true;
			
		} catch (NumberFormatException e) {
			// TODO: handle exception}
			label.setText("Solamente números !!");
			return false;
		}
	}
}
