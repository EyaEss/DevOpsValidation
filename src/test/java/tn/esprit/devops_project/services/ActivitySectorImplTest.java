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
        List<ActivitySector> activitySectors = new ArrayList<>();
        //when(activitySectorRepository.findAll()).thenReturn(activitySectors);
        List<ActivitySector> result = activitySectorService.retrieveAllActivitySectors();

        assertEquals(activitySectors, result);
    }
}