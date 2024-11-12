class Atm{

constructor(amnt){
    this.amnt = amnt
}


    #updateBalance(whatever){
        var balance = 999192783176
        var newBalance = balance - whatever
        return `Thank you, your new balance is now €${newBalance}`
    }

    withdraw(){
        return this.#updateBalance(this.amnt)


    }
}

var transaction = new Atm(37)

console.log(transaction.withdraw())
