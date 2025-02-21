package com.example.agriario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class AgriarioController {

        @Autowired
        private TractorRepository tractorRepository;
        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;

        // Funcion para añadir imagenes al serv
        public String subirImagen(MultipartFile imagen) {
                // Ruta base para guardar las imágenes
                String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/img/";

                try {
                        // Crear el directorio si no existe
                        Path uploadPath = Paths.get(uploadDir);
                        if (!Files.exists(uploadPath)) {
                                Files.createDirectories(uploadPath);
                        }

                        // Guardar el archivo
                        String fileName = imagen.getOriginalFilename();
                        String rutaArchivo = uploadDir + fileName;
                        imagen.transferTo(new File(rutaArchivo));

                        return fileName; // Retornamos el nombre del archivo
                } catch (IOException e) {
                        e.printStackTrace();
                        return null; // En caso de error, retornamos null
                }
        }

        // INDEX: Listar tractores
        @GetMapping("/index")
        public String verIndex(Model model) {
                // Espera para asegurar la carga de la imagen
                try {
                        Thread.sleep(700); // Simula un retraso de 2 segundos
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

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

        // VER FORM AÑADIR USUARIO
        @GetMapping("/nuevoUsuario")
        public String verFormularioUsuario(Model model) {
                model.addAttribute("usuario", new Usuario());
                return "add_usuario";
        }

        // Añadir Usuario
        @PostMapping("/addusuario")
        public String agregarUsuario(@ModelAttribute Usuario usuario) {
                String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());
                usuario.setContrasena(contrasenaEncriptada);

                usuarioRepository.save(usuario); // Guarda el nuevo usuario en la base de datos
                return "redirect:/index"; // Redirige al índice o a una página de confirmación
        }

        // AÑADIR TRACTOR
        @PostMapping("/addtractor")
        public String addTractor(@ModelAttribute Tractor tractor, @RequestParam("imagen") MultipartFile imagen) {
                String fileName = subirImagen(imagen);
                tractor.setImage(fileName);

                tractorRepository.save(tractor);
                return "redirect:/index";
        }

        // ELIMINAR TRACTOR
        @GetMapping("/eliminar/{id}")
        public String eliminarTractor(@PathVariable("id") Long id, Model model) {
                Tractor tractor = tractorRepository.findById(id).orElse(null);

                if (tractor != null) {
                        // 1. Eliminar la imagen del disco
                        String imagePath = System.getProperty("user.dir") + "/src/main/resources/static/img/"
                                        + tractor.getImage();
                        try {
                                Files.deleteIfExists(Paths.get(imagePath));
                        } catch (IOException e) {
                                e.printStackTrace(); // Log the error, maybe show a message to the user
                                model.addAttribute("errorMessage", "Error deleting image."); // Optional: Add error
                                                                                             // message to the model
                                List<Tractor> tractores = tractorRepository.findAll();
                                model.addAttribute("tractores", tractores);
                                return "index"; // Redirect back to the index page, even if image deletion fails.
                        }

                        // 2. Eliminar el tractor de la base de datos
                        tractorRepository.delete(tractor); // Use delete(entity) for proper cascading

                }

                return "redirect:/index";
        }

        // VER FORMULARIO DE EDITAR TRACTOR (modified)
        @GetMapping("/edit/{id}")
        public String verFormularioEditar(@PathVariable("id") Long id, Model model) {
                Tractor tractor = tractorRepository.findById(id).orElse(null);
                if (tractor != null) {
                        model.addAttribute("tractor", tractor);
                        return "edit_tractor";
                }
                return "redirect:/index";
        }

        // EDITAR TRACTOR
        @PostMapping("/edit/{id}")
        public String editarTractor(@PathVariable("id") Long id, @ModelAttribute Tractor tractor,
                        @RequestParam("imagen") MultipartFile imagen, Model model) {
                Tractor existingTractor = tractorRepository.findById(id).orElse(null);
                if (existingTractor != null) {
                        // ... (other fields update)

                        if (!imagen.isEmpty()) {
                                // 1. Delete the old image if it exists
                                if (existingTractor.getImage() != null && !existingTractor.getImage().isEmpty()) { // Check
                                                                                                                   // if
                                                                                                                   // an
                                                                                                                   // image
                                                                                                                   // exists
                                                                                                                   // before
                                                                                                                   // deleting
                                        String oldImagePath = System.getProperty("user.dir")
                                                        + "/src/main/resources/static/img/"
                                                        + existingTractor.getImage();
                                        try {
                                                Files.deleteIfExists(Paths.get(oldImagePath));
                                        } catch (IOException e) {
                                                e.printStackTrace(); // Log the error
                                                model.addAttribute("errorMessage", "Error deleting old image.");
                                                model.addAttribute("tractor", existingTractor);
                                                return "edit_tractor";
                                        }
                                }

                                // 2. Upload the new image
                                String fileName = subirImagen(imagen);
                                if (fileName != null) {
                                        existingTractor.setImage(fileName);
                                } else {
                                        model.addAttribute("errorMessage", "Error uploading image.");
                                        model.addAttribute("tractor", existingTractor);
                                        return "edit_tractor";
                                }
                        }
                        tractorRepository.save(existingTractor);
                }
                return "redirect:/index";
        }
}