package student;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the HourlyEmployee class.
 */
class HourlyEmployeeTest {

    @Test
    void runPayroll() {
        // Create an employee with a pay rate of 15.0, pretax deduction of 5.0,
        // and initial YTD earnings and taxes paid of 0.
        HourlyEmployee emp = new HourlyEmployee("Henry", "H008", 15.0, 0.0, 0.0, 5.0);

        // Test that negative hours return null.
        assertNull(emp.runPayroll(-1), "runPayroll should return null for negative hours");

        // Test a payroll run for 40 hours (no overtime).
        IPayStub stub = emp.runPayroll(40);
        assertNotNull(stub, "runPayroll should not return null for positive hours");

        /* Expected calculation for 40 hours:
           - Regular hours = 40, Overtime hours = 0.
           - Gross pay = 40 * 15.0 = 600.
           - Taxable pay = Gross pay - pretax deductions = 600 - 5 = 595.
           - Taxes = 595 * 0.2265 ≈ 134.77 (rounded)
           - Net pay = Gross pay - taxes - pretax deductions = 600 - 134.77 - 5 = 460.23.
        */
        double expectedNetPay = 460.23;
        double expectedTaxesPaid = 134.77;

        // Use getPay() and getTaxesPaid() because IPayStub does not have getNetPay() or getTaxes().
        assertEquals(expectedNetPay, stub.getPay(), 0.01, "Net pay should be calculated correctly");
        assertEquals(expectedTaxesPaid, stub.getTaxesPaid(), 0.01, "Taxes paid should be calculated correctly");

        // If YTD values are part of the employee and not returned by IPayStub,
        // then consider testing them directly on the employee instance, for example:
        assertEquals(expectedNetPay, emp.getYTDEarnings(), 0.01, "YTD earnings should be updated correctly");
        // And similarly for YTD taxes if applicable.
    }
}
