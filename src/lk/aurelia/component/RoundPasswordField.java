/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.aurelia.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import javax.swing.JPasswordField;

/**
 *
 * @author Akila_Ya
 */
public class RoundPasswordField extends JPasswordField{
    
    public RoundPasswordField(){
        init();
    }
    
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true; arc:999; margin:5, 10, 5, 10");
        this.setBackground(new Color(211,231,240));
        this.setForeground(new Color(4,79,118));
    }
    
}
