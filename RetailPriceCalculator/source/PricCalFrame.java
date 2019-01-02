import javax.swing.JFrame;

public class PricCalFrame
{
	public static void main(String[] args)
	{
	CalcModel theM = new CalcModel();
	MatInputView theMv = new MatInputView();
	MatController theMC = new MatController(theMv, theM);

	theMv.setVisible(true);
	}
}