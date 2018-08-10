import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class textBoxBackground extends Application 
{
  @Override
  public void start( Stage primaryStage ) 
  {
    final TextField[] textFields = new TextField[4]; // 4 example textfields.

    GridPane gridPane = new GridPane();    // Create UI.
    gridPane.setAlignment( Pos.CENTER );
    gridPane.setHgap( 5 );
    gridPane.setVgap( 5 );

    // Setup 4 example textfields.
    for ( int i=0; i<4; i++ ) 
    {
      textFields[i] = new TextField();
      gridPane.add( textFields[i], 1, i );
      gridPane.add( new Label( (i + 1) + ":" ), 0, i );
      textFields[i].setAlignment( Pos.BOTTOM_RIGHT );
      textFields[i].textProperty().addListener( ( obs, oVal, nVal ) -> 
      {
        validate( (TextField)((StringProperty)obs).getBean() );
      } );
    }

    Scene scene = new Scene( gridPane, 300, 200 ); // Create the scene and place it in the stage
    primaryStage.setTitle( "Textbox Border Example" );
    primaryStage.setScene( scene );        // Place the scene in the stage.
    primaryStage.show();                   // Display the stage.
    scene.getStylesheets().add( getClass().getClassLoader().getResource( "textfield.css" ).toString() );
  }

  private void validate( TextField tf ) 
  {
	//System.out.println( tf.getText() );  // Used to facilitate debugging.
    if ( tf.getText().isEmpty() || tf.getText().matches( "(\\d*)" ) ) 
    {
    	tf.getStyleClass().clear();        // Remove red border.
    	tf.getStyleClass().addAll("text-field", "text-input");
    }
    else 
        tf.getStyleClass().add( "error" ); // Add red border.
  }

  public static void main( String[] args ) { launch( args ); }
}

/*
// Previous attempt.
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class textBoxBackground extends Application {
  // Setup 4 textfields.
  private final TextField[] textFields = new TextField[4];
        
   // JavaFX application start method.
   @Override
   public void start( Stage primaryStage ) {
     // Input and output label texts.
     final String[] tfLabels = new String[] { "Textfield #1:", "Textfield #2:", "Textfield #3:", "Textfield #4:" };

     // Grid pane column and row positions for textfields and labels.
     final int[] columns = new int[] { 1, 1, 1, 1 };
     final int[] rows = new int[] { 0, 1, 2, 3 };

     // Style changes based upon default modena.css
     setUserAgentStylesheet( STYLESHEET_MODENA );

     // Create UI.
     GridPane gridPane = new GridPane();
     gridPane.setAlignment( Pos.CENTER );
     gridPane.setHgap( 5 );
     gridPane.setVgap( 5 );
 
     // Setup 4 example textfields.
     for ( int i=0; i<4; i++ ) {
       textFields[i] = new TextField();
       gridPane.add( textFields[i], columns[i], rows[i] );
       gridPane.add( new Label( tfLabels[i] ), columns[i] - 1, rows[i] );
       textFields[i].setAlignment( Pos.BOTTOM_RIGHT );
       // Listeners draw textfields when new data is entered.
       textFields[i].textProperty().addListener( ( observable, oldValue, newValue ) -> {
         setTextFieldBackgroundColor( (TextField)((StringProperty)observable).getBean() );
       } );
       // Update textfield border and background style.
       textFields[i].setStyle( "-fx-background-color: black, -fx-control-inner-background, -fx-faint-focus-color," +
                               "linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background);" );
     }

     // Create the scene and place it in the stage
     Scene scene = new Scene( gridPane, 300, 200 );
     // Set window title.
     primaryStage.setTitle( "Textbox Border Example" );
     primaryStage.setScene( scene ); // Place the scene in the stage.
     primaryStage.show();            // Display the stage.
   }

    // Change textfield color depending upon character validity.
   private void setTextFieldBackgroundColor( TextField tf ) {
     String s = "red"; // Assume invalid characters present.
     if ( tf.getText().isEmpty() || tf.getText().matches( "(\\d*)" ) )
       s = "-fx-control-inner-background"; // All chars are valid.
     // Update textfield border and background style.
     tf.setStyle( "-fx-background-color: black, -fx-control-inner-background, -fx-faint-focus-color," +
                  "linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), " + s + " );" );
   }

   public static void main( String[] args ) { launch( args ); }
}
*/


