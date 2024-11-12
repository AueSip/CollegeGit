class phoneMake{
    constructor(make){
        this.make = make
    }

    thisMake(phoneChosen, phoneMakes){
        for (var i = 0; i < phoneMakes.length; i++) {
            if (phoneMakes[i].make == phoneChosen) {
                var output = `${phoneChosen}: ${phoneMakes[i].model}`
                console.log(output)
              
                
        
            }
           
        }
        return this.make = phoneChosen
       
    }

  
}

class Phone extends phoneMake {

    constructor(make,model,price){
        super(make)
        this.price = price
        this.model = model

    }

    phoneDetails(){
        for (var i = 0; i < phoneMakes.length; i++) {
       if (phoneMakes[i].model == modelChosen) {
             
           return this.model = phoneMakes[i].model
         
       }
    }
    
    }

    thisPrice(){
        for (var i = 0; i < phoneMakes.length; i++) {
            if (phoneMakes[i].model == modelChosen) {
               
                return this.price = phoneMakes[i].price
            }
        }
              
        
    }

    newPhone(){
        return  `You bought the ${this.make}: ${this.model}, for €${this.thisPrice()}`
    }
    
}
        
    var phone = new Phone
    const readlineSync = require('readline-sync');
        const fs = require('fs');
        const jsonFile = fs.readFileSync('phonemodels.json')
        const phoneMakes = JSON.parse(jsonFile)


    var phoneChosen = readlineSync.question("Apple or Samsung? ").toLowerCase()    
    phone.thisMake(phoneChosen, phoneMakes)

    var modelChosen = readlineSync.question("Which from the list? ").toLowerCase()
    phone.phoneDetails(modelChosen, phoneMakes)

    console.log(phone.newPhone())


    
