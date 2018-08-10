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
