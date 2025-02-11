package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryEmployee extends HourlyEmployee {
    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    @Override
    public String getEmployeeType() { return "SALARY"; }

    @Override
    public IPayStub runPayroll(double ignored) {
        BigDecimal grossPay = BigDecimal.valueOf(getPayRate())
                .divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);
        BigDecimal taxablePay = grossPay.subtract(BigDecimal.valueOf(getPretaxDeductions()));
        BigDecimal taxes = taxablePay.multiply(new BigDecimal("0.2265"))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal netPay = taxablePay.multiply(new BigDecimal("0.7735"))
                .setScale(2, RoundingMode.HALF_UP);
        ytdEarnings = BigDecimal.valueOf(getYTDEarnings()).add(grossPay).subtract(BigDecimal.valueOf(getPretaxDeductions())).subtract(taxes);
        ytdTaxesPaid = BigDecimal.valueOf(getYTDTaxesPaid()).add(taxes);
        return new PayStub(getName(), netPay.doubleValue(), taxes.doubleValue(),
                ytdEarnings.doubleValue(), ytdTaxesPaid.doubleValue());
    }
}
