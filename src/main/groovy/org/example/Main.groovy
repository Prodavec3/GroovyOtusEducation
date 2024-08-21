package org.example

static void main(String[] args) {
    ATM atm = new ATM();

    atm.acceptBanknotes(["10":4, "50":6, "100":2, "200":3, "500":4, "1000":0, "2000":2, "5000":2])

    println(atm)

    atm.giveOutMoney(21)
}