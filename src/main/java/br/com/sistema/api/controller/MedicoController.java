package br.com.sistema.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.sistema.api.model.medico.DadosAtualizacaoMedico;
import br.com.sistema.api.model.medico.DadosCadastroMedico;
import br.com.sistema.api.model.medico.Medico;
import br.com.sistema.api.model.medico.MedicoRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    // CRUD BÁSICO
    @PostMapping("/cadastro") // localhost:8080/medico/cadastro
    @Transactional
    public void cadastrarMedico(@RequestBody DadosCadastroMedico dados) {
        medicoRepository.save(new Medico(dados));
    }

    // GET Request -> Response -> Ex: Tela home
    @GetMapping("/listar-todos") // Aponta para localhost:8080/medico
    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    // DEL - Exclusão real
    @DeleteMapping("/deletar/{id}") // Aponta para localhost:8080/medico/deletar/1
    @Transactional
    public void excluir(@PathVariable Integer id){
        medicoRepository.deleteById(id);
    } 
    // DEL - Exclusão lógica
    @DeleteMapping("/alterar-status/{id}")
    @Transactional 
    public void alterarStatus(@PathVariable Integer id) {
        var medico = medicoRepository.getReferenceById(id); // O var está sendo utilizado para que assim que o id for chamado e acessado, eu pegue todos os atributos e guarde agora no objeto medico.
        medico.excluirLogico();
    }

    // PUT 
    @PutMapping("/atualizar")
    @Transactional 
    public void atualizar(@RequestBody DadosAtualizacaoMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }


}
