package action;

import util.Util;

class Multiplication extends SingAction{
    @Override
    protected String calculation(String str) {
        String returnWord;
        String[] array = str.split("\\*");
        Double[] doubles = Util.parseArray(array);
        Double result = doubles[0] * doubles[1];
        if (result < 0){
            returnWord = "[" + result + "]";
        }else returnWord = String.valueOf(result);
        System.out.println("resultMultiplication = " + returnWord);
        return returnWord;
    }
}
