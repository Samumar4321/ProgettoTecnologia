/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettotecnologie;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ThreadDisegna extends Thread {

    private boolean finito = false;
    private NewJFrame f;
    private boolean Umax = false;
    private boolean Umin = false;
    private boolean Tmin = false;
    private boolean Tmax = false;
    private boolean luce = false;
    private javax.swing.JTextField txtLuce;
    private javax.swing.JTextField txtTmax;
    private javax.swing.JTextField txtTmin;
    private javax.swing.JTextField txtUmax;
    private javax.swing.JTextField txtUmin;
    private javax.swing.JButton btnSalva;

    public ThreadDisegna(NewJFrame f, JTextField txtLuce, JTextField txtTmax, JTextField txtTmin, JTextField txtUmax, JTextField txtUmin, JButton btnSalva) {
        this.f = f;
        this.txtLuce = txtLuce;
        this.txtTmax = txtTmax;
        this.txtTmin = txtTmin;
        this.txtUmax = txtUmax;
        this.txtUmin = txtUmin;
        this.btnSalva = btnSalva;
    }

    public boolean checkInt(String s) {
        boolean check = true;
        if (s == null) {
            check = false;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9') {
                check = false;
            }
        }
        if (s.length() == 0) {
            check = false;
        }
        return check;
    }

    private void controllo() {
        Tmax = checkInt(txtTmax.getText());
        Tmin = checkInt(txtTmin.getText());
        Umax = checkInt(txtUmax.getText());
        Umin = checkInt(txtUmin.getText());
        luce = checkInt(txtLuce.getText());
        if (Tmax && Tmin && Umax && Umin && luce) {
            btnSalva.enable();
        } else {
            btnSalva.disable();
        }
    }

    @Override
    public void run() {

        while (true) {
            while (finito) {
                controllo();
                f.repaint();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadDisegna.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public boolean isFinito() {
        return finito;
    }

    public void setFinito(boolean finito) {
        this.finito = finito;
    }

    public NewJFrame getF() {
        return f;
    }

    public void setF(NewJFrame f) {
        this.f = f;
    }

    public boolean isUmax() {
        return Umax;
    }

    public void setUmax(boolean Umax) {
        this.Umax = Umax;
    }

    public boolean isUmin() {
        return Umin;
    }

    public void setUmin(boolean Umin) {
        this.Umin = Umin;
    }

    public boolean isTmin() {
        return Tmin;
    }

    public void setTmin(boolean Tmin) {
        this.Tmin = Tmin;
    }

    public boolean isTmax() {
        return Tmax;
    }

    public void setTmax(boolean Tmax) {
        this.Tmax = Tmax;
    }

    public boolean isLuce() {
        return luce;
    }

    public void setLuce(boolean luce) {
        this.luce = luce;
    }

    public JTextField getTxtLuce() {
        return txtLuce;
    }

    public void setTxtLuce(JTextField txtLuce) {
        this.txtLuce = txtLuce;
    }

    public JTextField getTxtTmax() {
        return txtTmax;
    }

    public void setTxtTmax(JTextField txtTmax) {
        this.txtTmax = txtTmax;
    }

    public JTextField getTxtTmin() {
        return txtTmin;
    }

    public void setTxtTmin(JTextField txtTmin) {
        this.txtTmin = txtTmin;
    }

    public JTextField getTxtUmax() {
        return txtUmax;
    }

    public void setTxtUmax(JTextField txtUmax) {
        this.txtUmax = txtUmax;
    }

    public JTextField getTxtUmin() {
        return txtUmin;
    }

    public void setTxtUmin(JTextField txtUmin) {
        this.txtUmin = txtUmin;
    }

    public JButton getBtnSalva() {
        return btnSalva;
    }

    public void setBtnSalva(JButton btnSalva) {
        this.btnSalva = btnSalva;
    }
    
}
