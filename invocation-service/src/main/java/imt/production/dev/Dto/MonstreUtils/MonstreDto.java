package imt.production.dev.Dto.MonstreUtils;

import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonstreDto { 

    private String nom;
    private TypeElementaire typeElementaire;
    private Stats stats;
    private List<CompetenceDto> competences;

}
