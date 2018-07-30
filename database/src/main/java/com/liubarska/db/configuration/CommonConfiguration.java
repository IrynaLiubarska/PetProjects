package com.liubarska.db.configuration;

import com.liubarska.db.common.DaoRegistry;
import com.liubarska.db.delete.DeleteCascade;
import com.liubarska.db.delete.DeleteNoAction;
import com.liubarska.db.delete.DeleteRestrict;
import org.springframework.context.annotation.Bean;

/**
 * Created by Iryna on 26.07.2018.
 */
public class CommonConfiguration {
    
    @Bean
    public DaoRegistry daoRegistry() {
        return new DaoRegistry();
    }

   
    @Bean
    public DeleteNoAction deleteNoAction() {
        return new DeleteNoAction();
    }

    @Bean
    public DeleteCascade deleteCascade() {
        return new DeleteCascade();
    }

    @Bean
    public DeleteRestrict deleteRestrict() {
        return new DeleteRestrict();
    }
}
