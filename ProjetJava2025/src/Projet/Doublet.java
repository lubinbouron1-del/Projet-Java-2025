package Projet;

public class Doublet<T, U> {
    private T premier;
    private U second;

    public Doublet(T premier, U second) {
        this.premier = premier;
        this.second = second;
    }

    public T getPremier() {
        return premier;
    }

    public void setPremier(T premier) {
        this.premier = premier;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + premier + ", " + second + ")";
    }
}