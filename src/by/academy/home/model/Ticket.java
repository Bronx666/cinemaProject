package by.academy.home.model;

public class Ticket {

    private final int idTicket;
    private final int userIdTicket;
    private final int filmIdTicket;
    private final int number;
    private final boolean state;

    public Ticket(int idTicket, int userIdTicket, int filmIdTicket, int number, boolean state) {
        this.idTicket = idTicket;
        this.userIdTicket = userIdTicket;
        this.filmIdTicket = filmIdTicket;
        this.number = number;
        this.state = state;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public int getUserIdTicket() {
        return userIdTicket;
    }

    public int getFilmIdTicket() {
        return filmIdTicket;
    }

    public int getNumber() {
        return number;
    }

    public boolean isState() {
        return state;
    }

    @Override
    public String toString() {
        return "Место #" + number + " ";
    }
}
