/*module.exports will explort the class */


class Vehicle{

constructor(type, make, model, year, color){
    this.type = type
    this.make = make
    this.model = model
    this.year = year
    this.color = color

}

getValue(){
    var depreciation
    if (this.year < 2000){
        depreciation = 0.03
    }
    else {
        depreciation = 0.09
    }

    
    return ((this.year * depreciation) * this.year/8).toFixed(0)
}

getAge(){
    var date = new Date()
    return  date.getFullYear() - this.year

}



}

var car1 = new Vehicle("Car", "Toyota", "Supra", 2001, "Red")



var output = `This ${car1.type} is a ${car1.color} ${car1.make} ${car1.model} which is ${car1.getAge()} years old. It will cost you €${car1.getValue()}`
console.log(output)