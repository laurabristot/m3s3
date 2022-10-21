package br.com.futurodev.semana3.controllers;

import br.com.futurodev.semana3.model.PessoaModel;
import br.com.futurodev.semana3.repository.PessoaRepository;
import org.hibernate.collection.internal.PersistentSortedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<PessoaModel> cadastrar (@RequestBody PessoaModel pessoaModel) {
        PessoaModel pessoa = pessoaRepository.save(pessoaModel);
        return new ResponseEntity<PessoaModel>(pessoa, HttpStatus.CREATED);
    }

    @PutMapping(value = "/", produces = "application/json")
    private ResponseEntity<PessoaModel> atualizar (@RequestBody PessoaModel pessoaModel){
        PessoaModel pessoa = pessoaRepository.save(pessoaModel);
        return new ResponseEntity<PessoaModel>(pessoa, HttpStatus.OK);
    }

    @GetMapping(value = "/{idPessoa}")
    public ResponseEntity<PessoaModel> buscarById(@PathVariable(value = "idPessoa") Long idPessoa) {
        PessoaModel pessoa = pessoaRepository.findById(idPessoa).get();
        return new ResponseEntity<PessoaModel>(pessoa, HttpStatus.OK);
    }


    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idPessoa){
        pessoaRepository.deleteById(idPessoa);
        return new ResponseEntity<String>("contato deletado com sucesso!", HttpStatus.OK);
    }


    @GetMapping(value= "/buscar-por-nome", produces = "application/json")
    public ResponseEntity<List<PessoaModel>> getPessoaByName(@RequestParam(name = "nome") String nome){
        List<PessoaModel> pessoa = pessoaRepository.getPessoaByName(nome);
        return new ResponseEntity<List<PessoaModel>>(pessoa, HttpStatus.OK);
    }

}