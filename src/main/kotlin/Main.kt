package ru.netology

fun main() {
    var amount = 14000
    var typeCard = "VK Pay"
    var previousTransfer = 0

    resultTransfer(amount, typeCard, previousTransfer)
    previousTransfer += resultTransfer(amount, typeCard, previousTransfer).toInt()
    resultPrint(amount, typeCard, previousTransfer)

    println()

    amount = 15000
    typeCard = "VK Pay"
    resultTransfer(amount, typeCard, previousTransfer)
    previousTransfer += resultTransfer(amount, typeCard, previousTransfer).toInt()
    resultPrint(amount, typeCard, previousTransfer)

    println()

    amount = 11000
    typeCard = "VK Pay"
    resultTransfer(amount, typeCard, previousTransfer)
    previousTransfer += resultTransfer(amount, typeCard, previousTransfer).toInt()
    resultPrint(amount, typeCard, previousTransfer)

    println()

    amount = 100000
    typeCard = "Maestro"
    resultTransfer(amount, typeCard, previousTransfer)
    previousTransfer += resultTransfer(amount, typeCard, previousTransfer).toInt()
    resultPrint(amount, typeCard, previousTransfer)


}

fun typeMMCommission(amount: Int, previousTransfer: Int): Double {
    var commission = if (amount > 75000) {
        amount * 0.6 / 100 + 20
    } else {
        0
    }
    if (amount > 150000 || previousTransfer > 600000) commission = -1
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
    return if (resultTransfer(
            amount,
            typeCard,
            previousTransfer
        ).toInt() == -1
    ) println("Вы превысили лимиты перевода денежных средств")
    else println(
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

}

fun resultTransfer(amount: Int, typeCard: String = "VK Pay", previousTransfer: Int = 0): Number {
    val result = if (typeCard == "VK Pay" && amount > 15000 || typeCard == "VK Pay" && previousTransfer > 40000
    ) -1
    else if (typeMMCommission(amount, previousTransfer).toInt() == -1) -1 else amount - transferCommission(
        amount,
        typeCard,
        previousTransfer
    )


    return result
}








