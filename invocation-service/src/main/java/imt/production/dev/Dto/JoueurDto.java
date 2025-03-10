package imt.production.dev.Dto;

import jakarta.validation.constraints.NotBlank;

public class JoueurDto {
    
    @NotBlank
    private String username;

    public JoueurDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
