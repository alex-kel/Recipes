package ru.dz.receipts.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Alex on 30.04.16.
 */
class AccountUpdateDto {

    @NotNull
    @NotEmpty
    @Size(min = 6)
    String password
}
