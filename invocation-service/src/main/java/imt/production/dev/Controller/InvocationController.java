package imt.production.dev.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import imt.production.dev.Dto.InvocationDto;
import imt.production.dev.Service.InvocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/invocations")
@Tag(name = "Invocation", description = "Gestion des invocations")
public class InvocationController {

    InvocationService invocationService;

    public InvocationController(InvocationService invocationService) {
        this.invocationService = invocationService;
    }

    @Operation(summary = "Invoque un monstre")
    @PostMapping()
    public ResponseEntity<Map<String, String>> invoque(HttpServletRequest request, @Valid @RequestBody InvocationDto dto) {
        String token = request.getHeader("Authorization");
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok(invocationService.invoque(dto, token, username));
    }


    @Operation(summary = "Rejoue les invocations stoppées")
    @PostMapping("/recup")
    public ResponseEntity<List<String>> recup(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String username = (String) request.getAttribute("username");
        List<String> recups = invocationService.recup(token, username);

        return ResponseEntity.ok(recups);
    }    
}


