package Scheduluxe;

public class Budget {
    private int budgetId;
    private String budgetName;

    public Budget(int budgetId, String budgetName) {
        this.budgetId = budgetId;
        this.budgetName = budgetName;
    }

    /* getters */
    public int getBudgetId() {
        return budgetId;
    }

    public String getBudgetName() {
        return budgetName;
    }
    
}
