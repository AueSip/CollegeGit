const readLineSync = require('readline-sync');

var userName = readLineSync.question(`Firstname? `);
var surName = readLineSync.question(`Secondname? `);
var previousAge = readLineSync.question(`What Age Are You? `);
var timeDuration = readLineSync.question(`How Many Years Will You Wait? `);
var age = parseInt(previousAge) + parseInt(timeDuration)

resultOutcome(previousAge,userName,surName,age,timeDuration)

//Input Text using Redline Module

function resultOutcome(val1,val2,val3,val4,val5){
  console.log(`Hello You are ${val1} years old. Your name is ${val2} ${val3}, you will be ${val4} in ${val5} years!`)
}
    
  
  
