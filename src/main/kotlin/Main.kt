package ru.netology

fun main() {
    var amount = 100000
    var typeCard = "Maestro"
    var previousTransfer = 0
    resultTransfer(amount, typeCard, previousTransfer)
    previousTransfer =
        if (previousTransfer + resultTransfer(
                amount,
                typeCard,
                previousTransfer
            ).toInt() <= 600000
        ) previousTransfer + resultTransfer(
            amount,
            typeCard,
            previousTransfer
        ).toInt() else previousTransfer
    resultPrint(amount, typeCard, previousTransfer)

    println()

    amount = 100000
    typeCard = "Mastercard"
    resultTransfer(amount, typeCard, previousTransfer)
    previousTransfer =
        if (previousTransfer + resultTransfer(
                amount,
                typeCard,
                previousTransfer
            ).toInt() <= 600000
        ) previousTransfer + resultTransfer(
            amount,
            typeCard,
            previousTransfer
        ).toInt() else previousTransfer
    resultPrint(amount, typeCard, previousTransfer)


}

fun typeMMCommission(amount: Int, previousTransfer: Int): Double {
    val commission = if (amount > 75000) {
        amount * 0.6 / 100 + 20
    } else {
        0
    }
    return commission.toDouble()
}

fun typeVMCommission(amount: Int): Double {
    val commission = if (amount * 0.75 / 100 > 35) amount * 0.75 / 100 else 35
    return commission.toDouble()
}

fun transferCommission(amount: Int, typeCard: String = "VK Pay", previousTransfer: Int = 0): Double = when (typeCard) {
    "Mastercard", "Maestro" -> typeMMCommission(amount, previousTransfer = 0)
    "Visa", "Mir" -> typeVMCommission(amount)
    else -> 0.0
}

fun resultPrint(amount: Int, typeCard: String, previousTransfer: Int) {
    return if (amount - transferCommission(
            amount,
            typeCard,
            previousTransfer
        ) <= 150000
    ) println(
        "Сумма вашего перевода: $amount рублей\n" +
                "Перевод с $typeCard\n" +
                "Итого сумма перевода с учётом комиссии: ${
                    resultTransfer(
                        amount,
                        typeCard,
                        previousTransfer
                    )
                } рублей\n" +
                "Сумма переводов за месяц: $previousTransfer рублей"
    )
    else println("Вы превысили лимиты перевода денежных средств")
}

fun resultTransfer(amount: Int, typeCard: String, previousTransfer: Int): Double {
    val result = if (amount - transferCommission(
            amount,
            typeCard,
            previousTransfer
        ) > 150000
    )
        0.0 else amount - transferCommission(amount, typeCard, previousTransfer)
    return result
}








