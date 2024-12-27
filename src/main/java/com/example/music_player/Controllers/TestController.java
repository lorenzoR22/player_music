package com.example.music_player.Controllers;

import com.example.music_player.Services.CancionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final CancionService cancionService;

    @GetMapping("/canciones")
    public String getAllCanciones(Model model) {
        model.addAttribute("canciones", cancionService.getAll());
        return "canciones";
    }

    @GetMapping(value = "/reproducir/{id}", produces = "audio/mpeg")
    public void reproducirCancion(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Resource cancion = cancionService.reproducirCancion(id);
        response.setContentType("audio/mpeg");
        IOUtils.copy(cancion.getInputStream(), response.getOutputStream());
        response.flushBuffer();
    }
}
