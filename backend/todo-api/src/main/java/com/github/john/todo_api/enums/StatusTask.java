package com.github.john.todo_api.enums;

public enum StatusTask {
    FINISH("Finalizada"),
    PENDING("Pendente"),

    CANCELED("Cancelada");

    private final String description;

    StatusTask(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
