package Login;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.*;
import static Login.Usuario.usuarioSistema;
import java.awt.HeadlessException;

public class TelaAlteracao extends JFrame {

    private final JPanel tela;
    private final JTextField txtNome;
    private final JPasswordField passAtual;
    private final JPasswordField passSenha;
    private final JPasswordField confPassSenha;

    public TelaAlteracao() {

        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Senac - Alteração");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212);

        tela = new JPanel();
        tela.setBackground(SystemColor.gray);
        setContentPane(tela);
        tela.setLayout(null);

        JLabel lblIdentificacao = new JLabel("Informar campos para alteração");
        lblIdentificacao.setBounds(60, 0, 500, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        tela.add(lblIdentificacao);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(24, 35, 100, 15);
        tela.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 35, 218, 20);
        tela.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblSenhaAtual = new JLabel("Senha Atual:");
        lblSenhaAtual.setBounds(24, 60, 100, 15);
        tela.add(lblSenhaAtual);

        passAtual = new JPasswordField();
        passAtual.setBounds(120, 60, 219, 19);
        tela.add(passAtual);

        JLabel lblNovaSenha = new JLabel("Nova Senha:");
        lblNovaSenha.setBounds(24, 85, 100, 15);
        tela.add(lblNovaSenha);

        passSenha = new JPasswordField();
        passSenha.setBounds(120, 85, 219, 19);
        tela.add(passSenha);

        JLabel lblConfSenha = new JLabel("Confirmar Senha:");
        lblConfSenha.setBounds(24, 110, 130, 15);
        tela.add(lblConfSenha);

        confPassSenha = new JPasswordField();
        confPassSenha.setBounds(120, 110, 219, 19);
        tela.add(confPassSenha);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(200, 136, 117, 25);
        tela.add(btnAlterar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 136, 117, 25);
        tela.add(btnCancelar);

        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaInicio telaIni = new TelaInicio();
            telaIni.setVisible(true);
            dispose();
        });

        btnAlterar.addActionListener((ActionEvent e) -> {
            try {
                Usuario usu = new Usuario();
                usu.setUsuario(usuarioSistema); // Define o usuário logado

                // Nome não pode estar vazio
                if ("".equals(txtNome.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Campo nome do usuário precisa ser informado!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    txtNome.grabFocus();
                    return;
                }

                // Senha atual não pode estar vazia
                if ("".equals(passAtual.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Campo senha atual precisa ser informado!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passAtual.grabFocus();
                    return;
                }

                // Verificar se a senha atual está correta
                if (!usu.verificaUsuario(usuarioSistema, passAtual.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Senha atual inválida, verifique!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passAtual.grabFocus();
                    return;
                }

                // Verificar se nova senha e confirmação são iguais
                if (!passSenha.getText().equals(confPassSenha.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "A nova senha e a confirmação não coincidem!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    confPassSenha.grabFocus();
                    return;
                }

                // Dados válidos, atualizar
                usu.setNome(txtNome.getText());
                usu.setSenha(passSenha.getText());

                if (usu.alteraUsuario(usu.getNome(), usu.getUsuario(), usu.getSenha())) {
                    JOptionPane.showMessageDialog(null,
                            "Dados do usuário alterados com sucesso!",
                            "Alteração",
                            JOptionPane.INFORMATION_MESSAGE);

                    TelaLogin tLogin = new TelaLogin();
                    tLogin.abreTela();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Erro ao atualizar o usuário!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (HeadlessException ec) {
                System.out.println("Erro ao alterar o usuário: " + ec.getMessage());
            }
        });

        // Atribui o nome atual ao campo nome
        txtNome.setText(Usuario.nomeUsuario);
    }

    public void abreTela() {
        TelaAlteracao telaAlteracao = new TelaAlteracao();
        telaAlteracao.setVisible(true);
    }
}
