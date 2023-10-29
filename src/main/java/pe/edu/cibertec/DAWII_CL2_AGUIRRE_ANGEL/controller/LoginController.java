package pe.edu.cibertec.DAWII_CL2_AGUIRRE_ANGEL.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWII_CL2_AGUIRRE_ANGEL.model.bd.Usuario;
import pe.edu.cibertec.DAWII_CL2_AGUIRRE_ANGEL.service.UsuarioService;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {

    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(){
        return "auth/frmLogin";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "auth/frmRegistroUsuario";
    }

    @PostMapping("/login-success")
    public String loginSucces(HttpServletRequest request){
        UserDetails usuario = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario.getUsername());
        return "auth/home";
    }

    @PostMapping("/change-password")
    public String changePassword(HttpServletRequest request){
        UserDetails usuario = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario.getUsername());
        return "auth/home";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.saveUser(usuario);
        return "auth/frmLogin";
    }

    @GetMapping("/change-password")
    public String changePasswordForm() {
        return "auth/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        User user = userService.findByUsername(principal.getName());
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);
            userService.saveUser(user);
            return "redirect:/home";
        } else {
            return "change-password";
        }
    }
}
