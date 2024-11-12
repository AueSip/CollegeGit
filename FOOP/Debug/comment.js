class Actor {
    constructor(actorName, city, filmType) {
        this.name = actorName;
        this.city = city;
        this.filmType = filmType
    }


    getGenre() {
      
        return "This actor is best known for " + this.filmType + " movies";
    }

    getActorDetails() {


        return "This actor is called " +  this.name + " they are origionally from " + this.filmType;

    }
    
   
   

}

var actor = new Actor("Liam", "Navan", "Action");
console.log(actor.getGenre())
console.log(actor.getActorDetails())