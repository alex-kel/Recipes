package ru.dz.recipes.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.dz.recipes.domain.Account
import ru.dz.recipes.repository.AccountRepository

/**
 * Created by Alex on 30.04.16.
 */
@Service
class AuthService {

    @Autowired AccountRepository accountRepository

    Account getCurrrentUser() {
        def auth = SecurityContextHolder.getContext().authentication
        auth == null ? null : accountRepository.findByLogin(auth.getName())
    }
}
