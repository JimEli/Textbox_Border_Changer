# Textbox Background and Border Color Change

Java demonstration of how to change a textbox background and border based upon input validation. This turned out to be much harder than it seems.

This is a complicated topic. JavaFX8 draws a textbox (or textfield) as a “region”, with backgrounds and borders that are style-able via stylesheets (css). A Region is typically a rounded rectangle, though this can be modified to be an arbitrary shape. All Regions have the same set of CSS properties as described here. To further complicate matters, the properties for the textbox are inherited from the region properties in a somewhat confusing cascading manner.

  https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html#region

Each Region (or textbox) consists of several layers, painted from bottom to top, in this order:
1.	background fills
2.	background images
3.	border strokes
4.	border images
5.	contents

The border is actually overlaid upon the background and inset from the outside edge of the textbox. Therefore, if you change the background, you’ve overdrawn the border. Also, in the default textbox, if you look closely you should notice the top 5 pixels are drawn in a gradient-like shadow from the top edge. If that’s not enough, when a textbox has the focus, it is drawn using a different set of properties.

The properties for how a textfield is drawn can be found in the default stylesheet (modena.css), which is inside the JavaFX runtime JAR file, *jfxrt.jar*. This style sheet defines styles for the root node and the UI controls. To view this file, go to the *\jre\lib\ext* directory under the directory in which the Java Development Kit (JDK) is installed. Use the following command to extract the style sheet from the JAR file:

`jar xf jfxrt.jar com/sun/javafx/scene/control/skin/modena/modena.css`

Having covered all that background (pun intended), in order to properly draw a textbox (and make it look normal) you need to manipulate the textbox’s (region) properties. This is accomplished using the textfield’s `.setStyle()` method. The complete set of properties that define the textbox border and background are the following:

`
"-fx-background-color: -fx-focus-color, -fx-control-inner-background, -fx-faint-focus-color, "linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background"
`

You’ll notice many of these properties are inherited from other controls, in the cascading manner I described above.

My example code shows how to manipulate these properties to properly change the background color while maintaining a border, even when the focus changes.
