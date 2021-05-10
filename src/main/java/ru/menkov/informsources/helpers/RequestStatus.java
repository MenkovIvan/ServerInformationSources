package ru.menkov.informsources.helpers;

public enum RequestStatus {

    OK(1),
    ERROR(-1),
    SERVER_ERROR(0);

    RequestStatus(Integer status){
        this.status = status;
    }
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
