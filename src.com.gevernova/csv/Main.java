package csv;

public class Main {
    public static void main(String[] args) {
        // File paths - adjust paths if needed
        String studentFile = "students.csv";
        String employeeFile = "employees.csv";
        String updatedEmployeeFile = "employees_updated.csv";

        // Call methods from CSVPractice
        Csv.readAndPrintCSV(studentFile);
        Csv.writeEmployeesCSV(employeeFile);
        Csv.countRows(employeeFile);
        Csv.filterStudentsByMarks(studentFile);
        Csv.searchEmployee(employeeFile, "Alice Williams");
        Csv.increaseITSalary(employeeFile, updatedEmployeeFile);
        Csv.top5Salaries(updatedEmployeeFile);
    }
}
