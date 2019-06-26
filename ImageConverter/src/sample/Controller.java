package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {

    @FXML
    private ImageView image;

    @FXML
    private TextField tfFindImage;

    @FXML
    private RadioButton rbJPG;
    @FXML
    private RadioButton rbPNG;
    @FXML
    private RadioButton rbBMP;
    @FXML
    private RadioButton rbWBMP;

    @FXML
    private Label lblOutput;

    File selectedImage;

    public void findImage(ActionEvent actionEvent){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images","*.jpg","*.png","*.bmp","*.wbmp"),
                new FileChooser.ExtensionFilter ("JPG Images","*.jpg" ),
                new FileChooser.ExtensionFilter ("PNG Images","*.png"),
                new FileChooser.ExtensionFilter ("BMP Images","*.bmp"),
                new FileChooser.ExtensionFilter ("WBMP Images","*.wbmp")
                );

        selectedImage = fc.showOpenDialog(null);
        if(selectedImage != null){
            tfFindImage.setText(selectedImage.getName());
        }

        try {
            image.setImage(new Image(new FileInputStream(selectedImage.getAbsolutePath())) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void convertImage(ActionEvent actionEvent){
        if(validate()){
            ImageConverter ic = new ImageConverter();
            if(ic.convert(selectedImage,getOutputType())){
                lblOutput.setText("Image Succesfull converted");
            }

        }



    }

    private String getOutputType(){
        String outputType = "";
        if(rbJPG.isSelected()){
            outputType = "jpg";
        }
        if(rbPNG.isSelected()){
            outputType = "png";
        }
        if(rbBMP.isSelected()){
            outputType = "bmp";
        }
        if(rbWBMP.isSelected()){
            outputType = "wbmp";
        }
        return outputType;
    }

    private boolean validate(){
        lblOutput.setTextFill(Color.web("Black"));
        String errorMessage = "";
        if(!rbJPG.isSelected() &&!rbPNG.isSelected() &&!rbBMP.isSelected() &&!rbWBMP.isSelected()){
            errorMessage += "The output type has not been selected  \n";
        }
        if(selectedImage == null){
            errorMessage += "You have not selected an image to convert  \n";
            lblOutput.setTextFill(Color.web("Red"));
        }

        if(errorMessage!=""){
            lblOutput.setTextFill(Color.web("Red"));
        }

        lblOutput.setText(errorMessage);

        if(errorMessage==""){
            return true;
        } else{
            return false;
        }
    }
}
