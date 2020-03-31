package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FornecedoresDAO {
        
   //Variavel de Conexão
    private Connection con;

    //Método Construtor Conexão
    public FornecedoresDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
     //Método Cadastrar Fornecedores
    public void cadastrarFornecedores(Fornecedores obj) {

        try {

            //1 Passo - Criar o comando SQL
            String sql = "insert into tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

            //2-Passo - Conectar o BD e organizar o camando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());

            //3Passo - Executar e fechar o Comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado Com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }
    
    //Método Excluir Fornecedores
    public void excluirFornecedores(Fornecedores obj) {
        try {
            
            //1ºPasso - Criar o comando SQL
            String sql = "delete from tb_fornecedores where id=?";
            
            //2ºPasso - Conectar o BD e Organizar o Comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,obj.getId());
            
            //3ºPasso - Executar o Comando SQL
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Excluido com Sucesso!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro : "+ erro);
        }
    
    }
    
     //Método Alterar Fornecedores
    public void alterarFornecedores(Fornecedores obj) {
        
        try {

            //1 Passo - Criar o comando SQL
            String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,"
                    +"complemento=?,bairro=?,cidade=?,estado=? where id=?";
                    

            //2-Passo - Conectar o BD e organizar o camando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            
            stmt.setInt(13, obj.getId());


            //3Passo - Executar e fechar o Comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado Com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
        

    }
    
    //Método Listar Fornecedores
    public List<Fornecedores> listarFornecedores() {
        try {
            //1Passo criar a lista
            List<Fornecedores> lista = new ArrayList<>();

            //2Passo - criar o sql e executar
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                
                lista.add(obj);

            }
            
            return lista;

        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null,"Erro : "+erro);
            return null;
            
        }
    }
    
    //Método Buscar Fornecedores por Nome
        public List<Fornecedores> buscaFornecedoresPorNome(String nome) {
        try {
            //1Passo criar a lista
            List<Fornecedores> lista = new ArrayList<>();

            //2Passo - criar o sql e executar
            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                
                lista.add(obj);

            }
            
            return lista;

        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null,"Erro : "+erro);
            return null;
            
        }
    
        }
        
        ////////////////////////////////////////////////////////////////
        //metodo consultaFornecedoresPornome
      public Fornecedores consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_fornecedores where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
           Fornecedores obj = new Fornecedores();


            if (rs.next()) {

               obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));             
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
            return null;
        }
    }
           
        
    
}
