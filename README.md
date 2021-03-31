# Tag Cloud
This project scrapes the HTML of any website given and generates a word cloud based on its contents. The sizing of each word is determined by the tag(s) that the word is enclosed by, as well as the number of times it appears on the website. The word cloud is optimized to prevent any overlapping of words.

## Directory
```
.
├── GuitarHero                        # Main project files
│   ├── src                           # Source
│   │   ├── Data.java                 # Creates a map containing a word cloud based on HTML from Scraper.java
│   │   ├── Display.java              # Calculates optimal positions and creates a word cloud
│   │   ├── Main.java                 # Runner
│   │   ├── Scraper.java              # Takes in a URL as input and scrapes its HTML
│   │   └── Word.java                 # Word object, represents each word displayed
│   └── ...                           # etc.
├── images                            # Images for demonstration
└── README.md                         # ReadMe
```
## Console
This is what console input looks like.
![Console image](images/console.png?raw=true "Image")

# Demonstrations
## CFISD
This is the word cloud generated when the input is the [CFISD Website](https://www.cfisd.net/en).
![CFISD Word Cloud](images/cfisd.png?raw=true "Image")

## UH
This is the word cloud generated when the input is the [University of Houston Website](https://uh.edu/).
![UH Word Cloud](images/cfisd.png?raw=true "Image")


