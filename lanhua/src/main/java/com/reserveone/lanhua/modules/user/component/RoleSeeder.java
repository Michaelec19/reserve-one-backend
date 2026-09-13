package com.reserveone.lanhua.modules.user.component;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.reserveone.lanhua.modules.user.entity.Rol;
import com.reserveone.lanhua.modules.user.repository.RolRepository;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RolRepository rolRepository;

    public RoleSeeder(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.findByNameRol("ADMIN").isEmpty()) {
            Rol admin = new Rol();
            admin.setNameRol("ADMIN");
            rolRepository.save(admin);
        }

        if (rolRepository.findByNameRol("CLIENT").isEmpty()) {
            Rol client = new Rol();
            client.setNameRol("CLIENT");
            rolRepository.save(client);
        }
    }
}