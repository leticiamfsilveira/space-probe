package com.elo7.space_probe.ui;

import com.elo7.space_probe.ui.probes.ProbeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProbeDTOTest {

    @Mock
    private ProbeDTO dummyProbe;

    @Test
    void testExample() {
        Mockito.when(dummyProbe.name()).thenReturn("banana");

        assertEquals("banana", dummyProbe.name());
    }

}