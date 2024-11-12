class Shoe {


    constructor (make){
        this.make = make
    }


    shoeMake(){
        return `This shoe is a ${this.make}`
    }


}

class Model extends Shoe {

    constructor(make,model){
       super(make)
       this.model = model
    }
    
    shoeDetails(){
        return `The shoe data: ${this.shoeMake()} ${this.model}`
    }

}
var liamsshoe = new Model("nike","air")
console.log(liamsshoe.shoeDetails())