package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PayStub implements IPayStub {
    private final String employeeName;
    private final double netPay;
    private final double taxes;
    private final double ytdEarnings;
    private final double ytdTaxesPaid;

    /**
     * Constructs a PayStub with the given parameters.
     *
     * @param employeeName  The name of the employee.
     * @param netPay        The net pay for the current pay period.
     * @param taxes         The taxes paid for the current pay period.
     * @param ytdEarnings   The year-to-date earnings.
     * @param ytdTaxesPaid  The year-to-date taxes paid.
     */
    public PayStub(String employeeName, double netPay, double taxes, double ytdEarnings, double ytdTaxesPaid) {
        this.employeeName = employeeName;
        this.netPay = netPay;
        this.taxes = taxes;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    /**
     * Returns the net pay for the current pay period.
     *
     * @return the net pay.
     */
    @Override
    public double getPay() {
        return netPay;
    }

    /**
     * Returns the taxes paid for the current pay period.
     *
     * @return the taxes paid.
     */
    @Override
    public double getTaxesPaid() {
        return taxes;
    }

    /**
     * Formats a monetary value according to the following rules:
     * - If the value is an integer, force one decimal place (e.g., 1661 becomes "1661.0").
     * - If there is a fractional part, remove unnecessary trailing zeros (e.g., 773.50 becomes "773.5").
     *
     * @param value The monetary value to format.
     * @return A string representation of the value in the desired format.
     */
    private String formatMoney(BigDecimal value) {
        BigDecimal stripped = value.stripTrailingZeros();
        // If there is no fractional part (scale <= 0), set scale to 1.
        if (stripped.scale() <= 0) {
            stripped = stripped.setScale(1, RoundingMode.UNNECESSARY);
        }
        return stripped.toPlainString();
    }

    /**
     * Converts the PayStub object to a CSV string in the following format:
     * "employee_name,net_pay,taxes,ytd_earnings,ytd_taxes_paid"
     *
     * @return the CSV string.
     */
    @Override
    public String toCSV() {
        return String.join(",",
                employeeName,
                formatMoney(BigDecimal.valueOf(netPay)),
                formatMoney(BigDecimal.valueOf(taxes)),
                formatMoney(BigDecimal.valueOf(ytdEarnings)),
                formatMoney(BigDecimal.valueOf(ytdTaxesPaid))
        );
    }
}
