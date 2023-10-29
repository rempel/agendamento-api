package cc.hidev.agendamento.api.controller.authentication;

import cc.hidev.agendamento.api.domain.model.authentication.AuthenticationDto;
import cc.hidev.agendamento.api.domain.model.authentication.TokenJWTDto;
import cc.hidev.agendamento.api.domain.model.usuario.UsuarioEntity;
import cc.hidev.agendamento.api.infra.security.TokenService;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity makeAuth(@RequestBody @Valid AuthenticationDto auth) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(auth.username(), auth.password());
        var authentication = manager.authenticate(authenticationToken);

        String tokenJWT = tokenService.generateToken((UsuarioEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDto(tokenJWT));
    }
}
