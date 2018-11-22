package hf_Test;
/**
 * Egy exepton class amit azért kreáltam mert szerettem volna egy sajátot irni
 * Throwable leszármazotja, egy egyszerű irélkező hibaüzenetet dobb (ezt meg tudtam volna tenni egy másik már létező exeptionnel de akartam egy sajátot)
 * 
 * @author Mihály András
 * @see Throwable
 */
public class NullAnimExepton extends Throwable {

	
	private static final long serialVersionUID = 1L;

	public NullAnimExepton() {
		super("You Tried to start without loading a file SHAME ON YOU");
	}

}
