//All calculations and information storing that need to be performed
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;

public class CalcModel
{
	private double overhead, priceTotal, wholesale, retail, sum;

	public void addTableRow(DefaultTableModel mod, String nm, double pr, double qt, double total)//adds table row
	{
		Object[] col = new Object[4];
		col[0] = nm;
		col[1] = pr;
		col[2] = qt;
		col[3] = total;
		mod.addRow(col);
	}
	public void removeTableRow(DefaultTableModel m,  JTable t)//removes table row
	{
		m.removeRow(t.getSelectedRow());
	}
	public void getMatTotal(double price, double qt) //calc value of price and quantity
	{
		priceTotal = price * qt;
	}
	public double getPriceTotal() //returns the price total
	{
		return priceTotal;
	}
	public void calcSum(DefaultTableModel mod) //sums the Total Material Cost Column
	{
		sum = 0;
		for(int i = 0; i < mod.getRowCount(); i++)
		{
			sum = sum + Double.parseDouble(mod.getValueAt(i,3).toString());
		}
	}
	public double getSum() //holds value of the Total Material Cost Column
	{
		return sum;
	}
	public void calcOverhead(double oh) //calc overhead cost
	{
		overhead = sum * (oh/100);
	}
	public double getOverhead() //returns overhead value
	{
		return overhead;
	}
	public void calcWholesale() //calculates wholesale price
	{
		wholesale = (sum * 2) + overhead;
	}
	public double getWholesale() //returns wholesale price
	{
		return wholesale;
	}
	public void calcRetail()//calculates retail price
	{
		retail = (sum * 4) + overhead;
	}
	public double getRetail() //returns retail price
	{
		return retail;
	}
}