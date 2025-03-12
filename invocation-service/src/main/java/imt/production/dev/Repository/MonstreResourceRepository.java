package imt.production.dev.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import imt.production.dev.Model.ResourceMonstre.MonstreResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MonstreResourceRepository {

    @Autowired
    private ResourceLoader resourceLoader;

    public List<MonstreResource> findAllMonstres() {
        try {
            Resource resource = resourceLoader.getResource("classpath:invocations.json");
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, MonstreResource.class);
            return new ObjectMapper().readValue(resource.getInputStream(), collectionType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
    