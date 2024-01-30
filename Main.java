import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    BufferedImage image1;
    BufferedImage image2;

    public static void main(String[] args) {
        System.out.println("AADLSLDA");
        Main m = new Main();
    }

    public Main() {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        double windowWidth = 1920.0;
        double windowHeight = 1200.0;


        BufferedImage combinedImage;
        try {

            


            image1 = ImageIO.read(new FileInputStream("image1.png"));
            double wid1 = image1.getWidth();
            double hei1 = image1.getHeight();

            image2 = ImageIO.read(new FileInputStream("image2.png"));
            double wid2 = image2.getWidth();
            double hei2 = image2.getHeight();

            

            AffineTransform at = new AffineTransform();

            AffineTransform at1 = new AffineTransform();


            at1.scale(0.6, 0.6);

            wid1=wid1*0.6; hei1=hei1*0.6;



            double scaleIndex = 0.8;

            // at.translate((wid1/2)-(wid2/2), 319-(hei2/2));
            // at.rotate(Math.toRadians(180));
            // at.translate( -((wid1/2)-(wid2/2)), -(319-(hei2/2)));

            if (scaleIndex * hei2 >= hei1*0.795) {
                System.out.println(
                        "You might make a scaleIndex lower, because currently your element oversteps the 954px limit!\n");
                return;
            } else {

                System.out.println("Before scale: Wid2 is: " + wid2 + " hei2 is: " + hei2);

                at.scale(scaleIndex, scaleIndex);

                double wid2Scal = wid2 * scaleIndex;
                double hei2Scal = hei2 * scaleIndex;

                at.translate( ((wid1 / 2) - (wid2Scal / 2)) / scaleIndex,  (hei1*0.795-hei2Scal)/4);

            }

            


            // 954, 319
            // center of mass trapezoid is ~ 375px, 414px

            
            // at.translate( (wid1/2)- (1.5*wid2/2), 490-(hei2/2));
            // at.rotate(Math.toRadians(180));

            System.out.println("Wid2 is: " + scaleIndex * wid2 + " hei2 is: " + scaleIndex * hei2);

            combinedImage = new BufferedImage(1152, 720, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = combinedImage.createGraphics();

            g.drawImage(image1, at1, null);

            g.drawImage(image2, at, null);

            g.dispose();

            JLabel label = new JLabel();

            window.add(label);
            label.setIcon(new ImageIcon(combinedImage));

        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }

        window.pack();
        window.setVisible(true);

    }
}
