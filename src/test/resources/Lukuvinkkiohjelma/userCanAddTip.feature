Feature: A user can add a new tip   

    Scenario: a booktip can be added
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And program is terminated
        Then system will respond with "Uusi kirjavinkki lisatty onnistuneesti!" 
        And output will contain text "Opus"

    Scenario: a podcasttip can be added
        Given command lisaa podcast is selected
        When a new podcasttip with title "Hauska podcast" and url "www.podcast.org" is added
        And program is terminated
        Then system will respond with "Uusi podcastvinkki lisatty onnistuneesti!" 

    Scenario: a blogtip can be added
        Given command lisaa blogi is selected
        When a new blogtip with title "Kivablogi", author "Bloggaaja" and url "www.blogi.org" is added
        And program is terminated
        Then system will respond with "Uusi blogivinkki lisatty onnistuneesti!" 

    Scenario: a booktip without author can not be added
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "" and ISBN "1234" is added
        And program is terminated    
        And output will contain text "Toiminto"

    Scenario: a podcasttip without title can not be added
        Given command lisaa podcast is selected
        When a new podcasttip with title "" and url "www.podcast.org" is added
        And program is terminated
        And output will contain text "Toiminto"

    Scenario: a blogtip without url can not be added
        Given command lisaa blogi is selected
        When a new blogtip with title "Kivablogi", author "Bloggaaja" and url "" is added
        And program is terminated
        And output will contain text "Toiminto" 


    Scenario: adding a booktip can be cancelled at the title
        Given command lisaa kirja is selected
        When cancel command "" is entered
        And program is terminated
        Then output will contain text "Toiminto"

    Scenario: adding a booktip can be cancelled at the author
        Given command lisaa kirja is selected
        When input "Opus" is entered
        And cancel command "" is entered
        And program is terminated
        Then output will contain text "Toiminto"

    Scenario: adding a booktip can be cancelled at the ISBN
        Given command lisaa kirja is selected
        When input "Opus" is entered
        And input "Kirjoittaja" is entered 
        And cancel command "" is entered
        And program is terminated
        Then output will contain text "Toiminto"


    Scenario: adding a podcasttip can be cancelled at the title
        Given command lisaa podcast is selected
        When cancel command "" is entered
        And program is terminated
        Then output will contain text "Toiminto"

    Scenario: adding a podcasttip can be cancelled at the url
        Given command lisaa podcast is selected
        When input "Podcast" is entered
        And cancel command "" is entered
        And program is terminated
        Then output will contain text "Toiminto"


    Scenario: adding a blogtip can be cancelled at the title
        Given command lisaa blogi is selected
        When cancel command "" is entered
        And program is terminated
        Then output will contain text "Toiminto"
    
    Scenario: adding a blogtip can be cancelled at the author
        Given command lisaa blogi is selected
        When input "Testiblogi" is entered
        And cancel command "" is entered
        And program is terminated
        Then output will contain text "Toiminto"

    Scenario: adding a blogtip can be cancelled at the url
        Given command lisaa blogi is selected
        When input "Testiblogi" is entered
        When input "Blogin kirjoittjaja" is entered
        And cancel command "" is entered
        And program is terminated
        Then output will contain text "Toiminto"