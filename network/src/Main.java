

import com.company.ui.HomePage;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("network");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        HomePage homePage = new HomePage();
        homePage.placeMeuu(frame,panel);
        homePage.placeComponents(panel);

        frame.setVisible(true);
    }
}
