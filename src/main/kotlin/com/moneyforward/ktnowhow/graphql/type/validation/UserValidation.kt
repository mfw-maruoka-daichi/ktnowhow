package com.moneyforward.ktnowhow.graphql.type.validation

import com.moneyforward.ktnowhow.graphql.type.UserInputType
import io.konform.validation.Invalid
import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength

interface UserValidation {

    fun UserInputType.validate() {
        val result = Validation<UserInputType> {
            UserInputType::rawId required { }
            UserInputType::name ifPresent { run(nameValidator) }
            UserInputType::iconUrl ifPresent { run(iconUrlValidator) }
        }(this)

        if (result is Invalid) throw IllegalArgumentException(result.errors.toString())
    }

    fun validateUserProperty(name: String, iconUrl: String?) {
        val results = listOfNotNull<ValidationResult<*>>(
            nameValidator.validate(name),
            iconUrl?.let { iconUrlValidator.validate(it) }
        )

        val errors = results.mapNotNull {
            if (it is Invalid) it.errors
            else null
        }

        if (errors.isNotEmpty()) throw IllegalArgumentException(errors.toString())
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