package ru.netology

fun main() {
    val result = resultsTransfers(100000, "Visa")

}

fun typeMMCommission(amount: Int): Double {
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

fun transferCommission(amount: Int, typeCard: String) = when {
    typeCard == "Mastercard" -> amount - typeMMCommission(amount)
    typeCard == "Maestro" -> typeMMCommission(amount)
    typeCard == "Visa" -> typeVMCommission(amount)
    typeCard == "Mir" -> typeVMCommission(amount)
    else -> 0
}

fun calculationAmountTransfers(amount: Int, typeCard: String) = when {
    typeCard == "Mastercard" -> amount - typeMMCommission(amount)
    typeCard == "Maestro" -> amount - typeMMCommission(amount)
    typeCard == "Visa" -> amount - typeVMCommission(amount)
    typeCard == "Mir" -> amount - typeVMCommission(amount)
    else -> amount
}

fun resultsTransfers(amount: Int, typeCard: String) {
    val amountTransfers = amount
    val card = typeCard
    val commission = transferCommission(amount, typeCard)
    val resultAmount = calculationAmountTransfers(amount, typeCard)
    return println(
        "Сумма вашего перевода: $amount рублей\n" +
                "Перевод с $card\n" +
                "Сумма комиссии: $commission рублей\n" +
                "Итого сумма перевода с учётом комиссии: $resultAmount рублей\n"
    )
}







