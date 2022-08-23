package action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bracket {
    private int startBracket;
    private int closeBracket;
    private String between;
    private int priority;

    public Sign getSingInBracket() {
        return singInBracket;
    }

    public void setSingInBracket(Sign singInBracket) {
        this.singInBracket = singInBracket;
    }

    private Sign singInBracket;

    public static List<Bracket> getBracketsFrom(List<Sign> signs, String input){
        int start = 0;
        int priority = 0;
        int countEnterBracket = 0;
        int count = 0;
        List<Bracket> b = new ArrayList<>();
        List<Sign> brackets = signs.stream().filter(Sign::isBracket).collect(Collectors.toList());
        if (isCorrectBrackets(brackets)){
            for (Sign bracket: brackets){
                if (bracket.getSign().equals("(")) {
                    Bracket bracket1 = new Bracket(bracket.getPlaceInWord());
                    priority++;
                    bracket1.setPriority(priority);
                    b.add(bracket1);
                    count++;
                    countEnterBracket++;
                }else {
                    b.get(count - 1).setCloseBracket(bracket.getPlaceInWord());
                    count--;
                    priority--;
                    if (count == start){
                        count = countEnterBracket;
                        start = countEnterBracket;
                    }
                }
            }
            for (Bracket bracket: b){
                setBetween(bracket, input);
            }
            setSingInBracket(signs, brackets, b);
        }
        return b;
    }

    private static void setSingInBracket(List<Sign> signs, List<Sign> brackets, List<Bracket> bracketList){
        List<Sign> openBrackets = brackets.stream().filter(b -> b.getSign().equals("(")).collect(Collectors.toList());
        for (Sign bracketSing: openBrackets){
            for (int i = 0; i < signs.size(); i++) {
                if (signs.get(i).equals(bracketSing)){
                    Bracket bracket = bracketList.stream()
                            .filter(b -> b.startBracket == bracketSing.getPlaceInWord())
                            .findFirst()
                            .orElse(null);
                    assert bracket != null;
                    bracket.setSingInBracket(signs.get(i + 1));
                    break;
                }
            }
        }
    }

    private static void setBetween(Bracket bracket, String input){
        String between = input.substring(bracket.startBracket, bracket.closeBracket - 1);
        System.out.println("between: " + between);
        bracket.setBetween(between);
    }

    public Bracket(int startBracket) {
        this.startBracket = startBracket;
    }

    private static boolean isCorrectBrackets(List<Sign> brackets) throws IllegalArgumentException{
        List<Sign> openBrackets = brackets.stream().filter(b -> b.getSign().equals("(")).collect(Collectors.toList());
        List<Sign> closeBrackets = brackets.stream().filter(b -> b.getSign().equals(")")).collect(Collectors.toList());
        return openBrackets.size() == closeBrackets.size() && openBrackets.get(0).getSign().equals("(");
    }

    public int getStartBracket() {
        return startBracket;
    }

    public void setStartBracket(int startBracket) {
        this.startBracket = startBracket;
    }

    public int getCloseBracket() {
        return closeBracket;
    }

    public void setCloseBracket(int closeBracket) {
        this.closeBracket = closeBracket;
    }

    public String getBetween() {
        return between;
    }

    public void setBetween(String between) {
        this.between = between;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Bracket{" +
                "startBracket=" + startBracket +
                ", closeBracket=" + closeBracket +
                ", between='" + between + '\'' +
                ", priority=" + priority +
                '}';
    }
}
