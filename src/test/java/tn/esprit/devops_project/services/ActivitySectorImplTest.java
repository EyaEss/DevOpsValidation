package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ActivitySectorImplTest {
    @Mock
    private ActivitySectorRepository activitySectorRepository;

    @InjectMocks
    private ActivitySectorImpl activitySectorService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testRetrieveAllActivitySectors() {
        // Given
        ActivitySector activitySector1 = new ActivitySector("Sector 1");
        ActivitySector activitySector2 = new ActivitySector("Sector 2");
        List<ActivitySector> activitySectors = Arrays.asList(activitySector1, activitySector2);
        when(activitySectorRepository.findAll()).thenReturn(activitySectors);

        // When
        List<ActivitySector> result = activitySectorService.retrieveAllActivitySectors();

        // Then
        assertEquals(2, result.size());
        assertEquals("Sector 1", result.get(0).getName());
        assertEquals("Sector 2", result.get(1).getName());
    }

    @Test
    public void testAddActivitySector() {
        // Given
        ActivitySector activitySector = new ActivitySector("New Sector");
        when(activitySectorRepository.save(activitySector)).thenReturn(activitySector);

        // When
        ActivitySector result = activitySectorService.addActivitySector(activitySector);

        // Then
        assertEquals("New Sector", result.getName());
    }

    @Test
    public void testDeleteActivitySector() {
        // Given
        Long id = 1L;

        // When
        activitySectorService.deleteActivitySector(id);

        // Then
        verify(activitySectorRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateActivitySector() {
        // Given
        ActivitySector activitySector = new ActivitySector("Updated Sector");
        when(activitySectorRepository.save(activitySector)).thenReturn(activitySector);

        // When
        ActivitySector result = activitySectorService.updateActivitySector(activitySector);

        // Then
        assertEquals("Updated Sector", result.getName());
    }

    @Test
    public void testRetrieveActivitySector() {
        // Given
        Long id = 1L;
        ActivitySector activitySector = new ActivitySector("Sector");
        when(activitySectorRepository.findById(id)).thenReturn(Optional.of(activitySector));

        // When
        ActivitySector result = activitySectorService.retrieveActivitySector(id);

        // Then
        assertEquals("Sector", result.getName());
    }
 
}
