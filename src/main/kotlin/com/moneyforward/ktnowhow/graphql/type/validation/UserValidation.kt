package com.moneyforward.ktnowhow.graphql.type.validation

import com.moneyforward.ktnowhow.graphql.type.UserPropertyType
import io.konform.validation.Invalid
import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength

interface UserValidation {

    fun UserPropertyType.validate() {
        val result = Validation<UserPropertyType> {
            UserPropertyType::name { run(nameValidator) }
            UserPropertyType::iconUrl ifPresent { run(iconUrlValidator) }
        }(this)

        if (result is Invalid) throw IllegalArgumentException(result.errors.toString())
    }

    private val nameValidator: Validation<String>
        get() = Validation {
            minLength(1)
            maxLength(30)
        }

    private val iconUrlValidator: Validation<String>
        get() = Validation {
            maxLength(2000)
            // todo URLの形式チェックやるなら書く
        }
}