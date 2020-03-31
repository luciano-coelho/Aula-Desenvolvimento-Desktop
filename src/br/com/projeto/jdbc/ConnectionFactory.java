package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    public Connection getConnection(){
        
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/bdvendas","aulateste","123");
            
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
        
    }
    
    
}
