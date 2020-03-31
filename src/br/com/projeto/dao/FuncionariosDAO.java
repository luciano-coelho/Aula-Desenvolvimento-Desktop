package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Funcionarios;
import br.com.projeto.view.FrmLogin;
import br.com.projeto.view.FrmMenu;
import br.com.projeto.view.Frmmenuu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FuncionariosDAO {

    //Variavel de Conexão
    private Connection con;

    //Método Construtor Conexão
    public FuncionariosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Método Cadastrar Funcionários
    public void cadastrarFuncionarios(Funcionarios obj) {

        try {

            //1 Passo - Criar o comando SQL
            String sql = "insert into tb_funcionarios(nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //2-Passo - Conectar o BD e organizar o camando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());

            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());

            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            //3Passo - Executar e fechar o Comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado Com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }

    //Método Listar Funcionarios
    public List<Funcionarios> listarFuncionarios() {
        try {
            //1Passo criar a lista
            List<Funcionarios> lista = new ArrayList<>();

            //2Passo - criar o sql e executar
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));

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

            JOptionPane.showMessageDialog(null, "Erro : " + erro);
            return null;

        }
    }

    //Método Excluir Funcionario
    public void excluirFuncionario(Funcionarios obj) {
        try {

            //1ºPasso - Criar o comando SQL
            String sql = "delete from tb_funcionarios where id=?";

            //2ºPasso - Conectar o BD e Organizar o Comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //3ºPasso - Executar o Comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro : " + erro);
        }

    }

    //Método Alterar Funcionario
    public void alterarFuncionario(Funcionarios obj) {

        try {

            //1 Passo - Criar o comando SQL
            String sql = "update tb_funcionarios set nome=?,rg=?,cpf=?,email=?,senha=?,cargo=?,nivel_acesso=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,"
                    + "complemento=?,bairro=?,cidade=?,estado=? where id=?";

            //2-Passo - Conectar o BD e organizar o camando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());

            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());

            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            stmt.setInt(17, obj.getId());

            //3Passo - Executar e fechar o Comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado Com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    //Método Buscar Funcionario por Nome
    public List<Funcionarios> buscaFuncionario(String nome) {
        try {
            //1Passo criar a lista
            List<Funcionarios> lista = new ArrayList<>();

            //2Passo - criar o sql e executar
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));

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

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Não encontrado!");
            return null;

        }

    }
    
     //Metodo efetuaLogin
    public void efetuaLogin(String email, String senha ) {
        try {

            //1 passo - SQL
            String sql = "select * from tb_funcionarios where email = ? and senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
              

                //Caso o usuario seja do tipo admin
                if (rs.getString("nivel_acesso").equals("Administrador")) {

                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    Frmmenuu tela = new Frmmenuu();
                    tela.usuariologado = rs.getString("nome");
                    tela.setVisible(true);
                } 

//Caso o usuario seja do tipo limitado 
                else if (rs.getString("nivel_acesso").equals("Usuario")) {
                    
                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    Frmmenuu tela = new Frmmenuu();
                    tela.usuariologado = rs.getString("nome");
                    //Desabilitar os menus
                    tela.menu_posicao.setEnabled(false);
                    tela.menu_controlevendas.setEnabled(false);
                   tela.setVisible(true);

                }

            } else {
                //Dados incorretos
                JOptionPane.showMessageDialog(null, "Dados incorretos!");
                new FrmLogin().setVisible(true);

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro : " + erro);
        }

    }

    
    
    
    
}
