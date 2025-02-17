package com.example.agriario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AgriarioController {

        @Autowired
        private TractorRepository tractorRepository;

        // INDEX: Listar tractores
        @GetMapping("/index")
        public String verIndex(Model model) {
                List<Tractor> tractores = tractorRepository.findAll();
                model.addAttribute("tractores", tractores);
                return "index";
        }

        @GetMapping("/login")
        public String mostrarLogin() {
                return "login";
        }

        // DETALLES: Ver detalles de un tractor
        @GetMapping("/detalles/{id}")
        public String verDetalles(@PathVariable("id") Long id, Model model) {
                Tractor tractor = tractorRepository.findById(id).orElse(null);
                if (tractor != null) {
                        model.addAttribute("tractor", tractor);
                        return "detalles";
                }
                return "redirect:/index";
        }

        // VER FORMULARIO DE AÑADIR TRACTOR
        @GetMapping("/veraddtractor")
        public String verFormularioAdd(Model model) {
                model.addAttribute("tractor", new Tractor());
                return "add_tractor";
        }

        // AÑADIR TRACTOR
        @PostMapping("/addtractor")
        public String addTractor(@ModelAttribute Tractor tractor) {
                tractorRepository.save(tractor);
                return "redirect:/index";
        }

        // ELIMINAR TRACTOR
        @GetMapping("/eliminar/{id}")
        public String eliminarTractor(@PathVariable("id") Long id) {
                tractorRepository.deleteById(id);
                return "redirect:/index";
        }
}
