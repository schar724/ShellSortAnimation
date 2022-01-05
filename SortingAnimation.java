import java.awt.Color;
import java.util.Random;

import org.piccolo2d.activities.PActivity;
import org.piccolo2d.nodes.PText;

public class SortingAnimation extends AnimationScreen {
    private static final long serialVersionUID = 1L;

    private static final int totalWidth = 500;
    private static final int totalHeight = 300;
    private static final int baseY = 50;

    private static PText header;

    public static ShellSort shell = new ShellSort();
    private static TextBoxNode[] myTextBoxes = new TextBoxNode[shell.array.length];

 @Override
 public void addInitialNodes() {
     this.setBounds(0, 0, totalWidth, totalHeight);

     // add background box
     addColouredBox(0, 0, totalWidth, totalHeight, Color.getHSBColor(totalHeight, serialVersionUID*22, baseY/51));

     // add header text
     header = addText(100, 0, "========================================");
     header.setTextPaint(Color.CYAN);
     header = addText(100, 10, " ======== SHELL SORTING IS COOL! =======");
     header.setTextPaint(Color.CYAN);
     header = addText(100, 20, "========================================");
     header.setTextPaint(Color.CYAN);
     header = addText(0,235, "By Scott, Daniel and Brock");
     header.setTextPaint(Color.CYAN);

     int xCord = 25;
     // System.out.println(shell.arraySize);
     for (int i = 0; i < shell.array.length; i++) {
         String j = shell.array[i].toString();
         myTextBoxes[i] = addTextBox(0, 0, 30, 30, j, xCord);

         myTextBoxes[i].animateToPositionScaleRotation(xCord, baseY, 1, 0, 0);
         myTextBoxes[i].setPaint(Color.WHITE);
         myTextBoxes[i].setTextPaint(Color.BLACK);
         xCord = xCord + 65;

     }
 }

 public static void main(String[] args) {
     SortingAnimation screen = new SortingAnimation();

     // wait for initialization before animating
     screen.waitForInitialization();

     shell.shellSort();

     for (int i = 0; i < shell.array.length; i++) {
         System.out.println(shell.array[i].toString());
     }

     System.out.println("******");

     for (int i = 0; i < shell.array.length; i++) {
         System.out.println(myTextBoxes[i].getText());
     }
 }

 private static void swap(int nodeIndex1, int nodeIndex2) {

     int yLowered = baseY + 50;
     int x1 = myTextBoxes[nodeIndex1].getXCord();
     int x2 = myTextBoxes[nodeIndex2].getXCord();

     myTextBoxes[nodeIndex1].setPaint(Color.PINK);
     myTextBoxes[nodeIndex2].setPaint(Color.PINK);

     waitForActivity(myTextBoxes[nodeIndex1].animateToPositionScaleRotation(x1, yLowered, 1, 0, 750));
     waitForActivity(myTextBoxes[nodeIndex2].animateToPositionScaleRotation(x2, yLowered, 1, 0, 750));

     myTextBoxes[nodeIndex1].setPaint(Color.GREEN);
     myTextBoxes[nodeIndex2].setPaint(Color.GREEN);

     try {
         Thread.sleep(300);
     } catch (InterruptedException e) {
		e.printStackTrace();
     }

     myTextBoxes[nodeIndex1].setPaint(Color.PINK);
     myTextBoxes[nodeIndex2].setPaint(Color.PINK);

     waitForActivity(myTextBoxes[nodeIndex1].animateToPositionScaleRotation(x2, baseY, 1, 0, 750));
     waitForActivity(myTextBoxes[nodeIndex2].animateToPositionScaleRotation(x1, baseY, 1, 0, 750));

     myTextBoxes[nodeIndex1].setPaint(Color.WHITE);
     myTextBoxes[nodeIndex2].setPaint(Color.WHITE);

     myTextBoxes[nodeIndex1].setXCord(x2);
     myTextBoxes[nodeIndex2].setXCord(x1);
 }

 private static void compare (int nodeIndex1, int nodeIndex2) {

     int yLowered = baseY + 50;
     int x1 = myTextBoxes[nodeIndex1].getXCord();
     int x2 = myTextBoxes[nodeIndex2].getXCord();

     myTextBoxes[nodeIndex1].setPaint(Color.PINK);
     myTextBoxes[nodeIndex2].setPaint(Color.PINK);

     waitForActivity(myTextBoxes[nodeIndex1].animateToPositionScaleRotation(x1, yLowered, 1, 0, 750));
     waitForActivity(myTextBoxes[nodeIndex2].animateToPositionScaleRotation(x2, yLowered, 1, 0, 750));


     myTextBoxes[nodeIndex1].setPaint(Color.RED);
     myTextBoxes[nodeIndex2].setPaint(Color.RED);

     try {
         Thread.sleep(300);
     } catch (InterruptedException e) {
		e.printStackTrace();
     }

     myTextBoxes[nodeIndex1].setPaint(Color.PINK);
     myTextBoxes[nodeIndex2].setPaint(Color.PINK);

     waitForActivity(myTextBoxes[nodeIndex1].animateToPositionScaleRotation(x1, baseY, 1, 0, 750));
     waitForActivity(myTextBoxes[nodeIndex2].animateToPositionScaleRotation(x2, baseY, 1, 0, 750));

     myTextBoxes[nodeIndex1].setPaint(Color.WHITE);
     myTextBoxes[nodeIndex2].setPaint(Color.WHITE);
 }

 private static void waitForActivity(PActivity activity) {
     long endTime = activity.getStartTime() + activity.getDuration();
     while (System.currentTimeMillis() < endTime) {
         try {
             Thread.sleep(100);
         } catch (InterruptedException e) {
             // Whatever, I do what I want.
         }
     }
 }

 public static class ShellSort {


     Random rand = new Random();

     // Obtain a number between [0 - 49].
     int n1 = rand.nextInt(50);
     int n2 = rand.nextInt(50);
     int n3 = rand.nextInt(50);
     int n4 = rand.nextInt(50);
     int n5 = rand.nextInt(50);
     int n6 = rand.nextInt(50);
     int n7 = rand.nextInt(50);

     public Integer[] array = { n1, n2, n3, n4, n5, n6,n7};

     public void shellSort() {
         TextBoxNode boxTemp;
         Boolean swapped = false;
         for (int gap = array.length / 2; gap > 0; gap /= 2) {

             for (int i = gap; i < array.length; i++) {

                 int temp = array[i];
                 boxTemp = myTextBoxes[i];
                 TextBoxNode tempy;
                 int boxTempValue = Integer.parseInt(boxTemp.getText());

                 int j;
                 for (j = i; j >= gap && Integer.parseInt(myTextBoxes[j - gap].getText()) > boxTempValue; j -= gap) {
                     tempy = myTextBoxes[j];
                     array[j] = array[j - gap];
                     swap(j, j - gap);
                     myTextBoxes[j] = myTextBoxes[j - gap];
                     myTextBoxes[j - gap] = tempy;
                     swapped = true;
                 }
                 if (swapped == false) {
                     compare(j, j - gap);
                 }
                 swapped = false;
                 array[j] = temp;
                 myTextBoxes[j] = boxTemp;

             }
         }
     }
  }
}