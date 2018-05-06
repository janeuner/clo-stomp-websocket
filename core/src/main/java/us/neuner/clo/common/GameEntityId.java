package us.neuner.clo.common;

import com.fasterxml.jackson.annotation.JsonValue;

/*
 * @author Mag Faris <mfaris627@gmail.com>
 * @author Jarod Neuner <jarod@neuner.us>
 * Enumerates the CLO game entities, including locations, weapons, and suspects.
 */
public enum GameEntityId {
	InvalidValue("Invalid Value"),
	MissScarlet("Miss Scarlet"),
	MrsWhite("Mrs White"),
	MrGreen("Mr Green"),
	MrsPeacock("Mrs Peacock"),
	ProfessorPlum("Professor Plum"),
	ColonelMustard("Colonel Mustard"), 

	LeadPipe("Lead Pipe"),
	CandleStick("Candle Stick"),
	Rope("Rope"),
	Knife("Knife"),
	Wrench("Wrench"),
	Revolver ("Revolver"),

	BilliardRoom("Billiard Room"),
	Kitchen("Kitchen"),
	BallRoom("Ball Room"),
	Conservatory("Conservatory"),
	Library("Library"),
	Study("Study"),
	Hall("Hall"), 
	Lounge ("Lounge"),
	DiningRoom ("Dining Room"),

	H01("Hallway 01"),
	H02("Hallway 02"), 
	H03("Hallway 03"), 
	H04("Hallway 04"),
	H05("Hallway 05"), 
	H06("Hallway 06"), 
	H07("Hallway 07"),
	H08("Hallway 08"), 
	H09("Hallway 09"), 
	H10("Hallway 10"),
	H11("Hallway 11"),
	H12("Hallway 12"),
	
	MissScarletStart("Start - Miss Scarlet"),
    MrsWhiteStart("Start - Mrs White"), 
	MrGreenStart("Start - Mr Green"),
	MrsPeacockStart("Start - Mrs Peacock"), 
	ProfessorPlumStart ("Start Professor Plum"),
	ColonelMustardStart("Start - Colonel Mustard");
	
	private final String text;
	
	GameEntityId(final String text) {
		this.text = text;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return text;
	}
}
