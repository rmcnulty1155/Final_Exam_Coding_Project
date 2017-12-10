package pkgEmpty;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;

import org.apache.poi.ss.formula.functions.*;
public class TestFinance {

	
	@Test 
	public void TestAmounts()
	{
		double dMonthsToWork = 40 * 12;
		double rAnnualReturnWorking = 0.07 / 12;
		double dMonthsRetired = 20 * 12;
		double rAnnualReturnRetired = 0.02 / 12;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;
		double pv = FinanceLib.pv(rAnnualReturnRetired, dMonthsRetired, dRequiredIncome - dMonthlySSI, 0, false);
		
		double pmt = FinanceLib.pmt(rAnnualReturnWorking, dMonthsToWork, 0, pv, false);
		
		System.out.println(pv);
		System.out.println(pmt);
	}
	
	@Test
	public void TestPV()
	{
		double r = 0.025 / 12;
		double n = 20 * 12;
		double y = 10000-2642;
		double f = 0;
		boolean t = false;
		double pv = Math.round(FinanceLib.pv(r, n, y, f, t)*100.0)/100.0;
		
		assertEquals(pv,-1388556.30,0.01);
		//System.out.println(pv);
		
	}
	
	@Test
	public void TestPMT() {
		double r = 0.042 / 12;
		double n = 60;
		double p = 30000;
		double f = 0;
		boolean t = false;
		
		double d = Math.round(FinanceLib.pmt(r, n, p, f, t)*100.0)/100.0;
		
		assertEquals(d,-555.21,0.01);
		//System.out.println(d);
	}
	
	@Test
	public void TestRetirement() {
		int a = 40;
		double b = 7;
		int c = 20;
		double d = 2;
		double e = 10000;
		double f = 2642;
		boolean t = false;
		
		double pv = FinanceLib.pv((d/100) / 12, c * 12, e - f, 0, t);	
		double pmt = FinanceLib.pmt((b/100) / 12, a * 12, 0, pv, t);
		Retirement retirement = new Retirement(a,b,c,d,e,f);
		
		assertEquals(retirement.TotalAmountSaved(),Math.abs(Math.round(pv*100.0)/100.0),0.01);
		assertEquals(retirement.AmountToSave(),Math.abs(Math.round(pmt*100.0)/100.0),0.01);
		
		//System.out.println(pv);
		//System.out.println(retirement.TotalAmountSaved());
		
		//System.out.println(pmt);
		//System.out.println(retirement.AmountToSave());
	}

}
