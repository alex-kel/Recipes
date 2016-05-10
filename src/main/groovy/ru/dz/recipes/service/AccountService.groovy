package ru.dz.recipes.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.dz.recipes.domain.Account
import ru.dz.recipes.dto.AccountCreationDto
import ru.dz.recipes.dto.AccountUpdateDto
import ru.dz.recipes.exceptions.LoginAlreadyInUseException
import ru.dz.recipes.repository.AccountRepository

/**
 * Created by Alex on 30.04.16.
 */
@Service
class AccountService {

    @Autowired AccountRepository accountRepository
    @Autowired AuthService authService

    private Account updateAccountEntity(Account account, AccountUpdateDto dto) {
        account.setPassword(dto.getPassword())
        account
    }

    Account updateAccount(AccountUpdateDto accountUpdateDto) {
        def account = authService.getCurrrentUser()
        account = updateAccountEntity(account, accountUpdateDto)
        accountRepository.save(account)
    }

    Account createNewAccount(AccountCreationDto accountCreationDto) {
        def checkedAccount = accountRepository.findByLogin(accountCreationDto.getLogin())
        if (checkedAccount != null) {
            throw new LoginAlreadyInUseException()
        }
        def account = new Account();
        account.setLogin(accountCreationDto.getLogin())
        account.setPassword(accountCreationDto.getPassword())
        accountRepository.save(account)
    }

    Account getCurrentAccount() {
        authService.getCurrrentUser()
    }


}
