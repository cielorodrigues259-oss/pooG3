package pe.edu.upeu.asistencia.control;

import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

 import javafx.fxml.FXML;
 import javafx.scene.control.TextField;
 import org.springframework.stereotype.Controller;



@Controller
public class asistenciaController {
 @FXML TextField txtNum1,txtNum2;
 @FXML Label txtResult;

  @FXML
 public void sumar() {
   double Num1 = Double.parseDouble(txtNum1.getText());
   double Num2 = Double.parseDouble(txtNum2.getText());
   double resultado = Num1 + Num2;
   txtResult.setText(String.valueOf(resultado));


  }
}
