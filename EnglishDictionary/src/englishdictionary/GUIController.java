/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package englishdictionary;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author shsel
 */
public class GUIController implements Initializable {

    @FXML
    private TextField searchBar;
    @FXML
    private TextArea textArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textArea.setText(OxfordAPI.retrieveData(OxfordAPI.searchWord("apple")));
    }

    @FXML
    private void searchTheWord(ActionEvent event) {

        if (searchBar.getText().length() == 0) {
            textArea.setText("Please write down a word!");
        } else if (searchBar.getText().contains(" ")&searchBar.getText().matches(".*\\d+.*")) {
            textArea.setText("Please don't include spaces, or numbers while searching for a specific word!");
        } else if (searchBar.getText().matches(".*\\d+.*")) {
            textArea.setText("Please don't include numbers! \n Only one word that contains only letters!");
        } else if (searchBar.getText().contains(" ")) {
            textArea.setText("Please don't include spaces while searching for a word");
        } else {
            textArea.setText(OxfordAPI.retrieveData(OxfordAPI.searchWord(searchBar.getText())));
        }
    }

}
