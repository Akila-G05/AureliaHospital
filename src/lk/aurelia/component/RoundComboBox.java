/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.aurelia.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import javax.swing.JComboBox;

/**
 *
 * @author Akila_Ya
 */
public class RoundComboBox extends JComboBox<Object>{
    
    public RoundComboBox(){
        init();
    }
    
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:999;"); // TOP LEFT BOTTOM RIGHT
        this.setBackground(new Color(211,231,240));
        this.setForeground(new Color(4,79,118));
        
        this.addItem("Select ");
    }
    
}
