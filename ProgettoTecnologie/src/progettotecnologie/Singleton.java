/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettotecnologie;

import com.fazecast.jSerialComm.SerialPort;
import java.util.ArrayList;

/**
 *
 * @author lucab
 */
public class Singleton {
    ArrayList<Seriale> VettSerial;
    public static Singleton _instance = null;

    private Singleton() {
        VettSerial = new ArrayList<Seriale>();
    }
    
    public void addSerialPort(Seriale sp){
        VettSerial.add(sp);
    }
    
    public Seriale getSerial(int pos){
        return VettSerial.get(pos);
    }
    
    public static Singleton GetInstance()
    {
        if(_instance == null)
        {
            synchronized(Singleton.class)
            {
                if(_instance == null)
                {
                    _instance = new Singleton();
                }
            }
        }
        return _instance;
    }
}
