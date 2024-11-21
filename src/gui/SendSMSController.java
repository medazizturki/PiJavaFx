package gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SendSMSController {


    @FXML
    private Label statusLabel;

    @FXML
    private TextField textfield;

    @FXML
    private void sendSMS() {
    String toPhoneNumber = textfield.getText();
    if (toPhoneNumber == null || toPhoneNumber.trim().isEmpty()) {
        statusLabel.setText("Please enter a phone number.");
        return;
    }

    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String messageText = "Votre Ressource et enregistrer avec succées ! " + formatter.format(currentDate);
    Message message = Message.creator(new PhoneNumber(toPhoneNumber),
            messageText).create();

    if (message.getSid() != null) {
        statusLabel.setText("SMS sent successfully to " + toPhoneNumber + "!");
    } else {
        statusLabel.setText("Error sending SMS to " + toPhoneNumber + ".");
    }
}

}
