var express = require("express");
var app = express();
const Shoes = require("c:/Users/fowle/Desktop/CollegeGit/Go-Liam/MyAttempt/Controllers/shoes");
const shoeController = new Shoes();
app.set('view engine', 'ejs'); // Set the template engine 
app.use(express.static(__dirname + '/images'));



// **********************************  Code from here **************************
app.get('/', function(req,res){
    const shoesData = shoeController.showAll();
    console.log(shoesData)
    res.render("home", {shoesData})
    //only once in application
    
})


// **********************************  Code to here **************************



app.post('/shoes', (req,res)=>{
    const newShoe = {
        Make: "converse",
        Model: "sneaker",
        Price: 100
    }
    
    shoeController.addShoe(newShoe);
    res.status(201).json({
        message: "New shoe Added",
        shoe :newShoe
    });

});
  
app.listen(process.env.PORT || 3000, process.env.IP || "0.0.0.0" , function(){
    console.log("New Full Demo is Live")
  });
