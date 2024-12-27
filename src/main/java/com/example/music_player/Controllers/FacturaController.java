package com.example.music_player.Controllers;

import com.example.music_player.Dtos.FacturaDTO;
import com.example.music_player.Services.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/facturas")
public class FacturaController {
    private final FacturaService facturaService;

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FacturaDTO getFactura(@PathVariable Long id,
                                 @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {
        return facturaService.getFactura(id, userDetails.getUsername());
    }
}
