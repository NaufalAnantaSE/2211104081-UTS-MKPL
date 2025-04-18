package lib;

public class TaxFunction {

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        if (numberOfChildren > 3) {
            numberOfChildren = 3;
        }

        int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
        int taxableIncome = annualIncome - deductible - nonTaxableIncome;

        int tax = calculateFivePercentTax(taxableIncome);

        return Math.max(tax, 0); // Jika pajak negatif, kembalikan 0
    }

    private static int calculateAnnualIncome(int salary, int otherIncome, int months) {
        return (salary + otherIncome) * months;
    }

    private static int calculateNonTaxableIncome(boolean married, int children) {
        int base = 54000000;
        int marriedAllowance = married ? 4500000 : 0;
        int childAllowance = Math.min(children, 3) * 1500000;
        return base + marriedAllowance + childAllowance;
    }

    private static int calculateFivePercentTax(int taxableIncome) {
        return (int) Math.round(0.05 * taxableIncome);
    }
}
