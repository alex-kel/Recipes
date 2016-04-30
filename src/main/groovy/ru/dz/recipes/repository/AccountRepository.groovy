package ru.dz.recipes.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.dz.recipes.domain.Account

/**
 * Created by Alex on 22.03.16.
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    public Account findByLogin(String login);
}
