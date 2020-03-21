package com.config;

import com.model.Ingredient;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CsvFileReader {
    @Bean
    public FlatFileItemReader<Ingredient> readeFile(@Value("${input}") Resource resource){
        FlatFileItemReader<Ingredient> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setName("CSV reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(getLineMap());
        return flatFileItemReader;
    }

    @Bean
    private LineMapper<Ingredient> getLineMap() {
        DefaultLineMapper<Ingredient> defaultLineMapper = new DefaultLineMapper<Ingredient>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames(new String[]{"id", "name", "amount", "unit"});
        BeanWrapperFieldSetMapper<Ingredient> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Ingredient.class);
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        return defaultLineMapper;
    }
}
