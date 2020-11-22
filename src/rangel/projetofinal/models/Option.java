package rangel.projetofinal.models;



/**
 * @author Rangel Cardoso Dias;
 * @matricula UC18200693;
 * @implNote Enum that represents the options o supplies menu;
 */
public enum Option {

	
	REMOVE,
	ADD,
	LIST,
	CHAT,
	SERVER_INPUT,
	NONE,
	EXIT;
	
	
	
	
	Option getOptionBy(Integer rawValue) {
		switch (rawValue) {
		case 0: {
			return Option.EXIT;
		}
		case 1: {
			return Option.ADD;
		}
		case 2: {
			return Option.REMOVE;
		}
		case 3: {
			return Option.LIST;
		}
		case 4: {
			return Option.CHAT;
		}
		default:
			return Option.NONE;
		}
	}
	
}
