class Phone{


    constructor(make){
        this.make = make
    }

    thisMake(){
        return this.make
    }

  
}

class Model extends Phone {

    constructor(make,model,price){
        super(make)
        this.price = price
       this.model = model

    }

    thisPrice(){
        
        return this.price
    }
    
    phoneDetails(){
        return `You bought a ${this.thisMake()} ${this.model} for €${this.thisPrice()}`
    }

}

const readlineSync = require('readline-sync');


var phoneChosen = readlineSync.question("Apple or Samsung? ").toLowerCase()
var phone = new Phone(phoneChosen)

const fs = require('fs');
const jsonFile = fs.readFileSync('phonemodels.json')
const phoneMakes = JSON.parse(jsonFile)


for (var i = 0; i < phoneMakes.length; i++) {
    if (phoneMakes[i].make == phone.thisMake()) {
        output = `${phone.thisMake()}: ${phoneMakes[i].model}`
        console.log(output)

    }
}

var modelChosen = readlineSync.question("Which from the list? ").toLowerCase()
for (var i = 0; i < phoneMakes.length; i++) {
    if (phoneMakes[i].model == modelChosen) {
       
        var model = phoneMakes[i].model
        var price = phoneMakes[i].price
        

       
        phone = new Model(phoneChosen, model, price)
        console.log(`${boughtPhone.phoneDetails()}`)
    }


   
    }
