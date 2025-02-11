package student;

public class TimeCard implements ITimeCard {
    /**
     * final String employeeID.
     */
    private final String employeeID;
    /**
     * final double hoursWorked.
     */
    private final double hoursWorked;

    /**
     *
     * @param employeeID Employee's ID
     * @param hoursWorked HoursWorked
     */
    public TimeCard(String employeeID, double hoursWorked) {
        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getEmployeeID() {
        return employeeID;
    }

    @Override
    public double getHoursWorked() {
        return hoursWorked;
    }
}
