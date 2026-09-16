package org.example.grupo_7_praticaatdd.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.example.grupo_7_praticaatdd.domain.VO.EmailUsuario;
import org.example.grupo_7_praticaatdd.domain.VO.NomeUsuario;
import org.example.grupo_7_praticaatdd.domain.VO.SenhaCriptografada;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private NomeUsuario nome;

    @Embedded
    private EmailUsuario email;

    @Embedded
    private SenhaCriptografada senha;

    // O usuario e o dono do relacionamento, entao a FK fica na tabela de usuarios.
    // cascade = ALL salva a assinatura junto com o usuario.
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "assinatura_id")
    private Assinatura assinatura;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matricula> matriculas = new ArrayList<>();

    protected Usuario() {
    }

    // A senha recebida aqui ja deve chegar criptografada pela camada de service.
    public Usuario(String nome, String email, String senhaCriptografada) {
        this.nome = new NomeUsuario(nome);
        this.email = new EmailUsuario(email);
        this.senha = new SenhaCriptografada(senhaCriptografada);
        this.assinatura = new Assinatura();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome.getValor();
    }

    public String getEmail() {
        return email.getValor();
    }

    public String getSenha() {
        return senha.getValor();
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void adicionarMatricula(Matricula matricula) {
        this.matriculas.add(matricula);
    }

    public Matricula matricularEm(Curso curso) {
        Matricula matricula = new Matricula(this, curso, false);
        adicionarMatricula(matricula);
        return matricula;
    }

    public void concluirCurso(Matricula matricula, double notaFinal) {
        matricula.concluir(notaFinal);
        if (matricula.concluidoComAproveitamento()) {
            this.assinatura.registrarConclusaoComSucesso();
        }
    }

    public Matricula desbloquearCurso(Curso curso) {
        this.assinatura.consumirCredito();
        Matricula matricula = new Matricula(this, curso, true);
        adicionarMatricula(matricula);
        return matricula;
    }
}
