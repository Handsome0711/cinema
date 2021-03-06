package com.dev.cinema.model.dto.request;

import javax.validation.constraints.NotNull;

public class RequestMovieDto {
    @NotNull
    private String tittle;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
