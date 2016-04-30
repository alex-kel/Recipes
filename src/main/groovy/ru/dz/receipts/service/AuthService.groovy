package ru.dz.receipts.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.dz.receipts.domain.Account
import ru.dz.receipts.repository.AccountRepository

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
