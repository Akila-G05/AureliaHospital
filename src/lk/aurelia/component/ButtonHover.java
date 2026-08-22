/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.aurelia.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

/**
 *
 * @author Akila_Ya
 */
public class ButtonHover {
    
    public static MouseListener ml = new MouseAdapter() {

//        @Override
//        public void mouseEntered(java.awt.event.MouseEvent evt){            
//            Component c = evt.getComponent();                   
//            c.setBackground(new Color(2,49,73));
//        }                                      
//
//        @Override
//        public void mouseExited(java.awt.event.MouseEvent evt){                                      
//            Component c = evt.getComponent();
//            c.setBackground(new Color(4,79,118));
//        }  
        
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt){
            
            Component c = evt.getComponent();                   
            c.setBackground(new Color(2,49,73));
        }
    
    };
    
}
