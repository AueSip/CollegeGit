class Cart {
    constructor() {
        this.itemList = [];
        this.delivery = 20; 
        this.totalCost = 0;
    }

    addToCart(item, qty, price) {
        let xxx = {
            name: item, quantity: qty, lineTotal: price * qty
        };
        this.itemList.push(xxx);
        console.log(`${qty} ${xxx.name} added to cart.`);
    }

    viewCart() {
        for (i = 0; i < this.itemList.length; i++) {
            const item = this.itemList[i];
            console.log(`You bought: ${item.name} - ${item.price}`);
        }
    }
    deliveryCost() {
        for (i = 0; i < this.itemList.length; i++){
            this.totalCost += this.itemList[i].lineTotal;
        }
        return (this.totalCost + this.delivery);
}

}

const items = require('./items.json');


//Loops through items.json and displays available items
console.log("Shop Items:");
for (i = 0; i < items.length; i++) {
    const item = items[i];
    console.log(`No${i + 1}: ${item.name} - ${item.price}`);
}

readlineSync = require('readline-sync');
var accepted = false;
const cart = new Cart();
//Prompts user to enter item name and quantity and asks if they are finished adding items to the cart
while (!accepted) {
    var tempItem = parseInt(readlineSync.question('Choose Item No: ')) - 1;
    var quantity = readlineSync.question('Enter the quantity: ');
    var found = false;
    
        var item = items[tempItem];
        console.log(`You have selected: ${item.name} - ${item.price}`);
        cart.addToCart(item.name, quantity, item.price);
        found = true;
        
        
    
    if (!found) {
        console.log("Item not found.");
    }


    var input = readlineSync.question('Are you finished adding items to the cart? (y/n): ');
    accepted = (input == 'y');
}

//Displays items bought
console.log("Bought Items:");
for (i = 0; i < cart.itemList.length; i++) {
    console.log(`You bought: ${cart.itemList[i].quantity} ${cart.itemList[i].name} for ${cart.itemList[i].lineTotal}`);
}

//Displays total cost
console.log(`In total it costed: ${cart.deliveryCost()}`, `and the delivery cost was: ${cart.delivery}`);
console.log("Thank you for shopping with us!");

        

