package ru.menkov.informsources.model.commerce;

public class Score {
    private Integer id;
    private Integer financeId;
    private Double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Integer financeId) {
        this.financeId = financeId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
