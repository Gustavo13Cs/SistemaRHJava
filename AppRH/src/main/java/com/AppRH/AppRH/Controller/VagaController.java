package com.AppRH.AppRH.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.Models.Candidato;
import com.AppRH.AppRH.Models.Vaga;
import com.AppRH.Repository.CandidatoRepository;
import com.AppRH.Repository.VagaRepository;

import jakarta.validation.Valid;

@Controller
public class VagaController {

    private VagaRepository vr;
    private CandidatoRepository cr;

    //CADASTRA VAGA
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String form() {
        return "vaga/formVaga";
    }

    @RequestMapping(value = "/cadatrarVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarVaga";
        }

        vr.save(vaga);
        attributes.addFlashAttribute("mensagem","Vaga cadastrada com sucesso");
        return "redirect:/cadastrarVaga";
    }

    // Lista Vagas

    @RequestMapping("/vagas")
    public ModelAndView listaVagas() {

        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga>vagas = vr.findAll();
        mv.addObject("vagas", vagas);  // ta mandando pra view as vagas
        return mv;
    }

    //
    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {

        Vaga vaga = vr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("vaga/detalhesVaga");
        mv.addObject("vaga", vaga);

        Iterable<Candidato>candidatos = cr.findByVaga(vaga);
        mv.addObject("candidatos", candidatos);

        return mv;
    }

    //DELETE
    @RequestMapping("deletarVaga")
    public String deletarVaga(long codigo) {
        Vaga vaga = vr.findByCodigo(codigo);
        vr.delete(vaga);
        return "redirect:/vagas";
    }

    public String detalhesVagasPost(@PathVariable("codigo") long codigo, @Valid Candidato candidato, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem","Verifique os campos");
            return "redirect:/{codigo}";
        }

        //rg duplicado
        if (cr.findByRg(candidato.getRg()) != null) {
            attributes.addFlashAttribute("mensagem erro", "RG duplicado");
            return "redirect:/{codigo}";
        }

        Vaga vaga = vr.findByCodigo(codigo);
        candidato.setVaga(vaga);
        cr.save(candidato);
        attributes.addFlashAttribute("mensagem", "Candidato adicionado com sucesso");
        return "redirect:/{codigo}";
    }

    //DELETA CANDIDATO PELO RG


}
