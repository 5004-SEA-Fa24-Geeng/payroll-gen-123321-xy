# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

```mermaid
classDiagram
    class PayrollGenerator {
        +main(args: String[]): void
    }

    class IEmployee {
        <<interface>>
        +getName(): String
        +calculatePay(): double
        +getTax(): double
    }

    class HourlyEmployee {
        -hoursWorked: double
        -hourlyRate: double
        +calculatePay(): double
        +getTax(): double
    }

    class SalaryEmployee {
        -salary: double
        +calculatePay(): double
        +getTax(): double
    }

    class ITimeCard {
        <<interface>>
        +getHours(): double
        +getRate(): double
    }

    IEmployee <|-- HourlyEmployee
    IEmployee <|-- SalaryEmployee
    PayrollGenerator ..> IEmployee
    PayrollGenerator ..> ITimeCard
  ``````







## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the `Employee` class properly returns `name` from `getName()`
2. Test that the `Employee` class properly returns `id` from `getId()`
3. continue to add your brainstorm here (you don't need to super formal - this is a brainstorm) - yes, you can change the bullets above to something that fits your design.
4. Test if HourlyEmployee correctly calculates pay based on hours worked and hourly rate. 
5. Test if SalaryEmployee correctly returns the expected salary. 
6. Test if PayrollGenerator correctly reads and processes the employees.csv file. 
7. Test if PayrollGenerator correctly reads and processes the time_cards.csv file. 
8. Test if PayrollGenerator correctly writes the pay_stubs.csv output. 
9. Test if getTax() calculates the correct tax deductions for employees. 
10. Test if invalid CSV data is handled properly (e.g., missing fields, incorrect format). 
11. Test if employees with negative hours or salaries are correctly rejected.


## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.
```mermaid
classDiagram
    class PayrollGenerator {
        +main(args: String[]): void
    }

    class IEmployee {
        <<interface>>
        +getName() : String
        +getID() : String
        +getPayRate() : double
        +getEmployeeType() : String
        +getYTDEarnings() : double
        +getYTDTaxesPaid() : double
        +getPretaxDeductions() : double
        +runPayroll(hoursWorked: double) : IPayStub
        +toCSV() : String
    }

    class HourlyEmployee {
        - name : String
        - id : String
        - payRate : BigDecimal
        # ytdEarnings : BigDecimal
        # ytdTaxesPaid : BigDecimal
        - pretaxDeductions : BigDecimal
        +HourlyEmployee(name: String, id: String, payRate: double, ytdEarnings: double, ytdTaxesPaid: double, pretaxDeductions: double)
        +getName() : String
        +getID() : String
        +getPayRate() : double
        +getEmployeeType() : String
        +getYTDEarnings() : double
        +getYTDTaxesPaid() : double
        +getPretaxDeductions() : double
        +runPayroll(hoursWorked: double) : IPayStub
        +toCSV() : String
    }

    class SalaryEmployee {
        - name : String
        - id : String
        - payRate : BigDecimal
        # ytdEarnings : BigDecimal
        # ytdTaxesPaid : BigDecimal
        - pretaxDeductions : BigDecimal
        +HourlyEmployee(name: String, id: String, payRate: double, ytdEarnings: double, ytdTaxesPaid: double, pretaxDeductions: double)
        +getName() : String
        +getID() : String
        +getPayRate() : double
        +getEmployeeType() : String
        +getYTDEarnings() : double
        +getYTDTaxesPaid() : double
        +getPretaxDeductions() : double
        +runPayroll(hoursWorked: double) : IPayStub
        +toCSV() : String
    }
    class IPayStub {
        <<interface>>
        +toCSV() : String
    }
    class ITimeCard {
        <<interface>>
        +getEmployeeID() : String
        +getHoursWorked() : double
    }

    IEmployee <|.. HourlyEmployee
    IPayStub <|.. HourlyEmployee
    IEmployee <|.. SalaryEmployee
    IPayStub <|.. SalaryEmployee
    PayrollGenerator --> IEmployee
    PayrollGenerator --> ITimeCard
    PayrollGenerator --> IPayStub
  ``````




## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two.
Initially, the design was very simple. I only had a few basic methods like getName, calculatePay, and getTax in the IEmployee interface, and I created two classes, HourlyEmployee and SalaryEmployee, that implemented this interface. However, as I worked on the project, I realized that a real payroll system needs to handle much more information. It became clear that we needed to include details like employee ID, pay rate, employee type, year-to-date earnings, and taxes. To meet these needs, I expanded the IEmployee interface by adding methods for these additional details.

I also introduced a new IPayStub interface to separate the logic of generating payroll output from calculating pay. This separation helped keep the code organized and made it easier to manage each part of the payroll process. From this experience, I learned that initial designs often need to be revised as new requirements emerge. In the future, I would spend more time gathering all the necessary requirements upfront and carefully consider how to structure the code to keep it simple yet flexible. The most challenging part was balancing the need for more functionality with the goal of keeping the design clear and maintainable.