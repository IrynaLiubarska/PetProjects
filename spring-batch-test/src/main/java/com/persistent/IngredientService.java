package com.persistent;

import com.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;
    
    public  void  addIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    public  void  addAllIngredient(List<Ingredient> ingredients){
        ingredientRepository.saveAll(ingredients);
    }
}
