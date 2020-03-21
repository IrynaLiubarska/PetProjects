package com.config;

import com.model.Ingredient;
import com.persistent.IngredientRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DBWriter implements ItemWriter<Ingredient> {
    
    @Autowired
    private IngredientRepository repository;


    @Override
    public void write(List<? extends Ingredient> ingredients) throws Exception {
        repository.saveAll((List<Ingredient>) ingredients);
    }
}
