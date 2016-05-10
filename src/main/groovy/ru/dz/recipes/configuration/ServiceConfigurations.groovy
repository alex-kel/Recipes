package ru.dz.recipes.configuration

import com.dropbox.core.DbxAuthInfo
import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v1.DbxAccountInfo
import com.dropbox.core.v2.DbxClientV2
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by Alex on 10.05.16.
 */
@Configuration
class ServiceConfigurations {

    @Bean
    DbxClientV2 dropboxClient() {
        DbxRequestConfig config = new DbxRequestConfig("DZRecipes", "en_US");
        new DbxClientV2(config, "K7vqHZhJq0wAAAAAAAAI1cPsM8_aJ8GF32FBLR_s5a10lVF0mca0AK_cUsLSgt7R")
    }
}
