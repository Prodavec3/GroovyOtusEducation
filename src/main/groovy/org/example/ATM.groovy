package org.example

class ATM implements AtmEmulator {

    def static countATM = 0

    def idATM

    ATM() {
        this.idATM = countATM
        countATM += 1
    }

    def moneyInAtm = 0
    def acceptMoneyInTransact = 0
    Map moneyAtmWithNominal = ["10":0, "50":0, "100":0, "200":0, "500":0, "1000":0, "2000":0, "5000":0]

    @Override
    def acceptBanknotes(Map args) {
        def error
        acceptMoneyInTransact = 0

        args.each {it ->
            if (!moneyAtmWithNominal.containsKey(it.key)){
                println("Некорректное значение номинала купюры.")
                error = true
                return
            }

            if (it.value || it.key){
                acceptMoneyInTransact += it.key as Integer * it.value
            }
        }

        if(error) return
        moneyAtmWithNominal += args
        moneyInAtm+=acceptMoneyInTransact
        println("Зачислено на счет: ${acceptMoneyInTransact} рублей")
    }

    @Override
    def giveOutMoney(double money) {
        println(moneyAtmWithNominal)
        if (money % 10 != 0) {
            println("ОШИБКА!\nВведите сумму кратную 10")
            return
        }
        if(!money || money < 10) return
        def currentIndexKey, currentValue
        def checkSummNeedNomMoney = 0
        Map currentDelivery = [:]
        moneyAtmWithNominal.eachWithIndex {it, index ->
            if((money / (it.key as Integer)) >= 1 && it.value){
                checkSummNeedNomMoney += it.key as Integer * it.value
                currentDelivery.put(it.key, it.value)
                currentIndexKey = it.key
                currentValue = it.value
            }
        }

        if (checkSummNeedNomMoney < money){
            println("К сожалению, купюр в банкомате недостаточно для выдачи ${money} рублей. " +
                    "\nДоступно для снятия не более ${checkSummNeedNomMoney} рублей.")
            return
        }

        def buffer = 0
        def countK = 0

        for (int i = 1; i <= currentDelivery.get(currentIndexKey); i++){
            //println(i + " i была")
            if ((currentIndexKey as Integer * i) <= money){
                buffer = currentIndexKey as Integer * i
            }
            countK = i
            if(buffer) break
        }

        moneyAtmWithNominal.put(currentIndexKey, moneyAtmWithNominal.get(currentIndexKey).toInteger() - countK)

        money -= buffer

        giveOutMoney(money)
    }

    @Override
    public String toString() {
        return "\nНомер банкомата: " + idATM +
                ", \nДенег в банкомате: " + moneyInAtm +
                "р, \nКупюр в контейнерах: \n" + moneyAtmWithNominal
    }
}

