package com.AppRH.AppRH.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.Models.Dependente;
import com.AppRH.AppRH.Models.Funcionario;
import com.AppRH.repository.DependenteRepository;
import com.AppRH.repository.FuncionarioRepository;


import jakarta.validation.Valid;

@Controller
public class FuncionarioController {

    @Autowired
	private FuncionarioRepository fr;

	@Autowired
	private DependenteRepository dr;

	// chamo o form de casdatrar funcionários
	@RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.GET)
	public String form() {
		return "funcionario/form-funcionario";
	}

	// cadastra funcionários
	@RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.POST)
	public String form(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarFuncionario";
		}

		fr.save(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso!");
		return "redirect:/cadastrarFuncionario";
	}

    //listar funcionario
    @RequestMapping("/funcionarios")
    public ModelAndView listaFuncionario(){
        ModelAndView mv = new ModelAndView("funcionario/lista-funcionario");

        Iterable<Funcionario> funcionarios = fr.findAll();
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    // listar dependentes
    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.GET)
    public ModelAndView dependentes(@PathVariable("id") long id) {
        Funcionario funcionario = fr.findById(id);
        ModelAndView mv = new ModelAndView("funcionario/dependentes");
        mv.addObject("funcionarios",funcionario);

        //lista de dependentes baseado no funcionario
        Iterable<Dependente> dependentes = dr.findByFuncionario(funcionario);
        mv.addObject("dependentes", dependentes);
        return mv;
    }

    //Adicionar dependentes
    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.POST)
    public String dependentesPost(@PathVariable("id") long id,Dependente dependentes, 
    BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os Campos");
            return "redirect:/dependentes/{id}";
        }

        if (dr.findByCpf(dependentes.getCpf()) != null) {
            attributes.addFlashAttribute("mensagem_erro", "CPF DUPLICADO");
            return "redirect:/dependentes/{id}";
        }

        Funcionario funcionario = fr.findById(id);
        dependentes.setFuncionario(funcionario);
        dr.save(dependentes);
        attributes.addFlashAttribute("mensagem","Dependente adicionado com sucesso");
        return "redirect:/dependentes/{id}";
    }

    //deletar funcionario
    @RequestMapping("/deletarFuncionario")
    public String deletarFuncionario(long id){
        Funcionario funcionario = fr.findById(id);
        fr.delete(funcionario);
        return "redirect:/funcionarios";
    }

    //Metodos que atualizam funcionario
    //form
    @RequestMapping(value = "/editar-funcionario", method = RequestMethod.GET)
    public ModelAndView editarFuncionario(long id) {
        Funcionario funcionario = fr.findById(id);
        ModelAndView mv = new ModelAndView("funcionario/update-funcionario");
        mv.addObject("funcionario", funcionario);
        return mv;
    }

    //update Funcionario
    @RequestMapping(value ="/editar-funcionario", method = RequestMethod.POST)
    public String updateFuncionario(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {

        fr.save(funcionario);
        attributes.addFlashAttribute("successs","Funcionario alterado com sucesso!");

        //pra n perder a url da variavel
        long idLong = funcionario.getId();
        String id = "" + idLong;
        return "redirect:/dependentes/" + id;
    }

    //deletar dependente
    @RequestMapping("/deletarDependente")
    public String deletarDependente(String cpf) {
        Dependente dependentes = dr.findByCpf(cpf);

        Funcionario funcionario = dependentes.getFuncionario();
        // pra voltar pra mesma pagina depois de deletar
        String codigo = "" + funcionario.getId();
        dr.delete(dependentes);
        return "redirect:/dependentes/" + codigo;
    }
    
}
