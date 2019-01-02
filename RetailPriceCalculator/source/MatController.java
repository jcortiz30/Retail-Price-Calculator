//Controller
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class MatController
{
	private MatInputView theView;
	private CalcModel theModel;

	public MatController(MatInputView view, CalcModel model)
	{
		this.theView = view;
		this.theModel = model;

		this.theView.addAdditionListener(new addListener());
		this.theView.addRemoveListener(new removeListener());
		this.theView.addCalcListener(new calcListener());
	}
	class addListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String name = theView.getMatName();
			double price = theView.getPrice();
			double qt = theView.getQt();

			try
			{
				DefaultTableModel model = theView.getModel();

				theModel.getMatTotal(price, qt);
				theModel.addTableRow(model, name, price, qt, theModel.getPriceTotal());
				if(name.equals(""))throw new Exception();
				if(price <= 0 || qt <= 0) throw new Exception();

			}
			catch(NumberFormatException ei)
			{
				theView.displayErrorMess("Please use numeric values for price and quantity fields");
			}
			catch(Exception eii)
			{
				DefaultTableModel model = theView.getModel();
				JTable table = theView.getTable();

				theView.displayErrorMess("All fields must be filled out \nValues must be greater than 0");
				model.removeRow(model.getRowCount() - 1);
			}
		}
	}
	class removeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent i)
		{
			DefaultTableModel model = theView.getModel();
			JTable table = theView.getTable();

				theModel.removeTableRow(model, table);
		}
	}
	class calcListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ii)
		{
			DefaultTableModel model = theView.getModel();
			Double overhead = theView.getOverhead();

			//sums the last column
			theModel.calcSum(model);
			theView.setMaterialTotal(theModel.getSum());

			try{
				//calculations for overhead cost, retail and wholesale price
				theModel.calcOverhead(overhead);
				if(overhead < 0) throw new Exception();

			}
			catch(NumberFormatException eiii)
			{
				theView.displayErrorMess("Please use numeric values for overhead field");
			}
			catch(Exception eiiii)
			{
				theView.displayErrorMess("Please use a numeric value greater than 0");
			}

			theModel.calcWholesale();
			theModel.calcRetail();
			theView.setWholesalePrice(theModel.getWholesale());
			theView.setRetailPrice(theModel.getRetail());
			theView.setPriceLabel(theModel.getWholesale(), theModel.getRetail());
		}
	}
}