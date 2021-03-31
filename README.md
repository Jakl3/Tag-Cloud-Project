# Guitar Hero
This project scrapes the HTML of any website given and generates a word cloud based on its contents. The sizing of each word is determined by the tag(s) that the word is enclosed by, as well as the number of times it appears on the website. The word cloud is optimized to prevent any overlapping of words.

## Directory
```
.
├── GuitarHero                        # Main project files
│   ├── src                           # Source
│   │   ├── AutoGuitarHero.java       # Automatic player
│   │   ├── GuitarHero.java           # Manual player
│   │   ├── GuitarHeroLite.java       # Light demonstration program
│   │   ├── GuitarHeroVisual.java     # Main program - culminates all other files
│   │   ├── GuitarString.java         # Implements Karplus-Strong algorithm for synthesis
│   │   ├── RingBuffer.java           # Implements Karplus-Strong algorithm for synthesis
│   │   ├── RingBufferTester.java     # Testing file
│   │   ├── StdAudio.java             # Standard Audio from Princeton University
│   │   ├── StdDraw.java              # Standard Draw from Princeton University
│   │   ├── Stopwatch.java            # Stopwatch for timing
│   │   └── Test.java                 # Testing file
│   ├── .txt                          # All files ending in .txt are song files
│   └── ...                           # etc.
├── images                            # Images for demonstration
└── README.md                         # ReadMe
```

# Demonstrations
## Image
This is an image of the user interface when the program is ran.
![Alt text](images/Demonstration.png?raw=true "Image")

## Video
This is a video demonstration of the program with sound, along with code explanations. (click below)
[![Video Demonstration](images/Video.png)](https://www.youtube.com/watch?v=WWzUKfgd2AU "Open in YouTube")


