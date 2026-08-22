/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.aurelia.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.*;
import javax.swing.JTextArea;

/**
 *
 * @author Huawei
 */
public class RoundTextArea extends JTextArea{
    
        public RoundTextArea(){
        init();
    }
    
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "margin:5, 10, 5, 10"); // TOP LEFT BOTTOM RIGHT
        this.setBackground(new Color(211,231,240));
        this.setForeground(new Color(4,79,118));
        
    }
    
}
