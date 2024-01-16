package ru.netology

fun main() {
    var amount = 15000
    var typeCard = "VK Pay"
    var previousTransfer = 0

    resultTransfer(amount, typeCard, previousTransfer)
    previousTransfer += resultTransfer(amount, typeCard, previousTransfer).toInt()
    resultPrint(amount, typeCard, previousTransfer)

    println()

    amount = 10000
    typeCard = "Maestro"
    resultTransfer(amount, typeCard, previousTransfer)
    previousTransfer += resultTransfer(amount, typeCard, previousTransfer).toInt()
    resultPrint(amount, typeCard, previousTransfer)

    println()

    amount = 100000
    typeCard = "Mastercard"
    resultTransfer(amount, typeCard, previousTransfer)
    previousTransfer += resultTransfer(amount, typeCard, previousTransfer).toInt()
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
    return if (typeCard == "VK Pay" && amount > 15000 || typeCard == "VK Pay" && previousTransfer + amount > 40000
    ) println("Вы превысили лимиты перевода денежных средств")
    else if (amount <= 150000 || previousTransfer + amount <= 600000
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

fun resultTransfer(amount: Int, typeCard: String = "VK Pay", previousTransfer: Int = 0): Double {
    val result = if (typeCard == "VK Pay" && amount > 15000 || typeCard == "VK Pay" && previousTransfer + amount > 40000
    )
        0.0 else if (amount > 150000 || previousTransfer + amount > 600000) 0.0 else amount - transferCommission(
        amount,
        typeCard,
        previousTransfer
    )
    return result
}








