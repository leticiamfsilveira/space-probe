package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.app.exceptions.InvalidMovementException;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoveProbeServiceTest {

    @Mock
    private ValidateProbePositionService mockValidateProbePositionService;

    @Mock
    private Probes mockProbes;

    @InjectMocks
    private MoveProbeService moveProbeService;

    private Probe probe;

    @BeforeEach
    public void setUp() {
        Planet planet = new Planet("Terra", 5, 5);
        probe = new Probe("Probe1", 2, 3, planet);
    }

    @Test
    public void testExecute_MoveForward() {
        String commands = "M";

        when(mockProbes.save(probe)).thenReturn(probe);

        moveProbeService.execute(probe, commands);

        assertEquals(2, probe.getXPosition());
        assertEquals(4, probe.getYPosition());

        verify(mockProbes).save(probe);
    }

    @Test
    public void testExecute_TurnLeft() {
        String commands = "L";

        when(mockProbes.save(probe)).thenReturn(probe);

        moveProbeService.execute(probe, commands);

        assertEquals(Probe.Orientation.WEST, probe.getOrientation());
        verify(mockProbes).save(probe);
    }

    @Test
    public void testExecute_TurnRight() {
        String commands = "R";

        when(mockProbes.save(probe)).thenReturn(probe);

        moveProbeService.execute(probe, commands);

        assertEquals(Probe.Orientation.EAST, probe.getOrientation());
        verify(mockProbes).save(probe);
    }

    @Test
    public void testExecute_InvalidMovement() {
        String commands = "MMM";
        doThrow(new InvalidMovementException("Movimentação inválida: fora dos limites do planeta"))
                .when(mockValidateProbePositionService).execute(anyInt(), anyInt(), eq(probe.getPlanetId()));

        InvalidMovementException exception = assertThrows(InvalidMovementException.class, () ->
                moveProbeService.execute(probe, commands));
        assertEquals("Movimentação inválida: fora dos limites do planeta", exception.getMessage());
    }

    @Test
    public void testExecute_CollisionWithAnotherProbe() {
        String commands = "M";
        doThrow(new InvalidMovementException("Movimentação inválida: colisão entre sondas"))
                .when(mockValidateProbePositionService).execute(anyInt(), anyInt(), eq(probe.getPlanetId()));

        InvalidMovementException exception = assertThrows(InvalidMovementException.class, () ->
                moveProbeService.execute(probe, commands));
        assertEquals("Movimentação inválida: colisão entre sondas", exception.getMessage());
    }
}
