package cc.hidev.agendamento.api.domain.repository.usuario;

import cc.hidev.agendamento.api.domain.model.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UserDetails findByUsername(String username);

}
