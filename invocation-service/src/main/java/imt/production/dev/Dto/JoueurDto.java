package imt.production.dev.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
public class JoueurDto {
    
    @NotBlank
    private String username;

}
