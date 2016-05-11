package ru.dz.recipes.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Created by Alex on 22.03.16.
 */
@Entity
class Account {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "account_id")
    long id;

    String login;

    @JsonIgnore
    String password;

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Account account = (Account) o

        if (id != account.id) return false
        if (login != account.login) return false

        return true
    }

    int hashCode() {
        int result
        result = (int) (id ^ (id >>> 32))
        result = 31 * result + (login != null ? login.hashCode() : 0)
        return result
    }
}
