package com.config;

import com.model.Ingredient;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Processor implements ItemProcessor<Ingredient, Ingredient> {
    @Override
    public Ingredient process(Ingredient ingredient) throws Exception {
        ingredient.setDate(new Date());
        return ingredient;
    }
}
