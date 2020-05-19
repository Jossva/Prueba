package umg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class frmFruta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtColor;
	private JButton btnNewButton;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmFruta frame = new frmFruta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int getSumaAscii(String linea) {
		int total = 0;
		for (int i = 0; i < linea.length(); i++) {
			char c = linea.charAt(i);
			int ascii = (int) c;
			total += ascii;
		}
		int id = (total % 50);
		return id;
	}
	
	private boolean verificarColosion(int id) {
		boolean existe = false;
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			String valor = tableModel.getValueAt(i, 0).toString();
			if (id == Integer.parseInt(valor)) {
				existe = true;
				break;
			}
		}
		return existe;
	}
	
	/**
	 * Create the frame.
	 */
	public frmFruta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(21, 21, 68, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fruta");
		lblNewLabel_1.setBounds(21, 82, 68, 28);
		contentPane.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(144, 25, 154, 24);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtColor = new JTextField();
		txtColor.setBounds(144, 82, 154, 28);
		contentPane.add(txtColor);
		txtColor.setColumns(10);
		
		btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = getSumaAscii(txtNombre.getText().trim());
				if (verificarColosion(id)) {
					JOptionPane.showMessageDialog(null, "La fruta ya existe en la lista", "Duplicidad de informacion", JOptionPane.ERROR_MESSAGE);
				} else {
					Fruta f = new Fruta();
					f.setId(id);
					f.setNombre(txtNombre.getText().trim());
					f.setColor(txtColor.getText().trim());
					tableModel.addRow(new Object[] {f.getId(), f.getNombre(), f.getColor()});
					txtNombre.setText("");
					txtColor.setText("");
				}
			}
			
		});
		btnNewButton.setBounds(380, 24, 113, 28);
		contentPane.add(btnNewButton);
		
		table = new JTable();
		table.setBounds(20, 149, 512, 176);
		contentPane.add(table);
	}
}
