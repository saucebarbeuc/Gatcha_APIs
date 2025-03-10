package imt.production.dev.Mapper;

import imt.production.dev.Dto.InvocationDto;
import imt.production.dev.Model.Invocation;

public class InvocationMapper {

    public static Invocation dtoToModel(InvocationDto invocationDto) {
        return new Invocation(
            invocationDto.getTaux(),
            invocationDto.getMonstreDto().getTypeElementaire(),
            invocationDto.getMonstreDto().getStats(),
            invocationDto.getMonstreDto().getCompetences()
        );
    } 


    
}
