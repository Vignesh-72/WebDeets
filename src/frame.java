import java.awt.*;
import javax.swing.*;

public class frame {

    private
    JFrame frame;
    JTextField textField;
    JPanel panel;
    JLabel label;
    public
        frame(){

            frame = new JFrame("Frame");
            frame.setDefaultCloseOperation(3);
            frame.setBounds(50,50,650,550);
            frame.setLayout(new FlowLayout());
            frame.setBackground(Color.LIGHT_GRAY);
            set_panel();
            frame.setVisible(true);
        }
        public void set_panel(){
            JLabel label = new JLabel();
            label.setText("null");
            label.setHorizontalAlignment(JLabel.RIGHT);
            label.setVerticalAlignment(JLabel.BOTTOM);
            label.setBounds(50,50,75,75);
            frame.add(label);
        }
        public void text_Field(){
            
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,50));
        textField.setFont(new Font("Comic sans",Font.BOLD,25));
        textField.setForeground(Color.white);
        textField.setBackground(Color.black);
        panel.add(textField);
        frame.add(panel);
        }

}
