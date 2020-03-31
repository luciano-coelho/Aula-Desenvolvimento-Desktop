package br.com.projeto.model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Utilitarios {
    
    //Método Limpar Campos
    public void limpaTela(JPanel container){
        Component components[] = container.getComponents();
        for(Component component : components){
            if(component instanceof JTextField){
                ((JTextField)component).setText(null);
            }
        }
    }
    
    
}
