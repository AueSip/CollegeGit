// /controllers/ShoesController.js
const fs = require('fs');
const path = require('path');

class Shoes {
    constructor() {
        this.shoesFilePath = path.join(__dirname, '../models/shoes.json'); // Path to the shoes.json file
    }

    // Method to get all shoes
    showAll() {
        // Read the JSON file synchronously (for simplicity)
        const shoesData = fs.readFileSync(this.shoesFilePath, 'utf-8');
        return JSON.parse(shoesData); // Parse and return JSON data as an object/array
    }

    addShoe(newShoe) {
        const shoesData = this.showAll(); // Get current shoe list
        shoesData.push(newShoe); // Add new shoe to the list
        fs.writeFileSync(this.shoesFilePath, JSON.stringify(shoesData, null, 2)); // Write updated list back to the file
    }
}

module.exports = Shoes;
