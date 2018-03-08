/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertnumbers;

import com.alee.laf.WebLookAndFeel;
import javax.swing.SwingUtilities;

/**
 *
 * @author tienanhbui
 */
public class ConvertNumbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater (() -> {
            WebLookAndFeel.install ();
            
            MainScreen app = new MainScreen();
            app.setTitle("Number Conversion v0.1");
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
        
    }
    
}
