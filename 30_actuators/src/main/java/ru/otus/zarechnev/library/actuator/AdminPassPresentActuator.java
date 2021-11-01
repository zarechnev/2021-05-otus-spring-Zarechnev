package ru.otus.zarechnev.library.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.zarechnev.library.repository.LibraryUserRepository;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class AdminPassPresentActuator implements HealthIndicator {

    private final LibraryUserRepository libraryUserRepository;

    @Override
    public Health health() {
        AtomicBoolean adminPassPresent = new AtomicBoolean();
        libraryUserRepository.findFirstByLogin("admin").ifPresent(admin ->
                adminPassPresent.set(!"".equals(admin.getPassword()))
        );

        return adminPassPresent.get() ? Health.up().build() : Health.down().build();
    }
}
