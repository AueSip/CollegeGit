const fs = require('fs')
const path = require('path')

class Shoes{
    constructor(){
        this.shoesFilePath = path.join(__dirname, '../models/shoes.json');
    }
    
    showAll(){
        const shoeData = fs.readFileSync(this.shoesFilePath, 'utf-8');
        return JSON.parse(shoeData);
    }
    addShoe(newShoe){
        const shoesData = this.showAll();
        shoesData.push(newShoe);
        fs.writeFileSync(this.shoesFilePath, JSON.stringify(shoesData, null, 2));
    }
}

module.exports = Shoes;

