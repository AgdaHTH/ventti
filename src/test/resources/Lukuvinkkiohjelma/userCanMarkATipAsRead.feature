Feature: A user can mark a tip as read

    Scenario: a booktip can be marked as read
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And command merkitse luetuksi is selected
        And tip number "0" is selected
        And program is terminated
        Then output will contain text "Vinkki merkittiin luetuksi/kuunnelluksi!"
    
    Scenario: a podcasttip can be marked as listened
        Given command lisaa podcast is selected
        When a new podcasttip with title "Podcast" and url "www.podcast.com/testPodcast" is added
        And command merkitse luetuksi is selected
        And tip number "0" is selected
        And program is terminated
        Then output will contain text "Vinkki merkittiin luetuksi/kuunnelluksi!"

    Scenario: a blogtip can be marked as read
        Given command lisaa blogi is selected
        When a new blogtip with title "Blogi", author "Bloggaaja" and url "www.blogspot.com/testiBlogi" is added
        And command merkitse luetuksi is selected
        And tip number "0" is selected
        And program is terminated
        Then output will contain text "Vinkki merkittiin luetuksi/kuunnelluksi!"

    Scenario: a nonexisting tip can't be marked as read
        Given command lisaa kirja is selected
        When a new booktip with title "Opus", author "Kirjoittaja" and ISBN "1234" is added
        And command merkitse luetuksi is selected
        And tip number "-1" is selected
        And program is terminated
        Then output will contain text "Ei"