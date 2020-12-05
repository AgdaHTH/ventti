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

