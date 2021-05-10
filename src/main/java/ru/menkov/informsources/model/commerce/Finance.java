package ru.menkov.informsources.model.commerce;

public class Finance {
    private Integer finance_id;
    private Integer user_id;

    private String card;
    private Double money;
    private Boolean agreement;

    public Integer getFinance_id() {
        return finance_id;
    }

    public void setFinance_id(Integer finance_id) {
        this.finance_id = finance_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Boolean getAgreement() {
        return agreement;
    }

    public void setAgreement(Boolean agreement) {
        this.agreement = agreement;
    }
}
