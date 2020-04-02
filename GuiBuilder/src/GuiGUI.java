import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
	private ArrayList <User> users;
	private ScrollPane scrollPane;

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
		btnNewButton.setBounds(414, 140, 83, 29);
		contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) {
      		  User t = new User (txtFirstName.getText(), txtLastName.getText(), txtAge.getText());

      		  try {
				RESTHandler.postRequest(t);
      		  } catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
      		  }
              users = RESTHandler.readJSON();
              table = createTable(users);
              scrollPane.add(table);
      		  
      	  } 
      });
		
        JButton btnNewButton_1 = new JButton("Filter");
        btnNewButton_1.setBounds(144, 140, 117, 29);
        contentPane.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("New");
        btnNewButton_2.setBounds(353, 140, 61, 29);
        contentPane.add(btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		txtId.setText("");
              	txtFirstName.setText("");
              	txtLastName.setText("");
              	txtAge.setText("");
        	  } 
        });
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(495, 140, 67, 29);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() { 
	      	  public void actionPerformed(ActionEvent e) {
	      		  String t = table.getValueAt(table.getSelectedRow(), 0).toString();
	      		  System.out.println("Delete: " + t.toString());
	      	  } 
	      });
		
        scrollPane = new ScrollPane();
        scrollPane.setBounds(30, 174, 624, 294);
        contentPane.add(scrollPane);
		
        
        users = RESTHandler.readJSON();
        table = createTable(users);
        scrollPane.add(table);
        
	}


	private JTable createTable(ArrayList<User> users) {
		
		Object[][] data = new Object[users.size()][];
        for(int i = 0 ; i < users.size() ; i++){
        	data[i] = users.get(i).toList();
        }
        String[] columns = new String[] {"Id", "First Name", "Last Name", "Age"};

        table = new JTable(data, columns);
        
        //Select only one row at the time
        table.setSelectionModel(new ForcedListSelectionModel());
        
        //Set column width
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        
        //Center Alignment in 3 last columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
            	txtId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
            	txtFirstName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
            	txtLastName.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
            	txtAge.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });
        
        table.setBounds(30, 201, 624, 261);
        return table;
	}

}
