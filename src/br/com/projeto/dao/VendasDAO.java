
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class VendasDAO {
    
    //Variavel de Conexão
    private final Connection con;

    //Método Construtor Conexão
    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Cadastrar Venda
    public void cadastrarVenda(Vendas obj){
        try {
            String sql = "insert into tb_vendas(cliente_id,data_venda,total_venda,observacoes)values(?,?,?,?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());
            
            stmt.execute();
            stmt.close();
            
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Erro: " + e);
            
        }
        
        
    }
       
    //Retorna a ultima Venda
    public int retornaUltimaVenda(){
        try {
            int idvenda = 0;
            
            String sql = "select max(id)id from tb_vendas";
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Vendas p = new Vendas();
                
                p.setId(rs.getInt("id"));
                
                idvenda=p.getId();
            }
            return idvenda;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        
    }
    
    //Método Filtrar Vendas por Datas
    public List<Vendas>listarVendasPorPeriodo(LocalDate data_inicio, LocalDate data_fim){
        
        try {
            
            //1º Passo Criar a Lista
            List<Vendas> lista = new ArrayList<>();
            
            //2ºPasso - Criar Sql, organizar e executar
            String sql = "select v.id,date_format(v.data_venda,'%d/%m/%Y')as data_formatada,c.nome,v.total_venda,v.observacoes from tb_vendas as v "
                         + "inner join tb_clientes as c on(v.cliente_id=c.id)where v.data_venda BETWEEN? AND?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, data_inicio.toString());
            stmt.setString(2, data_fim.toString());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Vendas vendas = new Vendas();
                Clientes cliente = new Clientes();
                
                vendas.setId(rs.getInt("v.id"));
                vendas.setData_venda(rs.getString("data_formatada"));
                
                cliente.setNome(rs.getString("c.nome"));
                
                vendas.setTotal_venda(rs.getDouble("v.total_venda"));
                vendas.setObs(rs.getString("v.observacoes"));
                
                //Colcando Objeto cliente dentro do objeto vendas
                vendas.setCliente(cliente);
                
                lista.add(vendas);
                
                }
            return lista;
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro: " + e);
            
        }
        return null;
        
    }
    
    //Método que Calcula total da venda por data
    public double  retornaTotalVendaPorData(LocalDate data_venda){
        
        try {
            
            double totalvenda = 0;
            
            String sql = "select sum(total_venda) as total from tb_vendas where data_venda = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_venda.toString());
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                totalvenda = rs.getDouble("total");
                
            }
            
            return totalvenda;
            
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        
    }
    

}
