package action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sign {
    public static final List<String> SINGS = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "(", ")"));

    private int id;
    private int placeInWord;
    private String sign;
    private boolean isBracket;

    public Sign(int id, int placeInWord, String sign, boolean isBracket) {
        this.id = id;
        this.placeInWord = placeInWord;
        this.sign = sign;
        this.isBracket = isBracket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getPlaceInWord() {
        return placeInWord;
    }

    public void setPlaceInWord(int placeInWord) {
        this.placeInWord = placeInWord;
    }

    public boolean isBracket() {
        return isBracket;
    }

    public void setBracket(boolean bracket) {
        isBracket = bracket;
    }

    @Override
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", placeInWord=" + placeInWord +
                ", sign='" + sign + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sign sign1 = (Sign) o;

        if (id != sign1.id) return false;
        if (placeInWord != sign1.placeInWord) return false;
        if (isBracket != sign1.isBracket) return false;
        return sign != null ? sign.equals(sign1.sign) : sign1.sign == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + placeInWord;
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        result = 31 * result + (isBracket ? 1 : 0);
        return result;
    }
}
