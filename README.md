# Partial Activity

## Objective

The objective of this activity is that studend study concepts view in class and apply them into a simple project that gives practices and security to introduce them into own projects under its own architectures and methodologies.

## Instructions

From the Pokedex App Project that we have been building through the partial, and which code up to date it will be uploaded into this same repository it is ask from you the following.

Develop a detail view for each one of the pokemon using the reference API for a specific pokemon. Ex. [https://pokeapi.co/api/v2/pokemon/1/](https://pokeapi.co/api/v2/pokemon/1/)

A resume for the call of the url will return the following:
```json
{
  "abilities": [],
  "base_experience": 64,
  "forms": [],
  "game_indices": [],
  "height": 7,
  "held_items": [],
  "id": 1,
  "is_default": true,
  "location_area_encounters": "https://pokeapi.co/api/v2/pokemon/1/encounters",
  "moves": [],
  "name": "bulbasaur",
  "order": 1,
  "species": {
    "name": "bulbasaur",
    "url": "https://pokeapi.co/api/v2/pokemon-species/1/"
  },
  "sprites": {
    "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png",
    "back_female": null,
    "back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/1.png",
    "back_shiny_female": null,
    "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
    "front_female": null,
    "front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
    "front_shiny_female": null
  },
  "stats": [],
  "types": [],
  "weight": 69
}
```
## Evaluation

Developing format will be open and at least must integrate the following criteria:

* 1 View Only to Develop
* Design based on Material Design for Android (colors, dimensions, typography, components, etc.)
* Development should contain at least the following API data: 
	* Pokemon Number
	* Pokemon Name
	* At least 1 Pokemon Sprite (image)
	* Pokemon type(s)
	* Pokemon Base Statistics
	* All possible Pokemon movements that it can learn
* View should be opened from general Pokemon List and it will be using an INTENT that passes url of the pokemon to search
* Optimization detail it should work for landscape and portrait, it is not allowed to block view to just work 1 orientation

### Each activity is individual, it will not be accepted projects that come from copy of the internet or from other students. Designs must be uniques take time to plan it, better to start creating designs instead to do a very cool one that is not yours. Cases from this type will end up in FIA.

## Delivery Format

Repository should be delivered by github classroom platform.

Repository must contain the following:

* Source Code of the Project
* .APK File SIGNED to install inside the repository, it can be at the root or inside the default folder that Android Studio generates
* File in TXT format that includes Name, Student Id, and answer to the following questions: What made it easier for you to implement the view? What made it difficult for you to implement the view?

## Rubric

The next rubric will be used to grade partial activity
| Concept                                                                   | Percentage    |
| :------------------------------------------------------------------------:|:-------------:|
| 1 Detail View Only                                                        |  5%           |
| Design based on Material Design                                           | 30%           |
| Integrate the 6 fields asked of the API                                   | 25%           |
| Open of the view it must be from an INTENT with the asked format          |  5%           |
| Optimization view for landscape and portrait                              | 25%           |
| Repository contains source code and .apk signed                           |  5%           |
| Reflection Document in TXT                                                |  5%           |

### In case that installing the project and the .apk doesn't run or execute the source it also fails a direct grade of 0 will be assigned. Verify all your deliveries work correctly and avoid "en el mío si corre no se por que en el suyo no".

## Delivery Date

### 23/03/2020 11:59 a través de GitHub Classroom