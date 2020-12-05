Feature: A user can delete an existing a tip

    Scenario: a booktip can be deleted
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And command poista vinkki is selected
        And tip number "0" is selected
        And program is terminated
        And output will contain text "Vinkki poistettu onnistuneesti!"

    Scenario: a podcasttip can be deleted
        Given command lisaa podcast is selected
        When a new podcasttip with title "Hauska podcast" and url "www.podcast.org" is added
        And command poista vinkki is selected
        And tip number "0" is selected
        And program is terminated
        And output will contain text "Vinkki poistettu onnistuneesti!"

    Scenario: a blogtip can be added
        Given command lisaa blogi is selected
        When a new blogtip with title "Kivablogi", author "Bloggaaja" and url "www.blogi.org" is added
        And command poista vinkki is selected
        And tip number "0" is selected
        And program is terminated
        And output will contain text "Vinkki poistettu onnistuneesti!"