package ru.netology

fun main() {
    val result = resultsTransfers(100000, "Visa")
    println()
    val result1 = resultsTransfers(100000, "Mir")
    println()
    val result2 = resultsTransfers(100000, "Maestro")
    println()
    val result3 = resultsTransfers(100000)

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

fun transferCommission(amount: Int, typeCard: String) = when (typeCard) {
    "Mastercard", "Maestro" -> typeMMCommission(amount)
    "Visa", "Mir" -> typeVMCommission(amount)
    else -> println("")
}

fun calculationAmountTransfers(amount: Int, typeCard: String): Number = when {
    typeCard == "Mastercard" -> amount - typeMMCommission(amount)
    typeCard == "Maestro" -> amount - typeMMCommission(amount)
    typeCard == "Visa" -> amount - typeVMCommission(amount)
    typeCard == "Mir" -> amount - typeVMCommission(amount)
    else -> amount
}

fun resultsTransfers(amount: Int, typeCard: String = "VK Pay", previousTransfer: Int = 0) {
    val amountTransfers = amount
    val card = typeCard
    val commission = transferCommission(amount, typeCard)
    val resultAmount: Int = calculationAmountTransfers(amount, typeCard).toInt()
    val month = previousTransfer + resultAmount
    return println(
        "Сумма вашего перевода: $amount рублей\n" +
                "Перевод с $card\n" +
                "Сумма комиссии: $commission рублей\n" +
                "Итого сумма перевода с учётом комиссии: $resultAmount рублей\n" +
                "Сумма переводов за месяц: $month рублей"
    )
}







