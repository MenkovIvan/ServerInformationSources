package ru.menkov.informsources.model.commerce;

public class Score {
    private Integer score_id;
    private Integer finance_id;
    private Double money;

    public Integer getScore_id() {
        return score_id;
    }

    public void setScore_id(Integer score_id) {
        this.score_id = score_id;
    }

    public Integer getFinance_id() {
        return finance_id;
    }

    public void setFinance_id(Integer finance_id) {
        this.finance_id = finance_id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
