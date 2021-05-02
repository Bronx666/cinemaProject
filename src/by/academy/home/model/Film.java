package by.academy.home.model;

import java.time.LocalDate;

public class Film {
    private final int idFilm;
    private final String nameFilm;
    private final String description;
    private final int costFilm;
    private final LocalDate dateFilm;

    public Film(int idFilm, String nameFilm, String description, int costFilm, LocalDate dateFilm) {
        this.idFilm = idFilm;
        this.nameFilm = nameFilm;
        this.description = description;
        this.costFilm = costFilm;
        this.dateFilm = dateFilm;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public LocalDate getDateFilm() {
        return dateFilm;
    }

    public int getCostFilm() {
        return costFilm;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Film{" +
                "idFilm=" + idFilm +
                ", nameFilm='" + nameFilm + '\'' +
                ", description='" + description + '\'' +
                ", costFilm=" + costFilm +
                ", dateFilm=" + dateFilm +
                '}';
    }
}
