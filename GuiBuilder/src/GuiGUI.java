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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	private JCheckBox chckbxId;
	private JCheckBox chckbxFirstName;
	private JCheckBox chckbxLastName;
	private JCheckBox chckbxAge;
	
	
	public GuiGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 490);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Serach label
		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setBounds(20, 20, 61, 16);
		contentPane.add(lblNewLabel);
		
		//Checkboxes for different filters with listeners - if checkBox is unchecked add empty string to textFiled next to it
		chckbxId = new JCheckBox("Id");
		chckbxId.setBounds(30, 48, 128, 23);
		contentPane.add(chckbxId);
		chckbxId.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		           
		        } else {
		        	filterIdInput.setText("");
		        }
		    }
		});
		
		chckbxFirstName = new JCheckBox("First Name");
		chckbxFirstName.setBounds(30, 69, 128, 23);
		contentPane.add(chckbxFirstName);
		chckbxFirstName.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		           
		        } else {
		        	filterFNInput.setText("");
		        }
		    }
		});
		
		chckbxLastName = new JCheckBox("Last Name");
		chckbxLastName.setBounds(30, 89, 128, 23);
		contentPane.add(chckbxLastName);
		chckbxLastName.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		           
		        } else {
		        	filterLNInput.setText("");
		        }
		    }
		});
		
		chckbxAge = new JCheckBox("Age >=");
		chckbxAge.setBounds(30, 111, 128, 23);
		contentPane.add(chckbxAge);
		chckbxAge.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		           
		        } else {
		        	filterAgeInput.setText("");
		        }
		    }
		});
		
		
		//Textfields for inputs in filter next to checkboxes
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
		
		
		//Textfields for inputs for creating/editing users
		txtId = new JTextField(64);
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
		
		
		//"Save" Button
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(414, 140, 83, 29);
		contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
      		  	//If clicked - create new user with information in textFields
        		User t = new User (txtId.getText(), txtFirstName.getText(), txtLastName.getText(), Integer.parseInt(txtAge.getText()));
      		  		try{
      		  			//Send user to DB through RESTHandler
      		  			RESTHandler.postRequest(t);
      		  
      		  		} catch (IOException | InterruptedException e1) {
      		  			e1.printStackTrace();
      		  		}
              
      		  	users = readFilter();
      	        table = createTable(users);	  
      	  	} 
        });
		
        
        //"Serach" button
        JButton btnNewButton_1 = new JButton("Search");
        btnNewButton_1.setBounds(144, 140, 117, 29);
        contentPane.add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() { 
        	//When clicked - read filters - store in users - and redo table
        	public void actionPerformed(ActionEvent e) { 
        		users = readFilter();
        		table = createTable(users);
                scrollPane.add(table);
        		
        	} 
        });
        
        
        //"New" button
        JButton btnNewButton_2 = new JButton("New");
        btnNewButton_2.setBounds(353, 140, 61, 29);
        contentPane.add(btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() { 
        	//When clicked empty all fields 
        	public void actionPerformed(ActionEvent e) { 
        		emptyFields();
        	} 
        });
		
        
        //"Delete" Button
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(495, 140, 67, 29);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() { 
			//When clicked - get user ID from selected row in table - send delete request through RESTHandler
			public void actionPerformed(ActionEvent e) {
				String t = table.getValueAt(table.getSelectedRow(), 0).toString();
	      		try {
					RESTHandler.deleteRequest(t);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	      		//After deletion present empty table
	      		readTable();
	      		emptyFields();
	      	  } 
	     });
		
        //Sets up scrollPane for table to present results
		scrollPane = new ScrollPane();
        scrollPane.setBounds(30, 174, 624, 294);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.add(table);
	}

	//Takes arrayList of User and sets up the table to present the users
	private JTable createTable(ArrayList<User> users) {
		
		//Set upp rows from ArrayList users
		Object[][] data = new Object[users.size()][];
        for(int i = 0 ; i < users.size() ; i++){
        	data[i] = users.get(i).toList();
        }
        //Name columns
        String[] columns = new String[] {"Id", "First Name", "Last Name", "Age"};

        //Create table
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
        
        //Set up click on row loads to fields
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
	
	//Reading all users in DB to table
	public void readTable(){		
        users = RESTHandler.readJSON();
        table = createTable(users);
        scrollPane.add(table);
	}

	//Empty Crud-update-delete fields
	public void emptyFields(){
		txtId.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtAge.setText("");
	}
	
	//Empty filtering textfields
	public void emptyFilters(){
		filterIdInput.setText("");
		filterFNInput.setText("");
		filterLNInput.setText("");
		filterAgeInput.setText("");
	}
	
	//Reading filters and sending read request through RESTHandler
	public ArrayList<User> readFilter(){
		
		//Read filter textfileds to variables
		String id = filterIdInput.getText();
		String firstName = filterFNInput.getText();
		String lastName = filterLNInput.getText();
		int age;
		if(filterAgeInput.getText().equals("")) {
			age = 0;
		}else {
			age = Integer.parseInt(filterAgeInput.getText());
		}

		
		//Depending on which checkBoxes that are selected, use RESTHandler to connect to correct REST endpoint
		if(chckbxId.isSelected()){
			return RESTHandler.readIDJSON("http://localhost:8081/users/"+id);
			
		}else if(chckbxFirstName.isSelected() && chckbxLastName.isSelected() && chckbxAge.isSelected()){
			return RESTHandler.readJSON("http://localhost:8081/fullNameAndAge/?firstName="+firstName+"&lastName="+lastName+"&age="+age);
		
		} else if(chckbxFirstName.isSelected() && chckbxLastName.isSelected()){
			return RESTHandler.readJSON("http://localhost:8081/fullName/?firstName="+firstName+"&lastName="+lastName);
		
		} else if(chckbxLastName.isSelected() && chckbxAge.isSelected()){
			return RESTHandler.readJSON("http://localhost:8081/lastNameAndAge/?lastName="+lastName+"&age="+age);
		
		} else if(chckbxFirstName.isSelected() && chckbxAge.isSelected()){
			return RESTHandler.readJSON("http://localhost:8081/firstNameAndAge/?firstName="+firstName+"&age="+age);
		
		} else if(chckbxFirstName.isSelected()){
			return RESTHandler.readJSON("http://localhost:8081/firstName/"+firstName);
		
		} else if(chckbxLastName.isSelected()){
			return RESTHandler.readJSON("http://localhost:8081/lastName/"+lastName);
		
		} else if(chckbxAge.isSelected()){
			return RESTHandler.readJSON("http://localhost:8081/age/"+age);
		
		} else{
			return RESTHandler.readJSON();
		}	
	}
}
