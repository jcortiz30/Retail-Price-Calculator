//Calculator view.

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class MatInputView extends JFrame
{
	//Declaring variables
	//Input panel components
	private JLabel prodName = new JLabel("Product Name: ");
	private JTextField prodField = new JTextField(10);

	private JLabel matName = new JLabel("Material Name: ");
	private JTextField matField = new JTextField(10);

	private JLabel priceNum = new JLabel("Price: ");
	private JTextField priceField = new JTextField(10);

	String[] measurement = new String[]{" in", " ft", " yd", " Sq. Ft.", " Oz."};
	JComboBox<String> measureList = new JComboBox<>(measurement);

	private JLabel qtLabel = new JLabel("Quantity: ");
	private JTextField qtField = new JTextField(5);

	private JButton addButton = new JButton("Add");
	private JButton removeButton = new JButton("Remove");

	//Table panel components
	private DefaultTableModel model;
	JTable table;

	//total panel component
	private JLabel calcPrice = new JLabel("Total Material Cost: ");
	private JTextField calcField = new JTextField(5);
	private JButton calcButton = new JButton("Calculate");
	private JLabel wholeLabel = new JLabel("Wholesale price: ");
	private JTextField wholeField = new JTextField(5);
	private JLabel retailLabel = new JLabel("Retail Price: ");
	private JTextField retailField = new JTextField(5);
	private JLabel ohLabel = new JLabel("Overhead(%): ");
	private JTextField ohField = new JTextField(5);
	private JLabel priceOutput = new JLabel("Add Overhead before pressing Calculate");


	MatInputView()
	{
	//JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setTitle("Product Price Calculator");

	// InputPanel and components
		JPanel inputPanel = new JPanel();

		//inputPanel.add(prodName); will utilize this later. Do not currently need.
		//inputPanel.add(prodField);
		inputPanel.add(matName);
		inputPanel.add(matField);
		inputPanel.add(priceNum);
		inputPanel.add(priceField);
		//inputPanel.add(measureList);Not currently needed.
		inputPanel.add(qtLabel);
		inputPanel.add(qtField);
		inputPanel.add(addButton);
		inputPanel.add(removeButton);

	//table
		model = new DefaultTableModel();
		model.addColumn("Material Name");
		model.addColumn("Price");
		model.addColumn("Quantity");
		model.addColumn("Price Total");
		table = new JTable(model);

	//totalPanel
		JPanel totalPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10,10,10,10);


		gbc.gridx = 0;
		gbc.gridy = 0;
		totalPanel.add(calcPrice, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		totalPanel.add(calcField, gbc);
		calcField.setEditable(false);
		gbc.gridx = 0;
		gbc.gridy = 1;
		totalPanel.add(ohLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		totalPanel.add(ohField, gbc);
		ohField.setToolTipText("Please enter percentage in whole number format, such as 25%");
		gbc.gridx = 2;
		gbc.gridy = 0;
		totalPanel.add(wholeLabel, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		totalPanel.add(wholeField, gbc);
		wholeField.setEditable(false);
		gbc.gridx = 2;
		gbc.gridy = 1;
		totalPanel.add(retailLabel, gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		totalPanel.add(retailField, gbc);
		retailField.setEditable(false);

		gbc.gridx = 0;
		gbc.gridy = 3;
		totalPanel.add(calcButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		totalPanel.add(priceOutput, gbc);

	//position on frame, size, making visible
		Container container = getContentPane();
		container.add(inputPanel, BorderLayout.NORTH);
		container.add(new JScrollPane(table), BorderLayout.CENTER);
		container.add(totalPanel, BorderLayout.SOUTH);

	}
	//getters and setters*********************************************************
	public DefaultTableModel getModel()
	{
		return model;
	}
	public JTable getTable()
	{
		return table;
	}
	public String getprodName()
	{
		return prodName.getText();
	}
	public String getMatName()
	{
		return matField.getText();
	}
	public double getPrice()
	{
		return Double.parseDouble(priceField.getText());
	}
	public double getQt()
	{
		return Double.parseDouble(qtField.getText());
	}
	public double getMaterialTotal()
	{
		return Double.parseDouble(calcField.getText());
	}
	public void setMaterialTotal(double sum) //sum of the Material price column
	{
		calcField.setText(String.format("%.2f", sum));
	}
	public double getOverhead()
	{
		return Double.parseDouble(ohField.getText());
	}
	public double getWholesale()
	{
		return Double.parseDouble(wholeField.getText());
	}
	public void setWholesalePrice(double whole) //set wholesale price
	{
		wholeField.setText(String.format("%.2f", whole));
	}
	public double getRetail()
	{
		return Double.parseDouble(retailField.getText());
	}
	public void setRetailPrice(double retail)//set retail price
	{
		retailField.setText(String.format("%.2f", retail));
	}
	public void setPriceLabel(double ws, double ret)
	{
		priceOutput.setText("Wholesale price: " + String.format("%.2f", ws) + " \n\nRetail price: " + String.format("%.2f", ret));
	}
	//action listeners for buttons
	void addAdditionListener(ActionListener addLis) //action listener for add button
	{
		addButton.addActionListener(addLis);
	}
	void addRemoveListener(ActionListener remLis) //action listener for remove button
		{
			removeButton.addActionListener(remLis);
	}
	void addCalcListener(ActionListener calcLis)//Calc button for total panel
	{
		calcButton.addActionListener(calcLis);
	}
	//display errors
	void displayErrorMess(String error) //what happens when one or more fields is blank
	{
			JOptionPane.showMessageDialog(this,error);
	}
	//end of getters, setters and listeners********************************
}