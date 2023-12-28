package com.spring.agendalive;
import com.spring.agendalive.document.LiveDocument;
import com.spring.agendalive.repository.LiveRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest


public class LiveRepositoryTest {
	

    @Autowired
    private LiveRepository liveRepository;

    @Test
    public void testFindByLiveDateAfterOrderByLiveDateAsc() {
        // Criar algumas LiveDocuments para teste
        LocalDateTime currentDate = LocalDateTime.now();
        LiveDocument live1 = new LiveDocument(/* configure conforme necessário */);
        LiveDocument live2 = new LiveDocument(/* configure conforme necessário */);
        liveRepository.saveAll(List.of(live1, live2));

        // Chamar o método de repositório que você está testando
        Page<LiveDocument> result = liveRepository.findByLiveDateAfterOrderByLiveDateAsc(currentDate.minusDays(1), PageRequest.of(0, 10));

        // Verificar se os resultados correspondem ao esperado
        assertEquals(2, result.getTotalElements());
        // Adicione mais verificações conforme necessário
    }

    @Test
    public void testFindByLiveDateBeforeOrderByLiveDateDesc() {
        // Criar algumas LiveDocuments para teste
        LocalDateTime currentDate = LocalDateTime.now();
        LiveDocument live1 = new LiveDocument(/* configure conforme necessário */);
        LiveDocument live2 = new LiveDocument(/* configure conforme necessário */);
        liveRepository.saveAll(List.of(live1, live2));

        // Chamar o método de repositório que você está testando
        Page<LiveDocument> result = liveRepository.findByLiveDateBeforeOrderByLiveDateDesc(currentDate.plusDays(1), PageRequest.of(0, 10));

        // Verificar se os resultados correspondem ao esperado
        assertEquals(2, result.getTotalElements());
        // Adicione mais verificações conforme necessário
    }
}


