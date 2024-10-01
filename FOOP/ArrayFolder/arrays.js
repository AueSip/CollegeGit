var list = require (`./practice.json`);
   
var text = ""

var students = {speed: "200m/s", age:"20", class:"Csoft1",
    speedModel: function(){

        return students.speed + " " + students.age + " " + students.class


    }

};

for (i = 0; i < list.length; i++){
    text += "Car Make:" +  list[i].model + " Color:" + list[i].color + " Year:" + list[i].age + "\n";


}



console.log(text)
console.log(students.speedModel())


