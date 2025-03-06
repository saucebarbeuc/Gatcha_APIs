package imt.production.dev.DTO;

import jakarta.validation.constraints.NotBlank;

public class JoueurDTO {
    
    @NotBlank
    private String username;

    public JoueurDTO(String username) {
        this.username = username;        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
