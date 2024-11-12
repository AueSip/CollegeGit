class ShopStaff {
    constructor(staff, salary, leaveTaken) {
        this.staff = staff;
        this.salary = salary;
        this.leaveTaken = leaveTaken
    }

 

    getSalary() {
        var bonus = this.salary * 0.1
        var fullPay = this.salary + bonus
        return "This year + " + this.staff + " has earned " + fullPay + "."
    }

    getLeave() {
        return  this.staff + " has taken " + this.leaveTaken  + " leave days off."
    }
    
    checkStaffDeta1ls() {
        console.log( this.staff + " Work's Here." + (" " + this.getLeave()) + (" " +  this.getSalary()))
    }
}
    var staff = ["John", "Paul", "George", "Ringo", "Pete", "Eric", "John", "Neil", "David", "Sting"]
    var staffMembers = []
    for (i = 0; i < staff.length; i++) {
        staffMembers.push(new ShopStaff(staff[i],50000, 30))
        
        staffMembers[i].checkStaffDeta1ls()
    }

   
    
    
    

