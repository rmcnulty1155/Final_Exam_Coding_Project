package pkgCore;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class Retirement {

	private int iYearsToWork;
	private double dAnnualReturnWorking;
	private int iYearsRetired;
	private double dAnnualReturnRetired;
	private double dRequiredIncome;
	private double dMonthlySSI;
				
	public Retirement(int iYearsToWork, double dAnnualReturnWorking, int iYearsRetired, double dAnnualReturnRetired,
			double dRequiredIncome, double dMonthlySSI) {
		super();
		this.iYearsToWork = iYearsToWork;
		this.dAnnualReturnWorking = dAnnualReturnWorking;
		this.iYearsRetired = iYearsRetired;
		this.dAnnualReturnRetired = dAnnualReturnRetired;
		this.dRequiredIncome = dRequiredIncome;
		this.dMonthlySSI = dMonthlySSI;
	}	
	
	public int getiYearsToWork() {
		return iYearsToWork;
	}

	public void setiYearsToWork(int iYearsToWork) {
		this.iYearsToWork = iYearsToWork;
	}

	public double getdAnnualReturnWorking() {
		return dAnnualReturnWorking;
	}

	public void setdAnnualReturnWorking(double dAnnualReturnWorking) {
		this.dAnnualReturnWorking = dAnnualReturnWorking;
	}

	public int getiYearsRetired() {
		return iYearsRetired;
	}

	public void setiYearsRetired(int iYearsRetired) {
		this.iYearsRetired = iYearsRetired;
	}

	public double getdAnnualReturnRetired() {
		return dAnnualReturnRetired;
	}

	public void setdAnnualReturnRetired(double dAnnualReturnRetired) {
		this.dAnnualReturnRetired = dAnnualReturnRetired;
	}

	public double getdRequiredIncome() {
		return dRequiredIncome;
	}

	public void setdRequiredIncome(double dRequiredIncome) {
		this.dRequiredIncome = dRequiredIncome;
	}

	public double getdMonthlySSI() {
		return dMonthlySSI;
	}

	public void setdMonthlySSI(double dMonthlySSI) {
		this.dMonthlySSI = dMonthlySSI;
	}

	public double TotalAmountSaved()
	{
		//	Determines amount to be saved based on Monthly SSI, Required Income, Annual return during retirement	and number of years retired.
		
		double pv = Math.abs(Math.round(FinanceLib.pv((dAnnualReturnRetired / 100) / 12, iYearsRetired * 12, dRequiredIncome - dMonthlySSI, 0, false)*100.0) / 100.0);
		return pv;
	}
	
	public double AmountToSave()
	{
		//Determines the amount to save each month based on TotalAmountSaved, YearsToWork and Annual return while working
		
		double pv = FinanceLib.pv((dAnnualReturnRetired / 100) / 12, iYearsRetired * 12, dRequiredIncome - dMonthlySSI, 0, false);
		double pmt = Math.abs(Math.round(FinanceLib.pmt((dAnnualReturnWorking / 100) / 12, iYearsToWork * 12, 0, pv, false)* 100.0) / 100.00);

		return pmt;
	}

}
