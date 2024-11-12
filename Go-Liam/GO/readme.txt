create a folder
open the folder
open a new terminal
in the terminal run
npm init
answer all the questions by pressing enter 
npm install ejs express
create a folder called views
inside the folder put home.ejs
inside the home.ejs file put the HTML Shell and some text saying hello
create a folder called controllers
inside this put a file called shoes.js
create a folder called models 
inside this put a file called shoes.json

copy the file from code from here for the files you need.

create a file in the main directory called index.js
inside the index file put the content frm the starter.txt file
run the application by typing node index.js in the terminal. 
When it starts go to http://localhost:3000/ in a browser and see your file.
when ready in the terminal press ctrl + c to stop the application
now we can continue to edit the files. 
in the conntroller file add the following to the shoes.js file 

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
}

module.exports = Shoes;

inside the index.js file put the following near the top 

const Shoes = require('./controllers/shoes')

now under this line Create an instance of the Shoes class
const shoesController = new Shoes();


now we can access the shoe data from inside the app.get / route as follows 
  const shoesData = shoesController.showAll();
    console.log(shoesData)

    the whole route will look like this 

app.get('/', function(req,res){
    res.render("home")
    const shoesData = shoesController.showAll();
    console.log(shoesData)
})

NOTE ensuer this code is only in the application once.

now for our next step we need to get the json data to appear on the page so we can modify the code to pass it to the view
the route looks like
app.get('/', function(req,res){
    
    const shoesData = shoesController.showAll();
    console.log(shoesData)
    res.render("home", {shoesData})
})

the order of these is important so ensure you pass the data after it is created

now modify the home.ejs file to be 

<!-- /views/home.ejs -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shoe List</title>
</head>
<body>
    <h1>Available Shoes</h1>
    <ul>
        <% shoesData.forEach(shoe => { %>
            <li>
                <strong>Brand:</strong> <%= shoe.Make %> <br>
                <strong>Model:</strong> <%= shoe.Model %> <br>
                <strong>Price:</strong> €<%= shoe.Price %>
            </li>
        <% }) %>
    </ul>
</body>
</html>


stop and restart the application by ctrl + c in the terminal followed by node index.js

now use the stuff you have learned in Web Authoring to make the shoe data look better. 
This bit is up to you

how about we create a post request to push a new shoe to the JSON file

create a post request in the index.js file

app.post('/shoes', (req, res) => {
    // Hardcode a new shoe object
    const newShoe = {
        Make: "Converse",
        Model: "Chuck Taylor",
        Price: 189
    };

    // Add the new shoe to the shoes.json file
    shoesController.addShoe(newShoe);

    // Respond with a success message and the new shoe data
    res.status(201).json({
        message: 'New shoe added successfully',
        shoe: newShoe
    });
});

add the following method to the controllers
addShoe(newShoe) {
        const shoesData = this.showAll(); // Get current shoe list
        shoesData.push(newShoe); // Add new shoe to the list
        fs.writeFileSync(this.shoesFilePath, JSON.stringify(shoesData, null, 2)); // Write updated list back to the file
    }

    now restart the application and go to the address
    http://localhost:3000/shoes
    in your browser.
    This should give a message that the she has been added.
    when done go back to 
    http://localhost:3000/
    and you will see the new shoe.

    the next thing we need to do is to allow the user to dynamically add a shoe through the UI

    ok lets do this we need to install a package called body-parser
    npm install body-parser

    this allows us to gather info put on the UI by the user and pass it to the app

    in the index.js put the following 
    var bodyParser = require("body-parser") // call body parser module and make use of it
app.use(bodyParser.urlencoded({extended:true}));

now create a view called add.ejs

inside this put 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Shoe</title>
    <link rel="stylesheet" href="/styles.css"> <!-- Link to a CSS file if you have one -->
</head>
<body>
    <div class="container">
        <h1>Add a New Shoe</h1>
        <form action="/shoes" method="POST">
            <div class="form-group">
                <label for="make">Make:</label>
                <input type="text" id="make" name="make" required>
            </div>
            <div class="form-group">
                <label for="model">Model:</label>
                <input type="text" id="model" name="model" required>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" required>
            </div>
            <button type="submit">Add Shoe</button>
        </form>
        <a href="/">Go Back</a>
    </div>
   
</body>
</html>

now in the index.js file make a route to show this page 

app.get('/add', function(req,res){
    
    
    res.render("add")
})

lastly we need to call the body parser module in the index.js
so near the top put 
var bodyParser = require("body-parser") // call body parser module and make use of it
app.use(bodyParser.urlencoded({extended:true}));


now we need to collect the info from the form in the post request so modify it to be as follows

we do this by modifying the /shoes url to be a post and collect the data 

app.post('/shoes', (req, res) => {
    // Hardcode a new shoe object
    const newShoe = {
        Make: req.body.make,
        Model: req.body.model,
        Price: parseFloat(req.body.price) // Ensure price is a number
    };

    // Add the new shoe to the shoes.json file
    shoesController.addShoe(newShoe);

    // Respond with a success message and the new shoe data
    res.redirect('/');
});

see here we also redesigned it to be res.redirect after the request is done 


Again style this any way you like 