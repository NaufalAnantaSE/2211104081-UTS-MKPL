package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private int yearJoined;
    private int monthJoined;
    private int dayJoined;
    private int monthWorkingInYear;

    private boolean isForeigner;
    private boolean gender; // true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    // Refactor: mengganti constructor panjang dengan parameter objek data
    public Employee(PersonalData data) {
        this.employeeId = data.employeeId;
        this.firstName = data.firstName;
        this.lastName = data.lastName;
        this.idNumber = data.idNumber;
        this.address = data.address;
        this.yearJoined = data.yearJoined;
        this.monthJoined = data.monthJoined;
        this.dayJoined = data.dayJoined;
        this.isForeigner = data.isForeigner;
        this.gender = data.gender;

        childNames = new LinkedList<>();
        childIdNumbers = new LinkedList<>();
    }

    public void setMonthlySalary(int grade) {
        if (grade == 1) {
            monthlySalary = 3000000;
        } else if (grade == 2) {
            monthlySalary = 5000000;
        } else if (grade == 3) {
            monthlySalary = 7000000;
        }

        if (isForeigner) {
            monthlySalary *= 1.5;
        }
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {
        LocalDate date = LocalDate.now();
        if (date.getYear() == yearJoined) {
            monthWorkingInYear = date.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12;
        }

        boolean isSingle = spouseIdNumber == null || spouseIdNumber.isEmpty();

        return TaxFunction.calculateTax(
                monthlySalary,
                otherMonthlyIncome,
                monthWorkingInYear,
                annualDeductible,
                isSingle,
                childIdNumbers.size()
        );
    }
}
