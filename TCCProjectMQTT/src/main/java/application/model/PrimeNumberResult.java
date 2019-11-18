package application.model;

import java.util.Date;

public class PrimeNumberResult{

    private int id = 0;
    private double numberOriginal;
    private boolean isPrime;
    private Date dateCalc;

    public PrimeNumberResult(double numberOriginal, boolean isPrime, Date dateCalc) {
        this.numberOriginal = numberOriginal;
        this.isPrime = isPrime;
        this.dateCalc = dateCalc;
    }


    public double getNumberOriginal() {
        return numberOriginal;
    }

    public void setNumberOriginal(int numberOriginal) {
        this.numberOriginal = numberOriginal;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    public Date getDateCalc() {
        return dateCalc;
    }

    public void setDateCalc(Date dateCalc) {
        this.dateCalc = dateCalc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
