class Student {

    constructor (name, age, course){ 
    
                this.name = name; // This will set the name of the student
                this.age = age; // This will set the age of the student
                this.course = course; // This will set course name of the student
                
    }
    
    studentData() {
    
        if (this.age >= 18) {
            console.log ("You are " + this.age + " years old")
            console.log ("Your name is " + this.name)
            console.log ("Your course is " + this.course)
        }
        else {
             console.log("You are not old enough to study")
        }
    }
}
    
    var student = new Student("Liam", 20, "Software")
    student.studentData()