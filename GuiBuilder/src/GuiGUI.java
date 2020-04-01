import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.ScrollPane;
import java.util.ArrayList;

public class GuiGUI extends JFrame {

	private JPanel contentPane;
	private JTextField filterIdInput;
	private JTextField filterFNInput;
	private JTextField filterLNInput;
	private JTextField filterAgeInput;
	private JTextField txtId;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtAge;
	private JLabel lblEditadd;
	private JTable table;


	/**
	 * Create the frame.
	 * @param s 
	 */
	public GuiGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 490);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filter by:");
		lblNewLabel.setBounds(20, 20, 61, 16);
		contentPane.add(lblNewLabel);
		
		JCheckBox chckbxId = new JCheckBox("Id");
		chckbxId.setBounds(30, 48, 128, 23);
		contentPane.add(chckbxId);
		
		JCheckBox chckbxFirstName = new JCheckBox("First Name");
		chckbxFirstName.setBounds(30, 69, 128, 23);
		contentPane.add(chckbxFirstName);
		
		JCheckBox chckbxLastName = new JCheckBox("Last Name");
		chckbxLastName.setBounds(30, 89, 128, 23);
		contentPane.add(chckbxLastName);
		
		JCheckBox chckbxAge = new JCheckBox("Age");
		chckbxAge.setBounds(30, 111, 128, 23);
		contentPane.add(chckbxAge);
		
		filterIdInput = new JTextField();
		filterIdInput.setBounds(134, 47, 130, 26);
		contentPane.add(filterIdInput);
		filterIdInput.setColumns(10);
		
		filterFNInput = new JTextField();
		filterFNInput.setColumns(10);
		filterFNInput.setBounds(134, 68, 130, 26);
		contentPane.add(filterFNInput);
		
		filterLNInput = new JTextField();
		filterLNInput.setColumns(10);
		filterLNInput.setBounds(134, 88, 130, 26);
		contentPane.add(filterLNInput);
		
		filterAgeInput = new JTextField();
		filterAgeInput.setColumns(10);
		filterAgeInput.setBounds(134, 110, 130, 26);
		contentPane.add(filterAgeInput);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText("Id");
		txtId.setBounds(430, 47, 130, 26);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setText("First Name");
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(430, 68, 130, 26);
		contentPane.add(txtFirstName);
		
		txtLastName = new JTextField();
		txtLastName.setText("Last Name");
		txtLastName.setColumns(10);
		txtLastName.setBounds(430, 88, 130, 26);
		contentPane.add(txtLastName);
		
		txtAge = new JTextField();
		txtAge.setText("Age");
		txtAge.setColumns(10);
		txtAge.setBounds(430, 110, 130, 26);
		contentPane.add(txtAge);
		
		lblEditadd = new JLabel("Edit/Add:");
		lblEditadd.setBounds(430, 20, 61, 16);
		contentPane.add(lblEditadd);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(430, 140, 67, 29);
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(495, 140, 67, 29);
		contentPane.add(btnDelete);
		
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setBounds(30, 174, 624, 294);
        contentPane.add(scrollPane);
		
        ArrayList <User> users = JSONHandler.readJSON();

        Object[][] data = new Object[users.size()][];
        for(int i = 0 ; i < users.size() ; i++){
        	data[i] = users.get(i).toList();
        }
        
        String[] columns = new String[] {"Id", "First Name", "Last Name", "Age"};

        
        JTable table = new JTable(data, columns);
        
        table.setBounds(30, 201, 624, 261);
        
        scrollPane.add(table);
   
        
        JButton btnNewButton_1 = new JButton("Update");
        btnNewButton_1.setBounds(144, 140, 117, 29);
        contentPane.add(btnNewButton_1);

	}
}
