public class Use implements Move {

    private Place place;
    private Character character;
    private String artifact;

    /**
     * Creates a Use object for a character that is trying to use an artifact in their inventory
     *
     * @param place     The place in where the character is currently in
     * @param character The character who is trying to use an artifact
     * @param artifact  The artifact that is trying to be used
     * @return Use object
     */
    public Use(Place place, Character character, String artifact) {
        this.place = place;
        this.character = character;
        this.artifact = artifact;
    }

    /**
     * Executes Use object to check if the specified artifact can do anything in the current place
     */
    @Override
    public void execute() {
        //Check to see if character is a player to either display the event or suppress it
        if (this.character instanceof Player) {

            //Check to see if player even has artifact specified
            if (this.character.checkForArtifact(this.artifact)) {

                //Notify player of the event
                System.out.println("================================================");
                System.out.println("PLAYER: " + character.name() + " used " + this.artifact);
                System.out.println("================================================");

                //Try using specified artifact in current place and check if anything happened
                if (this.place.useKey(this.character.retrieveArtifactFromInventory(this.artifact))) {
                    System.out.println("A door was unlocked\n");
                } else {
                    System.out.println("Nothing happened\n");
                }
            } else {
                System.out.println("Can't use it if you don't have it");
            }
        } else {

            //Do the same for a NPC character, but just don't display anything
            if (this.character.checkForArtifact(this.artifact)) {
                this.place.useKey(this.character.retrieveArtifactFromInventory(this.artifact));
            } else {
                System.out.println("Error: NPC is trying to use artifact that it does not have");
            }
        }
    }
}