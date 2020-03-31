package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    //Variavel de Conexão
    private Connection con;

    //Método Construtor Conexão
    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Método Cadastrar Produto
    public void cadastrar(Produtos obj) {

        try {

            //1º Passo - Criar o comando SQL
            String sql = "insert into tb_produtos(descricao,preco,qtd_estoque,for_id) values(?,?,?,?)";

            //2ºPasso - Conectar com o banco de dados e organizar comandos sql
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto Cadastrado Com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }
    
    //Método Alterar Produto
    public void alterar(Produtos obj) {

        try {

            //1º Passo - Criar o comando SQL
            String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? where id=?";

            //2ºPasso - Conectar com o banco de dados e organizar comandos sql
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            
            stmt.setInt(4, obj.getFornecedor().getId());
            
            stmt.setInt(5, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto Alterado Com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }
    
    //Método Excluir Produtos
    public void excluirProdutos(Produtos obj){
        try {
            
            String sql = "delete from tb_produtos where id=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Produto Excluido com sucesso!");
        } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }

    //Metodo listar Produtos
    public List<Produtos> listarProdutos() {
        try {

            //1 passo criar a lista
            List<Produtos> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id)";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
    
     //Método Buscar Fornecedores por Nome
        public List<Produtos> buscaProdutosPorNome(String nome) {
        try {
            //1Passo criar a lista
            List<Produtos> lista = new ArrayList<>();

            //2Passo - criar o sql e executar
            
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString(("f.nome")));
                
                obj.setFornecedor(f);
                
                lista.add(obj);

            }
            
            return lista;

        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null,"Erro : "+erro);
            return null;
            
        }
    
        }
        
        //metodo consultaProduto por Nome
    public Produtos consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.

            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao =  ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }
    
    //metodo buscaProduto  por Codigo
    public Produtos buscaPorCodigo(int id) {
        try {
            //1 passo - criar o sql , organizar e executar.

            String sql = "select * from tb_produtos where id =  ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));

            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }
    
    //Método que da baixa no estoque
    public void baixaEstoque(int id,int qtd_nova){
        
        try {
            
            String sql = "update tb_produtos set qtd_estoque=? where id=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            
            stmt.execute();
            stmt.close();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro: " + e);
        }
        
    }
    
    //Método que retorna o estoque atual de um produto
    public int retornaEstoqueAtual(int id){
        
        try {
            
            int qtd_estoque = 0;
            
            String sql = "SELECT qtd_estoque from tb_produtos where id=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                
                qtd_estoque = (rs.getInt("qtd_estoque"));
                
            }
            return qtd_estoque;
            
        } catch (SQLException e) {
        throw new RuntimeException(e);
        }
        
        
        
    }
    

}
