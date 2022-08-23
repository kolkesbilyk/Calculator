package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import action.Sign;

public class Util {

    public static void refreshSigns(List<Sign> signs, String input){
        boolean b = false;
        signs.clear();
        int count = 0;
        String[] s = input.split("");
        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            if (str.equals("[")){
                b = true;
            }else if (str.equals("]")){
                b = false;
            }
            if (b) {
                continue;
            }
            if (Sign.SINGS.contains(str)){
                signs.add(new Sign(count, i + 1, str, str.equals(")") || str.equals("(")));
                count++;
            }
        }
    }
    public static String getPartWord(List<Sign> signs, int idSign, String input){
        String returnWord;
        if (signs.size() > 1) {
            if (idSign == 0) {
                returnWord = input.substring(0, signs.get(1).getPlaceInWord() - 1);
            } else if (idSign == signs.size() - 1) {
                int start = signs.get(signs.size() - 2).getPlaceInWord();
                returnWord = input.substring(start);
            } else {
                returnWord = input.substring(signs.get(idSign - 1).getPlaceInWord(), signs.get(idSign + 1).getPlaceInWord() - 1);
            }
            System.out.println("getPartWord = " + returnWord);
        }else returnWord = input;
        return returnWord;
    }
    public static Sign getPrioritySign(List<Sign> signs){
        if (signs.size() > 1){
            if (signs.stream().anyMatch(sign1 -> sign1.getSign().equals("*") || sign1.getSign().equals("/"))){
                return signs.stream().filter(sign1 -> sign1.getSign().equals("*") || sign1.getSign().equals("/")).findFirst().orElse(null);
            }else return signs.stream().findFirst().orElse(null);
        }else return signs.get(0);
    }

    public static String removeEnd(String result){
        return result.replace(".0", "").replace("[", "").replace("]", "");
    }

    public static boolean isSampleWithBrackets(String input){
        return input.contains("(") && input.contains(")");
    }

    public static Double[] parseArray(String[] array){
        if (array.length > 2){
            List<String> stringList = Arrays.stream(array).collect(Collectors.toList());
            array = parseList(array);
        }
        Double[] doubleArray = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            String str = array[i];
            if (str.contains("]")) {
                str = str.replace("[-", "").replace("]", "");
                doubleArray[i] = -Double.parseDouble(str);

            } else {
                doubleArray[i] = Double.parseDouble(str);
            }
        }
        return doubleArray;
    }

    private static String[] parseList(String[] stringList){
        String[] result = new String[2];
        int newArrayCount = 0;
        int count = 0;
        for (int i = 0; i < stringList.length; i++) {
            if (stringList[i].equals("[")){
                count = i + 1;//5-6-7-8
                i++;
            }else {
                result[newArrayCount] = stringList[i];
                newArrayCount++;
            }
            if (count != 0){
                result[newArrayCount] = ("[-" + stringList[count]);
                count = 0;
                newArrayCount++;
            }
        }
        return result;
    }
}
