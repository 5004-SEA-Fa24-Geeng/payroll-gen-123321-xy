# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for?
   CSV stands for Comma-Separated Values. It is a simple file format used to store tabular data, where each row is represented as a line of text, and values within each row are separated by commas. CSV files are commonly used for exporting and importing data between different applications.

2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?
   It allows the list to store multiple types of employees that implement IEmployee, instead of being restricted to only HourlyEmployee.
   It follows the principle of abstraction, where the code depends on an interface rather than a specific implementation.
   It makes it easier to change the underlying list type (e.g., switching from ArrayList to LinkedList), without modifying other parts of the code.

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?
   This is called a "has-a" relationship.
   In object-oriented programming, a "has-a" relationship (also known as composition or aggregation) means that an object contains another object as a field or property. This is different from an "is-a" relationship, which indicates inheritance.
4. Can you provide an example of a has-a relationship in your code (if one exists)?
   @Override
   public IPayStub runPayroll(double hoursWorked) {
   ...
   return new PayStub(this, netPay.doubleValue(), tax.doubleValue());
   }
   Here, PayStub has-a reference to the employee (this), meaning that the PayStub object contains an HourlyEmployee instance.


5. Can you provide an example of an is-a relationship in your code (if one exists)?
   public class HourlyEmployee implements IEmployee {
   Here, HourlyEmployee is-a type of IEmployee, meaning that HourlyEmployee follows the contract defined by IEmployee

6. What is the difference between an interface and an abstract class?
   An abstract class is a partially implemented class that can have both concrete methods (with code) and abstract methods (without implementation).
   An interface defines a contract that a class must follow but does not provide any actual implementation.
7. What is the advantage of using an interface over an abstract class?
   Using an interface provides more flexibility because:
   A class can implement multiple interfaces, but it can only extend one abstract class (Java does not support multiple inheritance for classes).
   Interfaces help in decoupling code, making it easier to change implementations without breaking the rest of the system.
   Objects can interact based on common behavior rather than specific implementations, improving code maintainability.
   Interfaces can be implemented by unrelated classes, allowing for greater reusability.

8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it.
   No, this code is invalid because Java generics only work with reference types (objects), not primitive types.
   int is a primitive type, so it cannot be used with generics. Instead, Java provides wrapper classes for primitive types, such as Integer for int.
   Fixed Code:
   List<Integer> numbers = new ArrayList<>();
9. Which class/method is described as the "driver" for your application?
   The "driver" class is PayrollGenerator, which contains the main method that starts the application.


10. How do you create a temporary folder for JUnit Testing?
    JUnit provides the @TempDir annotation to create a temporary folder for testing.
    import org.junit.jupiter.api.io.TempDir;
    import java.nio.file.Path;

class MyTest {
@TempDir
Path tempFolder;

    @Test
    void testSomething() throws IOException {
        Path testFile = tempFolder.resolve("test.txt");
        Files.write(testFile, "Hello, world!".getBytes());
        assertTrue(Files.exists(testFile));
    }
}
This ensures that test files are created in a temporary directory, which is automatically deleted after the test runs.




## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 

Comprehensive Pay Equity Analysis Tools: Integrate robust analytics that examine compensation data across various demographics, such as gender, race, and age. These tools can identify unexplained pay gaps and provide actionable insights to address disparities.
Integration with Market-Based Pay Structures: Align compensation with market rates by integrating salary benchmarking data. This ensures that pay structures are competitive and equitable across similar roles in the industry.
https://peoplemanagingpeople.com/tools/best-pay-equity-software/?utm_source=chatgpt.com
https://hrbrain.ai/blog/pay-equity-audit-software-features/?utm_source=chatgpt.com