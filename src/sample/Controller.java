package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Controller {

    @FXML
    private Button getResult;
    @FXML
    private VBox history;
    @FXML
    private Text sample_info;
    @FXML
    private Text result;
    @FXML
    private TextField input;
    @FXML
    void initialize(){
        getResult.setOnAction(actionEvent -> {
            String inputText = input.getText().trim();
            String resultTask = Util.removeEnd(calculation(inputText));
            String historyTask = inputText + " = " + resultTask;

            result.setText(resultTask);

            List<String> tasks = new ArrayList<>();
            tasks.add(historyTask);
            for (String task: tasks){
                history.getChildren().add(new Label(task));
            }

        });
    }
    private static String calculation(String input){
        if (!(input.startsWith("(") || input.startsWith("-") || input.endsWith(")"))) {
            if (input.isBlank() || Sign.SINGS.stream().anyMatch(input::endsWith)) {
                return " your request is wrong!!!!";
            }
        }
        String output = input;
        List<Sign> signs = new ArrayList<>();//89+(6+(8+(19-9)))+(8+(6/3)-9)
        Util.refreshSigns(signs, output);
        output = resultR(output, signs);
        return output;
    }

    private static String resultR(String input, List<Sign> signs){
        String inputText = input;
        while (signs.size() > 0) {
            while (Util.isSampleWithBrackets(inputText)) {
                List<Bracket> brackets = Bracket.getBracketsFrom(signs, inputText);
                Bracket bracket = brackets.stream().max(Comparator.comparing(Bracket::getPriority)).orElse(null);
                Sign sign = bracket.getSingInBracket();
                SingAction action = SingActionManager.getInstance(sign);
                inputText = action.resultAction(inputText, sign, signs, true);
                Util.refreshSigns(signs, inputText);
            }
            Sign sign = Util.getPrioritySign(signs);
            SingAction action = SingActionManager.getInstance(sign);
            inputText = action.resultAction(inputText, sign, signs, false);
            Util.refreshSigns(signs, inputText);
            if (signs.size() < 1){
                return inputText;
            }
            }
        return inputText;
    }
}

