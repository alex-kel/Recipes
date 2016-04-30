package ru.dz.receipts.domain

import org.hibernate.annotations.GenericGenerator

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
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

    String password;
}
