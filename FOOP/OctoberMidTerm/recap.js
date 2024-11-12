var firstName = "Scott"
var lastName = "Fowler"
var birthDate = new Date(2005, 6, 27)
var currentYear = new Date().getFullYear()

console.log(`My name is ${firstName} ${lastName}. I was born on ${birthDate.toLocaleDateString()} and I am ${currentYear - birthDate.getFullYear()} years old. In ${currentYear + 10} I will be ${currentYear -birthDate.getFullYear() + 10}.`)

var family = [
    {
        name: "Paul",
        age: 44
    },
    {
        name: "Rebecca",
        age: 39
    },
    {
        name: "Shayne",
        age: 15
    },
    {
        name: "Jake",
        age: 12
    },
    {
        name: "Sienna",
        age: 7
    }]

    console.log(family)

    for (var i = 0; i < family.length; i++) {
        console.log(`${family[i].name} is ${family[i].age} years old.`)
    }

    var footballers = [
        {
            name: "Cristiano",
            age: 36,
            team: "Juventus",
            position: "Striker"
        },
        {
            name: "Lionel",
            age: 32,
            team: "Barcelona",
            position: "Striker"
        },
        {
            name: "Kylian",
            age: 22,
            team: "Paris St-Germain",
            position: "Striker"
        },
        {
            name: "Kevin",
            age: 28,
            team: "Manchester City",
            position: "Midfielder"
        },
        {
            name: "Neymar",
            age: 28,
            team: "Paris St-Germain",
            position: "Midfielder"
        },
        {
            name: "Mohamed",
            age: 28,
            team: "Liverpool",
            position: "Striker"
        },
        {
            name: "Harry",
            age: 26,
            team: "Tottenham Hotspur",
            position: "Striker"
        },
        {
            name: "Mauro",
            age: 32,
            team: "Paris St-Germain",
            position: "Striker"
        },
        {
            name: "Eden",
            age: 29,
            team: "Real Madrid",
            position: "Midfielder"
        },
        {
            name: "Robert",
            age: 31,
            team: "Bayern Munich",
            position: "Striker"
        }
    ]

    console.log(footballers.length)

    for (var i = 0; i < footballers.length; i++) {
        console.log(`${footballers[i].name} is ${footballers[i].age} years old and plays for ${footballers[i].team} as a ${footballers[i].position}.`)
    }

    console.log(`${footballers[1].name} is ${footballers[1].age} years old and plays for ${footballers[1].team} as a ${footballers[1].position}.`)

    class Singer {
        constructor(name, nationality, popularSong) {
            this.name = name
            this.nationality = nationality
            this.popularSong = popularSong
        }
        }

        var singerArray=[
            new Singer("Ed Sheeran", "England", "Shape of You"),
            new Singer("Michael Jackson", "USA", "Billie Jean"),
            new Singer("Drake", "Canada", "God's Plan"),
            new Singer("Ariana Grande", "USA", "7 rings"),
            new Singer("Shawn Mendes", "USA", "Senorita"),
            new Singer("Taylor Swift", "USA", "Shake it off"),
            new Singer("Beyonce", "USA", "Crazy in Love"),
            new Singer("Katy Perry", "USA", "Firework"),
            new Singer("Maroon 5", "USA", "Moves"),
            new Singer("Bruno Mars", "USA", "24k Magic"),]
        
        for (var i = 0; i < singerArray.length; i++) {
            console.log(`${singerArray[i].name} is from ${singerArray[i].nationality} and their most popular song is ${singerArray[i].popularSong}.`)
        }