Feature: A user can modify an existing a tip

    Scenario: a booktip can be modified
        Given command lisaa blogi is selected
        When a new booktip with title "Opus", author "Kirjailija" and ISBN "1234" is added
        And command muokkaa vinkki is selected
        And tip number "0" is selected
        And input "0" is entered
        And input "Parempi kirjailija" is entered
        And cancel command "" is entered
        And program is terminated
        Then output will contain text "muokattu"

    Scenario: a podcasttip can be modified
        Given command lisaa podcast is selected
        When a new podcasttip with title "Podcasti" and url "www.podcast.com" is added
        And command muokkaa vinkki is selected
        And tip number "0" is selected
        And input "1" is entered
        And input "www.podcast.com/testicasti" is entered
        And cancel command "" is entered
        And program is terminated
        Then output will contain text "muokattu"
        
    Scenario: a blogtip can be modified
        Given command lisaa blogi is selected
        When a new blogtip with title "Mahtava blogi", author "Blogimaisteri" and url "www.blogspot.com" is added
        And command muokkaa vinkki is selected
        And tip number "4" is selected
        And input "2" is entered
        And input "www.blogspot.com/howToMakeBlogs" is entered
        And cancel command "" is entered
        And program is terminated

        Then output will contain text "muokattu"