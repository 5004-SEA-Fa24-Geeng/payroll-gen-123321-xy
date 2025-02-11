package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HourlyEmployee implements IEmployee {
    private final String name;//Employee's name
    private final String id;// Employee's unique ID
    private final BigDecimal payRate;// Hourly pay rate of the employee
    protected BigDecimal ytdEarnings;// Year-to-date earnings of the employee
    protected BigDecimal ytdTaxesPaid;// Year-to-date taxes paid by the employee
    private final BigDecimal pretaxDeductions;// Pretax deductions for the employee

    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = BigDecimal.valueOf(payRate);
        this.ytdEarnings = BigDecimal.valueOf(ytdEarnings);
        this.ytdTaxesPaid = BigDecimal.valueOf(ytdTaxesPaid);
        this.pretaxDeductions = BigDecimal.valueOf(pretaxDeductions);
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getID() { return id; }

    @Override
    public double getPayRate() { return payRate.doubleValue(); }

    @Override
    public String getEmployeeType() { return "HOURLY"; }

    @Override
    public double getYTDEarnings() { return ytdEarnings.doubleValue(); }

    @Override
    public double getYTDTaxesPaid() { return ytdTaxesPaid.doubleValue(); }

    @Override
    public double getPretaxDeductions() { return pretaxDeductions.doubleValue(); }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) return null;

        BigDecimal hours = BigDecimal.valueOf(hoursWorked);

        BigDecimal overtimeHours = hours.max(BigDecimal.valueOf(40))
                .subtract(BigDecimal.valueOf(40));
        BigDecimal regularHours = hours.subtract(overtimeHours);


        BigDecimal grossPay = regularHours.multiply(payRate)
                .add(overtimeHours.multiply(payRate.multiply(BigDecimal.valueOf(1.5))));
        BigDecimal taxablePay = grossPay.subtract(pretaxDeductions);
        BigDecimal taxes = taxablePay.multiply(new BigDecimal("0.2265"))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal netPay = grossPay.subtract(taxes)
                .subtract(pretaxDeductions)
                .setScale(2, RoundingMode.HALF_UP);

        ytdEarnings = BigDecimal.valueOf(getYTDEarnings()).add(netPay);
        ytdTaxesPaid = BigDecimal.valueOf(getYTDTaxesPaid()).add(taxes);
        return new PayStub(name, netPay.doubleValue(), taxes.doubleValue(),
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
