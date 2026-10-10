package br.com.sistema.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.sistema.api.model.medico.*;

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

		// GET Resquest -> Response -> Nome do Médico - map(), stream(), toList().
		@GetMapping("/listar-por-nome") // localhost:8080/medico/listar-por-nome
		public List<DadosListagemMedico> listarPorNome(@RequestBody String nome) {
			return medicoRepository.findByNomeContainingIgnoreCase(nome)
				.stream()
				.map(DadosListagemMedico::new)
				.toList();
		}

		// GET Request -> Response -> E-mail do Médico
		@GetMapping("/listar-por-email") // localhost:8080/medico/listar-por-nome
		public List<DadosListagemMedico> listarPorEmail(@RequestBody String email) {
			return medicoRepository.findByEmailContainingIgnoreCase(email)
				.stream()
				.map(DadosListagemMedico::new)
				.toList();
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
