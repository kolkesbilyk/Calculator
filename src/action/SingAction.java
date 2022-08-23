package action;

import java.util.List;

import util.Util;

public abstract class SingAction {
    public String resultAction(String input, Sign sign, List<Sign> signs, boolean inBrackets){
        String inputText = input;
        String partWord = Util.getPartWord(signs, sign.getId(), inputText);
        String result = calculation(partWord);
        inputText = inputText.replace(inBrackets && inputText.contains("(" + partWord + ")") ? "(" + partWord + ")": partWord, result);

        return inputText;
    }

    protected abstract String calculation(String str);
}
