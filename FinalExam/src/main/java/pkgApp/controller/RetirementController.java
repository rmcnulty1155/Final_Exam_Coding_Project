package pkgApp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {
	
	private RetirementApp mainApp = null;
	
	@FXML
	private TextField txtSaveEachMonth;
	
	@FXML
	private TextField txtYearsToWork;
	
	@FXML
	private TextField txtAnnualReturnWorking;
	
	@FXML
	private TextField txtWhatYouNeedToSave;
	
	@FXML
	private TextField txtYearsRetired;
	
	@FXML
	private TextField txtAnnualReturnRetirement;
	
	@FXML
	private TextField txtRequiredIncome;
	
	@FXML
	private TextField txtMonthlySSI;
	
	
	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}
		
    private boolean calculateClicked = false;
    private boolean clearClicked = false;

    
	private Retirement retirement;
    public void setRetirement(Retirement retirement) {
        this.retirement = retirement;
   
    }
	
    public boolean iscalculateClicked() {
        return calculateClicked;
    }
    
    public boolean isclearClicked() {
    		return clearClicked;
    }
         
	@FXML
	public void btnClear(ActionEvent event) {
		System.out.println("Clear pressed");
		
		//	Clears all the text inputs 
	    		
		txtSaveEachMonth.setText("");
        txtYearsToWork.setText("");
        txtAnnualReturnWorking.setText("");
        txtYearsRetired.setText("");
        txtAnnualReturnRetirement.setText("");
        txtRequiredIncome.setText("");
        txtMonthlySSI.setText("");
        txtWhatYouNeedToSave.setText("");
    
	}
	
	@FXML
	public void btnCalculate(ActionEvent event) {
		System.out.println("Calculate Pressed");
		if (isInputValid()) {
				
		int a = (Integer.parseInt(txtYearsToWork.getText()));
		double b = (Double.parseDouble(txtAnnualReturnWorking.getText()));
		int c = (Integer.parseInt(txtYearsRetired.getText()));
		double d = (Double.parseDouble(txtAnnualReturnRetirement.getText()));
		double e = (Double.parseDouble(txtRequiredIncome.getText()));
		double f = (Double.parseDouble(txtMonthlySSI.getText()));
		
		Retirement retirement = new Retirement(a,b,c,d,e,f);

		//	Calls AmountToSave and TotalAmountSaved and populate 

		txtSaveEachMonth.setText(Double.toString(retirement.AmountToSave()));
        txtWhatYouNeedToSave.setText(Double.toString(retirement.TotalAmountSaved()));

//        calculateClicked = true;
		}
		
	}	
	
    private boolean isInputValid() {
        String errorMessage = "";

        if (txtYearsToWork.getText() == null || txtYearsToWork.getText().length() == 0) {
            errorMessage += "Invalid Years To Work!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(txtYearsToWork.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid Years to Work (must be an integer)!\n"; 
            }
        }
                
        if (txtAnnualReturnWorking.getText() == null || txtAnnualReturnWorking.getText().length() == 0) {
            errorMessage += "Invalid Annual Return on Investment while Working!\n"; 
        } else {
            // try to parse the postal code into a double.
            try {
                double x = Double.parseDouble(txtAnnualReturnWorking.getText());
                if (x > 20 || x < 0) {
                	errorMessage += "Invalid Annual Return on Investment while Working (must be between 0-20%)!\n ";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Invalid Annual Return on Investment while Working (must be a double)!\n"; 
            }
        } 
        
        if (txtYearsRetired.getText() == null || txtYearsRetired.getText().length() == 0) {
            errorMessage += "Invalid Years Retired!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(txtYearsRetired.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid Years Retired (must be an integer)!\n"; 
            }
        }        
        
        if (txtAnnualReturnRetirement.getText() == null || txtAnnualReturnRetirement.getText().length() == 0) {
            errorMessage += "Invalid Annual Return on Investment while Retired!\n"; 
        } else {
            // try to parse the postal code into a double.
            try {
                double x = Double.parseDouble(txtAnnualReturnRetirement.getText());
                if (x > 3 || x < 0) {
                	errorMessage += "Invalid Annual Return on Investment while Retired (must be between 0-3%)!\n ";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Invalid Annual Return on Investment while Retired (must be a double)!\n"; 
            }
        }        
        
        if (txtRequiredIncome.getText() == null || txtRequiredIncome.getText().length() == 0) {
            errorMessage += "Invalid Required Income!\n"; 
        } else {
            // try to parse the postal code into a double.
            try {
                Double.parseDouble(txtRequiredIncome.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid Required Income (must be a double)!\n"; 
            }
        }

        if (txtMonthlySSI.getText() == null || txtMonthlySSI.getText().length() == 0) {
            errorMessage += "Invalid Monthly SSI!\n"; 
        } else {
            // try to parse the postal code into a double.
            try {
                Double.parseDouble(txtMonthlySSI.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid Monthly SSI (must be a double)!\n"; 
            }
        }
        
        if (txtSaveEachMonth.getText().length() != 0) {
            errorMessage += "Save Each Month ($) box should be empty (to be calculated)\n"; 
        } 
        
        if (txtWhatYouNeedToSave.getText().length() != 0) {
            errorMessage += "What you need to save ($) box should be empty (to be calculated)\n"; 
        } 

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}



