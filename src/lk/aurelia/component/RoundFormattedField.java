package lk.aurelia.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import javax.swing.JFormattedTextField;


public class RoundFormattedField extends JFormattedTextField{
    public void RoundFormattedField(){
        
    }
    
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:999; margin:5, 10, 5, 10"); // TOP LEFT BOTTOM RIGHT
        this.setBackground(new Color(211,231,240));
        this.setForeground(new Color(4,79,118));
    }
}
