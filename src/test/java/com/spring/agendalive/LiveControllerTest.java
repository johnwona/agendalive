package com.spring.agendalive;

import com.spring.agendalive.controller.LiveController;
import com.spring.agendalive.document.LiveDocument;
import com.spring.agendalive.service.LiveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LiveControllerTest {
	
	@Mock
    private LiveService liveService;

    @InjectMocks
    private LiveController liveController;

    @Test
    public void testGetAllLives() {
        // Mocking
        Page<LiveDocument> mockedPage = mock(Page.class);
        when(liveService.findAll(any(Pageable.class), anyString())).thenReturn(mockedPage);

        // Test
        ResponseEntity<Page<LiveDocument>> responseEntity = liveController.getAllLives(null, null);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedPage, responseEntity.getBody());
        verify(liveService, times(1)).findAll(any(Pageable.class), anyString());
    }

    @Test
    public void testGetOneLive() {
        // Mocking
        String liveId = "1";
        LiveDocument mockedLive = mock(LiveDocument.class);
        when(liveService.findById(liveId)).thenReturn(Optional.of(mockedLive));

        // Test
        ResponseEntity<LiveDocument> responseEntity = liveController.getOneLive(liveId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedLive, responseEntity.getBody());
        verify(liveService, times(1)).findById(liveId);
    }

    @Test
    public void testSaveLive() {
        // Mocking
        LiveDocument liveToSave = mock(LiveDocument.class);
        when(liveService.save(liveToSave)).thenReturn(liveToSave);

        // Test
        ResponseEntity<LiveDocument> responseEntity = liveController.saveLive(liveToSave);

        // Verify
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(liveToSave, responseEntity.getBody());
        verify(liveService, times(1)).save(liveToSave);
    }

    @Test
    public void testDeleteLive() {
        // Mocking
        String liveId = "1";
        LiveDocument mockedLive = mock(LiveDocument.class);
        when(liveService.findById(liveId)).thenReturn(Optional.of(mockedLive));

        // Test
        ResponseEntity<?> responseEntity = liveController.deleteLive(liveId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(liveService, times(1)).delete(mockedLive);
    }

    @Test
    public void testUpdateLive() {
        // Mocking
        String liveId = "1";
        LiveDocument existingLive = mock(LiveDocument.class);
        LiveDocument updatedLive = mock(LiveDocument.class);
        when(liveService.findById(liveId)).thenReturn(Optional.of(existingLive));
        when(liveService.save(updatedLive)).thenReturn(updatedLive);

        // Test
        ResponseEntity<LiveDocument> responseEntity = liveController.updateLive(liveId, updatedLive);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedLive, responseEntity.getBody());
        verify(existingLive, times(1)).setId(any());
        verify(liveService, times(1)).save(updatedLive);
    }

}
