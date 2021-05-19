/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettotecnologie;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucab
 */
public class RepaintInfo extends Thread{
    
    ControlloPiante f;

    public RepaintInfo(ControlloPiante f) {
        this.f = f;
    }

    @Override
    public void run() {
        while(true){
            f.repaint();
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(RepaintInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
}
