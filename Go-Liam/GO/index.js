var express = require("express");
var app = express();
const Shoes = require('./controllers/shoes')


app.set('view engine', 'ejs'); // Set the template engine 
var bodyParser = require("body-parser") // call body parser module and make use of it
app.use(bodyParser.urlencoded({extended:true}));

// Create an instance of the Shoes class
const shoesController = new Shoes();


// **********************************  Code from here **************************
app.get('/', function(req,res){
    
    const shoesData = shoesController.showAll();
    console.log(shoesData)
    res.render("home", {shoesData})
})


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

app.get('/add', function(req,res){
    
    
    res.render("add")
})


// **********************************  Code to here **************************

app.listen(process.env.PORT || 3000, process.env.IP || "0.0.0.0" , function(){
  console.log("New Full Demo is Live")
});