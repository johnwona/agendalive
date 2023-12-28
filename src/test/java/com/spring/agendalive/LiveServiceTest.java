package com.spring.agendalive;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.agendalive.repository.LiveRepository;
import com.spring.agendalive.service.LiveService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LiveServiceTest {
	
	@Mock
    private LiveRepository liveRepository;

    @InjectMocks
    private LiveService liveService;

    @Test
    public void testFindAllNext() {
        // Mocking
        Pageable pageable = mock(Pageable.class);
        Page<LiveDocument> mockedPage = mock(Page.class);
        when(liveRepository.findByLiveDateAfterOrderByLiveDateAsc(any(LocalDateTime.class), any(Pageable.class))).thenReturn(mockedPage);

        // Test
        Page<LiveDocument> result = liveService.findAll(pageable, "next");

        // Verify
        assertEquals(mockedPage, result);
        verify(liveRepository, times(1)).findByLiveDateAfterOrderByLiveDateAsc(any(LocalDateTime.class), any(Pageable.class));
        verify(liveRepository, never()).findByLiveDateBeforeOrderByLiveDateDesc(any(LocalDateTime.class), any(Pageable.class));
        verify(liveRepository, never()).findAll(any(Pageable.class));
    }

    @Test
    public void testFindAllPrevious() {
        // Mocking
        Pageable pageable = mock(Pageable.class);
        Page<LiveDocument> mockedPage = mock(Page.class);
        when(liveRepository.findByLiveDateBeforeOrderByLiveDateDesc(any(LocalDateTime.class), any(Pageable.class))).thenReturn(mockedPage);

        // Test
        Page<LiveDocument> result = liveService.findAll(pageable, "previous");

        // Verify
        assertEquals(mockedPage, result);
        verify(liveRepository, never()).findByLiveDateAfterOrderByLiveDateAsc(any(LocalDateTime.class), any(Pageable.class));
        verify(liveRepository, times(1)).findByLiveDateBeforeOrderByLiveDateDesc(any(LocalDateTime.class), any(Pageable.class));
        verify(liveRepository, never()).findAll(any(Pageable.class));
    }

    @Test
    public void testFindAll() {
        // Mocking
        Pageable pageable = mock(Pageable.class);
        Page<LiveDocument> mockedPage = mock(Page.class);
        when(liveRepository.findAll(any(Pageable.class))).thenReturn(mockedPage);

        // Test
        Page<LiveDocument> result = liveService.findAll(pageable, null);

        // Verify
        assertEquals(mockedPage, result);
        verify(liveRepository, never()).findByLiveDateAfterOrderByLiveDateAsc(any(LocalDateTime.class), any(Pageable.class));
        verify(liveRepository, never()).findByLiveDateBeforeOrderByLiveDateDesc(any(LocalDateTime.class), any(Pageable.class));
        verify(liveRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testFindById() {
        // Mocking
        String liveId = "1";
        Optional<LiveDocument> mockedLive = Optional.of(mock(LiveDocument.class));
        when(liveRepository.findById(liveId)).thenReturn(mockedLive);

        // Test
        Optional<LiveDocument> result = liveService.findById(liveId);

        // Verify
        assertEquals(mockedLive, result);
        verify(liveRepository, times(1)).findById(liveId);
    }

    @Test
    public void testSave() {
        // Mocking
        LiveDocument liveToSave = mock(LiveDocument.class);
        when(liveRepository.save(liveToSave)).thenReturn(liveToSave);

        // Test
        LiveDocument result = liveService.save(liveToSave);

        // Verify
        assertEquals(liveToSave, result);
        verify(liveRepository, times(1)).save(liveToSave);
    }

    @Test
    public void testDelete() {
        // Mocking
        LiveDocument liveToDelete = mock(LiveDocument.class);

        // Test
        liveService.delete(liveToDelete);

        // Verify
        verify(liveRepository, times(1)).delete(liveToDelete);
    }

}
