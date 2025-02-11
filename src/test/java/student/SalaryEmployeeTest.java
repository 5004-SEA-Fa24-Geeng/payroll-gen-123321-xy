package student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest {

    @Test
    void runPayroll() {
        // Create an employee with a pay rate of 15.0, pretax deduction of 5.0,
        // and initial YTD earnings and taxes paid of 0.
        SalaryEmployee emp = new SalaryEmployee("Nami", "s193", 200000.0, 17017, 4983, 1000.0);

        // Test that negative hours return null.
        assertNull(emp.runPayroll(-1), "runPayroll should return null for negative hours");

        /* Expected calculation for Nami:
           - Gross pay = 200000/24 =8333.33
           - Taxable pay = Gross pay - pretax deductions = 8333.33 - 1000 = 7333.33.
           - Taxes = 7333.33 * 0.2265 ≈ 1660.99
           - Net pay = Taxable pay * 0.7735 = 7333.33 * 0.7735 = 5672.33
           - YTDEarning = 17017 + 5672.33 = 22689.33
           - YTDTaxedPaid = 1660.99 + 4983 ≈ 6643
        */
        double expectedNetPay = 5672.33;
        double expectedTaxesPaid = 1661 ;
        double expectedYTDEarning = 22689.33 ;
        emp.runPayroll(1);
        // Use getPay() and getTaxesPaid() because IPayStub does not have getNetPay() or getTaxes().
        assertEquals(expectedNetPay, emp.getPay(), 0.01, "Net pay should be calculated correctly");
        assertEquals(expectedTaxesPaid, emp.getTaxesPaid(), 0.01, "Taxes paid should be calculated correctly");

        // If YTD values are part of the employee and not returned by IPayStub,
        // then consider testing them directly on the employee instance, for example:
        assertEquals(expectedYTDEarning, emp.getYTDEarnings(), 0.01, "YTD earnings should be updated correctly");
        // And similarly for YTD taxes if applicable.
    }
}