package it.courtbook.availabilityservice.config;

import it.courtbook.availabilityservice.model.Campo;
import it.courtbook.availabilityservice.model.TipoCampo;
import it.courtbook.availabilityservice.repository.CampoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatiIniziali implements CommandLineRunner {

    private final CampoRepository campoRepository;

    @Override
    public void run(String... args) {
        if (campoRepository.count() == 0) {
            campoRepository.save(new Campo("Padel 1", TipoCampo.PADEL));
            campoRepository.save(new Campo("Tennis 1", TipoCampo.TENNIS));
            campoRepository.save(new Campo("Calcetto 1", TipoCampo.CALCETTO));
        }
    }
}
