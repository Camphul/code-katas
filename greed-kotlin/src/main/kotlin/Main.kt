package nl.adesso.kgreed

import java.util.*

/**
 * Created on 07/02/2024
 *///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val scan = Scanner(System.`in`)

    Greed.playGame(4000) { scan.nextLine() }
}
