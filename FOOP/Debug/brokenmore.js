class Vehicle {
    constructor(make, model, regYear) {
        this.make = make;
        this.model = model;
        this.regYear = regYear
    }

    getAge() {
        //Getter
        var age = 2024 - this.regYear
        return age;
    }
    getDetails() {
       console.log("Make:" + this.make + " Model:" + this.model + " Reg:" + this.regYear + " Age:" + this.getAge());
       return this.getAge()
    }
    checkTradeIn() {
        var age = this.getDetails()
        if (age > 5) {

            return "You can trade in this car";
        } else {

            return "You cannot trade in this car";
        }
    }
}
var myCar = new Vehicle ("Ford", "Focus", 2019 )
console.log(myCar.checkTradeIn())