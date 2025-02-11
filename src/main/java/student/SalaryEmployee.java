package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryEmployee implements IEmployee, IPayStub {
    /**
     * Employee's name.
     */
    private final String name;
    /**
     * Employee's unique ID.
     */
    private final String id;
    /**
     * Hourly pay rate of the employee.
     */
    private final BigDecimal payRate;
    /**
     * Hourly pay rate of the employee.
     */
    private BigDecimal netpay;
    /**
     * Year-to-date earnings of the employee.
     */
    private BigDecimal ytdEarnings;
    /**
     * Year-to-date taxes paid by the employee.
     */
    private BigDecimal ytdTaxesPaid;
    /**
     * Pretax deductions for the employee.
     */
    private final BigDecimal pretaxDeductions;

    BigDecimal netPay;
    BigDecimal taxablePay;
    BigDecimal taxes;


    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = BigDecimal.valueOf(payRate);
        this.ytdEarnings = BigDecimal.valueOf(ytdEarnings);
        this.ytdTaxesPaid = BigDecimal.valueOf(ytdTaxesPaid);
        this.pretaxDeductions = BigDecimal.valueOf(pretaxDeductions);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }
    @Override
    public double getPay() {
        return netPay.doubleValue();
    }

    @Override
    public double getTaxesPaid() {
        return taxes.doubleValue();
    }

    @Override
    public double getPayRate() {
        return payRate.doubleValue();
    }

    @Override
    public String getEmployeeType() {
        return "SALARY";
    }

    @Override
    public double getYTDEarnings() {
        return ytdEarnings.doubleValue();
    }

    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid.doubleValue();
    }

    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions.doubleValue();
    }

    @Override
    public IPayStub runPayroll(double ignored) {
        if (ignored < 0) {
            return null;
        }
        BigDecimal grossPay = BigDecimal.valueOf(getPayRate())
                .divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);
         taxablePay = grossPay.subtract(BigDecimal.valueOf(getPretaxDeductions()));
         taxes = taxablePay.multiply(new BigDecimal("0.2265"))
                .setScale(2, RoundingMode.HALF_UP);
         netPay = taxablePay.multiply(new BigDecimal("0.7735"))
                .setScale(2, RoundingMode.HALF_UP);
        ytdEarnings = BigDecimal.valueOf(getYTDEarnings()).add(grossPay).subtract(BigDecimal.valueOf(getPretaxDeductions())).subtract(taxes);
        ytdTaxesPaid = BigDecimal.valueOf(getYTDTaxesPaid()).add(taxes);
        return new PayStub(getName(), netPay.doubleValue(), taxes.doubleValue(),
                ytdEarnings.doubleValue(), ytdTaxesPaid.doubleValue());
    }
    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                getEmployeeType(), name, id,
                payRate.stripTrailingZeros().toPlainString(),
                pretaxDeductions.stripTrailingZeros().toPlainString(),
                ytdEarnings.stripTrailingZeros().toPlainString(),
                ytdTaxesPaid.stripTrailingZeros().toPlainString());
    }
}
