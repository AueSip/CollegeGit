class Chocolate {

    /*Basic class syntax*/

    /* information is passed, gathered and set */
    constructor(flavour, calories, price) {
        this.flavour = flavour,
        this.calories = calories,
        this.price = price
        
    }


       /*Basic method syntax

       eatChocolate(){

        return 

    } */
                   
    eatChocolate(){

        return `you have eaten this delicious ${this.flavour} chocolate. It costed €${this.price} and you have consumed ${this.calories} calories!`

        /*extra code after return is ignored*/

    }



}

var food = new Chocolate("Mint", 235, 2.50)

console.log(food.eatChocolate())
