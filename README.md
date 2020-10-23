# clean-code-examination

Ännu en rolig uppgift!

Jag började med att bestämma vilka interface jag skulle ha, nämligen UserInterface, DAO, och GameLogic. På detta 
vis blir det lätt att byta dessa komponenter.

Sedan funderade jag på ifall jag skulle ha en eller flera DAO:er, en för varje spel dvs. Jag bestämde mig för att prova
att enbart ha en DAO och göra den anpassningsbar.

I min första version var jag tvungen att ändra i koden på två ställen för att lägga till nya spel, i main-metoden 
och i DAO-implementationen. Jag funderade på ett lösningsförslag där jag enbart behövde ändra på ett ställe.
Jag lyssnade på ditt råd och gjorde så att speltiteln används som grund för generering och val av resultattabell.

Säg bara till om du vill att jag förklarar någonting mer.