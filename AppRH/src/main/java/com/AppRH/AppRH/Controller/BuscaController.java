package com.AppRH.AppRH.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AppRH.repository.CandidatoRepository;
import com.AppRH.repository.DependenteRepository;
import com.AppRH.repository.FuncionarioRepository;
import com.AppRH.repository.VagaRepository;

@Controller
public class BuscaController {

    @Autowired
    private FuncionarioRepository fr;
    @Autowired
    private VagaRepository vr;
    @Autowired
    private DependenteRepository dr;
    @Autowired
    private CandidatoRepository cr;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView abrirIndex() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView buscarIndex(@RequestParam("buscar") String buscar,@RequestParam("nome") String nome) {
        
        ModelAndView mv = new ModelAndView("index");
        String mensagem = "Resultados da busca por " + buscar;

        if (nome.equals("nomefuncionario")) {
            //ta mandando a pesquisa pros funcionarios
            mv.addObject("funcionarios", fr.findByNomes(buscar));
        } else if (nome.equals("nomedependente")) {
            mv.addObject("dependentes", dr.findByNomesDependentes(buscar));
        } else if (nome.equals("nomecandidato")) {
            mv.addObject("candidatos", cr.findByNomescandidatos(buscar));
        } else if (nome.equals("titulovaga")) {
            mv.addObject("vagas", vr.findByNomesVaga(buscar));
        } else {
            mv.addObject("funcionarios", fr.findByNomes(buscar));
            mv.addObject("dependentes", dr.findByNomesDependentes(buscar));
            mv.addObject("candidatos", cr.findByNomescandidatos(buscar));
            mv.addObject("vagas", vr.findByNomesVaga(buscar));
        }

        mv.addObject("mensagem",mensagem);

        return mv;
    }
    
}
