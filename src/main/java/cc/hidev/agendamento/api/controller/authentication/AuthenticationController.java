package cc.hidev.agendamento.api.controller.authentication;

import cc.hidev.agendamento.api.domain.model.authentication.AuthenticationDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity makeAuth(@RequestBody @Valid AuthenticationDto auth) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(auth.username(), auth.password());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok("");
    }
}
