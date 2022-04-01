# Lucas Hu's Personal Project
## Anime/Manga/Manhwa Cataloging Desktop Application

![Oreki Reading](https://bit.ly/3Gz5JVQ)

- *Knock, knock*
- Who's there?
- *Lucas*
- Lucas who?
- *That's me, Lucas Hu!*

Welcome to my Personal Project for CPSC 210! Sorry for including
the bad joke, but it's kind of my trademark.

### Project Proposal

*Here, I will give a general description of what I seek to
design for my personal project.*

Essentially, I want to design an application that can sort, manage,
and allow the user to keep track of anime/manga/manhwa they've
seen. Ideally, it would have similar functionality to online 
catalogers such as MyAnimeList or Anilist, except that my cataloger
would be a desktop application where the user manually enters
anime/manga/manhwa entries. It should kind of be like a spoof
of the recipe manager application project idea. My target audience 
for this cataloging desktop application would be other people 
who like to watch anime and read manga/manhwa like me.
Many of my friends watch anime and read manga/manhwa, so I thought
it would be a cool idea to make a cataloger and share it with them
if they also wanted to track their reading/watching. 

This project is of interest to me because I love anime/manga/manhwa! 
However, the main reason I want to design my own cataloger with manual 
entries despite also having a MyAnimeList (MAL) is because these online 
catalogers do not include 99% of all **manhwa** (meaning I can't add 
them to my catalogue). Manga and manhwa are both types of graphic 
novels, the difference is that manga is Japanese and manhwa 
is Korean. Manhwa is primarily published online in the form of 
webcomics or webtoons, but MAL has a rule to only include works that
have been physically published. I, myself, primarily only read manhwa
nowadays which is the exact reason why I want to design my own
cataloger and why it interests me so much. Sorry for the small rant!

### User Stories

*Here, I will list some user stories that I would like to add to my 
application. Note that when I say manhwa, I mean any
anime/manga/manhwa.*

Phase 1 User Stories:
- As a user, I want to be able to add a manhwa to my catalogue. ✅
- As a user, I want to be able to view a list of all the manhwa in my catalogue. ✅
- As a user, I want to be able to select a manhwa and view its details (title, description, etc.). ✅
- As a user, I want to be able to select a manhwa and rate it based on a 10-point scale. ✅

Phase 2 User Stories:
- As a user, when I select to quit the application, I want to be given the option to
either save my catalogue to file or simply quit the application without saving. ✅
- As a user, when I start this application, I want to be given the option to either
load my catalogue from file or create a new catalogue. ✅

Phase 3:
- Add GUI ✅

Phase 4: 

    Phase 4: Task 2 - Sample of events logged

        Wed Mar 30 22:45:44 PDT 2022
        Loaded list from ./data/catalogue.json
        Wed Mar 30 22:45:44 PDT 2022 
        Added manhwa: Overgeared
        Wed Mar 30 22:45:44 PDT 2022
        Added manhwa: Sweet home
        Wed Mar 30 22:45:44 PDT 2022
        Added manhwa: The beginning after the end
        Wed Mar 30 22:45:45 PDT 2022
        Retrieved list
        Wed Mar 30 22:45:51 PDT 2022
        Retrieved details of the manhwa Overgeared
        Wed Mar 30 22:45:51 PDT 2022
        Retrieved details of the manhwa Overgeared
        Wed Mar 30 22:45:58 PDT 2022
        Rated Overgeared a 10
        Wed Mar 30 22:46:15 PDT 2022
        Added manhwa: Pigpen
        Wed Mar 30 22:46:18 PDT 2022
        Saved list to ./data/catalogue.json

Phase 4: Task 3 - Changes I would have liked to make
- I would have liked to make an exceptions package with my
own specific exception classes instead of using Java's default exceptions.
- I would have liked to change Manhwa to extend an abstract class such as
"FormOfMedia" because that way it would make it much easier for me to create other
classes in the future such as "Manga" or "Anime" which would also extend "FormOfMedia".
This would allow me to add manga and anime (other forms of media) to my catalogue.
- I would have liked to change ManhwaCatalogue to extend an abstract class such as
"Catalogue". This goes by the same logic as the last change I listed because 
in the future, I may want to have separate lists of varying forms of media.
- I would have liked to allow my GUI to make multiple lists instead of just one
  (although this would require adding more features).


